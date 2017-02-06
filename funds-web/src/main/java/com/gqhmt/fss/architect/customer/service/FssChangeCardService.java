package com.gqhmt.fss.architect.customer.service;/**
 * Created by yuyonf on 15/11/30.
 */

import com.gqhmt.extServInter.dto.account.UpdateBankCardAnotherDto;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.customer.entity.FssChangeCardEntity;
import com.gqhmt.fss.architect.customer.mapper.read.FssChangeCardReadMapper;
import com.gqhmt.fss.architect.customer.mapper.write.FssChangeCardWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.account.service.NoticeService;
import com.gqhmt.funds.architect.mapping.service.FuiouBankCodeService;
import com.gqhmt.pay.fuiou.util.CoreConstants;
import com.gqhmt.pay.fuiou.util.HttpClientUtil;
import com.gqhmt.core.util.JsonUtil;
import com.gqhmt.business.architect.invest.service.InvestmentService;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.extServInter.dto.loan.ChangeCardResponse;
import com.gqhmt.util.LogUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   15/11/30 17:25
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 15/11/30  于泳      1.0     1.0 Version
 */
@Service
public class FssChangeCardService {

    @Resource
    private FssChangeCardReadMapper changeCardReadMapper;
    @Resource
    private FssChangeCardWriteMapper changeCardWriteMapper;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private BankCardInfoService bankCardinfoService;

    @Resource
    private FundAccountService fundAccountService;
    
    @Resource
    private FundOrderService fundOrderService;

    @Resource
    private FuiouBankCodeService fuiouBankCodeService;

    @Resource
    private NoticeService noticeService;
    
    @Resource
    private InvestmentService investmentService;
    
    @Resource
    private FssBackplateService fssBackplateService;

    public void insert(FssChangeCardEntity changeCardEntity) throws FssException{
    	changeCardWriteMapper.insertSelective(changeCardEntity);
    }
    
    public void update(FssChangeCardEntity changeCardEntity) throws FssException{
    	changeCardWriteMapper.updateByPrimaryKeySelective(changeCardEntity);
    }

    public FssChangeCardEntity get(Long id)throws FssException{
        return changeCardReadMapper.selectByPrimaryKey(id);
    }

//    public Page query(FundsAccountBean.ChangeCardBean changeCardEntity){
//        return changeCardReadMapper.query(changeCardEntity);
//    }

    /**
     * 录入客户修改银行卡信息,前台传入,需要根据id获取客户实体bean
     * @param bankNo
     * @param bankId
     * @param bankAddr
     * @param bankCity
     * @param filePath
     * @throws Exception
     */
    public void addChangeCard(String custNo, String bankNo, String bankId, String bankAddr, String bankCity, String filePath,String seqNo,String tradeType,String mchn) throws FssException {
    	CustomerInfoEntity customerInfo  = customerInfoService.getCustomerById(Long.valueOf(custNo));
        if(customerInfo == null){
            throw new FssException("90002007");
        }
        if(filePath ==null || "".equals(filePath)){
            throw new FssException("90002032");
        }

        this.addChangeCard(customerInfo,bankNo,bankId,bankAddr,bankCity,filePath,1,seqNo,mchn,tradeType);
    }

    /**
     * 录入客户修改银行卡信息,前台传入,需要根据id获取客户实体bean
     * @param certNo
     * @param bankNo
     * @param bankId
     * @param bankAddr
     * @param bankCity
     * @param filePath
     * @throws Exception
     */
    public void addChangeCard(String certNo, String bankNo, String bankId, String bankAddr, String bankCity, String filePath,String seqNo,int type) throws Exception {
        CustomerInfoEntity customerInfo  = customerInfoService.queryCustomerInfoByCertNo(certNo);
        if(customerInfo == null){
            throw new Exception("90002007");
        }
        if(filePath ==null || "".equals(filePath)){
            throw new Exception("90002032");
        }
        this.addChangeCard(customerInfo,bankNo,bankId,bankAddr,bankCity,filePath,type,seqNo,null,null);
    }


