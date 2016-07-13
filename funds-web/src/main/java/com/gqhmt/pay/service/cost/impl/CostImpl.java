package com.gqhmt.pay.service.cost.impl;

import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.extServInter.dto.cost.CostDto;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.fss.architect.trade.entity.FssChargeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssChargeRecordService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.trade.service.FundTradeService;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.cost.ICost;
import com.gqhmt.pay.service.trade.impl.FundsTradeImpl;
import com.gqhmt.util.ThirdPartyType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.pay.service.cost.impl.Cost
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/11 10:02
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/11  于泳      1.0     1.0 Version
 */
@Service
public class CostImpl  implements ICost{

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private TradeRecordService tradeRecordService;

    @Resource
    private FssAccountService fssAccountService;
    @Resource
    private FundSequenceService fundSequenceService;
    @Resource
    private FundTradeService fundTradeService;
    @Resource
    private FundsTradeImpl fundsTradeImpl;
    @Resource
    private FssChargeRecordService fssChargeRecordService;
    private  final Map<String,Long> map = new ConcurrentHashMap<>();

    private CostImpl(){
        map.put("10990003_10040001",5l); //服务费(咨询费)   北京
        map.put("10990003_10040002",8l); //               天津
        map.put("10990003_10040003",11l); //              上海
        map.put("10990003_10040099",5l); //             其他城市

        map.put("10990004_10040001",1l);//账户管理费
        map.put("10990004_10040002",6l);
        map.put("10990004_10040003",9l);
        map.put("10990004_10040099",1l);
        
        map.put("10990005_10040001",5l);//特殊服务费
        map.put("10990005_10040002",8l);
        map.put("10990005_10040003",11l);
        map.put("10990005_10040099",5l);
        
        map.put("10990006_10040001",2l);//准时还款保证金
        map.put("10990006_10040002",2l);
        map.put("10990006_10040003",2l);
        map.put("10990006_10040099",2l);
        
        map.put("10990007_10040001",1l);//实收罚息滞纳金
        map.put("10990007_10040002",6l);
        map.put("10990007_10040003",9l);
        map.put("10990007_10040099",1l);
        
        map.put("10990008_10040001",1l);//实收逾期还款违约金
        map.put("10990008_10040002",6l);
        map.put("10990008_10040003",9l);
        map.put("10990008_10040099",1l);
        
        map.put("10990010_10040001",1l);//营业外收入
        map.put("10990010_10040002",6l);
        map.put("10990010_10040003",9l);
        map.put("10990010_10040099",1l);
        
        map.put("10990011_10040001",1l);//上门费
        map.put("10990011_10040002",6l);
        map.put("10990011_10040003",9l);
        map.put("10990011_10040099",1l);
        
        map.put("10990012_10040001",1l);//展期费
        map.put("10990012_10040002",6l);
        map.put("10990012_10040003",9l);
        map.put("10990012_10040099",1l);

        map.put("10990013_10040001",1l);//减免金
        map.put("10990013_10040002",6l);
        map.put("10990013_10040003",9l);
        map.put("10990013_10040099",1l);

        map.put("10999001_10040001",1l);//应收罚息滞纳金
        map.put("10999001_10040002",6l);
        map.put("10999001_10040003",9l);
        map.put("10999001_10040099",1l);

        map.put("10999002_10040001",1l);//应收逾期还款违约金
        map.put("10999002_10040002",6l);
        map.put("10999002_10040003",9l);
        map.put("10999002_10040099",1l);

        //冠E通收费交易类型映射公司账户类型
        map.put("11060001_10040001",5l);//提现手续费
        map.put("11060001_10040002",8l);
        map.put("11060001_10040003",11l);
        map.put("11060001_10040099",5l);

        map.put("11060002_10040001",1l);//账户管理费
        map.put("11060002_10040002",6l);
        map.put("11060002_10040003",9l);
        map.put("11060002_10040099",1l);

        map.put("11060003_10040001",5l);  //服务费
        map.put("11060003_10040002",8l);
        map.put("11060003_10040003",11l);
        map.put("11060003_10040099",5l);

        map.put("11060004_10040001",5l);  //咨询费
        map.put("11060004_10040002",8l);
        map.put("11060004_10040003",11l);
        map.put("11060004_10040099",5l);

        map.put("11060005_10040001",2l);  //收风险保证金
        map.put("11060005_10040002",2l);
        map.put("11060005_10040003",2l);
        map.put("11060005_10040099",2l);

        map.put("11060006_10040001",2l);  //退风险保证金
        map.put("11060006_10040002",2l);
        map.put("11060006_10040003",2l);
        map.put("11060006_10040099",2l);

        map.put("11060007_10040001",3l);  //逆服务费（补差额从公司3账户出）
        map.put("11060007_10040002",3l);
        map.put("11060007_10040003",3l);
        map.put("11060007_10040099",3l);

        map.put("11060008_10040001",1l);  //逆服务费（垫付利息）
        map.put("11060008_10040002",6l);
        map.put("11060008_10040003",9l);
        map.put("11060008_10040099",1l);

//代偿11070001、11070002、11070003、11070004
        map.put("11070001_10040001",3l);  //借款人逾期代偿
        map.put("11070001_10040002",3l);
        map.put("11070001_10040003",3l);
        map.put("11070001_10040099",3l);

        map.put("11070002_10040001",3l);  //借款人逾期代偿资金退回
        map.put("11070002_10040002",3l);
        map.put("11070002_10040003",3l);
        map.put("11070002_10040099",3l);

        map.put("11070003_10040001",3l);  //委托出借人代偿
        map.put("11070003_10040002",3l);
        map.put("11070003_10040003",3l);
        map.put("11070003_10040099",3l);

        map.put("11070004_10040001",3l);  //委托出借代偿退回
        map.put("11070004_10040002",3l);
        map.put("11070004_10040003",3l);
        map.put("11070004_10040099",3l);
//红包返现劵返现
        map.put("11130001_10040001",4l);  //web返现红包入账
        map.put("11130001_10040002",4l);
        map.put("11130001_10040003",4l);
        map.put("11130001_10040099",4l);
    }

