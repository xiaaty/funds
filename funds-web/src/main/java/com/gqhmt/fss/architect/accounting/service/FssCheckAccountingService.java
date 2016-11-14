package com.gqhmt.fss.architect.accounting.service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.mapper.read.FssCheckAccountingReadMapper;
import com.gqhmt.fss.architect.accounting.mapper.write.FssCheckAccountingWriteMapper;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpColomField;
import com.gqhmt.fss.architect.fuiouFtp.bean.FuiouFtpOrder;
import com.gqhmt.fss.architect.fuiouFtp.mapper.read.FuiouFtpColomFieldReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.mapper.read.FuiouFtpOrderReadMapper;
import com.gqhmt.fss.architect.fuiouFtp.mapper.write.FuiouFtpColomFieldWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.entity.FundSequenceEntity;
import com.gqhmt.funds.architect.account.mapper.read.FundSequenceReadMapper;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/1
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/1  jhz     1.0     1.0 Version
 */
@Service
public class FssCheckAccountingService {

    @Resource
    private FssCheckAccountingReadMapper fssCheckAccountingReadMapper;

    @Resource
    private FssCheckAccountingWriteMapper fssCheckAccountingWriteMapper;

    @Resource
    private FuiouFtpOrderReadMapper fuiouFtpOrderReadMapper;

    @Resource
    private FuiouFtpColomFieldReadMapper fuiouFtpColomFieldReadMapper;

    @Resource
    private FuiouFtpColomFieldWriteMapper fuiouFtpColomFieldWriteMapper;

    @Resource
    private FundSequenceReadMapper sequenceReadMapper;

    @Resource
    private FundSequenceService fundSequenceService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private FssCheckDateService fssCheckDateService;

    @Resource
    private FundOrderService fundOrderService;

    @Resource
    private TradeRecordService tradeRecordService;

    /**
     * jhz
     * 添加
     * @param checkAccountingEntity
     */
    public void insert(FssCheckAccountingEntity checkAccountingEntity)throws FssException{
        fssCheckAccountingWriteMapper.insertSelective(checkAccountingEntity);
    }
    /**
     * jhz
     * 添加
     * @param enList
     */
    public int insertCheckList(List<FssCheckAccountingEntity> enList)throws FssException{
        int i=0;
        for (FssCheckAccountingEntity entity:enList) {
            try {
                this.insert(entity);
                i++;
            }catch (FssException e){
                continue;
            }
        }
        return i;
    }

    /***
     * jhz
     * 批量添加
     * @param list
     */
    public int insertList(List<FssCheckAccountingEntity> list)throws FssException{
        if(CollectionUtils.isEmpty(list)){
            return 0;
        }
        return fssCheckAccountingWriteMapper.insertList(list);
    }
    /**
     * jhz
     * 修改
     * @param checkAccountingEntity
     */
    public void update(FssCheckAccountingEntity checkAccountingEntity)throws FssException{
        checkAccountingEntity.setModifyTime(new Date());
        fssCheckAccountingWriteMapper.updateByPrimaryKey(checkAccountingEntity);
    }
    /**
     * jhz
     * 列表
     * @param map
     */
    public List<FssCheckAccountingEntity> list(Map<String,String> map){
        Map<String, String> map2= Maps.newHashMap();
        if (map != null) {
            String startTime = map.get("startTime");
            String endTime = map.get("endTime");
            map2.put("orderNo",map.get("orderNo"));
            map2.put("type",map.get("type"));
            map2.put("abnormalState",map.get("abnormalState"));
            map2.put("userName",map.get("userName"));
            map2.put("accName",map.get("accName"));
            map2.put("contractNo", map.get("contractNo"));
            map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
            map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
        }
       return fssCheckAccountingReadMapper.queryList(map2);
    }

