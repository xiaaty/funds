package com.gqhmt.controller.fss.accounting;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.LogUtil;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.fss.architect.accounting.entity.*;
import com.gqhmt.fss.architect.accounting.service.*;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.funds.architect.account.service.FundSequenceService;
import com.gqhmt.funds.architect.order.entity.FundOrderEntity;
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.pay.service.TradeRecordService;
import com.gqhmt.util.ReadExcelUtil;
import com.gqhmt.util.exception.ReadExcelErrorException;
import com.gqhmt.util.exception.ReadExcelException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gqhmt.controller.fss.trade.FssTradeApplyController
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/23 16:46
 * Description:对账控制器
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/23  jhz      1.0     1.0 Version
 */
@Controller
public class FssCheckAccountingController {

    @Resource
    private FssCheckAccountingService fssCheckAccountingService;
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FundOrderService fundOrderService;
    @Resource
    private TradeRecordService tradeRecordService;
    @Resource
    private FundSequenceService fundSequenceService;

    /**
     * jhz
     * 对账列表
     *  10980001：提现交易对账
     *  10980002：提现退票对账
     *  10980003：充值交易对账
     *  10980004:转账交易对账
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/checkAccounting/checkAccountList", method = {RequestMethod.GET, RequestMethod.POST})
    @AutoPage
    public String queryLendPayableList(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map) throws FssException {
        List<FssCheckAccountingEntity> checkAccountingEntityList = fssCheckAccountingService.list(map);
        model.addAttribute("page", checkAccountingEntityList);
        model.put("map", map);

        return "fss/accounting/checkAccounting/checkAccounting_list";
    }

    /**
     * jhz
     * 导入数据
     * @param request
     * @param multipartFile
     * @param type
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/upload/{type}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object importData(HttpServletRequest request,
                              @RequestParam(value="file",required=true)MultipartFile multipartFile,
                              @PathVariable String type) throws FssException {
        int i=0;
        int j=0;
        Map map= Maps.newHashMap();
        String basePath = request.getSession().getServletContext().getRealPath("/")+"upload";
        LogUtil.debug(this.getClass(),basePath);
        ReadExcelUtil<FssCheckAccountingEntity> excelUtil = new ReadExcelUtil<FssCheckAccountingEntity>(basePath,FssCheckAccountingEntity.class);

        String[] columns = null;
        if ("10980001".equals(type)||"10980002".equals(type)){
            //提现和体现退票
            columns = new String[]{"orderNo", "tradeTime","accountingNo", "accountingTime", "amount", "accNo", "accName", "userName", "remark",
                    "status"};
        } else if("10980003".equals(type)){
            //充值
            columns = new String[]{"orderNo", "tradeTime","accountingNo", "accountingTime","rechargeWay", "amount", "accNo", "accName", "userName", "remark",
                    "status"};
        }else if(StringUtils.equals("10980004",type)){
            //转账
            columns = new String[]{"orderNo", "tradeTime","accountingNo", "accountingTime", "amount", "accNo", "accName", "userName","toAccNo", "toAccName",
                    "toUserName", "contractNo","itemNo","remark","status"};
        }
        try {
            //通过反射得到导入的对账列表
            List<FssCheckAccountingEntity> list = excelUtil.getExcelData(multipartFile,columns, 0);
            LogUtil.debug(this.getClass(),list.toString());

            //循环便利集合得到客户表id并添加进对象
            List<FssCheckAccountingEntity> enList= Lists.newArrayList();
            FundAccountEntity fundAccountEntity=null;
            FundAccountEntity toFundAccountEntity=null;
            String accName=null;
            String  toAccName=null;
            Map<String,String> map1=ResourceUtil.list("config.account");
            for (FssCheckAccountingEntity entity: list) {
                if(map1!=null){
                    accName = map1.get(entity.getAccName());
                }
                if(StringUtils.isNotEmpty(accName)){
                    fundAccountEntity= fundAccountService.getFundAccount(accName,1);
                }else {
                    fundAccountEntity = fundAccountService.getFundAccount(entity.getAccName(), 1);
                }
                if(fundAccountEntity!=null) {
                    entity.setCustId(fundAccountEntity.getCustId().toString());
                }
                if(StringUtils.isNotEmpty(entity.getToAccName())){
                    if(map1!=null){
                        toAccName = map1.get(entity.getToAccName());
                    }
                    if(StringUtils.isNotEmpty(toAccName)){
                        toFundAccountEntity= fundAccountService.getFundAccount(toAccName,1);
                    }else {
                        toFundAccountEntity = fundAccountService.getFundAccount(entity.getToAccName(), 1);
                    }
                    if(toFundAccountEntity!=null) {
                        entity.setToCustId(toFundAccountEntity.getCustId().toString());
                    }
                }
                entity=fssCheckAccountingService.createChecking(entity,type);
                enList.add(entity);
            }
            i=enList.size();
            for (FssCheckAccountingEntity entity:enList) {
                try {
                    fssCheckAccountingService.insert(entity);
                    j++;
                }catch (FssException e){
                    continue;
                }
            }
            map.put("code","0000");
            map.put("msg","共有"+i+"条，导入成功"+j+"条");
        } catch (ReadExcelErrorException e) {
            LogUtil.error(this.getClass(),e.getMessage());
            e.printStackTrace();
            map.put("code","0001");
            map.put("msg","导入失败！请检查您的数据模版");
        }catch (ReadExcelException e) {
            LogUtil.error(this.getClass(),e.getMessage());
            map.put("code","0001");
            map.put("msg","导入失败！请检查您的数据模版");
        }catch (Exception e){
            LogUtil.error(this.getClass(),e.getMessage());
            map.put("code","0001");
            map.put("msg","共有"+i+"条，导入成功"+j+"条");
        }
        return map;
    }

    /**
     * jhz
     * 充值/提现调单对账
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/checkAccounting/singleTransfer", method = {RequestMethod.GET, RequestMethod.POST})
    public Object singleTransfer(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map) throws FssException {
       String accNos=null;
        if(StringUtils.isNotEmpty((String)map.get("mobile"))){
            accNos= fundAccountService.getFundsAccountIds((String)map.get("mobile"));
        }
        map.put("accNos", accNos);
        return  new ModelAndView("redirect:/checkAccounting/singleTransfers",map);
    }
    /**
     * jhz
     * 充值/提现调单对账
     * @param request
     * @param model
     * @param map
     * @return
     * @throws FssException
     */

