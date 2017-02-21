package com.gqhmt.common.context;

import com.gqhmt.common.base.AbstractMultiThread;
import com.gqhmt.common.base.Constants;
import com.gqhmt.common.base.IConfigurable;
import com.gqhmt.common.exception.FrameException;
import com.gqhmt.common.exception.XmlParseException;
import com.gqhmt.common.hook.ConfigShutdownHook;
import com.gqhmt.common.log.Logger;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.lang.reflect.Constructor;
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
public class XMLConfigureParse implements ContextConstants {

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
    public XMLConfigureParse(String configFileName,ConfigShutdownHook hook) throws XmlParseException, FrameException {
        Logger.info(getClass(),"loadConfigurations starting .....");
        this.hook = hook;
        try {
            /** 第一步：加载所有配置到内存从配置文件 */
            parser.parse(configFileName);
            document = parser.getDocument();
            root = document.getDocumentElement();
            Logger.info(getClass(),"loadConfigurations succeed!");


            /** 第二步：设置shutdown钩子 */
            Runtime.getRuntime().addShutdownHook(hook);
            Logger.info(getClass(),"addShutdownHook succeed");


            /** 第三步：加载Common标签下的内容 */
            generateSequence();
            Logger.info(getClass(),"generateSequence succeed!");

            loadInstances();
            Logger.info(getClass(),"loadInstances succeed!");

            startDaemons();
            Logger.info(getClass(),"startDaemons succeed!");


        } catch (FrameException e) {
            Logger.info(getClass(),"loadConfigurations fail : " + configFileName);
            throw e;
        } catch (SAXException e) {
            throw new XmlParseException("loadConfigurations fail : " + configFileName,e);
        } catch (IOException e) {
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
     * 加载所有类实例
     * @return boolean
     */
    private boolean loadInstances() throws FrameException {
        for (int i = 0, s = sequence.size(); i < s; i++) {
            String name = sequence.get(i);
            try {
                System.out.println("load config node : " + name);
                String className = getValueProperty(name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_CLASSNAME);
                className = className.trim();//防止在配置文件中结尾的标签换行导致className换行
                Logger.info(this.getClass(),"load config node class : " + className);
                Object instance = null;
                Constructor<?> constructor = null;
                try {
                    constructor = Class.forName(className).getConstructor(String.class);
                } catch (Exception ex) {
                    Logger.info(this.getClass(),"No String structure parameters : "+className);
                }

                if (constructor != null) {
                    instance = constructor.newInstance(name);
                } else {
                    instance = Class.forName(className).newInstance();
                }

                String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
                instances.put(instanceName, instance);
                if (instance instanceof IConfigurable) {
                    hook.registerComponent((IConfigurable) instance);
                }

                String infoMessage = MSG_COMPONENT_LOAD + name;
                Logger.info(this.getClass(),infoMessage);
            } catch (Exception ex) {
                Logger.error(this.getClass(),MSG_ERROR_COMPONENT + name);
                Logger.error(this.getClass(),MSG_START_ERROR);
                Logger.error(this.getClass(),ex);
                throw new FrameException("load instance error",ex,"1");
            }
        }
        return true;
    }

    /**
     * 启动守护线程
     * @return
     */
    private boolean startDaemons() {
        for (int i = 0, s = sequence.size(); i < s; i++) {
            String name = sequence.get(i);
            String instanceName = name + Constants.KEY_SPLIT + Constants.KEY_SUFFIX_INSTANCE;
            Object instance = instances.get(instanceName);
            if ((instance != null) && (instance instanceof AbstractMultiThread)) {
                AbstractMultiThread daemon = (AbstractMultiThread) instance;
                Thread thread = new Thread(daemon);
                daemon.setThread(thread);
                thread.setDaemon(true);
                thread.start();
                hook.registerDaemon(daemon);
                String infoMessage = MSG_DAEMON_START + name;
                Logger.info(getClass(),infoMessage);
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
