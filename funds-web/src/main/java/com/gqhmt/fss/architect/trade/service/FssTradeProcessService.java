package com.gqhmt.fss.architect.trade.service;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.mapper.read.TradeProcessReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.TradeProcessWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.funds.service.record.TradeSeqSwrvice
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author keyulai
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/12/27 12:00
 * Description:
 * <p>
 *     交易流程处理service
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/12/27  keyulai      1.0     1.0 Version
 */
@Service
public class FssTradeProcessService {

    @Resource
    private TradeProcessReadMapper tradeProcessReadMapper;

    @Resource
    private TradeProcessWriteMapper tradeProcessWriteMapper;

    @Resource
    private FundOrderService fundOrderService;
    @Resource
    private PaySuperByFuiou paySuperByFuiou;
    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private FundWithrawChargeService fundWithrawChargeService;

    /**
     * 保存交易数据
     * @param entity
     */
    public void saveTradeProcess(TradeProcessEntity entity) throws FssException{
        try{
            tradeProcessWriteMapper.insertSelective(entity);
            //保存子集
            List<TradeProcessEntity> list = entity.getList();
            if(list!=null && list.size()>0){
                for(TradeProcessEntity tradeProcessEntity:list){
                    tradeProcessEntity.setParnetId(entity.getId());
                }
                tradeProcessWriteMapper.insertList(list);
            }
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage());
            throw new FssException("91009804",e);
        }
    }
    public void save(TradeProcessEntity entity)throws FssException{
        tradeProcessWriteMapper.insertSelective(entity);
    }

    /**
     * 根据统一支付业务订单号查询交易流程数据
     * @param orderNo
     * @return
     */
    public TradeProcessEntity getTradeProcessByOrderNo(String orderNo){
        TradeProcessEntity entity=tradeProcessReadMapper.getTradeProcessByOrderNo(orderNo);
        return entity;
    }

    /**
     * 修改业务流程状态
     * @param entity
     * @throws FssException
     */
    public int updateTradeProcessEntity(TradeProcessEntity entity) throws FssException{
        int count = 0;
        try{
            entity.setModifyTime(new Date());
            count = tradeProcessWriteMapper.updateByPrimaryKey(entity);
        }catch (Exception e){
            LogUtil.debug(this.getClass(),entity+":"+entity.getId());
            throw new FssException("91009804",e);
        }
        return count;
    }

    /**
     *根据id获取流程数据
     * @param id
     * @return
     */
    public TradeProcessEntity getTradeProcessByOrderNo(Long id){
        TradeProcessEntity entity=tradeProcessReadMapper.selectByPrimaryKey(id);
        return entity;
    }
    /**
     * 查询交易流程数据
     * @param map
     * @return
     */
    public List<TradeProcessEntity> listTradeProcess(Map<String, String> map){
        Map<String, String> map2=new HashMap<String, String>();
        if (map != null) {
            String startTime = map.get("startTime");
            String endTime = map.get("endTime");

            map2.put("type",map.get("type"));
            map2.put("status",map.get("status"));
            map2.put("mobile",map.get("mobile"));
            map2.put("custName", map.get("custName"));
            map2.put("tradeType", map.get("tradeType"));
            map2.put("status", map.get("status"));
            map2.put("processState", map.get("processState"));
            map2.put("startTime", startTime != null ? startTime.replace("-", "") : null);
            map2.put("endTime", endTime != null ? endTime.replace("-", "") : null);
        }
        return tradeProcessReadMapper.listTradeProcess(map2);
    }

    public TradeProcessEntity findById(Long id){
        return tradeProcessReadMapper.selectByPrimaryKey(id);
    }


    /**
     * 根据actionType 和 ParentId 查询子交易
     * @param actionType
     * @param parentId
     * @return
     */
    public List<TradeProcessEntity> findByParentIdAndActionType(String actionType, String parentId) {
        return tradeProcessReadMapper.findByParentIdAndActionType(actionType,parentId);
    }

    public int insertSelective(TradeProcessEntity tradeProcess) throws FssException{
        return tradeProcessWriteMapper.insertSelective(tradeProcess);
    }

   public BigDecimal getChargeAmountByParentId(Long parentId){
       BigDecimal chargeAmount=tradeProcessReadMapper.getChargeAmount(parentId);
       return chargeAmount;
   }

    public TradeProcessEntity creatTradeProcess(TradeProcessEntity entity,String bidId,String memo,BigDecimal amt,String actionType,String fundType,String sync,String callBack){
            entity.setBidId(bidId);
            entity.setOrderNo(fundOrderService.getOrderNo());
            entity.setMemo(memo);
            entity.setAmt(amt);//交易金额
            entity.setActionType(actionType);//业务类型
            entity.setFundType(fundType);//进程类型
            entity.setSync(sync);//是否同步
            entity.setCallback(callBack);//是否回调
            entity.setCreateTime(new Date());
            entity.setModifyTime(new Date());
        return entity;
    }

    /**
     * 创建交易流程数据（添加公共参数）
     * @param seqNo
     * @param tradeType
     * @param fromAccEntity
     * @param toAccEntity
     * @return
     */
    public TradeProcessEntity general(String seqNo, String tradeType, FundAccountEntity fromAccEntity, FundAccountEntity toAccEntity){
        TradeProcessEntity tradeProcessEntity = new TradeProcessEntity();
        tradeProcessEntity.setSeqNo(seqNo);
        if(StringUtils.isNotEmpty(tradeType)){
            tradeProcessEntity.setTradeTypeParent(tradeType.substring(0,4));
        }
        tradeProcessEntity.setTradeType(tradeType);
        boolean autoPass=true;
        String processState = "10170001";
        if(autoPass){
            processState = "10170002";
        }
        tradeProcessEntity.setProcessState(processState);//流程状态（提交待处理：10170001，流程处理中：10170002，流程完结：10170003）
        tradeProcessEntity.setStatus("10030001");//交易提交
        tradeProcessEntity.setFromCustNo(fromAccEntity!=null ? String.valueOf(fromAccEntity.getCustId()) : null); //出账账户客户编号
        tradeProcessEntity.setFromCustType(fromAccEntity!=null ? String.valueOf(fromAccEntity.getBusiType()): null); //出账账户客户类型，1，2，3，10，11，90，96
        tradeProcessEntity.setFromAccId(fromAccEntity!=null ? fromAccEntity.getId(): null); //出账支付系统账户id
        tradeProcessEntity.setFromCustMobile(fromAccEntity!=null ? fromAccEntity.getUserName() : null); //出账账户客户手机号
        tradeProcessEntity.setFromCustName(fromAccEntity!=null ? fromAccEntity.getCustName() : null); //出账账户客户姓名
        tradeProcessEntity.setToCustNo(toAccEntity!=null ? String.valueOf(toAccEntity.getCustId()) : null); //入账账户客户编号
        tradeProcessEntity.setToCustType(toAccEntity!=null ? String.valueOf(toAccEntity.getBusiType()) : null);//入账账户客户编号
        tradeProcessEntity.setToAccId(toAccEntity!=null ? toAccEntity.getId() : null); //入账支付系统账户id
        tradeProcessEntity.setToCustMobile(toAccEntity!=null ? toAccEntity.getUserName() : null);//入账账户客户手机号
        tradeProcessEntity.setToCustName(toAccEntity!=null ? toAccEntity.getCustName() : null);//入账账户客户姓名
        return tradeProcessEntity;
    }
    /**
     * jhz
     * 查询所有未进行提现交易的数据
     * @return
     */
    public List<TradeProcessEntity> getWithDrawProcess(){
        return tradeProcessReadMapper.getWithDrawProcess();
    }
    /**
     * jhz
     * 查询所有提现失败的数据
     * @return
     */
    public List<TradeProcessEntity> getFailWithDrawProcess(){
        return tradeProcessReadMapper.getFailWithDrawProcess();
    }
    public List<TradeProcessEntity> childTradeProcess(Long parentId){
        return tradeProcessReadMapper.childTradeProcess(parentId);
    }
    //收费
    public void charge(TradeProcessEntity entity,FundOrderEntity fundOrderEntity) throws FssException {
        //提现订单不存在，返回
        if(fundOrderEntity==null) return;
        //查询提现收费子交易
        List<TradeProcessEntity> chilList=this.findByParentIdAndActionType("1106",String.valueOf(entity.getId()));
        if(CollectionUtils.isEmpty(chilList)) return;
        TradeProcessEntity charge=chilList.get(0);
        //收费子交易存在，更新提现订单收费金额
        fundOrderEntity.setChargeAmount(charge.getAmt());
        fundOrderService.update(fundOrderEntity);
        //主交易处于提现成功是继续收费操作
        if(StringUtils.equals("10050030",entity.getProcessState())){
            //查询出账账户
            FundAccountEntity accEntity=fundAccountService.select(charge.getFromAccId());
            FundAccountEntity toEntity=fundAccountService.select(charge.getToAccId());
            FundOrderEntity fundOrderEntityCharge=null;
            try {
                //访问第三方进行收费
                fundOrderEntityCharge =  paySuperByFuiou.chargeAmount(accEntity,toEntity,charge.getAmt(),charge.getOrderNo());
                if(fundOrderEntityCharge != null) {
                    //添加收费交易记录
                    fundWithrawChargeService.add(fundOrderEntity.getOrderNo(), accEntity, fundOrderEntity.getOrderAmount(), fundOrderEntity.getChargeAmount());
                    tradeRecordService.chargeAmount(accEntity,toEntity,fundOrderEntity,fundOrderEntityCharge);
                }
                //修改收费子交易状态
                charge.setRespCode(fundOrderEntityCharge.getRetCode());
                charge.setRespMsg(fundOrderEntityCharge.getRetMessage());
                charge.setProcessState("10050007");//处理完成
                charge.setStatus("10030002");//交易成功

                entity.setProcessState("10050007");//收费成功
                entity.setStatus("10030002");//收费成功
            }catch (Exception e){
                LogUtil.info(this.getClass(),e.getMessage());
                if(fundOrderEntityCharge != null) {
                    charge.setRespCode(fundOrderEntityCharge.getRetCode());
                    charge.setRespMsg(fundOrderEntityCharge.getRetMessage());
                }
                charge.setProcessState("10050032");//费用收取失败
                charge.setStatus("10030003");//交易失败

                entity.setProcessState("10050032");//费用收取失败
                entity.setStatus("10030003");//费用收取失败
            }
            this.updateTradeProcessEntity(charge);
            this.updateTradeProcessEntity(entity);

        }
    }
}
