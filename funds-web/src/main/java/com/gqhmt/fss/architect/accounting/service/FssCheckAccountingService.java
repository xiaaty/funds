package com.gqhmt.fss.architect.accounting.service;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ThreadExecutor;
import com.gqhmt.fss.architect.account.entity.FuiouAccountInfoEntity;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.fss.architect.accounting.entity.FssCheckDate;
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
import com.gqhmt.quartz.job.accounting.CheckAccountingJob;
import com.gqhmt.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.jsp.tagext.TryCatchFinally;
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
        FssCheckDate fssCheckDate;
        try {
            fssCheckDate = fssCheckDateService.queryDate(); //20150601之后的日期
            if (null == fssCheckDate) {
                return;
            }
            LogUtil.info(this.getClass(),"满标回款历史对账，当前查询批次日期为：" + fssCheckDate.getOrderDate());
            if (StringUtils.isNotEmpty(fssCheckDate.getOrderDate()))
                checkHistoryAccount(fssCheckDate.getOrderDate());
        } catch (FssException e) {
            throw new FssException("满标回款查询对账定时任务异常，当前查询批次日期为[]", e);
        }
    }

    /**
     * wanggp
     * 查询历史标的当日对账数据对账
     * @param orderDate
     * @throws FssException
     */
    public void checkHistoryAccount(String orderDate) throws FssException {
        List<FuiouFtpColomField> fuiouFtpColomFieldList = new ArrayList<FuiouFtpColomField>();
        List<FundSequenceEntity> sequenceList = new ArrayList<FundSequenceEntity>();
        String orderNo = "";
        BigDecimal sumSequenceAmount = new BigDecimal("0");
        BigDecimal sumFieldAmount = new BigDecimal("0");

        List<FssCheckAccountingEntity> checkAccountingList = fssCheckAccountingReadMapper.queryCheckAcctListByDate(orderDate);
        int checkAcctSize = checkAccountingList.size() - 1;

        FssCheckAccountingEntity checkAccounting = new FssCheckAccountingEntity();
        List<FuiouFtpOrder> fuiouFtpOrderList = fuiouFtpOrderReadMapper.queryOrderNoListByDate(orderDate);
        //当天每一笔订单对账
        for (FuiouFtpOrder fuiouFtpOrder : fuiouFtpOrderList) {
            int ret = fssCheckDateService.updateInputUserState(orderDate);//更新对账日期为已对账
            if (ret !=1)
                throw new FssException("更新对账日期失败！");

            orderNo = fuiouFtpOrder.getOrderNo();
            LogUtil.info(this.getClass(),"满标回款历史对账，查询ftpField和FtpOrder订单号" + orderNo);
            if (StringUtils.isEmpty(orderNo))
                continue;

            fuiouFtpColomFieldList = fuiouFtpColomFieldReadMapper.getByOrderNo(orderNo);
            if (null == fuiouFtpColomFieldList && fuiouFtpColomFieldList.isEmpty()) {
                continue;
            } else {
                LogUtil.info(this.getClass(),"满标回款历史对账，ftpField与checkAccounting 对账start：" + orderDate);
                //遍历field，1.匹配checkAccounting
                for (FuiouFtpColomField fuiouFtpColomField : fuiouFtpColomFieldList) {
                    //是否旧数据
                    if (StringUtils.isNotEmpty(fuiouFtpColomField.getFeildOrderNo()))
                        continue;
                    LogUtil.info(this.getClass(),"满标回款历史对账，ftpField订单号：" + fuiouFtpColomField.getOrderNo()
                            + "ftpField id:" + fuiouFtpColomField.getId());
                    for (int i=0; i<checkAccountingList.size(); i++) {
                        checkAccounting = checkAccountingList.get(i);
                        if (!fuiouFtpColomField.getFromUserName().equals(checkAccounting.getAccName()) &&
                                !fuiouFtpColomField.getToUserName().equals(checkAccounting.getToAccName()) &&
                                fuiouFtpColomField.getAmt().compareTo(new BigDecimal(checkAccounting.getAmount())) != 0) {
                            if (checkAcctSize == i) { //对比最后一条，无符合数据，则更新为异常
                                updateFieldStatus(orderNo);
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    //获取field总金额
                    if (fuiouFtpColomField.getAmt() != null && fuiouFtpColomField.getAmt().compareTo(new BigDecimal("0")) > 0)
                        sumFieldAmount = sumFieldAmount.add(fuiouFtpColomField.getAmt());
                }
                //2.核对sequence总金额
                sequenceList  = sequenceReadMapper.queryByOrderNo(orderNo);
                if (null == sequenceList && sequenceList.isEmpty()) {
                    continue;
                } else {
                    LogUtil.info(this.getClass(),"满标回款历史对账，ftpField与sequence 对账start：" + orderDate);
                    for (FundSequenceEntity sequence : sequenceList) {
                        if (sequence.getAmount() != null && sequence.getAmount().compareTo(new BigDecimal("0")) > 0)
                            sumSequenceAmount = sumSequenceAmount.add(sequence.getAmount());
                    }
                    if (sumFieldAmount.compareTo(sumSequenceAmount) != 0) //校验总金额
                        updateFieldStatus(orderNo);
                }
            }
        }
    }

    /**
     * wanggp
     * 更新field差异状态
     * @param orderNo
     * @throws FssException
     */
    public void updateFieldStatus(String orderNo) throws FssException {
        LogUtil.info(this.getClass(),"满标回款历史对账，更新ftpField对账异常状态，订单号：" + orderNo);
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
    public void  checkFundOrder(FssCheckAccountingEntity account)throws FssException{

        LogUtil.info(this.getClass(), "核对订单");

        if(account == null){
            return;
        }
        FundOrderEntity order=fundOrderService.findfundOrder(account.getOrderNo());
        if(order == null){
            return;
        }
        if(StringUtils.equals("交易成功",account.getStatus())){
            account.setStatus("0000");
        }

        if(order.getRetCode().equals(account.getStatus())){
            if(StringUtils.equals("0000",order.getRetCode())){
                if(StringUtils.equals("10980004",account.getTradeType())){
                        //满标转账回款转账对账
                    if (order.getOrderType()==1190020){
                        this.checkRepayment(order,account);
                    //普通转账对账
                    }else {
                        int size=fundSequenceService.getSizeByOrderNo(order.getOrderNo());
                        if (size == 2) {
                            fundOrderService.updateFundsOrder(order, "98080001", "98010001");
                            LogUtil.info(this.getClass(), "订单号为" + account.getOrderNo() + ",转账数据正常");
                        } else {
                            fundOrderService.updateFundsOrder(order, "98080002", "98010002");
                            LogUtil.info(this.getClass(), "订单号为" + account.getOrderNo() + ",转账流水记录大于2条数据异常");
                        }
                        }
                //充值提现对账
                }else{
                    int size=fundSequenceService.getSizeByOrderNo(order.getOrderNo());
                    if(size==1){
                        fundOrderService.updateFundsOrder(order,"98080001","98010001");
                        LogUtil.info(this.getClass(),"订单号为"+account.getOrderNo()+",充值提现数据正常");
                    }else if(size>1){
                        fundOrderService.updateFundsOrder(order,"98080002","98010002");
                        LogUtil.info(this.getClass(),"订单号为"+account.getOrderNo()+",充值提现流水记录大于1条数据异常");

                    }else{
                        //流水表无记录的话进行流水添加
                        fundOrderService.updateFundsOrder(order,"98080002","98010002");
                        LogUtil.info(this.getClass(),"订单号为"+account.getOrderNo()+",充值提现流水记录不存在数据异常");
                    }
                }
            }
        //富友成功，本地失败
        }else if(!"0000".equals(order.getRetCode()) && "0000".equals(account.getStatus())){
            order.setRetCode("0000");
            order.setOrderState(2 );
            fundOrderService.updateFundsOrder(order,"98080002","98010002");
            LogUtil.info(this.getClass(),"订单号为"+account.getOrderNo()+"数据异常");
        }
    }

    /**
     * jhz
     * 满标转账回款转账对账
     * @param order
     * @param account
     * @throws FssException
     */
    public void checkRepayment(FundOrderEntity order,FssCheckAccountingEntity account) throws FssException{
        int size=fundSequenceService.getSizeBySOrderNo(order.getOrderNo());
        List<FundSequenceEntity> sequenceEntities=fundSequenceService.queryBySOrderNo(order.getOrderNo());
        if(sequenceEntities.size()==0){
            fundOrderService.updateFundsOrder(order, "98080002", "98010002");
            LogUtil.info(this.getClass(), "订单号为" + account.getOrderNo() + ",回款流水不存在数据异常");
        } else {
            if(order.getOrderAmount().compareTo(sequenceEntities.get(0).getAmount().abs())==0){
                fundOrderService.updateFundsOrder(order, "98080001", "98010001");
                LogUtil.info(this.getClass(), "订单号为" + account.getOrderNo() + ",回款数据正常");
            } else {
                fundOrderService.updateFundsOrder(order, "98080002", "98010002");
                LogUtil.info(this.getClass(), "订单号为" + account.getOrderNo() + ",回款流水和订单金额不一致数据异常");
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

    /**
     * wanggp
     * 一般交易对账
     * @param orderDate
     * @throws FssException
     */
    public void checkAcctOperate(String orderDate) throws FssException {
        FssCheckDate fssCheckDate = fssCheckDateService.getFssCheckDate(orderDate);
        if (null == fssCheckDate)
            return;
        fssCheckDate.setOrderUserState("98010001");
        fssCheckDateService.update(fssCheckDate);
        try {
            List<FssCheckAccountingEntity> checkAccountings=this.getCheckAccounts(orderDate);
            if(CollectionUtils.isEmpty(checkAccountings)){
                return;
            }
            for (FssCheckAccountingEntity check:checkAccountings) {
                this.checkFundOrder(check);
            }
        } catch (FssException e) {
            LogUtil.error(this.getClass(),e.getMessage());
        }
    }
}
