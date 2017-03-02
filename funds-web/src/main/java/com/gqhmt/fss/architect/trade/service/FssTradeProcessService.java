package com.gqhmt.fss.architect.trade.service;

import com.beust.jcommander.internal.Lists;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.Application;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.loan.RepaymentChildDto;
import com.gqhmt.extServInter.dto.loan.RepaymentResponse;
import com.gqhmt.fss.architect.accounting.service.FssCheckAccountingService;
import com.gqhmt.fss.architect.backplate.entity.FssBackplateEntity;
import com.gqhmt.fss.architect.backplate.service.FssBackplateService;
import com.gqhmt.fss.architect.trade.entity.TradeProcessEntity;
import com.gqhmt.fss.architect.trade.mapper.read.TradeProcessReadMapper;
import com.gqhmt.fss.architect.trade.mapper.write.TradeProcessWriteMapper;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundWithrawChargeService;
import com.gqhmt.funds.architect.customer.entity.BankCardInfoEntity;
import com.gqhmt.funds.architect.customer.entity.CustomerInfoEntity;
import com.gqhmt.funds.architect.customer.service.BankCardInfoService;
import com.gqhmt.funds.architect.customer.service.CustomerInfoService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.pay.core.command.CommandResponse;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.trade.IFundsTrade;
import com.gqhmt.util.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

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

    @Resource
    private CustomerInfoService customerInfoService;

    @Resource
    private BankCardInfoService bankCardInfoService;
    @Resource
    private FssBackplateService fssBackplateService;
    @Resource
    private IFundsTrade fundsTradeImpl;
    @Resource
    private FssCheckAccountingService fssCheckAccountingService;
    @Resource
    private FundTradeService fundTradeService;
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
            if(StringUtils.equals("0",map.get("parentId"))){
                map2.put("parentId","0");
            }else{
                map2.put("parentId",null);
            }
            map2.put("status",map.get("status"));
            map2.put("mobile",map.get("mobile"));
            map2.put("toMobile",map.get("toMobile"));
            map2.put("toCustName", map.get("toCustName"));
            map2.put("custName", map.get("custName"));
            map2.put("tradeType", map.get("tradeType"));
            map2.put("fundType", map.get("fundType"));
            map2.put("processState", map.get("processState"));
            map2.put("memo", map.get("memo"));
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
    /**
     * jhz
     * 查询所有处于处理中的划扣交易
     * @param fundType
     * @return
     */
    public List<TradeProcessEntity> processList(String fundType)throws FssException {
        return tradeProcessReadMapper.repaymentList(fundType);
    }

    /**
     * 根据ParentId 查询子交易
     * @param parentId
     * @return
     */
    public List<TradeProcessEntity> findByParentId(Long parentId) {
        return tradeProcessReadMapper.findByParentId(parentId);
    }

    public int insertSelective(TradeProcessEntity tradeProcess) throws FssException{
        return tradeProcessWriteMapper.insertSelective(tradeProcess);
    }

   public BigDecimal getChargeAmountByParentId(Long parentId){
       BigDecimal chargeAmount=tradeProcessReadMapper.getChargeAmount(parentId);
       return chargeAmount;
   }

    public TradeProcessEntity creatTradeProcess(TradeProcessEntity entity,String bidId,String memo,BigDecimal amt,String actionType,String fundType,String sync,String callBack,String serialNumber,String withHoldType,String contractNo){
        entity.setBidId(bidId);
        entity.setOrderNo(fundOrderService.getOrderNo());
        entity.setSerialNumber(serialNumber);
        entity.setWithHoldType(withHoldType);
        entity.setLoanContractNo(contractNo);
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
    public TradeProcessEntity general(String seqNo,String mchnNo, String tradeType, FundAccountEntity fromAccEntity, FundAccountEntity toAccEntity,boolean autoPass){
        TradeProcessEntity tradeProcessEntity = new TradeProcessEntity();
        tradeProcessEntity.setMchnNo(mchnNo);
        tradeProcessEntity.setSeqNo(seqNo);
        if(StringUtils.isNotEmpty(tradeType)){
            tradeProcessEntity.setTradeTypeParent(tradeType.substring(0,4));
        }
        tradeProcessEntity.setTradeType(tradeType);
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
    public List<TradeProcessEntity> getWithDrawProcess()throws FssException{
        return tradeProcessReadMapper.getWithDrawProcess();
    }
    /**
     * jhz
     * 查询未进行交易的流程
     * @param actionType
     * @return
     */
    public List<TradeProcessEntity> getTradeProcess(String actionType)throws FssException{
        return tradeProcessReadMapper.getTradeProcess(actionType);
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
        List<TradeProcessEntity> chilList=this.findByParentIdAndActionType("1403",String.valueOf(entity.getId()));
        if(CollectionUtils.isEmpty(chilList)) return;
        TradeProcessEntity charge=chilList.get(0);
        //收费子交易存在，更新提现订单收费金额
        fundOrderEntity.setChargeAmount(charge.getAmt());
        fundOrderService.update(fundOrderEntity);
        //主交易处于提现成功是继续收费操作
        if(StringUtils.equals("10170030",entity.getProcessState()) || StringUtils.equals("10170041",entity.getProcessState())){
            //查询出账账户
            FundAccountEntity accEntity=fundAccountService.getFundAccount(Long.valueOf(charge.getFromCustNo()),GlobalConstants.ACCOUNT_TYPE_FREEZE);

            FundAccountEntity toEntity=fundAccountService.select(charge.getToAccId());
            FundOrderEntity fundOrderEntityCharge=null;
            try {
                //访问第三方进行收费
                fundOrderEntityCharge =  paySuperByFuiou.chargeAmount(accEntity,toEntity,charge.getAmt(),charge.getOrderNo());
                if(fundOrderEntityCharge != null) {
                    //添加收费交易记录
                    fundWithrawChargeService.add(fundOrderEntityCharge.getOrderNo(), accEntity, fundOrderEntity.getOrderAmount(), fundOrderEntity.getChargeAmount());
                    tradeRecordService.chargeAmount(accEntity,toEntity,fundOrderEntity,fundOrderEntityCharge);
                }
                //修改收费子交易状态
                charge.setRespCode(fundOrderEntityCharge.getRetCode());
                charge.setRespMsg(fundOrderEntityCharge.getRetMessage());
                charge.setProcessState("10170040");//处理完成
                charge.setStatus("10030002");//交易成功

                entity.setProcessState("10170040");//收费成功
                entity.setStatus("10030002");//收费成功
            }catch (Exception e){
                LogUtil.info(this.getClass(),e.getMessage());
                if(fundOrderEntityCharge != null) {
                    charge.setRespCode(fundOrderEntityCharge.getRetCode());
                    charge.setRespMsg(fundOrderEntityCharge.getRetMessage());
                }
                charge.setProcessState("10170041");//费用收取失败
                charge.setStatus("10030003");//交易失败

                entity.setProcessState("10170041");//费用收取失败
                entity.setStatus("10030003");//费用收取失败
            }
            this.updateTradeProcessEntity(charge);
            this.updateTradeProcessEntity(entity);

        }
    }

    /**
     * jhz
     * 提现或代扣交易拆分
     * @param entity
     * @return
     */
    public List<TradeProcessEntity> moneySplit(TradeProcessEntity entity) throws FssException {
        FundAccountEntity fromAcc=fundAccountService.getFundAccountById(entity.getFromAccId());
        FundAccountEntity toAcc=fundAccountService.getFundAccountById(entity.getToAccId());
        String memo="";
        if(StringUtils.equals("1401",entity.getActionType())){
            memo="充值";
        }else {
            memo="提现";
        }
        //限额
        BigDecimal limitAmount =this.getBankLimit(entity.getActionType(),Long.valueOf(entity.getToCustNo()));//根据cust_id 查询银行限额
        List<TradeProcessEntity> list= Lists.newArrayList();
        BigDecimal bg[] = entity.getAmt().divideAndRemainder(limitAmount);
        int splitCount = bg[0].intValue();
        BigDecimal lastamount = bg[1];
        for (int i = 0; i < splitCount; i++) {
            TradeProcessEntity childProcess = this.general(entity.getSeqNo(),entity.getMchnNo(),entity.getTradeType(),fromAcc,toAcc,true);
            childProcess=this.creatTradeProcess(childProcess,null,memo+"金额为"+limitAmount,limitAmount,entity.getActionType(),entity.getFundType(),entity.getSync(),entity.getCallback(),entity.getSerialNumber(),entity.getWithHoldType(),entity.getLoanContractNo());
            childProcess.setParnetId(entity.getId());
            this.save(childProcess);
            list.add(childProcess);
        }

        if (lastamount.compareTo(BigDecimal.ZERO) > 0) {
            TradeProcessEntity childProcess = this.general(entity.getSeqNo(),entity.getMchnNo(),entity.getTradeType(),fromAcc,toAcc,true);
            childProcess=this.creatTradeProcess(childProcess,null,memo+"金额为"+lastamount,lastamount,entity.getActionType(),entity.getFundType(),entity.getSync(),entity.getCallback(),entity.getSerialNumber(),entity.getWithHoldType(),entity.getLoanContractNo());
            childProcess.setParnetId(entity.getId());
            this.save(childProcess);
            list.add(0,childProcess);
        }
        return list;
    }
     /**
     *
     * @param actionType
     * @param custId
     * @return
     * @throws FssException
     */
    public BigDecimal  getBankLimit(String actionType,Long custId) throws FssException{
        CustomerInfoEntity customerInfoEntity=customerInfoService.getCustomerById(custId);
        if(customerInfoEntity==null){
            throw new FssException("90004027");
        }
        BankCardInfoEntity bankCardInfo=null;
        if(null!=customerInfoEntity.getBankId()&&!"".equals(customerInfoEntity.getBankId())) {
            bankCardInfo = bankCardInfoService.getBankCardInfoById(customerInfoEntity.getBankId());
        }else {
            List<BankCardInfoEntity> bankCardInfos = bankCardInfoService.getBankCardByCustNo(custId.toString());
            if (CollectionUtils.isNotEmpty(bankCardInfos)){
                bankCardInfo=bankCardInfos.get(0);
            }
        }
        if(bankCardInfo==null){
            throw new FssException("90004027");
        }
        int type=0;
        if(StringUtils.equals(actionType,"1401")){//充值
            type=1;
        }else{   //提现
            type=2;
        }
        Application instance = Application.getInstance();
        BigDecimal bankDealamountLimit = instance.getBankDealamountLimit(bankCardInfo.getParentBankId()+type);
        return bankDealamountLimit;
    }

    /**
     * jhz
     * 通过父ID查询条数
     * @param parentId
     * @return
     */
    public int getCountByParentId(Long parentId){
        return tradeProcessReadMapper.getCountByParentId(parentId);
    }
    /**
     * jhz
     * 通过父ID查询已执行条数
     * @param parentId
     * @return
     */
    public int getSuccessCountByParentId(Long parentId){
        return tradeProcessReadMapper.getSuccessCountByParentId(parentId);
    }
    /**
     * jhz
     * 通过父ID查询执行成功金额
     * @param parentId
     * @return
     */
    public BigDecimal getSuccessAmt(Long parentId){
        return tradeProcessReadMapper.getSuccessAmt(parentId);
    }


    /**
     * 修改执行状态
     * @param entity		实体备案
     * @param state
     * TradeResult: 98060001交易成功,98060003交易失败
     */
    public void  updateTradeProcessExecuteState(TradeProcessEntity entity,int state,String respCode) throws  FssException {
        String summary=null;
        String tradeResult=null;
        if(state == 1){
            tradeResult="10030002";//成功
            summary="交易成功";
        }else if(state==3){
            tradeResult="10030004";//中断
            summary=Application.getInstance().getDictName(respCode);
        }else{
            tradeResult="10030003";//失败
            summary=Application.getInstance().getDictName(respCode);
        }
        entity.setStatus(tradeResult);
        entity.setProcessState("10170003");//修改交易状态为已执行
        entity.setRespCode(respCode);
        entity.setRespMsg(summary);
        this.updateTradeProcessEntity(entity);
        //Apply 执行数量更新

        this.checkExecuteCount(entity);
    }
    /**
     *
     * author:jhz
     * time:2016年3月19日
     * function：判断 应执行数量 == 已执行数量,如果相等,执行状态 修改
     * @throws FssException
     *
     */
    public void checkExecuteCount(TradeProcessEntity entity) throws FssException {
        //得到拆分前交易对象
        TradeProcessEntity parentProcess=this.findById(entity.getParnetId());
        //得到拆分总条数
        int count=this.getCountByParentId(entity.getParnetId());
        //得到执行总条数
        int successCount=this.getSuccessCountByParentId(entity.getParnetId());
        //判断 应执行数量 == 已执行数量,如果相等,执行状态 修改
        if(count<=successCount){
            try {
                BigDecimal realTradeAmt=this.getSuccessAmt(entity.getParnetId());

                if(null ==realTradeAmt ||"".equals(realTradeAmt)){
                    realTradeAmt=BigDecimal.ZERO;
                }
                //划扣成功
                String status="";
                if(parentProcess.getAmt().compareTo(realTradeAmt)==0){
                    status="10030002";
                }else if(realTradeAmt.compareTo(BigDecimal.ZERO)==0){//划扣失败
                    status="10030003";
                }else{ //部分成功
                    status="10030006";
                }
//                if(!"10030002".equals(status) && StringUtils.equals("1402",parentProcess.getActionType())) {
//                    //代付失败进行资金解冻
////                    fundsTradeImpl.unFroze(applyEntity.getMchnChild(), applyEntity.getSeqNo(), applyEntity.getBusiType(), String.valueOf(applyEntity.getCustId()), applyEntity.getUserNo(), applyEntity.getTradeAmount().subtract(realTradeAmt), applyEntity.getCustType(),applyEntity.getSeqNo());
//                }
                if("11090001".equals(parentProcess.getTradeType())||"11090005".equals(parentProcess.getTradeType())){
                }else if("11092001".equals(parentProcess.getTradeType())||"11090006".equals(parentProcess.getTradeType())){
                    //借款系统和冠e通抵押权人提现不处理
                }else if("11093001".equals(parentProcess.getTradeType())||"11093002".equals(parentProcess.getTradeType())){
                    //还款代扣
                    this.repaymentProcess(entity,status,realTradeAmt);
                }else if("11090004".equals(parentProcess.getTradeType())){
                }else {

                }
            } catch (FssException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //通过交易类型,回调通知相应交易申请方.  //借款划扣 ,通知 相应划扣记录表..
        }
    }

    /**
     * jhz
     * 还款人划扣修改
     * @throws FssException
     */
    public void repaymentProcess(TradeProcessEntity entity,String status,BigDecimal realAmt)throws FssException{
        //得到拆分前划扣流程
        TradeProcessEntity parentProcess=this.findById(entity.getParnetId());
        //更新划扣流程状态
        parentProcess.setRealTradeAmount(realAmt);
        this.update(parentProcess,status,"10170003",entity.getRespCode(),entity.getRespMsg());
        //得到划扣主流程
        TradeProcessEntity garndProcess=this.findById(parentProcess.getParnetId());
        garndProcess.setRealTradeAmount(realAmt);
        //查询回盘表信息
        FssBackplateEntity fssBackplateEntity=fssBackplateService.selectByMchnAndseqNo(garndProcess.getMchnNo(),garndProcess.getOrderNo());
        if(StringUtils.equals("10030003",status)){//划扣失败
            this.update(garndProcess,status,"10170011",null,null);
        }else{
            this.update(garndProcess,status,"10170010",null,null);
            if(StringUtils.equals("10180002",garndProcess.getWithHoldType())){ //中间人代扣
                // 得到转账流程
                TradeProcessEntity transferProcess= this.findByParentIdAndActionType("1403",garndProcess.getId().toString()).get(0);
                //更新实际转账金额
                transferProcess.setAmt(realAmt);
                this.updateTradeProcessEntity(transferProcess);
                //执行转账操作
                this.transfer(transferProcess,garndProcess);
            }
        }
        //创建或更新回盘表
        if (fssBackplateEntity!=null){
            fssBackplateService.updatebackplate(fssBackplateEntity);
        }else {
            //创建回盘信息
            fssBackplateService.createFssBackplateEntity(garndProcess.getOrderNo(), garndProcess.getMchnNo(), garndProcess.getTradeType());
        }
    }
    public void update(TradeProcessEntity entity,String status,String processStatus,String respCode,String respMsg)throws FssException{
        entity.setStatus(status);
        entity.setProcessState(processStatus);
        entity.setRespCode(respCode);
        entity.setRespMsg(respMsg);
        this.updateTradeProcessEntity(entity);
    }
    /**
     * jhz
     * 核对交易在富有成功失败情况
     * @param entity
     * @throws FssException
     */
    public void checkResult(TradeProcessEntity entity)throws FssException{
        //查询出该笔交易的提现子交易
        TradeProcessEntity withDraw=this.findByParentIdAndActionType(entity.getActionType(),entity.getId().toString()).get(0);
        //查询冻结账户
        FundAccountEntity fromEntity=fundAccountService.getFundAccount(Long.valueOf(withDraw.getFromCustNo()),GlobalConstants.ACCOUNT_TYPE_FREEZE);
        ;
        //查询出账账户
        FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(withDraw.getOrderNo());
        String startTime= DateUtil.dateToString(fundOrderEntity.getCreateTime());
        String repCode=getResult(withDraw,startTime);
        if(StringUtils.isNotEmpty(repCode)){
            //富友成功
            if(StringUtils.equals(repCode,"0000")){
                //创建交易流水
                tradeRecordService.asynNotOrderCommand(fundOrderEntity.getOrderNo(), "success",withDraw.getAmt().toString(),withDraw.getFromCustMobile());

                //提现成功修改提现子交易状态
                withDraw.setProcessState("10170030");//提现成功
                withDraw.setStatus("10030002");//交易成功
                this.updateTradeProcessEntity(withDraw);
                //修改流程表状态
                entity.setProcessState("10170030");//提现成功
                entity.setStatus("10030002");//交易成功
                this.updateTradeProcessEntity(entity);
                //进行收费
                this.charge(entity,fundOrderEntity);
            }else {
                //得到线上账户
                FundAccountEntity account = fundsTradeImpl.getFundAccount(Integer.parseInt(entity.getFromCustNo()), GlobalConstants.ACCOUNT_TYPE_LEND_ON);
                //进行资金解冻
                tradeRecordService.unFrozen(fromEntity,account,entity.getAmt(),1004,null,"提现失败，退回金额"+ entity.getAmt() + "元",BigDecimal.ZERO,entity.getTradeType(),entity.getSeqNo());
                //修改流程表状态
                entity.setProcessState("10170039");//处理完成
                entity.setStatus("10030003");
                this.updateTradeProcessEntity(entity);
            }
        }
    }
    //查询富友，查看该笔交易是成功还是失败
    public String getResult(TradeProcessEntity entity,String startTime) throws FssException {
       //查询出账账户
        FundAccountEntity fromEntity=fundAccountService.select(entity.getFromAccId());


        String endTime=DateUtil.dateToString(new Date());

        if(StringUtils.isEmpty(entity.getOrderNo())) return null;

        //查询富友查看得到该客户提现数据
        List<Map<String,String>> listMap=fssCheckAccountingService.getFuiouTradeResult(fromEntity,startTime,endTime,"PWTX");
        if(CollectionUtils.isEmpty(listMap)){
            return "0001";
        }
        for (Map<String,String> map: listMap ) {
            if(StringUtils.equals(map.get("mchnt_ssn"),entity.getOrderNo())){
                return map.get("txn_rsp_cd");
            }
        }
        return null;
    }

    /**
     * jhz
     * 中间人重新转账
     * @param entity
     * @throws FssException
     */
    public void reTransfer(TradeProcessEntity entity)throws FssException{
        //得到交易主流程
        TradeProcessEntity parentEntity=this.findById(entity.getParnetId());
        //得到出账账户
        FundAccountEntity fromEntity=fundAccountService.select(entity.getFromAccId());
        //起始时间
        String startTime= DateUtil.dateTostring(entity.getCreateTime());
        //终止时间
        String endTime=DateUtil.dateTostring(new Date());
        //查询富友,得到转账集合
        List<Map<String,String>> listMap=this.getFuiouTransfers("PW03",fromEntity,entity.getOrderNo(),startTime,endTime);

        if(CollectionUtils.isEmpty(listMap)){//富友不存在转账记录，重新转账
            //更新订单号
            entity.setOrderNo(fundOrderService.getOrderNo());
            this.updateTradeProcessEntity(entity);
            this.transfer(entity,parentEntity);
        }else {
            for (Map<String, String> map : listMap) {
                if (StringUtils.equals(map.get("mchnt_ssn"), entity.getOrderNo())) {
                    if(StringUtils.equals(map.get("txn_rsp_cd"),"0000")){//富友返回成功
                        //更新转账交易流程状态和主状态.
                       FundOrderEntity fundOrderEntity = fundOrderService.findfundOrder(entity.getOrderNo());
                        fundOrderService.updateOrder(fundOrderEntity,2,"0000","成功");
                        //得到出账账户
                        FundAccountEntity toEntity=fundAccountService.select(entity.getToAccId());
                        //添加流水记录
                        tradeRecordService.transfer(fromEntity,toEntity,entity.getAmt(),1005,fundOrderEntity,8,null,"1005",entity.getTradeType(),entity.getLoanContractNo(),entity.getToAccId() != null ? entity.getToAccId().longValue():0l,null,entity.getToAccId(),entity.getLoanContractNo(),"0");
                        //添加交易记录
                        fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO,entity.getAmt(),1005, "转账成功，资金转出："+entity.getAmt()+" 元",BigDecimal.ZERO);
                        fundTradeService.addFundTrade(toEntity,entity.getAmt(), BigDecimal.ZERO,1005,"转账成功，资金转入："+entity.getAmt()+" 元");

                        entity.setRealTradeAmount(entity.getAmt());
                        this.update(entity,"10030002","10170020","0000","中间人转账成功");
                        //转账成功更新主流程状态
                        parentEntity.setProcessState("10170020");
                        this.updateTradeProcessEntity(parentEntity);
                    }else{ //富友失败重新转账
                        //更新订单号
                        entity.setOrderNo(fundOrderService.getOrderNo());
                        this.updateTradeProcessEntity(entity);
                        //执行转账
                        this.transfer(entity,parentEntity);
                    }
                }
            }
        }
    }
    /**
     * jhz
     *  查询富有转账交易
     * @param tradeType
     * @param entity
     * @param orderNo
     * @param startTime
     * @param endTime
     * @return
     * @throws FssException
     */
    public List<Map<String,String>> getFuiouTransfers(String tradeType,FundAccountEntity entity,String orderNo,String startTime,String endTime)throws  FssException{
        List<Map<String,String>> list = null;
        CommandResponse response=null;
        try{
            response =paySuperByFuiou.tradeQuery(tradeType,entity,orderNo,startTime,endTime,1);
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

    public void transfer(TradeProcessEntity transferEntity,TradeProcessEntity entity)throws FssException{
        try {
            //进行转账
            fundsTradeImpl.transefer(Integer.valueOf(transferEntity.getFromCustNo()), GlobalConstants.ACCOUNT_TYPE_LOAN, Integer.valueOf(transferEntity.getToCustNo()),
                    GlobalConstants.ACCOUNT_TYPE_LOAN, transferEntity.getAmt(), GlobalConstants.ORDER_TRANSFER, transferEntity.getId(), GlobalConstants.BUSINESS_TRANSFER,
                    transferEntity.getTradeType(), transferEntity.getLoanContractNo(), 1005, 3,transferEntity.getOrderNo());
            //转账成功更新转账流程状态
            transferEntity.setRealTradeAmount(transferEntity.getAmt());
            this.update(transferEntity,"10030002","10170020","0000","中间人转账成功");
            //转账成功更新主流程状态
            entity.setProcessState("10170020");
        }catch (Exception e){
            LogUtil.info(this.getClass(),e.getMessage());
            this.update(transferEntity,"10030003","10170021",e.getMessage(),Application.getInstance().getDictName(e.getMessage()));
            //转账失败更新主流程状态
            entity.setProcessState("10170021");
        }
        //更新主流程
        this.updateTradeProcessEntity(entity);
    }
    /**
     * 还款划扣通知
     */
    public RepaymentResponse rePaymentCallBack(String orderNo) throws FssException{
        RepaymentResponse repaymentResponse=new RepaymentResponse();
        List<RepaymentChildDto>repaymentChilds=new ArrayList<>();
        RepaymentChildDto repaymentChild=new RepaymentChildDto();
        TradeProcessEntity entity=this.getTradeProcessByOrderNo(orderNo);
        List<TradeProcessEntity> repaymentlist=this.childTradeProcess(entity.getId());
        if(repaymentlist==null){
            throw new FssException("还款划扣失败");
        }
        List<TradeProcessEntity> relList=this.findByParentIdAndActionType("1401",String.valueOf(entity.getId()));
        TradeProcessEntity repayment=relList.get(0);
        if("10180002".equals(entity.getWithHoldType())){//中间人代扣
            List<TradeProcessEntity> chilList=this.findByParentIdAndActionType("1403",String.valueOf(entity.getId()));
            TradeProcessEntity transfer=chilList.get(0);
            repaymentChild.setMid_cust_id(transfer.getFromCustNo());
            if(StringUtils.equals("10170020",transfer.getProcessState())){//转账成功
                repaymentResponse.setTransfer_resp_code("0000");
                repaymentResponse.setTransfer_resp_msg("中间人转账成功");
            }else{//转账失败
                repaymentResponse.setTransfer_resp_code("9999");
                repaymentResponse.setTransfer_resp_msg(transfer.getRespMsg());
            }
        }
        repaymentChild.setAcc_no(entity.getToCustNo());
        repaymentChild.setAmt(entity.getAmt());
        repaymentChild.setReal_repay_amt(entity.getRealTradeAmount());
        repaymentChild.setAccounting_no(repayment.getOrderNo());
        repaymentChild.setWithHold_type(entity.getWithHoldType());
        repaymentChild.setContract_id(null);
        repaymentChild.setContract_no(entity.getLoanContractNo());
        repaymentChild.setRemark(entity.getMemo());
        repaymentChild.setComplete_time(DateUtil.dateToString(entity.getModifyTime()));
        repaymentChild.setSerial_number(repayment.getSerialNumber());

        repaymentChilds.add(repaymentChild);

        if(!StringUtils.equals("10030003",repayment.getStatus())){
            repaymentResponse.setResp_code("0000");
            repaymentResponse.setResp_msg("成功");
        }else {
            repaymentResponse.setResp_code("9999");
            repaymentResponse.setResp_msg(repayment.getRespMsg());
        }
        repaymentResponse.setRepay_list(repaymentChilds);
        repaymentResponse.setMchn(entity.getMchnNo());
        repaymentResponse.setSeq_no(entity.getSeqNo());
        repaymentResponse.setTrade_type(entity.getTradeType());

        return repaymentResponse;
    }
}
