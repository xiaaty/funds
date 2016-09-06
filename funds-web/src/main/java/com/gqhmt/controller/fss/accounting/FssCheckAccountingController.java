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
import com.gqhmt.funds.architect.order.service.FundOrderService;
import com.gqhmt.util.ReadExcelUtil;
import com.gqhmt.util.exception.ReadExcelErrorException;
import com.gqhmt.util.exception.ReadExcelException;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            fssCheckAccountingService.insertList(enList);
            map.put("code","0000");
            map.put("msg","导入成功！");
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
            map.put("msg","导入失败！");
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
    @AutoPage
    public Object singleTransfer(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map) throws FssException {

        return null;
    }
}