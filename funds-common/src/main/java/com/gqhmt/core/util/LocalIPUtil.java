package com.gqhmt.core.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Filename:    com.gq.core.utils
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/5/9 16:08
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/5/9  于泳      1.0     1.0 Version
 */
public class LocalIPUtil {
    public static List<String> getLocalIpList()
    {
        Enumeration enums = null;
        List<String> list = new ArrayList<String>();
        try {
            enums = NetworkInterface.getNetworkInterfaces();
            while (enums.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) enums.nextElement();
                Enumeration ips = ni.getInetAddresses();
                ////System.out.println(ni.getDisplayName());
                while (ips.hasMoreElements()) {
                    InetAddress ip = (InetAddress) ips.nextElement();
                    // 去除127.0.0.1以及其他不是IP的数据
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1)
                    {
                        ////System.out.println(ip.getHostAddress());
                        list.add(ip.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
