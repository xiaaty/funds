package com.gqhmt.fss.pay.fuiou.command;


import com.gqhmt.fss.pay.core.command.AbstractThirdpartyCommand;
import com.gqhmt.fss.pay.core.command.ThirdpartyCommand;
import com.gqhmt.fss.pay.core.configer.Config;


/**
 * Created by yuyonf on 15/3/29.
 */
public class FuiouCommand extends AbstractThirdpartyCommand implements ThirdpartyCommand {
    @Override
    public Config getConfig() {
        return null;
    }

    @Override
    public String api(String apiName, String keyName, Object... obj) {
        return null;
    }
////    private final Config config = ConfigFactory.getConfigFactory().getConfig(PayChannel.PAY_CHANNEL_FUIOU);
//
//    @Override
//    public CommandResponse command(FundOrderEntity fundOrderEntity, Object... object) {
//        boolean isConnection = config.isConnection();
//        CommandResponse response=null;
//        if(!isConnection){
//            response = new CommandResponse();
//            response.setCode("0000");
//            response.setMsg("成功");
//            return response;
//        }
//
//
//        String apiName = "";
//        if(CommandEnum.AccountCommand.ACCOUNT_CREATE == commandEnum){
//            apiName = "reg.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.FundsCommand.FUNDS_WITHHOLDING == commandEnum){
//            apiName = "wtrecharge.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.TenderCommand.TENDER_BID == commandEnum){
//            apiName = "preAuth.action";
//            return excute(apiName,fundOrderEntity,object);
////            response = excute(apiName,fundOrderEntity,object);
////            if(response != null && response.getCode().equals("0000")){
////                FuiouPreauth fuiouPreauth = new FuiouPreauth();
////                FundAccountEntity entity = (FundAccountEntity)object[0];
////                FundAccountEntity toEntity = (FundAccountEntity)object[4];
////                String  loan = (String)object[1];
////                fuiouPreauth.setAccountId(entity.getId());
////                Double amt = (Double)object[2];
////                fuiouPreauth.setAmount((new BigDecimal(amt).multiply(new BigDecimal(100))).longValue());
////                fuiouPreauth.setSourceId(Integer.parseInt(loan));
////                fuiouPreauth.setType(com.gq.funds.util.GlobalConstants.ORDER_BID);
////                fuiouPreauth.setUserName(entity.getUserName());
////                fuiouPreauth.setState(1);
////                fuiouPreauth.setUseAmount(0l);
////                fuiouPreauth.setToUserName(toEntity.getUserName());
////                fuiouPreauth.setContractNo((String)response.getMap().get("contract_no"));
////                fuiouService.saveOrUpdate(fuiouPreauth);
//////            }
////            return response;
//        }else if(CommandEnum.TenderCommand.TENDER_SETTLE == commandEnum){
//            FundAccountEntity entity =(FundAccountEntity) object[0];
//            response = new CommandResponse();
////            fuiouService.uploadFile(fundOrderEntity.getOrderNo(),"PW03");        //
//            response.setCode("0002");
//            response.setMsg("已ftp上传到第三方，待交易结果结果返回");
//            return response;
//
//        }else if(CommandEnum.TenderCommand.TENDER_SETTLE_UNFROZEN == commandEnum){
//            FundAccountEntity entity =(FundAccountEntity) object[0];
//            response = new CommandResponse();
////            fuiouService.uploadFile(fundOrderEntity.getOrderNo(),"PWJD");        //
//            response.setCode("0002");
//            response.setMsg("已ftp上传到第三方，待交易结果结果返回");
//            return response;
//        }else if(CommandEnum.TenderCommand.TENDER_REPAY == commandEnum){
//            FundAccountEntity entity =(FundAccountEntity) object[0];
//            response = new CommandResponse();
////            fuiouService.uploadFile(fundOrderEntity.getOrderNo(),"PW03");        //
//            response.setCode("0002");
//            response.setMsg("已ftp上传到第三方，待交易结果结果返回");
//            return response;
//        }else if(CommandEnum.TenderCommand.TENDER_REPAY_FORZEN == commandEnum){
//            FundAccountEntity entity =(FundAccountEntity) object[0];
////            fuiouService.uploadFile(fundOrderEntity.getOrderNo(),"PWDJ");        //
//            response.setCode("0002");
//            response.setMsg("已ftp上传到第三方，待交易结果结果返回");
//            return response;
//        }else if(CommandEnum.FundsCommand.FUNDS_AGENT_WITHDRAW == commandEnum){
//            apiName = "wtwithdraw.action";
//            return excute(apiName,fundOrderEntity,object);
//           // apiName = "freeze.action";
//        }else if(CommandEnum.FundsCommand.FUNDS_TRANSFER == commandEnum){
//            apiName = "transferBu.action";
//            return  excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.FundsCommand.FUNDS_FREEZE == commandEnum){//冻结
//            apiName  = "freeze.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.FundsCommand.FUNDS_UNFREEZE == commandEnum){//解冻
//            apiName  = "unFreeze.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.FundsCommand.FUNDS_BALANCE == commandEnum){
//            apiName="BalanceAction.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if( CommandEnum.TenderCommand.TENDER_ABORT == commandEnum){
//            apiName="preAuthCancel.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else  if (CommandEnum.FundsCommand.FUNDS_WITHDRAW_CHARGE_AMOUNT == commandEnum){
//            apiName="transferBmu.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.AccountCommand.ACCOUNT_UPDATE_CARD == commandEnum){
//            apiName = "changeCard.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.AccountCommand.ACCOUNT_SET_MMS == commandEnum){
//            apiName = "setSms.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.AccountCommand.ACCOUNT_DROP_USER == commandEnum){
//            apiName = "cancelUserReq.action";
//            return excute(apiName,fundOrderEntity,object);
//        }else if(CommandEnum.FundsCommand.FUNDS_CASHWITHSETREQ == commandEnum){
//            apiName = "cashWithSetReq.action";
//            return excute(apiName,fundOrderEntity,object);
//        }
//        response = new CommandResponse();
//
//        if("".equals(apiName)){
//            response.setCode("0000");
//            response.setMsg("成功");
//            return response;
//        }
//        return response;
//    }
//
//
//
//
//    public CommandResponse excute(String apiName,FundOrderEntity fundOrderEntity,Object... object){
//        CommandResponse response = new CommandResponse();
//        Map<String,String> jsonMap = new HashMap<>();
//        object = addParam(fundOrderEntity,object);
//        super.parsePublic(apiName,jsonMap,object);
//        super.parseAPI(apiName, jsonMap, object);
//        Set<String> keySet  = jsonMap.keySet();
//        List<String> keyList = new ArrayList<>();
//        for(String tmp:keySet){
//            keyList.add(tmp);
//        }
//
//        Collections.sort(keyList);
//
//        String keySign = "";
//        for(String key:keyList){
//            String value = jsonMap.get(key);
//            String noSign = (String)config.getValue("api."+apiName+"."+key+".noSign");
//            if(noSign != null && "true".equals(noSign)){
//                continue;
//            }
//            if(value == null || "null".equals(value)) value = "";
//            keySign += value + "|";
//        }
//
//        keySign = keySign.substring(0,keySign.length()-1);
//
//        String signature = SecurityUtils.sign(keySign);
//        jsonMap.put("signature",signature);
//
//        String url = (String)config.getValue("api."+apiName+".url");
//        if(url == null){
//            url = config.getURL();
//        }
//
//        Map resultMap = ConnectionFuiou.send(url+apiName,jsonMap);//ConnectionDaqian.send(config.getURL(), jsonMap);
//
//        String resultCode = (String)resultMap.get("resp_code");
//        response.setMap(resultMap);
//        if(resultMap == null){
//            response.setCode("9900");
//            response.setThirdReturnCode(resultCode);
//            response.setMsg("连接富友失败");
//            return response;
//        }
//
//        if("0000".equals(resultCode) || "5343".equals(resultCode)){
//            response.setCode("0000");
//            response.setThirdReturnCode(resultCode);
//            response.setMsg("成功");
//        }else if("0001".equals(resultCode)){
//            response.setCode("0001");
//            response.setThirdReturnCode(resultCode);
//            response.setMsg("需要短信确认");
//        }else if("3201".equals(resultCode) || (resultCode!= null && resultCode.length() == 4 && "98".equals(resultCode.substring(0, 2)))){
//            response.setCode("0009");
//            response.setThirdReturnCode(resultCode);
//            response.setMsg("富友内部操作错误，需手动核对");
//        }else{
//            response.setCode("0100");
//            response.setThirdReturnCode(resultCode);
//            String msg = GlobalConstants.fuiouRezultCodeMap.get(resultCode);
//            response.setMsg("富友返回："+msg==null?"未知错误"+resultCode:msg );
//        }
//        return response;
//    }
//
//    @Override
//    public Config getConfig() {
//        return config;
//    }
//
//    @Override
//    public String api(String apiName, String keyName, Object... obj) {
//        return null;
//    }
}
