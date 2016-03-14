package com.gqhmt.pay.service.cost.impl;

import com.gqhmt.core.FssException;
import com.gqhmt.core.util.GlobalConstants;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.pay.service.PaySuperByFuiou;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.pay.service.cost.ICost;

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
public class CostImpl  implements ICost{

    @Resource
    private FundAccountService fundAccountService;

    @Resource
    private PaySuperByFuiou paySuperByFuiou;

    @Resource
    private TradeRecordService tradeRecordService;

    private  final Map<String,Integer> map = new ConcurrentHashMap<>();

    private CostImpl(){
        map.put("21992101_10040001",5); //服务费(咨询费)   北京
        map.put("21992101_10040002",8); //               天津
        map.put("21992101_10040003",11); //              上海

        map.put("21992102_10040001",1);//管理费
        map.put("21992102_10040002",6);
        map.put("21992102_10040003",9);

        map.put("21992104_10040001",2); //保证金(增信金)
        map.put("21992104_10040002",2);//天津 7 ,共用北京增信金账户
        map.put("21992104_10040003",2);//上海 10,共用北京增信金账户

        map.put("21992103_10040001",3);  //逆服务费
        map.put("21992103_10040002",3);
        map.put("21992103_10040003",3);



        map.put("21992105_10040001",12);//风险备用金
        map.put("21992105_10040002",12);
        map.put("21992105_10040003",12);

        map.put("21992105_10040001",4);//红包账户
        map.put("21992105_10040002",4);
        map.put("21992105_10040003",4);


    }

    @Override
    public void cost(String loanType, String fundsType, Integer custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException {
        Integer fromCustId = this.map.get(fundsType+"_"+loanType);
        if (fromCustId == null) throw new FssException("");

        FundAccountEntity  toAccountEntity= fundAccountService.getFundAccount(fromCustId, GlobalConstants.ACCOUNT_TYPE_PRIMARY);

        if (toAccountEntity == null) throw new FssException("");        //入账

        FundAccountEntity fromAccountEntity  = fundAccountService.getFundAccount(custId,bustType);

        if (fromAccountEntity == null) throw new FssException("");

        FundOrderEntity fundOrderEntity  = paySuperByFuiou.transerer(fromAccountEntity,toAccountEntity,decimal,GlobalConstants.ORDER_COST,busiId,busiType);

        tradeRecordService.transfer(fromAccountEntity,toAccountEntity,decimal,Integer.parseInt(fundsType),fundOrderEntity);

    }

    @Override
    public void cost(String fundsType, Integer custId, Integer bustType, BigDecimal decimal,Long busiId,Integer busiType) throws FssException {
        this.cost("10040001",fundsType,custId,bustType,decimal,busiId,busiType);
    }


}
