package com.gqhmt.fss.architect.accounting.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gqhmt.core.exception.FssException;
import com.gqhmt.core.util.ResourceUtil;
import com.gqhmt.fss.architect.accounting.entity.FssCheckAccountingEntity;
import com.gqhmt.funds.architect.account.entity.FundAccountEntity;
import com.gqhmt.funds.architect.account.service.FundAccountService;
import com.gqhmt.util.ReadExcelUtil;
import com.gqhmt.util.exception.ReadExcelException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Filename:    com.gqhmt.fss.architect.trade.service.FssTradeApplyService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/9/1
 * Description:xlsx导入数据
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/9/1  jhz     1.0     1.0 Version
 */
@Service
public class FssImportDataService {
    @Resource
    private FundAccountService fundAccountService;
    @Resource
    private FssCheckAccountingService fssCheckAccountingService;

    /**
     * jhz
     * 得到需要导入的对账列表
     * @param file
     * @param basePath
     * @param type
     * @return
     * @throws Exception
     */
    public  List<FssCheckAccountingEntity>   readExcelWithTitle(MultipartFile file,String basePath,String type) throws Exception {
        if(file == null){
            throw new ReadExcelException("0001");
        }
        Workbook wb=null;
        try{
            ReadExcelUtil<FssCheckAccountingEntity> excelUtil = new ReadExcelUtil<FssCheckAccountingEntity>(basePath,FssCheckAccountingEntity.class);
            wb = excelUtil.getWorkBook(file);
            List<FssCheckAccountingEntity> result = new ArrayList<FssCheckAccountingEntity>();//对应excel文件
            int sheetSize = wb.getNumberOfSheets();
            for (int i = 0; i < sheetSize; i++) {//遍历sheet页
                Sheet sheet = wb.getSheetAt(i);

                int rowSize = sheet.getLastRowNum() + 1;
                for (int j = 0; j < rowSize; j++) {//遍历行
                    FssCheckAccountingEntity  entity= null;//对应excel文件
                    Row row = sheet.getRow(j);
                    if (row == null) {//略过空行
                        continue;
                    }
                    int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
                    if (j == 0) {//第一行是标题行

                    } else {//其他行是数据行
                            if ("10980001".equals(type)||"10980002".equals(type)){
                                //提现和体现退票
//                                {"orderNo", "tradeTime","accountingNo", "accountingTime", "amount", "accNo", "accName", "userName", "remark",
//                                        "status"};
//                                entity=  fssCheckAccountingService.createChecking(row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),
//                                        row.getCell(3).toString(),row.getCell(4).toString(),null,row.getCell(5).toString(),row.getCell(6).toString(),row.getCell(7).toString()
//                                        ,row.getCell(8).toString(),row.getCell(9).toString(),type,null,"98010002");
                                entity.setOrderNo(row.getCell(0).toString());
                                entity.setTradeTime(row.getCell(1).toString());
                                entity.setAccountingNo(row.getCell(2).toString());
                                entity.setAccountingTime(row.getCell(3).toString());
                                entity.setAmount(row.getCell(4).toString());
                                entity.setAccNo(row.getCell(5).toString());
                                entity.setAccName(row.getCell(6).toString());
                                entity.setUserName(row.getCell(7).toString());
                                entity.setRemark(row.getCell(8).toString());
                                entity.setStatus(row.getCell(9).toString());
                                entity.setTradeType(type);
                                entity.setCreateTime(new Date());
                                entity.setModifyTime(new Date());
                                entity.setAccountingStatus("98010002");
                            } else if("10980003".equals(type)){
                                //充值
//                                {"orderNo", "tradeTime","accountingNo", "accountingTime","rechargeWay", "amount", "accNo", "accName", "userName", "remark",
//                                        "status"};
                                entity=  fssCheckAccountingService.createChecking(row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),
                                        row.getCell(3).toString(),row.getCell(5).toString(),null,row.getCell(6).toString(),row.getCell(7).toString(),row.getCell(8).toString()
                                        ,row.getCell(9).toString(),row.getCell(10).toString(),type,null,"98010002");
                                entity.setOrderNo(row.getCell(0).toString());
                                entity.setTradeTime(row.getCell(1).toString());
                                entity.setAccountingNo(row.getCell(2).toString());
                                entity.setAccountingTime(row.getCell(3).toString());
                                entity.setRechargeWay(row.getCell(4).toString());
                                entity.setAmount(row.getCell(5).toString());
                                entity.setAccNo(row.getCell(6).toString());
                                entity.setAccName(row.getCell(7).toString());
                                entity.setUserName(row.getCell(8).toString());
                                entity.setRemark(row.getCell(9).toString());
                                entity.setStatus(row.getCell(10).toString());
                                entity.setTradeType(type);
                                entity.setCreateTime(new Date());
                                entity.setModifyTime(new Date());
                                entity.setAccountingStatus("98010002");
                            }else if(StringUtils.equals("10980004",type)){
                                //转账
//                                {"orderNo", "tradeTime","accountingNo", "accountingTime", "amount", "accNo", "accName", "userName","toAccNo", "toAccName",
//                                        "toUserName", "contractNo","itemNo","remark","status"};
//                                entity=  fssCheckAccountingService.createChecking(row.getCell(0).toString(),row.getCell(1).toString(),row.getCell(2).toString(),
//                                        row.getCell(3).toString(),row.getCell(4).toString(),null,row.getCell(5).toString(),row.getCell(6).toString(),row.getCell(7).toString()
//                                        ,row.getCell(13).toString(),row.getCell(14).toString(),type,null,"98010002");
                                entity.setOrderNo(row.getCell(0).toString());
                                entity.setTradeTime(row.getCell(1).toString());
                                entity.setAccountingNo(row.getCell(2).toString());
                                entity.setAccountingTime(row.getCell(3).toString());
                                entity.setAmount(row.getCell(4).toString());
                                entity.setAccNo(row.getCell(5).toString());
                                entity.setAccName(row.getCell(6).toString());
                                entity.setUserName(row.getCell(7).toString());
                                entity.setToAccNo(row.getCell(8).toString());
                                entity.setToAccName(row.getCell(9).toString());
                                entity.setToUserName(row.getCell(10).toString());
                                entity.setContractNo(row.getCell(11).toString());
                                entity.setItemNo(row.getCell(12).toString());
                                entity.setRemark(row.getCell(13).toString());
                                entity.setStatus(row.getCell(14).toString());
                                entity.setTradeType(type);
                                entity.setCreateTime(new Date());
                                entity.setModifyTime(new Date());
                                entity.setAccountingStatus("98010002");
                            }

                        result.add(entity);
                    }
                }
            }

            return result;
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            if (wb != null) {
                wb.close();
            }
        }

    }

    /**
     * jhz
     * 给需要导入的数据添加cust_id
     * @param list
     * @return
     * @throws FssException
     */
    public List<FssCheckAccountingEntity> list(List<FssCheckAccountingEntity>  list)throws FssException{
    //循环便利集合得到客户表id并添加进对象
    List<FssCheckAccountingEntity> enList= Lists.newArrayList();
    FundAccountEntity fundAccountEntity=null;
    FundAccountEntity toFundAccountEntity=null;
    String accName=null;
    String  toAccName=null;
    //映射的到对公账户的金账户名
    Map<String,String> map1= ResourceUtil.list("config.account");
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
        fssCheckAccountingService.createChecking(entity);
        enList.add(entity);
    }
    return  enList;
}

    /**
     * jhz
     * 去重
     * @param list
     * @return
     */
    public List<FssCheckAccountingEntity> repeatClear(List<FssCheckAccountingEntity>  list,String tradeType){
       Map<String,FssCheckAccountingEntity> map=Maps.newHashMap();
        for (FssCheckAccountingEntity entity:list) {
            BigDecimal b=new BigDecimal(entity.getAmount());
            entity.setAmount(b.toString());
            entity.setTradeType(tradeType);
            map.put(entity.getOrderNo(),entity);
        }
        return new ArrayList<>(map.values());
    }











}
