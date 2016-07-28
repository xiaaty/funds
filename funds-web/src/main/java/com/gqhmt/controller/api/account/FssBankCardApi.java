package com.gqhmt.controller.api.account;

import com.gqhmt.core.util.LogUtil;
import com.gqhmt.extServInter.dto.QueryListResponse;
import com.gqhmt.extServInter.dto.Response;
import com.gqhmt.extServInter.dto.account.*;
import com.gqhmt.extServInter.service.account.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/7/20
 * Description:银行卡
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/7/20  jhz     1.0     1.0 Version
 */
@RestController
@RequestMapping(value = "/api")
public class FssBankCardApi {

    @Resource
    private IChangeBankCardAccount changeBankCardAccountImpl;
    @Resource
    private IChangeBankCardById changeBankCardByIdImpl;
    @Resource
    private IAddBankCardInfo addBankCardInfoImpl;
    @Resource
    private IChangeBankCardAnotherById changeBankCardAnotherByIdImpl;
    @Resource
    private IFindBankCardAnotherById findBankCardAnotherByIdImpl;
    /**
     *
     * author:jhz
     * time:2016年2月22日
     * function：变更银行卡(添加新的银行卡进行变更)
     */
    @RequestMapping(value = "/changeBankCard",method = {RequestMethod.GET,RequestMethod.POST})
    public Object changeBankCard(UpdateBankCardDto changeBankCardDto){
        Response response= null;
        try {
            response = changeBankCardAccountImpl.execute(changeBankCardDto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }
    /**
     *
     * author:jhz
     * time:2016年7月21日
     * function：变更银行卡（传银行卡id进行变更）
     */
    @RequestMapping(value = "/changeBankCardByCardId",method = {RequestMethod.GET,RequestMethod.POST})
    public Object changeBankCardById(UpdateBankCardByIdDto updateBankCardByIdDto){
        Response response= null;
        try {
            response = changeBankCardByIdImpl.execute(updateBankCardByIdDto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }
    /**
     *
     * author:jhz
     * time:2016年7月20日
     * function：添加银行卡
     */
    @RequestMapping(value = "/addBankCard",method = {RequestMethod.GET,RequestMethod.POST})
    public Object addBankCard(UpdateBankCardDto changeBankCardDto){
        Response response= null;
        try {
            response = addBankCardInfoImpl.execute(changeBankCardDto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }
    /**
     *
     * author:jhz
     * time:2016年7月20日
     * function：删除银行卡
     */
    @RequestMapping(value = "/deleteBankCard",method = {RequestMethod.GET,RequestMethod.POST})
    public Object deleteBankCard(DeleteBankCardDto deleteBankCardDto){
        Response response= null;
        try {
            response = changeBankCardAccountImpl.execute(deleteBankCardDto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }

    /**
     *
     * author:xdw
     * time:2016年7月20日
     * function：修改银行卡银行卡
     */
    @RequestMapping(value = "/updateBankCardAnother",method = {RequestMethod.GET,RequestMethod.POST})
    public Object updateBankCardAnother(UpdateBankCardAnotherDto updateBankCardAnotherDto){
        Response response= null;
        try {
            response = changeBankCardAnotherByIdImpl.execute(updateBankCardAnotherDto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }

    /**
     *
     * author:xdw
     * time:2016年7月20日
     * function：查询银行卡讯息
     */
    @RequestMapping(value = "/findBankCardAnother",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Object FindBankCardAnother(FindBankCardDto findBankCardDto){
        Response response= null;
        try {
            response = findBankCardAnotherByIdImpl.execute(findBankCardDto);
        } catch (Exception e) {
            response = this.execute(e);
        }
        return response;
    }

    private Response execute(Exception e){
        LogUtil.error(this.getClass(), e);
        Response response = new Response();
        response.setResp_code(e.getMessage());
        return response;
    }
}
