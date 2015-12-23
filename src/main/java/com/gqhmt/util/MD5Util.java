package com.gqhmt.util;

import java.security.MessageDigest;

/**
 * MD5加密
 * @author wangjitao
 *	2014-04-29
 */
public class MD5Util {
	
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
       return re_md5.toUpperCase();
   }
   
   public static void main(String[] args) {
       System.out.println("802616732D41051F6B53C90CE5333DA2:"+encryption("gqi.2009"));
   }
}
