package com.gqhmt.common.context;

import com.gqhmt.common.exception.XmlParseException;
import com.gqhmt.common.log.Logger;
import com.gqhmt.tyzf.common.frame.config.ConfigShutdownHook;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Filename:    com.gqhmt.util.XMLparseUtil
 * Copyright:   Copyright (c)2015
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2017/2/20 16:14
 * Description:
 * <p/>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2017/2/20  于泳      1.0     1.0 Version
 */
public class XMLConfigureParse {

    /** 配置文件属性键值 */
    private Map<String, String> property = new ConcurrentHashMap<>();
    /** 标签组集合 */
    private ArrayList<String> sequence = new ArrayList<String>();
    /** 类实例 */
    private Properties instances = new Properties();
    /*** 配置文件解析对象***/
    private DOMParser parser = new DOMParser();
    private Document document;
    private Element root;

    private ConfigShutdownHook hook;

    /** 类实例 */

    /**类加载顺序*/
    String COMMON_LOAD_SEQUENCE = "Common.LoadSequence";

    /**
     * 加载config.xml配置文件
     * @param configFileName config.xml配置文件
     * @return 文件加载是否成功
     */
    public XMLConfigureParse(String configFileName,ConfigShutdownHook hook) throws XmlParseException {
        Logger.info(getClass(),"loadConfigurations starting .....");
        this.hook = hook;
        try {
            parser.parse(configFileName);
            document = parser.getDocument();
            root = document.getDocumentElement();

            generateSequence();
        } catch (Exception e) {
            Logger.info(getClass(),"loadConfigurations fail : " + configFileName);
            throw new XmlParseException("loadConfigurations fail : " + configFileName,e);
        }
    }


    /**
     * 读入config配置中的节点和adapter配置文件内容
     * @return boolean
     */
    private boolean generateSequence() {
        String items = getValueProperty(COMMON_LOAD_SEQUENCE);
        if (items != null) {
            String item;
            while (items.indexOf(",") > 0) {
                item = items.substring(0, items.indexOf(",")).trim();
                if ((item != null) && (item.length() > 0)) {
                    sequence.add(item);
                }
                items = items.substring(items.indexOf(",") + 1);
            }
            item = items.trim();
            if ((item != null) && (item.length() > 0)) {
                sequence.add(item);
            }
        }
        return true;
    }



    /**
     * 获取节点内容
     * @param name 节点名称
     * @return boolean
     */
    private String getValueProperty(String name) {
        String rs = getValueFromNode(name);
        if ((rs != null) && !"".equals(rs.trim())) {
            property.put(name, rs);
        }
        return rs;
    }

    /**
     * 获取节点内容
     * @param name
     * @return
     */
    private String getValueFromNode(String name) {
        Node nd = getChildByName(name);
        String rs = null;
        if ((null != nd) && (null != nd.getFirstChild())) {
            rs = nd.getFirstChild().getNodeValue();
        }
        return rs;
    }

    /**
     * 获取子节点(xxx.xxx)
     * @param name
     * @return
     */
    private Node getChildByName(String name) {
        Node nd = root;
        String[] nameStructure = name.split("\\.");
        for (int k = 0; k < nameStructure.length; k++) {
            NodeList temp = null;
            if (null != nd) {
                temp = nd.getChildNodes();
            }
            if (null == temp) {
                return null;
            }
            String tempName;
            for (int i = 0; i < temp.getLength(); i++) {
                tempName = temp.item(i).getNodeName();
                if (tempName.equals(nameStructure[k])) {
                    nd = temp.item(i);
                    break;
                } else {
                    nd = null;
                }
            }
        }
        return nd;
    }
}
