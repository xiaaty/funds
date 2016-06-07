package com.gqhmt.controller.fss.customer;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.card.entiry.FssCardBinEntity;
import com.gqhmt.fss.architect.card.service.FssCardBinService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
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
 * Create at:   2016/5/31
 * Description:还款划扣
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/5/31  jhz     1.0     1.0 Version
 */
@Controller
public class FssCardBinController {
    @Resource
    private FssCardBinService fssCardBinService;

    /**
     * 展示cardBin list
     * @param
     * @return
     */
    @RequestMapping(value = "/fss/customer/cardBinList",method = {RequestMethod.GET,RequestMethod.POST})
    @AutoPage
    public Object cardBinList(ModelMap model, FssCardBinEntity fssCardBinEntity) throws FssException{
       List<FssCardBinEntity> list= fssCardBinService.findList(fssCardBinEntity);
        model.addAttribute("page", list);
        model.addAttribute("cardBin", fssCardBinEntity);
        return "sys/workAssist/cardBin/cardBin_list";
    }
    /**
     * 跳转到添加cardBin页面
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fss/customer/toAddCardBin",method = {RequestMethod.GET,RequestMethod.POST})
    public Object toAddCardBin(HttpServletRequest request, ModelMap model) throws FssException {

        return "sys/workAssist/cardBin/cardBin_add";
    }


    /**
     * 保存银行卡卡bin信息
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/fss/customer/saveCardBin",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object saveCardBin(FssCardBinEntity fssCardBinEntity){
        Date date=new Date();
        fssCardBinEntity.setCreateTime(date);//创建日期
        fssCardBinEntity.setModifyTime(date);//修改日期
        Map<String, String> map = new HashMap<String, String>();
        try {
            fssCardBinService.insert(fssCardBinEntity);
            map.put("code", "0000");
            map.put("message", "success");
        } catch (Exception e) {//保存失败
            e.printStackTrace();
            map.put("code", "0001");
            map.put("message", "error");
        }
        return map;
    }
    /**
     * 跳转到修改cardBin页面
     * @param request
     * @param model
     * @return
     * @throws FssException
     */
    @RequestMapping(value = "/fss/customer/toUpdateCardBin/{id}",method = {RequestMethod.GET,RequestMethod.POST})
    public Object toUpdateCardBin(HttpServletRequest request,@PathVariable Long id,ModelMap model) throws FssException {
        FssCardBinEntity fssCardBinEntity=fssCardBinService.selectedById(id);
        model.addAttribute("cardBin",fssCardBinEntity);
        return "sys/workAssist/cardBin/cardBin_update";
    }


    /**
     * 修改银行卡卡bin信息
     * @param
     * @param
     * @return
     */
    @RequestMapping(value = "/fss/customer/updateCardBin",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object updateCardBin(FssCardBinEntity fssCardBinEntity){
        Date date=new Date();
        fssCardBinEntity.setModifyTime(date);//修改日期
        Map<String, String> map = new HashMap<String, String>();
        try {
            fssCardBinService.update(fssCardBinEntity);
            map.put("code", "0000");
            map.put("message", "success");
        } catch (Exception e) {//修改失败
            e.printStackTrace();
            map.put("code", "0001");
            map.put("message", "error");
        }
        return map;
    }


}