    /**
     * 录入修改银行卡记录信息
     * @param custom
     * @param bankNo
     * @param bankId
     * @param bankAddr
     * @param bankCity
     * @param filePath
     * @throws Exception
     */
    public void addChangeCard(CustomerInfoEntity custom, String bankNo, String bankId, String bankAddr, String bankCity, String filePath,int type,String seqNo,String mchn,String tradeType) throws FssException {
//        //添加银行卡，变更后银行卡信息
//        BankCardInfoEntity bankCardInfo= bankCardinfoService.creatBankInfoEntity(custom.getId(),custom.getCertNo(),custom.getMobilePhone(),custom.getCustomerName(),bankNo,bankId,bankCity,filePath,tradeType);
//        bankCardinfoService.insert(bankCardInfo);

        List<Map<String, String>> noticeList= new ArrayList<Map<String, String>>();
		Map<String, String> noticeMap = new HashMap<String, String>();
		noticeMap.put("sysCode",CoreConstants.SYS_CODE);//商户系统编码，在平台系统查看
		noticeList.add(noticeMap);
        Integer bankCardId = custom.getBankId();
        //变更前银行卡信息
        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(bankCardId);
        if(bankCardinfoEntity==null) throw new FssException("90002036");
        if(bankCardinfoEntity.getChangeState() == 1){//变更中,请勿重复提交变更
        	throw new FssException("90002037");
        }
        if(bankNo.equals(bankCardinfoEntity.getBankNo())){
            throw new FssException("90002034");
        }
        FssChangeCardEntity entity = getChangeCardInstance(custom,bankCardinfoEntity,bankNo,bankId,bankAddr,bankCity,filePath ,type,seqNo,mchn,tradeType);
//        FssChangeCardEntity entity = getChangeCardInstance(custom,bankCardinfoEntity,bankCardInfo,type,seqNo,mchn,tradeType);
        try {
			this.insert(entity);
		} catch (Exception e) {
			throw new FssException("90002035");
		}

//      bankCardinfoEntity.setChangeState(1);
        bankCardinfoEntity.setMemo("变更申请已提交,原银行卡不能做提现操作");
        bankCardinfoEntity.setModifyTime(new Date());
        bankCardinfoService.updateBankCardInfo(bankCardinfoEntity);

        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custom.getId(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        fundAccountEntity.setIshangeBankCard(1);
        fundAccountService.update(fundAccountEntity);

        if (entity.getType() == 1 || entity.getType() == 11029003) {
            //发送站内通知短信
            noticeService.packSendNotice(noticeList,CoreConstants.FUND_UPDATE_BANKCARD_SUBMIT_TEMPCODE,CoreConstants.SMS_NOTICE,NoticeService.NoticeType.FUND_UPDATE_BANKCARD_SUBMIT,entity.getCreateUserId().intValue(), entity.getCustId().intValue(),tmCardNo(entity.getCardNo()));
            HttpClientUtil.sendMsgOrNotice(noticeList, CoreConstants.SMS_NOTICE);
            this.sendMms(entity.getMobile(), 1);
        }
        
    }

    public FssChangeCardEntity getChangeCardInstance(CustomerInfoEntity cus,BankCardInfoEntity bankCardinfoEntity, String bankNo, String bankId, String bankAddr, String bankCity, String filePath, int type, String seqNo,String mchn,String tradeType){
        FssChangeCardEntity entity = new FssChangeCardEntity();
        entity.setCustId(cus.getId().longValue());
        entity.setCardNo(bankNo);
        entity.setBankType(bankId);
        entity.setBankAdd(bankAddr);
        if(bankCity.length() == 6){
            try {
                bankCity = Application.getInstance().getFourCode(bankCity);
            } catch (FssException e) {
                LogUtil.error(getClass(),e);
            }
        }
        entity.setBankCity(bankCity);
        entity.setFilePath(filePath);
        entity.setbBankInfoId(cus.getBankId().longValue());
        entity.setCertNo(cus.getCertNo());
        entity.setCustName(cus.getCustomerName());
        entity.setCreateUserId(-1l);
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setState(1);
        entity.setTradeState(1);
        entity.setCertType(cus.getCertType());
        entity.setMobile(cus.getMobilePhone());
        entity.setType(type);
        entity.setBankType(bankId);
        entity.setBankName(bankCardinfoEntity.getBankSortName());
        entity.setCardNo(bankNo);
        entity.setMchn(mchn);
        if(seqNo != null){
            entity.setSeqNo(seqNo);
        }
        entity.setTradeType(tradeType);
        return  entity;
    }
//    public FssChangeCardEntity getChangeCardInstance(CustomerInfoEntity cus,BankCardInfoEntity bbankCardinfoEntity, BankCardInfoEntity abankCardinfoEntity, int type, String seqNo,String mchn,String tradeType){
//        FssChangeCardEntity entity = new FssChangeCardEntity();
//        entity.setCustId(cus.getId().longValue());
//        entity.setCardNo(abankCardinfoEntity.getBankNo());
//        entity.setBankType(abankCardinfoEntity.getParentBankId());
//        entity.setBankCity(abankCardinfoEntity.getCityId());
//        entity.setFilePath(abankCardinfoEntity.getFilePath());
//        entity.setbBankInfoId(cus.getBankId().longValue());
//        entity.setaBankInfoId(abankCardinfoEntity.getId().longValue());
//        entity.setCertNo(cus.getCertNo());
//        entity.setCustName(cus.getCustomerName());
//        entity.setCreateUserId(-1l);
//        entity.setCreateTime(new Date());
//        entity.setModifyTime(new Date());
//        entity.setState(1);
//        entity.setTradeState(1);
//        entity.setCertType(cus.getCertType());
//        entity.setMobile(cus.getMobilePhone());
//        entity.setType(type);
//        entity.setBankName(bbankCardinfoEntity.getBankSortName());
//        entity.setMchn(mchn);
//        if(seqNo != null){
//            entity.setSeqNo(seqNo);
//        }
//        entity.setTradeType(tradeType);
//        return  entity;
//    }


    //自动审核换卡需求
    public void autoPassChangeCard()throws FssException{
        List<FssChangeCardEntity> list = queryByTradeState(1);
        if(list != null && list.size() > 0) {
	        for(FssChangeCardEntity changeCardEntity : list){
	            changeCardEntity.setPassTime(new Date());
	            changeCardEntity.setPassUserId(-1l);
	            changeCardEntity.setTradeState(2);
	            update(changeCardEntity);
	        }
        }
    }

    public  List<FssChangeCardEntity> queryByTradeState(int tradeState)throws FssException{
        return changeCardReadMapper.queryByTradeState(tradeState);
    }

    //图片上传到富友ftp
    public void uploadImageFtp(FssChangeCardEntity changeCardEntity) throws Exception {
        update(changeCardEntity);
    }

    //图片上传到富友ftp
    public void uploadData(FssChangeCardEntity changeCardEntity) throws Exception {
        update(changeCardEntity);
        //TODO:为什么要变更失败
        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getbBankInfoId().intValue());
        bankCardinfoEntity.setChangeState(2);

        bankCardinfoService.updateBankCard(bankCardinfoEntity,"0");

    }

