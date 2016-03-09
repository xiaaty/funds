package com.gqhmt.core.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.*;

/**
 * Filename:    com.fuiou.util
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/4/1 13:39
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/4/1  于泳      1.0     1.0 Version
 */
public class XmlUtil {
    public static final Log log = LogFactory.getLog(XmlUtil.class);
    public static String generateXML(Map<String, Object> map){

        return generateXML(map, null);
    }

    public static String generateXML(Map<String, Object> map,String rootElement){
        Document document = DocumentHelper.createDocument();
        document.setXMLEncoding("GBK");
        String RootElementName = "root";
        if(rootElement != null){
            RootElementName = rootElement;
        }

        Element e = document.addElement(RootElementName);
        Set<String> set = map.keySet();
        Iterator<String> i = set.iterator();
        while (i.hasNext()) {
            String key = i.next();
            Element t = e.addElement(key);
            t.addText((String)map.get(key));

        }

        String result =  document.asXML();

        if(log.isDebugEnabled()){
            log.debug("generateXML:"+result);
        }

        return result ;
    }





    @SuppressWarnings("rawtypes")
    public static Map<String, Object>  getMap(String xml) {
        Map<String, Object> map  = null;
        Document dom;
        try {
            dom = DocumentHelper.parseText(xml);
            Element root = dom.getRootElement();
            String name = root.getName();


            //            Map<String, String>

            List list = root.elements();
            Map<String, Object> m = getMap(list);
            if(m != null){
                if(map == null){
                    map = new HashMap<String, Object>();
                }
                map.put(name, m);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        if(log.isDebugEnabled()){
            log.debug("getMap:"+map);
        }
        return map;

    }


    @SuppressWarnings("rawtypes")
    private static Map<String, Object> getMap( List list){
        Map<String, Object> map = null;
        if(list == null || list.size() == 0){
            return null;
        }
        if(log.isDebugEnabled()){
            log.debug("getMap_list:"+list+"    size:"+(list!= null?list.size():-1));
        }

        for(Object obj : list){

            Element t = (Element) obj;
            String name = t.getName();
            if(t.isTextOnly()){
                if(map == null){
                    map = new HashMap<String, Object>();
                }
                map.put(name, t.getText());
            }else{
                Map<String, Object> m = getMap(t.elements());
                if(m != null){
                    if(map == null){
                        map = new HashMap<String, Object>();
                    }
                    map.put(name, m);
                }
            }
        }

        return map;
    }
}