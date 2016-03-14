package com.gqhmt.fss.architect.customer.service;/**
 * Created by yuyonf on 15/11/30.
 */

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
import com.gqhmt.business.architect.invest.service.InvestmentService;
import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.ResourceUtil;
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
import java.util.Date;
import java.util.List;

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

    public void insert(FssChangeCardEntity changeCardEntity) throws FssException{
    	changeCardWriteMapper.insertSelective(changeCardEntity);
    }
    
    public void update(FssChangeCardEntity changeCardEntity) {
    	changeCardWriteMapper.updateByPrimaryKeySelective(changeCardEntity);
    }

    public FssChangeCardEntity get(Long id){
        return changeCardReadMapper.selectByPrimaryKey(id);
    }

//    public Page query(FundsAccountBean.ChangeCardBean changeCardEntity){
//        return changeCardReadMapper.query(changeCardEntity);
//    }

    /**
     * 录入客户修改银行卡信息,前台传入,需要根据id获取客户实体bean
     * @param customId
     * @param bankNo
     * @param bankId
     * @param bankAddr
     * @param bankCity
     * @param filePath
     * @throws Exception
     */
    public void addChangeCard(int customId, String bankNo, String bankId, String bankAddr, String bankCity, String filePath) throws FssException {
        CustomerInfoEntity customerInfo  = customerInfoService.queryCustomerById(customId);
        this.addChangeCard(customerInfo,bankNo,bankId,bankAddr,bankCity,filePath,1,null);
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
            throw new Exception("身份证未找到对应客户");
        }
        if(filePath ==null || "".equals(filePath)){
            throw new Exception("请上传审核图片");
        }
        this.addChangeCard(customerInfo,bankNo,bankId,bankAddr,bankCity,filePath,type,seqNo);
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
    public void addChangeCard(CustomerInfoEntity custom, String bankNo, String bankId, String bankAddr, String bankCity, String filePath,int type,String seqNo) throws FssException {
        if(bankNo.length()>19){
            throw new FssException("银行卡号错误");
        }
        if(bankCity.length()>4){
            throw new FssException("银行所属地区错误");
        }
        if(bankId.length()>4){
            throw new FssException("所属银行错误");
        }

        Integer bankCardId = custom.getBankId();
        if(bankCardId == null){
            throw new FssException("0021");
        }

        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(bankCardId);
       /* if(bankNo.equals(bankCardinfoEntity.getBankNo())){
            throw new FssException("0020");
        }*/

        FssChangeCardEntity entity = getChangeCardInstance(custom,bankNo,bankId,bankAddr,bankCity,filePath ,type,seqNo);
        insert(entity);

        bankCardinfoEntity.setChangeState(1);
        bankCardinfoEntity.setMemo("变更申请已提交,原银行卡不能做提现操作");
        bankCardinfoService.saveOrUpdate(bankCardinfoEntity);

        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custom.getId(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        fundAccountEntity.setIshangeBankCard(1);
        fundAccountService.update(fundAccountEntity);

//        this.noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_SUBMIT, entity.getCreateUserId().intValue(), entity.getCustId().intValue(),tmCardNo(entity.getCardNo()));
    }

    public FssChangeCardEntity getChangeCardInstance(CustomerInfoEntity cus, String bankNo, String bankId, String bankAddr, String bankCity, String filePath, int type, String seqNo){
        FssChangeCardEntity entity = new FssChangeCardEntity();
        entity.setCustId(cus.getId().longValue());
        entity.setCardNo(bankNo);
        entity.setBankType(bankId);
        entity.setBankAdd(bankAddr);
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
        if(seqNo != null){
            entity.setSeqNo(seqNo);
        }
        return  entity;
    }


    //自动审核换卡需求
    public void autoPassChangeCard(){
        List<FssChangeCardEntity> list = query(1);
        if(list != null && list.size() > 0) {
	        for(FssChangeCardEntity changeCardEntity : list){
	            changeCardEntity.setPassTime(new Date());
	            changeCardEntity.setPassUserId(-1l);
	            changeCardEntity.setTradeState(2);
	            update(changeCardEntity);
	        }
        }
    }

    public  List<FssChangeCardEntity> query(int changeState){
    	FssChangeCardEntity entity = new FssChangeCardEntity();
    	entity.setTradeState(changeState);
        return changeCardReadMapper.select(entity);
    }

    //图片上传到富友ftp
    public void uploadImageFtp(FssChangeCardEntity changeCardEntity) throws Exception {
        update(changeCardEntity);
    }

    //图片上传到富友ftp
    public void uploadData(FssChangeCardEntity changeCardEntity) throws Exception {
        update(changeCardEntity);
        
        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getbBankInfoId().intValue());
        bankCardinfoEntity.setChangeState(2);

    }

    //同步成功数据
    public void sycnChangeCardInfoBySucess(){
        List<FssChangeCardEntity> list = query(5);
        if(list != null && list.size() > 0) {
	        for(FssChangeCardEntity changeCardEntity : list){
	            //同步变更信息到银行卡信息表中
	            BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getbBankInfoId().intValue());
	            if(bankCardinfoEntity == null){
	                bankCardinfoEntity = new BankCardInfoEntity();
	            }
	
	            bankCardinfoEntity.setChangeState(0);
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
	            bankCardinfoService.saveOrUpdate(bankCardinfoEntity);
	            changeCardEntity.setState(2);
	            changeCardEntity.setEffectTime(new Date());
	            changeCardEntity.setTradeState(99);
	            FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(changeCardEntity.getCustId().intValue(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
	            fundAccountEntity.setIshangeBankCard(0);
	            fundAccountService.update(fundAccountEntity);
	            this.noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_SUCESS, changeCardEntity.getCreateUserId().intValue(), changeCardEntity.getCustId().intValue(),tmCardNo(changeCardEntity.getCardNo()));
	
	            CustomerInfoEntity customerInfoEntity = customerInfoService.queryCustomerById(changeCardEntity.getCustId().intValue());
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
    public void sycnChangeCardInfoByFaile(){
        List<FssChangeCardEntity> list = query(6);
        if(list != null && list.size() > 0) {
	        for(FssChangeCardEntity changeCardEntity : list){
	            //同步变更信息到银行卡信息表中
	            BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getbBankInfoId().intValue());
	            if(bankCardinfoEntity !=null){
	                bankCardinfoEntity.setChangeState(2);
	                bankCardinfoEntity.setMemo("资料审核不通过，新银行卡变更失败，请重新变更；原银行卡充值提现不受影响。");
	                bankCardinfoService.saveOrUpdate(bankCardinfoEntity);
	            }
	            changeCardEntity.setState(3);
	            changeCardEntity.setTradeState(99);
	            FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(changeCardEntity.getCustId().intValue(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
	            fundAccountEntity.setIshangeBankCard(0);
	            fundAccountService.update(fundAccountEntity);
	
	            noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_FAIL, changeCardEntity.getCreateUserId().intValue(), changeCardEntity.getCustId().intValue(),changeCardEntity.getRespMsg()!= null?changeCardEntity.getRespMsg():"未知错误",tmCardNo (bankCardinfoEntity.getBankNo()));
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

    private String tmCardNo(String cardNo){
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
	   FssChangeCardEntity fssChangeCardEntity=new FssChangeCardEntity();
	   fssChangeCardEntity.setCustId(custId);
	   changeCardReadMapper.select(fssChangeCardEntity);
	   return fssChangeCardEntity;
    }
    /**
     * 根据流水号和商户号查询返回银行卡变更信息
     * @param seqNo
     * @param mchn
     * @return
     * @throws FssException
     */
   public FssChangeCardEntity queryChangeCardByParam(String seq_no,String mchn) throws FssException{
	   FssChangeCardEntity fssChangeCardEntity=null;
	   fssChangeCardEntity=changeCardReadMapper.getChangeCardByParam(seq_no,mchn);
	   return fssChangeCardEntity;
    }
    
    

   public void syncBusiness(){
       List<FssChangeCardEntity> list = query(99);
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
           }
       }

//       this.saveOrUpdateAll(list);
       changeCardWriteMapper.insertList(list);
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
    
}