    //同步成功数据
    public void sycnChangeCardInfoBySucess()throws FssException{
        List<FssChangeCardEntity> list = queryByTradeState(5);
        if(list != null && list.size() > 0) {
	        for(FssChangeCardEntity changeCardEntity : list){
	            //同步变更信息到银行卡信息表中
                //变更后银行卡信息更新
//	            BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(Integer.parseInt(changeCardEntity.getaBankInfoId().toString()));
	            BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(Integer.parseInt(changeCardEntity.getbBankInfoId().toString()));
	            if(bankCardinfoEntity == null){
	                bankCardinfoEntity = new BankCardInfoEntity();
	            }
	
	            bankCardinfoEntity.setChangeState(0);
//	            bankCardinfoEntity.setStatus("90004042");//90004042 银行卡已激活
	            bankCardinfoEntity.setCustId(changeCardEntity.getCustId().intValue());
                bankCardinfoEntity.setCertNo(changeCardEntity.getCertNo());
                bankCardinfoEntity.setBankNo(changeCardEntity.getCardNo());
                bankCardinfoEntity.setCertName(changeCardEntity.getCustName());
                bankCardinfoEntity.setCityId(changeCardEntity.getBankCity());
	            bankCardinfoEntity.setMobile(changeCardEntity.getMobile());
	            bankCardinfoEntity.setBankLongName(changeCardEntity.getBankAdd());
	            bankCardinfoEntity.setParentBankId(changeCardEntity.getBankType());
	            bankCardinfoEntity.setBankSortName(fuiouBankCodeService.queryFuiouBankValueByCode(changeCardEntity.getBankType()));
	            bankCardinfoEntity.setMemo("变更成功");
	            bankCardinfoEntity.setModifyTime(new Date());
	            bankCardinfoService.update(bankCardinfoEntity);

//                //变更前银行卡信息更新
//                BankCardInfoEntity bbankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(Integer.parseInt(changeCardEntity.getbBankInfoId().toString()));
//                if(bbankCardinfoEntity != null){
//                    bbankCardinfoEntity.setChangeState(3);//3 新增
//                    bbankCardinfoEntity.setStatus("90004041");//90004041 银行卡未激活
//                    bbankCardinfoEntity.setMemo("变更成功");
//                    bbankCardinfoEntity.setModifyTime(new Date());
//                    bankCardinfoService.update(bbankCardinfoEntity);
//                }


	            changeCardEntity.setState(2);
	            changeCardEntity.setModifyTime(new Date());
	            changeCardEntity.setEffectTime(new Date());
	            changeCardEntity.setTradeState(99);
	            FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(changeCardEntity.getCustId(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
	            fundAccountEntity.setIshangeBankCard(0);
	            fundAccountService.update(fundAccountEntity);
	            this.noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_SUCESS, changeCardEntity.getCreateUserId().intValue(), changeCardEntity.getCustId().intValue(),tmCardNo(changeCardEntity.getCardNo()));
	
	            CustomerInfoEntity customerInfoEntity = customerInfoService.getCustomerById(changeCardEntity.getCustId());
//                customerInfoEntity.setBankId(bankCardinfoEntity.getId());
                customerInfoEntity.setModifyTime(new Date());
	              customerInfoEntity.setHasThirdAgreement(0);
	            try {
	                customerInfoService.update(customerInfoEntity);
	            } catch (Exception e) {
	                LogUtil.error(this.getClass(), e);
	            }
	            update(changeCardEntity);
	        }
        }
    }

    public void fuiouCallback(String orderNo,String bankNo,String resCode,String resMess) throws Exception {
        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(orderNo);
        if(fundOrderEntity == null){
            throw new Exception("未找到对应订单");
        }
        long id = fundOrderEntity.getOrderFrormId();
        FssChangeCardEntity changeCardEntity = this.get(id);

        if(changeCardEntity == null){
            throw new Exception("未找到对应换卡记录");
        }
        if( bankNo.equals(changeCardEntity.getCardNo())){
            if("1".equals(resCode)){
                changeCardEntity.setTradeState(5);
            }else if("2".equals(resCode)){
                changeCardEntity.setTradeState(6);
                changeCardEntity.setRespMsg(resMess);
                
            }
        }
        update(changeCardEntity);
    }

    //同步失败记录到换卡记录中
    public void sycnChangeCardInfoByFaile()throws FssException{
        List<FssChangeCardEntity> list = queryByTradeState(6);
        if(list != null && list.size() > 0) {
	        for(FssChangeCardEntity changeCardEntity : list){
	            //同步变更信息到银行卡信息表中
                //变更后银行卡信息修改
                BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getbBankInfoId().intValue());
//	            BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getaBankInfoId().intValue());
	            if(bankCardinfoEntity !=null){
                    bankCardinfoEntity.setModifyTime(new Date());
	                bankCardinfoEntity.setChangeState(2);
//	                bankCardinfoEntity.setStatus("90004044");//90004044 激活失败，请修改激活资料
	                bankCardinfoEntity.setMemo("资料审核不通过，新银行卡变更失败，请重新变更；原银行卡充值提现不受影响。");
	                bankCardinfoService.update(bankCardinfoEntity);
	            }
//	            //变更前银行卡信息修改
//	            BankCardInfoEntity bbankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getbBankInfoId().intValue());
//	            if(bbankCardinfoEntity !=null){
//                    bbankCardinfoEntity.setStatus("90004042");//90004042 银行卡已激活
//                    bbankCardinfoEntity.setModifyTime(new Date());
//	                bankCardinfoService.update(bbankCardinfoEntity);
//	            }
	            changeCardEntity.setState(3);
	            changeCardEntity.setTradeState(99);
	            FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(changeCardEntity.getCustId(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
	            fundAccountEntity.setIshangeBankCard(0);
	            fundAccountEntity.setModifyTime(new Date());
	            fundAccountService.update(fundAccountEntity);

                noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_FAIL, changeCardEntity.getCreateUserId().intValue(), changeCardEntity.getCustId().intValue(),changeCardEntity.getRespMsg()!= null?changeCardEntity.getRespMsg():"未知错误",tmCardNo (bankCardinfoEntity.getBankNo()));

//                noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_FAIL, changeCardEntity.getCreateUserId().intValue(), changeCardEntity.getCustId().intValue(),changeCardEntity.getRespMsg()!= null?changeCardEntity.getRespMsg():"未知错误",tmCardNo (bbankCardinfoEntity.getBankNo()));
	            update(changeCardEntity);
	        }
        }
    }

/*
    public void syncBusiness(){
        List<FssChangeCardEntity> list = query(99);
        if(list != null && list.size() > 0) {
	        for(FssChangeCardEntity changeCardEntity : list){
	            //同步业务系统数据,暂时只考虑出借系统
	            boolean isSuccess = false;
	            int type = changeCardEntity.getType();
	            if(type ==3 ){
	                changeCardEntity.setTradeState(101);
	                isSuccess = true;
	            }else{
	//                Long count = investmentService.queryByCustId(changeCardEntity.getCustId());
	//                if(count != null && count>0){
	//                    changeCardEntity.setTradeState(101);
	//                }
	                isSuccess = true;
	            }
	            if(isSuccess) {
	                changeCardEntity.setTradeState(100);
	            }
	            update(changeCardEntity);
	        }
        }
    }*/

    private String tmCardNo(String cardNo)throws FssException{
        if(cardNo == null || "".equals(cardNo)){
            return "无";
        }
        return cardNo.substring(0,4)+"***"+cardNo.substring(cardNo.length()-4);
    }
    
   /**
    * 根据custId查询银行卡变更账户
    * @param custId
    * @return
    * @throws FssException
    */
   public FssChangeCardEntity getChangeCardByCustId(Long custId) throws FssException{
	   return  changeCardReadMapper.selectByCustId(custId);
    }
    /**
     * 根据流水号和商户号查询返回银行卡变更信息
     * @param seq_no
     * @param mchn
     * @return
     * @throws FssException
     */
   public ChangeCardResponse queryChangeCardByParam(String seq_no,String mchn) throws FssException{
	   ChangeCardResponse changeCardResponse=changeCardReadMapper.getChangeCardByParam(seq_no,mchn);
	   return changeCardResponse;
    }
    
    

   public void syncBusiness() throws FssException{
       List<FssChangeCardEntity> list = queryByTradeState(99);
       if(list == null) return;
       for(FssChangeCardEntity changeCardEntity:list){
           //同步业务系统数据,暂时只考虑出借系统
           boolean isSuccess = false;
           int type = changeCardEntity.getType();
           if(type ==3 ){
               try {
                   this.syscBusinessLend(changeCardEntity);
               } catch (IOException e) {
                   LogUtil.error(this.getClass(),e);
                   continue;
               }
//               changeCardEntity.setTradeState(101);
               isSuccess = true;
           }else{
               int count = investmentService.queryByCustId(Integer.parseInt(changeCardEntity.getCustId().toString()));
               if(count>0){
                   try {
                       this.syscBusinessLend(changeCardEntity);
                   } catch (IOException e) {
                       LogUtil.error(this.getClass(),e);
                       continue;
                   }
//                   changeCardEntity.setTradeState(101);
               }
               isSuccess = true;
           }
           if(isSuccess) {
               changeCardEntity.setTradeState(100);
               this.update(changeCardEntity);
               fssBackplateService.createFssBackplateEntity(changeCardEntity.getSeqNo(), changeCardEntity.getMchn(), changeCardEntity.getTradeType());
           }
       }

//       this.saveOrUpdateAll(list);
   }
   
   private boolean syscBusinessLend(FssChangeCardEntity changeCardEntity) throws IOException {
       StringBuffer result = new StringBuffer();
       result.append("certNo=").append(changeCardEntity.getCertNo()).append("&");
       result.append("bankCity=").append(changeCardEntity.getBankCity()).append("&");
       result.append("bankId=").append(changeCardEntity.getBankType()).append("&");
       result.append("bankNo=").append(changeCardEntity.getCardNo()).append("&");
       try {
           result.append("bankAddr=").append(URLEncoder.encode(changeCardEntity.getBankAdd(), "utf-8")).append("&");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
           return false;
       }
       result.append("seqNo=").append(changeCardEntity.getSeqNo()!=null ? changeCardEntity.getSeqNo():"").append("&");
       result.append("filePath=").append(changeCardEntity.getFilePath()).append("&");
       result.append("resultCode=").append(changeCardEntity.getState()==2?"0000":"0001").append("&");
       result.append("msg=").append(changeCardEntity.getState()==2?"0000":changeCardEntity.getRespMsg());
       String urlValue  = ResourceUtil.getValue("config.server","lendCallback.url");
       try {
           PrintWriter out = null;
           URL url = new URL(urlValue);
           URLConnection conn  = url.openConnection();
           conn.setRequestProperty("accept", "*/*");
           conn.setRequestProperty("connection", "Keep-Alive");
           conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0;Windows NT 5.1;SV1)");

           conn.setDoOutput(true);
           conn.setDoInput(true);
           conn.setReadTimeout(15*1000);
           // 获得对象输出流
           out = new PrintWriter(conn.getOutputStream());
           // 发送请求参数
           out.print(result.toString());
           // 输出流缓冲
           out.flush();

           InputStream is = conn.getInputStream();
           BufferedReader in = null;
           String resultReturn = "";
           String line;
           try {
               in = new BufferedReader(new InputStreamReader(is));
               while ((line = in.readLine()) != null) {
                   resultReturn += line;
               }
           } catch (IOException e) {
               e.printStackTrace();
           }

           System.out.println(resultReturn);
           if("0".equals(resultReturn)){
               return true;
           }
       } catch (MalformedURLException e) {
           LogUtil.error(this.getClass(),e);
           throw e;
       } catch (IOException e) {
           LogUtil.error(this.getClass(),e);
           throw e;
       }

       return false;
   }
    
