package com.gqhmt.text;

import org.junit.Test;

import java.io.*;

/**
 * Filename:    com.gqhmt.text.LogParse
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   16/9/12 16:01
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 16/9/12  于泳      1.0     1.0 Version
 */
public class LogParse {

    String fileDir1 = "/Users/yuyonf/Desktop/funds-web/10.9.10.80/";
    String fileDir2 = "/Users/yuyonf/Desktop/funds-web/10.9.10.82/";

    @Test
    public void parserLog(){

        File file = new File(fileDir1+"catalina.out.bak");

        InputStream is = null;
        try {
            is = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(is,"UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while (bufferedReader.ready()){
                String line  =  bufferedReader.readLine();
                if(line.contains("连接富友")){

                    String time = line.substring(line.lastIndexOf("时长")+3);
                    if(Integer.parseInt(time) >5000) {
                        System.out.println(line);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
