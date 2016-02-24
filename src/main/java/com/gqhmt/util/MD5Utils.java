package com.gqhmt.util;

import java.security.MessageDigest;
/**
 * 
 * Filename:    com.gqhmt.extServInter.dto.account.CreateAccountByFuiou
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author jhz
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2016年2月23日
 * Description:	md5 加密
 * <p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2016年2月23日  jhz      1.0     1.0 Version
 */
public class MD5Utils {

	/**
    *
    * @param plainText
    *            明文
    * @return 32位密文
    */
   public static String encryption(String plainText) {
       String re_md5 = new String();
       try {
           MessageDigest md = MessageDigest.getInstance("MD5");
           md.update(plainText.getBytes());
           byte b[] = md.digest();

           int i;

           StringBuffer buf = new StringBuffer("");
           for (int offset = 0; offset < b.length; offset++) {
               i = b[offset];
               if (i < 0)
                   i += 256;
               if (i < 16)
                   buf.append("0");
               buf.append(Integer.toHexString(i));
           }

           re_md5 = buf.toString();

       } catch (Exception e) {
           e.printStackTrace();
       }
       return re_md5.toUpperCase().substring(6,26);
   }
   
   public static void main(String[] args) {
       System.out.println("802616732D41051F6B53C90CE5333DA2:"+encryption("gq.2009")+"++++"+encryption("gq.2009").substring(6,26));
   }
   
}