    public FssCheckAccountingEntity createChecking(String orderNo, String tradeTime, String accountingNo,
                                                   String accountingTime, String amount,String custId,String accNo,
                                                   String accName,String userName,String remark,String status,String tradeType,
                                                   String accountingResult,String accountingState){
        FssCheckAccountingEntity entity=new FssCheckAccountingEntity();
        entity.setOrderNo(orderNo);
        entity.setTradeTime(tradeTime);
        entity.setAccountingNo(accountingNo);
        entity.setAccountingTime(accountingTime);
        entity.setAmount(amount);
        entity.setCustId(custId);
        entity.setAccNo(accNo);
        entity.setAccName(accName);
        entity.setUserName(userName);
        entity.setRemark(remark);
        entity.setStatus(status);
        entity.setTradeType(tradeType);
        entity.setAccountingResult(accountingResult);
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setAccountingStatus(accountingState);//98010002未对帐，98010001已对账
        return  entity;
    }

    /**
     * jhz
     * 通过orderNo查询对账数据
     * @param orderNo
     * @return
     * @throws FssException
     */
    public FssCheckAccountingEntity queryByOrderNo(String orderNo)throws FssException{
        return fssCheckAccountingReadMapper.queryByOrderNo(orderNo);
    }

    public List<FssCheckAccountingEntity> getCheckList(List<String> orderNos){
        return fssCheckAccountingReadMapper.getCheckList(orderNos);
    }
    public FssCheckAccountingEntity createChecking(FuiouAccountInfoEntity accountInfo, String tradeType){
//        String state="";
//        if(StringUtils.equals("0000",accountInfo.getState())){
//            state="交易成功";
//        }else{
//            state="交易失败";
//        }
        FssCheckAccountingEntity entity= this.createChecking(accountInfo.getSeqNo(), DateUtil.dateTostring(accountInfo.getTradeTime()),
                accountInfo.getBatchFoiuFinance(),DateUtil.dateTostring(accountInfo.getTradeTime()),
                accountInfo.getBalance().toString(),null,null,accountInfo.getUserAccount(),accountInfo.getUserName(),
                accountInfo.getRemark(),accountInfo.getState(),"","10130001","98010002");
        entity.setToAccName(accountInfo.getInAccount());
        entity.setToUserName(accountInfo.getInUserName());
        entity.setContractNo(accountInfo.getContractNum());
        if(StringUtils.equals("ZZ",tradeType) || StringUtils.equals("HB",tradeType)){
            entity.setTradeType("10980004");
        }else if(StringUtils.equals("WTCZ",tradeType)){
            entity.setTradeType("10980003");
        }else if(StringUtils.equals("WTTX",tradeType)){
            entity.setTradeType("10980001");
        }
        return entity;

    }
    /**
     * jhz
     * @param entity
     * @return
     * @throws FssException
     */
    public FssCheckAccountingEntity createChecking(FssCheckAccountingEntity entity)throws FssException{
        entity.setCreateTime(new Date());
        entity.setModifyTime(new Date());
        entity.setAccountingStatus("98010002");//98010002未对帐，98010001已对账
        return  entity;
    }
    /**
     * jhz
     * 查询充值体现列表
     */
    public List<FssCheckAccountingEntity> queryWithList(){

        return fssCheckAccountingReadMapper.queryWithList();
    }

    /**
     * jhz
     * 充值体现对账
     * @throws FssException
     */
    public void checkAccounting() throws  FssException{
        List<FssCheckAccountingEntity> list=this.queryWithList();
        for (FssCheckAccountingEntity entity:list) {
            List<FundSequenceEntity> sequences= fundSequenceService.queryByOrderNo(entity.getOrderNo());
            if(CollectionUtils.isNotEmpty(sequences)){
                if(sequences.size()>1){
                    this.updateNormalOrder(entity,"98010001","98080002");
                }else{
                    if(new BigDecimal(entity.getAmount()).compareTo(sequences.get(0).getAmount())==0){
                        this.updateNormalOrder(entity,"98010001","98080001");
                    }else {
                        this.updateNormalOrder(entity,"98010001","98080002");
                    }
                }
            }else{
                this.updateNormalOrder(entity,"98010001","98080002");
            }
        }
    }
    /**
     * jhz
     * 添加状态并修改对账表
     * @param entity
     * @param accountingStatus 98010001已对账，98010002未对帐
     * @param abnormalstate 异常状态,'98080001'正常,'98080002'异常
     * @throws FssException
     */
    public void updateNormalOrder(FssCheckAccountingEntity entity,String accountingStatus,String abnormalstate)throws FssException{
        entity.setAccountingStatus(accountingStatus);
        entity.setAbnormalState(abnormalstate);
        this.update(entity);
    }

