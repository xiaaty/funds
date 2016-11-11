package com.gqhmt.fss.architect.accounting.service;

import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
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
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                                                   String accountingResult,String accountingState)throws FssException{
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
     * @param entity
     * @return
     * @throws FssException
     */
    public FssCheckAccountingEntity createChecking(FssCheckAccountingEntity entity,String type)throws FssException{
        entity.setTradeType(type);
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
     * 查询历史标的当日对账数据对账
     * @param inputDate
     * @throws FssException
     */
    public void queryOrderNo(String inputDate) throws FssException {
        List<FuiouFtpColomField> fuiouFtpColomFieldList = new ArrayList<FuiouFtpColomField>();
        List<FundSequenceEntity> sequenceList = new ArrayList<FundSequenceEntity>();
        String orderNo = "";
        //一天内富友对账数据
        List<FssCheckAccountingEntity> checkAccountingList = fssCheckAccountingReadMapper.queryCheckAcctListByDate(inputDate);
        //一天内ftp对账数据
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
            //1.ftp-field与check-accounting每一笔对账（金额、入户、出户），账不平，记录field表异常--9808
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
     * 校验本地Sequence与Field所有订单总金额
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
     * 更新field差异状态
     * @param orderNo
     * @throws FssException
     */
    public void updateFieldStatus(String orderNo) throws FssException {
        int result = fuiouFtpColomFieldWriteMapper.updateStatusByorderNo(orderNo);
        if (1 != result)
            throw new FssException("差异帐处理更新异常失败！订单号：[" + orderNo + "]");
    }
}
