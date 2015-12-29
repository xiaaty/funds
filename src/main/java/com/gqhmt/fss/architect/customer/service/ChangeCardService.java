package com.gqhmt.fss.architect.customer.service;/**
 * Created by yuyonf on 15/11/30.
 */

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.gqhmt.fss.architect.account.bean.FundsAccountBean;
import com.gqhmt.fss.architect.account.entity.FundAccountEntity;
import com.gqhmt.fss.architect.account.service.FundAccountService;
import com.gqhmt.fss.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.fss.architect.customer.entity.ChangeCardEntity;
import com.gqhmt.fss.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.fss.architect.customer.mapper.read.ChangeCardReadMapper;
import com.gqhmt.fss.architect.customer.mapper.write.ChangeCardWriteMapper;
import org.springframework.stereotype.Service;


import com.gqhmt.fss.architect.order.entity.FundOrderEntity;
import com.gqhmt.fss.architect.order.service.FundOrderService;
import com.gqhmt.util.GlobalConstants;
import com.gqhmt.util.LogUtil;

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
public class ChangeCardService {

    @Resource
    private ChangeCardReadMapper changeCardReadMapper;
    @Resource
    private ChangeCardWriteMapper changeCardWriteMapper;

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private BankCardInfoService bankCardinfoService;

    @Resource
    private FundAccountService fundAccountService;
    
    @Resource
    private FundOrderService fundOrderService;

//    @Resource
//    private InvestmentService investmentService;

//    @Resource
//    private NoticeService noticeService;


    public void insert(ChangeCardEntity changeCardEntity) throws Exception{
    	changeCardWriteMapper.insertSelective(changeCardEntity);
    }
    
    public void update(ChangeCardEntity changeCardEntity) {
    	changeCardWriteMapper.updateByPrimaryKeySelective(changeCardEntity);
    }

    public ChangeCardEntity get(Long id){
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
    public void addChangeCard(int customId, String bankNo, String bankId, String bankAddr, String bankCity, String filePath) throws Exception {
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
    public void addChangeCard(CustomerInfoEntity custom, String bankNo, String bankId, String bankAddr, String bankCity, String filePath,int type,String seqNo) throws Exception {
        if(bankNo.length()>19){
            throw new Exception("银行卡号错误");
        }
        if(bankCity.length()>4){
            throw new Exception("银行所属地区错误");
        }
        if(bankId.length()>4){
            throw new Exception("所属银行错误");
        }

        Integer bankCardId = custom.getBankId();
        if(bankCardId == null){
            throw new Exception("0021");
        }

        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(bankCardId);
        if(bankNo.equals(bankCardinfoEntity.getBankNo())){
            throw new Exception("0020");
        }

        ChangeCardEntity entity = getChangeCardInstance(custom,bankNo,bankId,bankAddr,bankCity,filePath ,type,seqNo);
        insert(entity);

        bankCardinfoEntity.setChangeState(1);
        bankCardinfoEntity.setMemo("变更申请已提交,原银行卡不能做提现操作");
        bankCardinfoService.saveOrUpdate(bankCardinfoEntity);

        FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(custom.getId(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        fundAccountEntity.setIshangeBankCard(1);
        fundAccountService.update(fundAccountEntity);

//        this.noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_SUBMIT, entity.getCreateUserId().intValue(), entity.getCustId().intValue(),tmCardNo(entity.getCardNo()));
    }

    public  ChangeCardEntity getChangeCardInstance(CustomerInfoEntity cus,String bankNo, String bankId, String bankAddr, String bankCity, String filePath,int type,String seqNo){
        ChangeCardEntity entity = new ChangeCardEntity();
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
        List<ChangeCardEntity> list = query(1);
        if(list != null && list.size() > 0) {
	        for(ChangeCardEntity changeCardEntity : list){
	            changeCardEntity.setPassTime(new Date());
	            changeCardEntity.setPassUserId(-1l);
	            changeCardEntity.setTradeState(2);
	            update(changeCardEntity);
	        }
        }
    }

    public  List<ChangeCardEntity> query(int changeState){
    	ChangeCardEntity entity = new ChangeCardEntity();
    	entity.setTradeState(changeState);
        return changeCardReadMapper.select(entity);
    }

    //图片上传到富友ftp
    public void uploadImageFtp(ChangeCardEntity changeCardEntity) throws Exception {
        update(changeCardEntity);
    }

    //图片上传到富友ftp
    public void uploadData(ChangeCardEntity changeCardEntity) throws Exception {
        update(changeCardEntity);
        
        BankCardInfoEntity bankCardinfoEntity = bankCardinfoService.queryBankCardinfoById(changeCardEntity.getbBankInfoId().intValue());
        bankCardinfoEntity.setChangeState(2);

    }

    //同步成功数据
    public void sycnChangeCardInfoBySucess(){
        List<ChangeCardEntity> list = query(5);
        if(list != null && list.size() > 0) {
	        for(ChangeCardEntity changeCardEntity : list){
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
	            bankCardinfoEntity.setMemo("变更成功");
	            bankCardinfoService.saveOrUpdate(bankCardinfoEntity);
	            changeCardEntity.setState(2);
	            changeCardEntity.setEffectTime(new Date());
	            changeCardEntity.setTradeState(99);
	            FundAccountEntity fundAccountEntity = fundAccountService.getFundAccount(changeCardEntity.getCustId().intValue(), GlobalConstants.ACCOUNT_TYPE_PRIMARY);
	            fundAccountEntity.setIshangeBankCard(0);
	            fundAccountService.update(fundAccountEntity);
	
	//            this.noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_SUCESS, changeCardEntity.getCreateUserId().intValue(), changeCardEntity.getCustId().intValue(),tmCardNo(changeCardEntity.getCardNo()));
	
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
        ChangeCardEntity changeCardEntity = this.get(id);

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
        List<ChangeCardEntity> list = query(6);
        if(list != null && list.size() > 0) {
	        for(ChangeCardEntity changeCardEntity : list){
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
	
	            //noticeService.sendNotice(NoticeService.NoticeType.FUND_UPDATE_BANKCARD_FAIL, changeCardEntity.getCreateUserId().intValue(), changeCardEntity.getCustId().intValue(),changeCardEntity.getRespMsg()!= null?changeCardEntity.getRespMsg():"未知错误",tmCardNo (bankCardinfoEntity.getBankNo()));
	            update(changeCardEntity);
	        }
        }
    }


    public void syncBusiness(){
        List<ChangeCardEntity> list = query(99);
        if(list != null && list.size() > 0) {
	        for(ChangeCardEntity changeCardEntity : list){
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
    }

    private String tmCardNo(String cardNo){
        if(cardNo == null || "".equals(cardNo)){
            return "无";
        }
        return cardNo.substring(0,4)+"***"+cardNo.substring(cardNo.length()-4);
    }
}