    @RequestMapping(value = "/checkAccounting/singleTransfers", method = {RequestMethod.GET, RequestMethod.POST})
    @AutoPage
    public String singleTransfers(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map) throws FssException {
        List<FundOrderEntity> orderEntities=Lists.newArrayList();
        String accNos=  map.get("accNos");
        List<Long> accIds=Lists.newArrayList();
        if(StringUtils.isNotEmpty(accNos)){
            String[] accNo=accNos.split(",");
            for(int i=0;i<accNo.length;i++){
                if(StringUtils.isNotEmpty(accNo[i])){
                    accIds.add(Long.valueOf(accNo[i]));
                }
            }
        }
        if(accIds.size()>0){
            orderEntities= fundOrderService.findfundOrdesrs(map,accIds);
        }
        model.addAttribute("page", orderEntities);
        model.put("map", map);
        return "fss/accounting/checkAccounting/singleTransfer";
    }
    /**
     * jhz
     * 查询富有
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/checkAccounting/queryForFuiou", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object queryForFuiou(HttpServletRequest request, ModelMap model, String  mobile,String startTime,String endTime) throws FssException{
        Map<String,String> returnMap=Maps.newHashMap();

        List<Map<String,String >> list = fssCheckAccountingService.getfuiouTradeCz(mobile,startTime,endTime);
        LogUtil.info(this.getClass(),"调单核对，获取"+startTime+"-"+endTime+"日期内富友交易订单总数："+list.size());
        int i=0;
        for(Map<String,String > map:list){
            String orderNo = map.get("mchnt_ssn");
            LogUtil.info(this.getClass(),"调单核对："+orderNo);
            int size = fundSequenceService.getSizeByOrderNo(orderNo);
            if(size == 1 ) {
                continue;
            }else if(size > 1){
                FssCheckAccountingEntity entity= fssCheckAccountingService.createChecking(orderNo,map.get("txn_date"),map.get("mchnt_ssn"),map.get("txn_date"),map.get("txn_amt"),null,map.get("out_fuiou_acct_no"),map.get("out_cust_no"),map.get("out_artif_nm"),map.get("remark"),map.get("txn_rsp_cd"),"10980003","10130006","98010001");
                entity.setRechargeWay(map.get("src_tp"));
                try {
                    fssCheckAccountingService.insert(entity);
                }catch (Exception e){
                    LogUtil.error(this.getClass(),e.getMessage());
                    returnMap.put("code","0001");
                    returnMap.put("msg","请您确认是否是第二次对账");
                    return returnMap;
                }
            }else{
                FundOrderEntity orderEntity = fundOrderService.findfundOrder(orderNo);
                if(orderEntity != null && orderEntity.getOrderState() == 2){
                    fundOrderService.updateOrder(orderEntity,3,"0001","交易核对订单无流水记录,改为失败状态");
                }
                tradeRecordService.asynCommand(orderEntity,"0000".equals(map.get("txn_rsp_cd")) ? "success" : "failed");
                i++;
            }
        }
        returnMap.put("code","0000");
        returnMap.put("msg","共有"+i+"条数据重新入账");
        return returnMap;
    }


}