    public List<Map<String,String>> getfuiouTradeCz(Long custid,String startTime,String endTime) throws  FssException{
        FundAccountEntity entity= fundAccountService.getFundAccount(custid, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        return this.getFuiouTradeCz(entity,startTime,endTime);
    }

    public List<Map<String,String>> getfuiouTradeCz(String  userName,String startTime,String endTime) throws FssException{
        FundAccountEntity entity= fundAccountService.getFundAccount(userName, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        return this.getFuiouTradeCz(entity,startTime,endTime);
    }

    public List<Map<String,String>> getFuiouTradeCz(FundAccountEntity entity,String startTime,String endTime)throws  FssException{
        List<Map<String,String>> list = null;
        CommandResponse response=null;
        try{
            response =paySuperByFuiou.tradeCZZTXQuery("PW11",entity,startTime,endTime,1);
            Map<String, Object> map=response.getMap();
            if(map == null || map.get("results") == null || map.get("total_number") == null ||  Integer.parseInt(map.get("total_number").toString())==0){
                return null;
            }

            if(Integer.parseInt(map.get("total_number").toString())==1) {
                Map<String, Object> resultsMap = (Map<String, Object>) map.get("results");
                Map<String, String> resultMap = (Map<String, String>) resultsMap.get(("result"));
                list = new ArrayList<>();
                list.add(resultMap);
                return list;
            }

            list  = (List<Map<String,String>>)map.get("results");
            return list;
        }catch (FssException e){
            LogUtil.error(this.getClass(),e.getMessage());

        }

        return null;
    }

    /**
     * wanggp
     * 满标回款查询对账定时任务
     */
    public void checkHistoryAccounting() throws FssException {
        String inputDate = "";
        try {
            inputDate = fssCheckDateService.queryInputDate();
            if (StringUtils.isNotEmpty(inputDate))
                queryOrderNo(inputDate);
        } catch (FssException e) {
            throw new FssException("满标回款查询对账定时任务异常，当前查询批次日期为[" + inputDate +"]");
        }
    }

    /**
     * wanggp
     * 查询历史标的当日对账数据对账
     * @param inputDate
     * @throws FssException
     */
    public void queryOrderNo(String inputDate) throws FssException {
        List<FuiouFtpColomField> fuiouFtpColomFieldList = new ArrayList<FuiouFtpColomField>();
        List<FundSequenceEntity> sequenceList = new ArrayList<FundSequenceEntity>();
        String orderNo = "";
        List<FssCheckAccountingEntity> checkAccountingList = fssCheckAccountingReadMapper.queryCheckAcctListByDate(inputDate);
        List<FuiouFtpOrder> fuiouFtpOrderList = fuiouFtpOrderReadMapper.queryOrderNoListByDate(inputDate);
        //当天每一笔订单对账
        for (FuiouFtpOrder fuiouFtpOrder : fuiouFtpOrderList) {
            orderNo = fuiouFtpOrder.getOrderNo();
            if (StringUtils.isEmpty(orderNo))
                continue;
            //sequence本地平台内所需对账数据
            fuiouFtpColomFieldList = fuiouFtpColomFieldReadMapper.getByOrderNo(orderNo);
            sequenceList  = sequenceReadMapper.queryByOrderNo(orderNo);//本地对账数据sequence
            //遍历field，1.匹配checkAccounting  2.核对sequence总金额
            //1.ftp-field与check-accounting每一笔对账（金额、入户、出户），账不平，记录field表异常
            if (!checkAcctAndField(fuiouFtpColomFieldList, checkAccountingList,inputDate)){
                updateFieldStatus(orderNo);
                return;
            }
            //2.ftp-field所有订单总金额与sequence正或负订单总金额对账，不平则记录field异常
            if (checkSequenceAndField(fuiouFtpColomFieldList, sequenceList))
                updateFieldStatus(orderNo);
        }
    }

    /**
     * wanggp
     * 校验checkAccounting与field金额、出户、入户
     * @param fuiouFtpColomFieldList
     * @param checkAccountingList
     * @return
     */
    public boolean checkAcctAndField(List<FuiouFtpColomField> fuiouFtpColomFieldList,
                                     List<FssCheckAccountingEntity> checkAccountingList, String inputDate) {
        //更新ftp-order为已对账状态
        fssCheckDateService.updateInputDate(inputDate);
        for (FuiouFtpColomField fuiouFtpColomField : fuiouFtpColomFieldList) {
            for (FssCheckAccountingEntity checkAccounting : checkAccountingList) {
                if (!fuiouFtpColomField.getFromUserName().equals(checkAccounting.getAccName()) &&
                        !fuiouFtpColomField.getToUserName().equals(checkAccounting.getToAccName()) &&
                        !fuiouFtpColomField.getAmt().equals(checkAccounting.getAmount()))
                    return false;
            }
        }
        return true;
    }

    /**
     * wanggp
     * 校验本地Sequence与Field订单总金额
     * @param fuiouFtpColomFieldList
     * @param sequenceList
     * @return
     */
    public boolean checkSequenceAndField(List<FuiouFtpColomField> fuiouFtpColomFieldList, List<FundSequenceEntity> sequenceList) {
        BigDecimal sumSequenceAmount = null;
        BigDecimal sumFieldAmount = null;
        for (FuiouFtpColomField fuiouFtpColomField : fuiouFtpColomFieldList) {
            if (fuiouFtpColomField.getAmt() != null && fuiouFtpColomField.getAmt().compareTo(new BigDecimal("0")) > 0)
                sumFieldAmount = sumFieldAmount.add(fuiouFtpColomField.getAmt());
        }
        for (FundSequenceEntity sequence : sequenceList) {
            if (sequence.getAmount() != null && sequence.getAmount().compareTo(new BigDecimal("0")) > 0)
                sumSequenceAmount = sumSequenceAmount.add(sequence.getAmount());
        }
        if (sumFieldAmount.compareTo(sumSequenceAmount) != 0) //校验金额
            return false;
        return true;
    }

    /**
     * wanggp
     * 更新field差异状态
     * @param orderNo
     * @throws FssException
     */
    public void updateFieldStatus(String orderNo) throws FssException {
        int result = fuiouFtpColomFieldWriteMapper.updateStatusByorderNo(orderNo);
        if (1 != result)
            throw new FssException("差异帐处理更新异常失败！订单号：[" + orderNo + "]");
    }
    /**
     * jhz
     * 查询前一天未对帐的充值体现
     * @return
     */
    public List<FssCheckAccountingEntity> getCheckAccounts(String orderDate){
        return  fssCheckAccountingReadMapper.getCheckAccounts(orderDate);
    }
    /**
     * 对账list转map
     * @param checkAccountings
     * @return
     */
    public Map<String, FssCheckAccountingEntity> convertToFundSOrderMap(List<FssCheckAccountingEntity> checkAccountings)throws FssException{
        Map<String, FssCheckAccountingEntity> checkAccMap = Maps.newHashMap();
        if (CollectionUtils.isNotEmpty(checkAccountings)) {
            checkAccMap = Maps.uniqueIndex(checkAccountings, new Function<FssCheckAccountingEntity, String>() {
                public String apply(FssCheckAccountingEntity item) {
                    return item.getOrderNo();
                }
            });
        }
        return checkAccMap;
    }
    /**
     * jhz
     * 核对订单表状态和对账文件状态是否一致
     */
    public void checkFundOrder(FundOrderEntity order,Map<String, FssCheckAccountingEntity> checkAccMap)throws FssException{
//        List<FundOrderEntity> orderEntities=fundOrderService.getOrders();
//        List<FssCheckAccountingEntity> checkAccountings=this.getCheckAccounts();
            FssCheckAccountingEntity account=checkAccMap.get(order.getOrderNo());
            if(account!=null){
                if(order.getRetCode().equals(account.getStatus())){
                    if(StringUtils.equals("0000",order.getRetCode())){
                    int size=fundSequenceService.getSizeByOrderNo(order.getOrderNo());
                        //普通转账对账
                        if(order.getOrderType() == 5){
                            if(size==2){
                                fundOrderService.updateFundsOrder(order,"98080001","98010001");
                            }else{
                                fundOrderService.updateFundsOrder(order,"98080002","98010002");
                            }
                            //满标转账回款转账对账
                        }else if(order.getOrderType() == 11990049 || order.getOrderType()==11990048){
                            List<FundSequenceEntity> sequenceEntities=fundSequenceService.queryByOrderNo(order.getOrderNo());
                            if(CollectionUtils.isEmpty(sequenceEntities)){
                                fundOrderService.updateFundsOrder(order,"98080002","98010002");
                            }
                            BigDecimal sum=BigDecimal.ZERO;
                            for (FundSequenceEntity se:sequenceEntities) {
                                if(se.getAmount().compareTo(BigDecimal.ZERO)==1){
                                    sum=sum.add(se.getAmount());
                                }
                            }
                            if(sum.compareTo(order.getOrderAmount())==0){
                                fundOrderService.updateFundsOrder(order,"98080001","98010001");
                            }else {
                                fundOrderService.updateFundsOrder(order,"98080002","98010002");
                            }
                            //充值提现对账
                        }else{
                            if(size==1){
                                fundOrderService.updateFundsOrder(order,"98080001","98010001");
                            }else if(size>1){
                                fundOrderService.updateFundsOrder(order,"98080002","98010002");
                            }else{
                                //流水表无记录的话进行流水添加
//                                tradeRecordService.asynCommand(order,"success");
                                fundOrderService.updateFundsOrder(order,"98080002","98010002");
                            }
                        }
                    }
                    //富友成功，本地失败
                }else if(!"0000".equals(account.getStatus())){
                    int size=fundSequenceService.getSizeByOrderNo(order.getOrderNo());
                    //充值提现进行记账
                    if(order.getOrderType() != 5 && order.getOrderType()!=11990049 && order.getOrderType()!=11990048){
//                        tradeRecordService.asynCommand(order,"success");
                        fundOrderService.updateFundsOrder(order,"98080002","98010002");
                    //转账进行反交易
                    }else{
//                        FundAccountEntity toAcc= fundAccountService.select(order.getAccountId());
//                        FundAccountEntity acc= fundAccountService.select(order.getToAccountId());
//                        paySuperByFuiou.transerer(acc,toAcc,order.getOrderAmount(),order.getOrderType(),order.getOrderFrormId(),order.getOrderSource(),order.getNewOrderType(),order.getTradeType(),order.getLendNo(),order.getToLendNo(),order.getLoanCustId(),order.getLoanNo());
                        fundOrderService.updateFundsOrder(order,"98080002","98010002");
                    }
                }
            }
    }

    /**
     * jhz
     * 进行反交易
     * @param orderNo
     * @throws FssException
     */
    public Boolean returnTrade(String orderNo) throws FssException{
        FundOrderEntity order=fundOrderService.findfundOrder(orderNo);
        if (order==null){
            LogUtil.error(this.getClass(),"未查询到订单信息");
        }
        FundAccountEntity toAcc= fundAccountService.select(order.getAccountId());
        FundAccountEntity acc= fundAccountService.select(order.getToAccountId());
        FundOrderEntity orderEntity=paySuperByFuiou.transerer(acc,toAcc,order.getOrderAmount(),order.getOrderType(),order.getOrderFrormId(),order.getOrderSource(),order.getNewOrderType(),order.getTradeType(),order.getLendNo(),order.getToLendNo(),order.getLoanCustId(),order.getLoanNo());
        if(StringUtils.equals("0000",orderEntity.getRetCode())){
            return true;
        }else {
            return false;
        }
    }
}
