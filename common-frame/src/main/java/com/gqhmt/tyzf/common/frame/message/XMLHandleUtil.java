package com.gqhmt.tyzf.common.frame.message;

import org.dom4j.Element;
import org.dom4j.Node;

/**
 * Created by zhou on 2016/10/23.
 * Description:XML处理工具类
 */
public class XMLHandleUtil {

    private XMLHandleUtil(){
        throw new IllegalAccessError("Utility class");
    }

    public static String getSelectSingleNode(Element element, String nodeName){
        Node node=element.selectSingleNode(nodeName);
        if(node==null)
            return null;
        return node.getStringValue();
    }

    public static String getSelectSingleNodeArr(Element element,String nodeName,String attributeName){
        Element node=(Element) element.selectSingleNode(nodeName);
        if(node==null)
            return null;
        return node.attributeValue(attributeName);
    }

}
