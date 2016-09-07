package com.gqhmt.controller.fss.trade;

import com.gqhmt.annotations.AutoPage;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.fss.architect.trade.entity.FssTradeInfoEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeInfoService;
import com.gqhmt.util.ReadExcelUtil;
import com.gqhmt.util.exception.ReadExcelErrorException;
import com.gqhmt.util.exception.ReadExcelException;
import org.apache.commons.collections.CollectionUtils;
import org.bouncycastle.asn1.ocsp.Request;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/6.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/6.  xdw         1.0     1.0 Version
 */
@Controller
public class FssTradeInfoController {

    @Resource
    private FssTradeInfoService fssTradeInfoService;

    @RequestMapping(value = "/trade/tradeInfo/list",method = {RequestMethod.GET, RequestMethod.POST})
    @AutoPage
    public String listTradeInfo(HttpServletRequest request, ModelMap model, @RequestParam Map<String, String> map, FssTradeInfoEntity tradeInfo) throws FssException {
        List<FssTradeInfoEntity> listTradeInfo = fssTradeInfoService.listTradeInfo(map);
        model.addAttribute("page",listTradeInfo);
        model.addAttribute("map",map);
        return "fss/trade/listTradeInfo";
    }

    @RequestMapping(value = "/trade/tradeInfo/loadExcel",method = {RequestMethod.GET, RequestMethod.POST})
    public Object tradeInfoloadExcel(HttpServletRequest request, ModelMap modelMap,@RequestParam(value = "infoFile") MultipartFile infoFile) throws ReadExcelErrorException, ReadExcelException, IOException {
        ReadExcelUtil excelUtil = new ReadExcelUtil("F:\\Program Files (x86)\\excel\\",FssTradeInfoEntity.class);
        String[] columnName = new String[]{"dataSource","sysCode","orglSeqNo","seqNo","chgCd","toAccTime","tradeTime","toAccNm","toAccNo","amount","tradeSts","cardVerify"};
        int sheetsSize = excelUtil.getWorkBook(infoFile).getNumberOfSheets();

        List<FssTradeInfoEntity> listTradeInfo = new ArrayList<FssTradeInfoEntity>();
        if(sheetsSize>0){
            for(int i=0; i<sheetsSize; i++){
                listTradeInfo.addAll((List<FssTradeInfoEntity>)excelUtil.getExcelData(infoFile,columnName,i));
            }
        }
        if(!CollectionUtils.isEmpty(listTradeInfo)){
            fssTradeInfoService.insertListTradeInfo(listTradeInfo);
        }
        return "redirect:"+request.getContextPath()+"/trade/tradeInfo/list";
    }

}
