package com.gqhmt.excel;

import com.gqhmt.sys.entity.DictEntity;
import com.gqhmt.sys.service.SystemService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Filename:    com.gqhmt.excel.ImportExcel
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/3/11 17:05
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/3/11  于泳      1.0     1.0 Version
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-*.xml")
public class ImportExcel {

    @Resource
    private SystemService systemService;
    @Test
    public void test() throws IOException {
        File file = new File("/Users/yuyonf/Documents/area.xlsx");
        InputStream in = new FileInputStream(file);

        Workbook wb = new XSSFWorkbook(in);

        Sheet sheet = wb.getSheetAt(0);

        Map<String,String> map = new HashMap<>();

        for(int i = 0 ;i<sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            Cell cell1 = row.getCell(0);
            Cell cell2 = row.getCell(1);
            map.put(String.valueOf(cell1.getNumericCellValue()),cell2.getStringCellValue());
            if(cell1.getCellType() == Cell.CELL_TYPE_BLANK){
                break;
            }
        }

        System.out.println(map.size());
        Set<String>  set = map.keySet();

        Iterator<String> iterator = set.iterator();
        List<DictEntity> list = new ArrayList<>();
        while (iterator.hasNext()){
            String key = iterator.next();
            String code = key.substring(0,key.length()-2);
            System.out.println("95"+code);

            String area1=code.substring(0,2);
            //System.out.println(area1);
            String area2=code.substring(2,4);
            //System.out.println(area2);
            String area3=code.substring(4,6);
            //System.out.println(area3);
            DictEntity dict = new DictEntity();

            dict.setDictId("95"+code);
            dict.setDictName(map.get(key));
            if(!"00".equals(area3)){
                dict.setParentId("95"+area1+area2+"00");
                dict.setIsEnd(98010001);
            }else if(!"00".equals(area2)){
                dict.setParentId("95"+area1+"0000");
                dict.setIsEnd(98010002);
            }else{
                dict.setParentId("95");
                dict.setIsEnd(98010002);
            }



            dict.setIsValid("98040001");

            dict.setCareateUserId(0L);

            dict.setCreateTime(new Date());

            dict.setModifyUserId(0L);
            dict.setModifyTime(new Date());

            //list.add(dict);

            this.systemService.insertDictmain(dict);
        }









    }
}