    @Override
    public void cost(String loanType, String fundsType, Long custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType,String contractNo) throws FssException {
        FundAccountEntity fromAccountEntity  = fundAccountService.getFundAccount(custId,bustType);
        this.cost(fromAccountEntity,decimal,fundsType,busiId,busiType,loanType,contractNo);
    }

    @Override
    public FundOrderEntity cost(String loanType, String fundsType, String accNo, BigDecimal decimal, Long busiId, Integer busiType,String contractNo) throws FssException {
        FssAccountEntity accountEntity  = this.fssAccountService.getFssAccountByAccNo(accNo);
        if (accountEntity == null ){
            throw new CommandParmException("90004006");
        }
        int accType = accountEntity.getAccType();
        int bustType = this.tradeRecordService.parseBusinessType(accType);
        FundAccountEntity fromAccountEntity  = fundAccountService.getFundAccount(accountEntity.getCustId(),bustType);
        return this.cost(fromAccountEntity,decimal,fundsType,busiId,busiType,loanType,contractNo);
    }

    @Override
    public void cost(String fundsType, Long custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException {
        this.cost("10040001",fundsType,custId,bustType,decimal,busiId,busiType,null);
    }
    private FundOrderEntity cost(FundAccountEntity fromAccountEntity,BigDecimal decimal,String fundsType,Long busiId,Integer busiType,String loanType,String contractNo) throws FssException {
        Long toCustId = this.map.get(fundsType+"_"+loanType);
        if (toCustId == null) throw new FssException("90002007");
        FundAccountEntity  toAccountEntity= fundAccountService.getFundAccount(toCustId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        if (fromAccountEntity == null) throw new FssException("90004006");
        FundOrderEntity fundOrderEntity = paySuperByFuiou.transerer(fromAccountEntity,toAccountEntity,decimal,GlobalConstants.ORDER_COST,busiId,busiType,fundsType.substring(0,3),fundsType,null,null,fromAccountEntity.getCustId()==null?null:fromAccountEntity.getCustId(),contractNo);
        tradeRecordService.transfer(fromAccountEntity,toAccountEntity,decimal,Integer.parseInt(fundsType),fundOrderEntity);
        return  fundOrderEntity;
    }

    @Override
    public FundOrderEntity costReturn(String loanType, String fundsType, String accNo, BigDecimal decimal, Long busiId, Integer busiType,String contractNo) throws FssException {
        FssAccountEntity accountEntity  = this.fssAccountService.getFssAccountByAccNo(accNo);
        if (accountEntity == null ){
            throw new CommandParmException("90004006");
        }
        int accType = accountEntity.getAccType();
        int bustType = this.tradeRecordService.parseBusinessType(accType);
        FundAccountEntity toAccountEntity  = fundAccountService.getFundAccount(accountEntity.getCustId(),bustType);

        return this.costReturn(toAccountEntity,decimal,fundsType,busiId,busiType,loanType,contractNo);

    }

    public FundOrderEntity costReturn(String fundsType, Long custId, Integer bustType, BigDecimal decimal, Long busiId, Integer busiType) throws FssException {
//        return this.costReturn("10040001",fundsType,custId,bustType,decimal,busiId,busiType);

        return  null;
    }


    private FundOrderEntity costReturn(FundAccountEntity toAccountEntity,BigDecimal decimal,String fundsType,Long busiId,Integer busiType,String loanType,String contractNo) throws FssException {
        Long fromCustId = this.map.get(fundsType+"_"+loanType);
        if (fromCustId == null) throw new FssException("90004006");
        FundAccountEntity  fromAccountEntity= fundAccountService.getFundAccount(fromCustId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);
        if (fromAccountEntity == null) throw new FssException("90004006");
        FundOrderEntity fundOrderEntity = paySuperByFuiou.transerer(fromAccountEntity,toAccountEntity,decimal,GlobalConstants.ORDER_COST_RETURN,busiId,busiType,fundsType.substring(0,3),fundsType,null,null,null,contractNo);
        tradeRecordService.transfer(fromAccountEntity,toAccountEntity,decimal,Integer.parseInt(fundsType),fundOrderEntity);
        return  fundOrderEntity;
    }

    /**
     * 费用收取
     * @param platform
     * @param trade_type
     * @param cust_no
     * @param busi_type
     * @param amt
     * @param accounts_type
     * @return
     * @throws FssException
     */
    public boolean charge(String platform, String trade_type,Integer cust_no,String busi_type,BigDecimal amt,String accounts_type) throws FssException{
//        this.cost(platform, trade_type, Long.valueOf(cust_no), Integer.valueOf(busi_type),amt,Long.valueOf(),Integer.valueOf(GlobalConstants.ORDER_COST));
        return true;
    }

    /**
     * 代偿、红包、费用等公共接口
     * @param trade_type
     * @param cust_id
     * @param busi_type
     * @param amt
     * @param busi_no
     * @param platform
     * @param accounts_type
     * @param seqNo
     * @param memo
     * @return
     * @throws FssException
     */
    public boolean compensation(String trade_type,Integer cust_id,Integer busi_type,BigDecimal amt,Long busi_no,String platform,String accounts_type,String seqNo,String memo) throws FssException{
        FundAccountEntity fromEntity=null;
        FundAccountEntity toEntity=null;
        String loanType=null;
        if(platform==null || "".equals(platform)){
            loanType="10040001";//默认交易平台为北京
        }else{
            loanType=platform;
        }
        //代偿、红包
        Long pubCustId = this.map.get(trade_type+"_"+loanType);
        if(pubCustId == null) throw new FssException("90002001");
        if(pubCustId.intValue() == cust_id.intValue()){
            throw  new FssException("90004017");
        }
        FundAccountEntity  publicAccount = fundAccountService.getFundAccount(pubCustId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);//对公账户
        FundAccountEntity  personalAccount = fundsTradeImpl.getFundAccount(cust_id,busi_type);//个人账户
        //判断是从对公账户转入到个人账户还是从个人账户转入对公账户
        if("11070002".equals(trade_type) || "11070004".equals(trade_type) || "11060001".equals(trade_type) || "11060002".equals(trade_type) || "11060003".equals(trade_type) || "11060004".equals(trade_type) || "11060005".equals(trade_type)){//借款人逾期代偿资金退回、委托出借代偿退回及费用收取
            fromEntity=personalAccount;
            toEntity=publicAccount;
        }else {//借款人逾期代偿、委托出借人代偿、web返现红包入账、wap返现红包入账、安卓返现红包入账、ios返现红包入账、微信返现红包入账、逆服务费
            fromEntity=publicAccount;
            toEntity=personalAccount;
        }
        FundOrderEntity fundOrderEntity=null;
        FssChargeRecordEntity chargeRecordEntity=fssChargeRecordService.addChargeRecord(fromEntity,toEntity,amt,loanType,String.valueOf(busi_type),trade_type,seqNo,String.valueOf(busi_no),String.valueOf(fromEntity.getBusiType()),String.valueOf(toEntity.getBusiType()),memo);
        try{
            this.hasEnoughBanlance(fromEntity,amt);
            Long lendNo=null;
            Long loanNo=null;
            if(busi_type==1){//借款账户
                loanNo= busi_no;//投标，满标，回款，等等对应借款合同编号
            }else if(busi_type==2){//线下出借账户
                lendNo=busi_no;
            }else if(busi_type==96){//应付账户（出借）
                lendNo=busi_no;
            }
            //第三方交易
//            final String newOrderType,final String tradeType,final String lendNo,final String toLendNo,final Long loanCustId,final String loanNo
            fundOrderEntity = this.paySuperByFuiou.transerer(fromEntity,toEntity,amt,3,busi_no,GlobalConstants.ORDER_TRANSFER,trade_type.substring(0,3),trade_type,lendNo==null?null:String.valueOf(lendNo),null,null,loanNo==null?null:String.valueOf(loanNo));
            //资金处理
            fundSequenceService.transfer(fromEntity,toEntity,fundOrderEntity.getOrderAmount(),3,4014,"资金代偿", ThirdPartyType.FUIOU,fundOrderEntity);
            //fssChargeRecordService.updateChargeRecord(chargeRecordEntity,fundOrderEntity.getOrderNo(),"10080002");
        }catch (Exception e){
            //fssChargeRecordService.updateChargeRecord(chargeRecordEntity,null,"10080010");
        }
        //添加交易记录
        fundTradeService.addFundTrade(fromEntity, BigDecimal.ZERO,fundOrderEntity.getChargeAmount(),4014, "资金转出",BigDecimal.ZERO);
        fundTradeService.addFundTrade(toEntity,fundOrderEntity.getChargeAmount(), BigDecimal.ZERO,4015,"资金转入");
        return true;
    }

    /**
     * 判余额是否充足
     * @param entity
     * @param amount
     * @throws CommandParmException
     */
    private void hasEnoughBanlance(FundAccountEntity entity, BigDecimal amount) throws CommandParmException {
        BigDecimal bigDecimal = entity.getAmount();
        if (bigDecimal.compareTo(amount) < 0) {
            throw new CommandParmException("90004007");
        }
    }

}