   /**
    * 创建银行卡变更实体类型
    * @param cus
    * @param bankId
    * @param bankAddr
    * @param bankCity
    * @param filePath
    * @param seqNo
    * @return
    */

   public FssChangeCardEntity createChangeCardInstance(CustomerInfoEntity cus, String cardNo, String bankId, String bankAddr, String bankCity, String filePath, String tradeType, String seqNo,String mchn,String accNo) throws FssException{
       FssChangeCardEntity entity = new FssChangeCardEntity();
       entity.setCustId(cus.getId().longValue());
       entity.setCardNo(cardNo);
       entity.setBankType(bankId);
       entity.setBankAdd(bankAddr);
       entity.setBankCity(Application.getInstance().getFourCode(bankCity));
       entity.setFilePath(filePath);
       entity.setAccNo(accNo);
       entity.setbBankInfoId(cus.getId());
       entity.setCertNo(cus.getCertNo());
       entity.setCustName(cus.getCustomerName());
       entity.setCreateUserId(-1l);
       entity.setCreateTime(new Date());
       entity.setModifyTime(new Date());
       entity.setState(1);
       entity.setTradeState(1);
       entity.setCertType(cus.getCertType());
       entity.setMobile(cus.getMobilePhone());
       entity.setType(Integer.parseInt(tradeType));
       if(seqNo != null){
           entity.setSeqNo(seqNo);
       }
       if(mchn != null){
       	entity.setMchn(mchn);
       }
       return  entity;
   }

