package com.gqhmt.pay.service.cost.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.fss.architect.account.entity.FssAccountEntity;
import com.gqhmt.fss.architect.account.service.FssAccountService;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.exception.CommandParmException;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.cost.ICost;
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

    private  final Map<String,Long> map = new ConcurrentHashMap<>();

    private CostImpl(){
        map.put("10990003_10040001",5l); //服务费(咨询费)   北京
        map.put("10990003_10040002",8l); //               天津
        map.put("10990003_10040003",11l); //              上海

        map.put("10990004_10040001",1l);//账户管理费
        map.put("10990004_10040002",6l);
        map.put("10990004_10040003",9l);
        
        map.put("10990005_10040001",5l);//特殊服务费
        map.put("10990005_10040002",8l);
        map.put("10990005_10040003",11l);
        
        map.put("10990006_10040001",1l);//准时还款保证金
        map.put("10990006_10040002",1l);
        map.put("10990006_10040003",1l);
        
        map.put("10990007_10040001",1l);//实收罚息滞纳金
        map.put("10990007_10040002",6l);
        map.put("10990007_10040003",9l);
        
        map.put("10990008_10040001",1l);//实收逾期还款违约金
        map.put("10990008_10040002",6l);
        map.put("10990008_10040003",9l);
        
        map.put("10990010_10040001",1l);//营业外收入
        map.put("10990010_10040002",6l);
        map.put("10990010_10040003",9l);
        
        map.put("10990011_10040001",1l);//上门费
        map.put("10990011_10040002",6l);
        map.put("10990011_10040003",9l);
        
        map.put("10990012_10040001",1l);//展期费
        map.put("10990012_10040002",6l);
        map.put("10990012_10040003",9l);

        map.put("10990013_10040001",1l);//减免金
        map.put("10990013_10040002",6l);
        map.put("10990013_10040003",9l);

        map.put("10999001_10040001",1l);//应收罚息滞纳金
        map.put("10999001_10040002",6l);
        map.put("10999001_10040003",9l);

        map.put("10999002_10040001",1l);//应收逾期还款违约金
        map.put("10999002_10040002",6l);
        map.put("10999002_10040003",9l);

        map.put("10990006_10040001",2l); //保证金(增信金)
        map.put("10990006_10040002",2l);//天津 7 ,共用北京增信金账户
        map.put("10990006_10040003",2l);//上海 10,共用北京增信金账户
        
        map.put("10990003_10040001",2l); //退保证金(增信金)
        map.put("10990003_10040002",2l);//天津 7 ,共用北京增信金账户
        map.put("10990003_10040003",2l);//上海 10,共用北京增信金账户

        map.put("21992103_10040001",3l);  //逆服务费
        map.put("21992103_10040002",3l);
        map.put("21992103_10040003",3l);



        map.put("21992105_10040001",12l);//风险备用金
        map.put("21992105_10040002",12l);
        map.put("21992105_10040003",12l);

        map.put("21992105_10040001",4l);//红包账户
        map.put("21992105_10040002",4l);
        map.put("21992105_10040003",4l);


    }

    @Override
    public void cost(String loanType, String fundsType, Long custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException {
        FundAccountEntity fromAccountEntity  = fundAccountService.getFundAccount(custId,bustType);

        this.cost(fromAccountEntity,decimal,fundsType,busiId,busiType,loanType,1);




    }

    @Override
    public FundOrderEntity cost(String loanType, String fundsType, String accNo, BigDecimal decimal, Long busiId, Integer busiType) throws FssException {
        FssAccountEntity accountEntity  = this.fssAccountService.getFssAccountByAccNo(accNo);
        if (accountEntity == null ){
            throw new CommandParmException("90004006");
        }
        int accType = accountEntity.getAccType();
        int bustType = this.tradeRecordService.parseBusinessType(accType);
        FundAccountEntity fromAccountEntity  = fundAccountService.getFundAccount(accountEntity.getCustId(),bustType);

        return this.cost(fromAccountEntity,decimal,fundsType,busiId,busiType,loanType,1);


    }

    @Override
    public void cost(String fundsType, Long custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException {
        this.cost("10040001",fundsType,custId,bustType,decimal,busiId,busiType);
    }
    @Override
    public void costReturn(String loanType, String fundsType, Long custId, Integer bustType, BigDecimal decimal, Long busiId, Integer busiType) throws FssException {
        FundAccountEntity fromAccountEntity  = fundAccountService.getFundAccount(custId,bustType);

        this.cost(fromAccountEntity,decimal,fundsType,busiId,busiType,loanType,2);
    }

    @Override
    public void costReturn(String loanType, String fundsType, String accNo, BigDecimal decimal, Long busiId, Integer busiType) {
    }


    private FundOrderEntity cost(FundAccountEntity fromAccountEntity,BigDecimal decimal,String fundsType,Long busiId,Integer busiType,String loanType,int type) throws FssException {

        Long toCustId = this.map.get(fundsType+"_"+loanType);
        if (toCustId == null) throw new FssException("");

        FundAccountEntity  toAccountEntity= fundAccountService.getFundAccount(toCustId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);

        if (fromAccountEntity == null) throw new FssException("90004006");
        FundOrderEntity fundOrderEntity = null;
        if(type == 1){
            fundOrderEntity  = paySuperByFuiou.transerer(fromAccountEntity,toAccountEntity,decimal,GlobalConstants.ORDER_COST,busiId,busiType);
            tradeRecordService.transfer(fromAccountEntity,toAccountEntity,decimal,Integer.parseInt(fundsType),fundOrderEntity);
        }else{
            fundOrderEntity  = paySuperByFuiou.transerer(toAccountEntity,fromAccountEntity,decimal,GlobalConstants.ORDER_COST_RETURN,busiId,busiType);
            tradeRecordService.transfer(toAccountEntity,fromAccountEntity,decimal,Integer.parseInt(fundsType),fundOrderEntity);
        }


        return  fundOrderEntity;
    }





}
