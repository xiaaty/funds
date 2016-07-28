package com.gqhmt.channelDeploy;

import com.gqhmt.TestService;
import com.gqhmt.fss.architect.trade.bean.FssTradeApplyBean;
import com.gqhmt.fss.architect.trade.entity.FssTradeRecordEntity;
import com.gqhmt.fss.architect.trade.service.FssTradeApplyService;
import com.gqhmt.fss.architect.trade.service.FssTradeRecordService;
import com.gqhmt.util.ExportExcel;
import org.junit.Test;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.gqhmt.pay.core.configer.ConfigAbstract.getClassPath;

/**
 * Filename:    com.gq.funds.service.ChangeCardService
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author xdw
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016/7/13.
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016/7/13.  xdw         1.0     1.0 Version
 */
public class TestExportExcel extends TestService {
    @Resource
    private FssTradeApplyService fssTradeApplyService;

    @Resource
    private FssTradeRecordService fssTradeRecordService;

    /*@Test
    public void test()
    {
        // 测试学生
        ExportExcel<Student> ex = new ExportExcel<Student>();
        String[] headers =
                { "学号", "姓名", "年龄", "性别", "出生日期" };
        List<Student> dataset = new ArrayList<Student>();
        dataset.add(new Student(10000001, "张三", 20, true, new Date()));
        dataset.add(new Student(20000002, "李四", 24, false, new Date()));
        dataset.add(new Student(30000003, "王五", 22, true, new Date()));
        // 测试图书
        ExportExcel<Book> ex2 = new ExportExcel<Book>();
        String[] headers2 =
                { "图书编号", "图书名称", "图书作者", "图书价格", "图书ISBN", "图书出版社", "封面图片" };
        List<Book> dataset2 = new ArrayList<Book>();
        try
        {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream("E:/test/book.png"));
            byte[] buf = new byte[bis.available()];
            while ((bis.read(buf)) != -1)
            {
                //
            }
            dataset2.add(new Book(1, "jsp", "leno", 300.33f, "1234567",
                    "清华出版社", buf));
            dataset2.add(new Book(2, "java编程思想", "brucl", 300.33f, "1234567",
                    "阳光出版社", buf));
            dataset2.add(new Book(3, "DOM艺术", "lenotang", 300.33f, "1234567",
                    "清华出版社", buf));
            dataset2.add(new Book(4, "c++经典", "leno", 400.33f, "1234567",
                    "清华出版社", buf));
            dataset2.add(new Book(5, "c#入门", "leno", 300.33f, "1234567",
                    "汤春秀出版社", buf));

            OutputStream out = new FileOutputStream("E://a.xls");
            OutputStream out2 = new FileOutputStream("E://b.xls");
            ex.exportExcel(headers, dataset, out);
            ex2.exportExcel(headers2, dataset2, out2);
            out.close();
            JOptionPane.showMessageDialog(null, "导出成功!");
            System.out.println("excel导出成功！");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }*/

    @Test
    public void test() throws IOException, IllegalAccessException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "552");
        List<FssTradeApplyBean> tradeApplyList = fssTradeApplyService.queryFssTradeApplyList(map);
        List<Map> mapList = new ArrayList<Map>();


        List<List<FssTradeRecordEntity>> tradeRecordListList = new ArrayList<List<FssTradeRecordEntity>>();
        if (tradeApplyList.size() > 0) {
            for (FssTradeApplyBean tradeApply : tradeApplyList) {
                /*Map<String,String> tradeAppMap = new HashMap<String,String>();
                tradeAppMap.put("applyNo",tradeApply.getApplyNo());
                tradeAppMap.put("applyNo",tradeApply.getApplyNo());
                tradeAppMap.put("applyNo",tradeApply.getApplyNo());
                tradeAppMap.put("applyNo",tradeApply.getApplyNo());*/
                //map.put("ApplyBeanId",tradeApply.getId().toString());
                //List<FssTradeApplyBean> tradeApplyList = this.queryFssTradeApplyList(map);
                Map<String, Object> mapObject = new HashMap<String, Object>();

                List<FssTradeRecordEntity> tradeRecordList = fssTradeRecordService.queryFssTradeRecordList(tradeApply.getApplyNo(), null);
                mapObject.put("tradeApply", tradeApply);
                mapObject.put("tradeRecordList", tradeRecordList);
                mapList.add(mapObject);
            }
        }

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String path = getClassPath();
        File filepath  = new File(path+"\\excel");
        String address = filepath+"\\"  + sdf.format(new Date()) +  ".xls";

        System.out.println("address: "+address);

        String fileName = checkFileName(address,0);

        OutputStream out = new FileOutputStream(fileName);
        String[] headers =
                {"业务编号", "申请单号", "客户姓名", "客户电话", "交易金额", "单次交易金额", "创建时间"};
        ExportExcel<Map> ex = new ExportExcel<Map>();

        ex.exportExcel("tradeApply", headers, mapList, out);
        if(out!=null){
            out.close();
        }

        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "导出成功!");
        System.out.println("导出成功");

    }

    public String checkFileName(String fileName,int i){
        //验证文件是否存在
        String addressFileName;

        int index=fileName.lastIndexOf(".");
        int index2=fileName.lastIndexOf("\\");
        String fileEnd = fileName.substring(index);
        String fileStart = fileName.substring(0,index);

        String fileFolder = fileStart.substring(0,index2);
        // 验证文件夹是否存在
        File folder = new File(fileFolder);
        if(!folder.exists()){
            folder.mkdir();
        }
        String newFileName;
        if(i!=0){
            newFileName = fileStart+"("+i+")"+fileEnd;
        }else{
            newFileName = fileName;
        }
        i++;
        File file=new File(newFileName);

        if(!file.exists()) {
            addressFileName = newFileName;
        }else{
            addressFileName = checkFileName(fileName,i);
        }

        return addressFileName;
    }

}