   private boolean sendMms(String phone,int type){
       List<Map<String, String>> list = new ArrayList<>();
       Map<String, String> baMap = new HashMap<>();
       baMap.put("sysCode", CoreConstants.FUNDS_SYS_CODE);	//商户系统编码，在平台系统查看
       String tempCode = "";
       if(type == 1) {
           tempCode = CoreConstants.FUNDS_CHANGE_CARD_SUMBIT;
       }else if(type == 2){
           tempCode = CoreConstants.FUNDS_CHANGE_CARD_SUCCES;
       }else if(type == 3){
           tempCode = CoreConstants.FUNDS_CHANGE_CARD_FAIL;
       }
       if("".equals(tempCode)){
           return false;
       }
       baMap.put("tempCode", tempCode);    //商户模板编码，在平台系统查看
       list.add(baMap);
       Map<String, String> map = new HashMap<String, String>();
       map.put("phoneNo", phone);	//手机号，多个用","分开
       list.add(map);
       try {
//           String result = HttpClientUtil.postBody(CoreConstants.BACKEND_SMS_URL, JsonUtil.toJson(list));
           String result = HttpClientUtil.sendBody(CoreConstants.BACKEND_SMS_URL,list);

           System.out.println(result);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return true;
   }

    /**
     *
     * author:jhz
     * time:2016年6月13日
     * function：根据变更前银行卡id查询银行卡变更对象
     */
    public FssChangeCardEntity getChangeCardBankInfoId(Long bBankInfoId) throws FssException{
        return  changeCardReadMapper.queryByChangeCardBankInfoId(bBankInfoId);
    }
    /**
     *
     * author:jhz
     * time:2016年6月13日
     * function：根据银行卡id进行银行卡变更
     */
//    public void changeBankCardById(String mchn,String seqNo,String custNo,String aBankInfoId,String bBankInfoId,String tradeType) throws FssException{
//        //添加银行卡，变更后银行卡信息
//        BankCardInfoEntity bankCardInfo= bankCardinfoService.getBankCardInfoById(Integer.parseInt(aBankInfoId));
//        //变更前银行卡信息
//        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(Integer.parseInt(bBankInfoId));
//
////        变更后银行卡信息修改
//        bankCardInfo.setStatus("90004043");
//        bankCardInfo.setChangeState(1);
//        bankCardInfo.setModifyTime(new Date());
//        bankCardinfoService.update(bankCardInfo);
//
//        List<Map<String, String>> noticeList= new ArrayList<Map<String, String>>();
//        Map<String, String> noticeMap = new HashMap<String, String>();
//        noticeMap.put("sysCode",CoreConstants.SYS_CODE);//商户系统编码，在平台系统查看
//        noticeList.add(noticeMap);
//
//        if(bankCardinfoEntity==null) throw new FssException("90002036");
//        if(bankCardinfoEntity.getChangeState() == 1){//变更中,请勿重复提交变更
//            throw new FssException("90002037");
//        }
//        if(bankCardInfo.getBankNo().equals(bankCardinfoEntity.getBankNo())){
//            throw new FssException("90002034");
//        }
//        CustomerInfoEntity  custom=customerInfoService.getCustomerById(Long.valueOf(custNo));
////        FssChangeCardEntity entity = getChangeCardInstance(custom,bankCardinfoEntity,bankNo,bankId,bankAddr,bankCity,filePath ,type,seqNo,mchn,tradeType);
//        FssChangeCardEntity entity = getChangeCardInstance(custom,bankCardinfoEntity,bankCardInfo,1,seqNo,mchn,tradeType);
//        try {
//            this.insert(entity);
//        } catch (Exception e) {
//            throw new FssException("90002035");
//        }
//
//        //变更前银行卡信息修改
////        bankCardinfoEntity.setChangeState(1);
//        bankCardinfoEntity.setMemo("变更申请已提交,原银行卡不能做提现操作");
//        bankCardinfoEntity.setModifyTime(new Date());
//        bankCardinfoService.updateBankCardInfo(bankCardinfoEntity);
//
//        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custom.getId(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
//        fundAccountEntity.setIshangeBankCard(1);
//        fundAccountEntity.setModifyTime(new Date());
//        fundAccountService.update(fundAccountEntity);
//
//        if (entity.getType() == 1 || entity.getType() == 11029003) {
//            //发送站内通知短信
//            noticeService.packSendNotice(noticeList,CoreConstants.FUND_UPDATE_BANKCARD_SUBMIT_TEMPCODE,CoreConstants.SMS_NOTICE,NoticeService.NoticeType.FUND_UPDATE_BANKCARD_SUBMIT,entity.getCreateUserId().intValue(), entity.getCustId().intValue(),tmCardNo(bankCardinfoEntity.getBankNo()));
//            HttpClientUtil.sendMsgOrNotice(noticeList, CoreConstants.SMS_NOTICE);
//
//            this.sendMms(entity.getMobile(), 1);
//        }
//    }

    /**
     *
     * author:xdw
     * time:2016年7月26日
     * function：根据银行卡号进行银行卡变更
     */
    public void changeBankCardAnotherById(String bankId, String bankNo, String cityId, String bankSortName, String filePath) throws FssException{

        //变更前银行卡信息
        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.getBankCardInfoById(new Integer(bankId));
        if(bankCardinfoEntity==null) throw  new FssException("银行卡信息不存在");
        BankCardInfoEntity newBankCardInfoEntity = new BankCardInfoEntity();
        newBankCardInfoEntity.setId(bankCardinfoEntity.getId());
        newBankCardInfoEntity.setBankNo(bankNo);
        newBankCardInfoEntity.setCityId(cityId);
        newBankCardInfoEntity.setBankSortName(bankSortName);
//        newBankCardInfoEntity.setFilePath(filePath);
        bankCardinfoService.updateBankCardinfo(newBankCardInfoEntity);
    }

    /**
     *
     * author:xdw
     * time:2016年7月26日
     * function：查询银行卡信息
     */
    public BankCardInfoEntity queryBankCardinfoById(int id) {
        return bankCardinfoService.queryBankCardinfoById(id);
    }
}
