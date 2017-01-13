package com.gqhmt.tyzf.common.frame.message;

import com.gqhmt.tyzf.common.frame.annotation.DFormat;
import com.gqhmt.tyzf.common.frame.annotation.OXMapper;
import com.gqhmt.tyzf.common.frame.annotation.OXMapping;
import com.gqhmt.tyzf.common.frame.annotation.XOMapping;
import com.gqhmt.tyzf.common.frame.exception.FrameConstans;
import com.gqhmt.tyzf.common.frame.exception.FrameException;

import com.gqhmt.tyzf.common.frame.util.log.LogUtil;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by zhou on 2016/10/21.
 * Description:报文对象
 */
public class MsgObject {

    /** 报文文本 */
    private String messageRevBody;

    private Document document;
    private Element root;

    private Element requestHeader;
    private Element requestMsg = null;

    private Element responseHeader;
    private Element responseMsg;

    /** 请求mo类型标志 ,请求报文 标识 */
    public static int initSR = 0;// 初始化请求mo

    /** 响应mo类型标志 ，响应报文标识 */
    public static int initSP = 1;// 初始化响应mo

    /**
     * 构造函数
     */
    private MsgObject() {
    }

    /**
     * 克隆方法
     * @return
     */
    public MsgObject clone(){
        MsgObject copy = new MsgObject();
        copy.document = (Document) this.document.clone();
        copy.messageRevBody = this.messageRevBody;
        copy.root = copy.document.getRootElement();
        copy.requestHeader = copy.root.element(MsgConstants.REQ_HEADER_TAG);
        copy.requestMsg = copy.root.element(MsgConstants.REQ_BODY_TAG);
        return copy;
    }

    /**
     * 构造函数
     * @param msg 报文
     * @throws FrameException
     */
    public MsgObject(String msg) throws FrameException {
        try {
            this.messageRevBody = msg;
            document = DocumentHelper.parseText(msg);
            root = document.getRootElement();
            requestHeader = root.element(MsgConstants.REQ_HEADER_TAG);
            requestMsg = root.element(MsgConstants.REQ_BODY_TAG);
        } catch (DocumentException e) {
            LogUtil.getInstance().error(e, FrameConstans.MO_INIT_ERR);
            throw new FrameException(FrameConstans.MO_INIT_ERR);
        }
    }

    /**
     * 构造函数
     * @param initFlag 创建请求报文/响应报文标识
     * @throws FrameException
     */
    public MsgObject(int initFlag) throws FrameException {
        document = DocumentHelper.createDocument();
        if (initSR == initFlag) {
            initRequest();//初始化请求报文
        } else if (initSP == initFlag) {
            initResponse(); // 初始化响应报文
        } else {
            throw new FrameException("initPara参数非法");
        }
    }

    /**
     * 初始化请求报文
     */
    private void initRequest() {
        root = document.addElement(MsgConstants.REQ_ROOT);
        requestHeader = root.addElement(MsgConstants.REQ_HEADER_TAG);
        requestMsg = root.addElement(MsgConstants.REQ_BODY_TAG);
    }

    /**
     * 初始化响应报文
     */
    public void initResponse() {
        root = document.addElement(MsgConstants.RESP_ROOT);
        responseHeader = root.addElement(MsgConstants.RESP_HEADER_TAG);
        responseMsg = root.addElement(MsgConstants.RESP_BODY_TAG);
    }


    /**
     * 获取请求服务的类名称
     * @return
     */
    public String getRequest_service_ID() {
        return XMLHandleUtil.getSelectSingleNode(root, MsgConstants.REQ_HEADER_TAG + MsgConstants.SPLIT_CHAR
                + MsgConstants.SERVICE_ID);
    }

    /**
     * 获取请求报文头中某一节点的值
     * @param nodeName
     * @return
     */
    public String getRequestHeaderParameter(String nodeName) {
        return XMLHandleUtil.getSelectSingleNode(root, MsgConstants.REQ_HEADER_TAG + MsgConstants.SPLIT_CHAR + nodeName);
    }

    /**
     * 设置请求报文头中的某一节点的值
     * @param node
     * @param value
     */
    public boolean setRequestHeaderParameter(String node, String value) {
        if (StringUtils.isBlank(node)) {
            return false;
        }
        String str = XMLHandleUtil.getSelectSingleNode(root, MsgConstants.REQ_HEADER_TAG + MsgConstants.SPLIT_CHAR + node);
        if (null != str) {
            Node nodeEle = root.selectSingleNode(MsgConstants.REQ_HEADER_TAG + MsgConstants.SPLIT_CHAR + node);
            nodeEle.setText((value != null) ? value:"" );
        } else {
            Element ele = requestHeader.addElement(node);
            ele.setText(value);
        }
        return true;
    }

    /**
     * 获取请求报文体中某一节点的值
     * @param nodeName
     * @return
     */
    public String getRequestMsgParameter(String nodeName) {
        return XMLHandleUtil.getSelectSingleNode(root, MsgConstants.REQ_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName);
    }

    /**
     * 设置请求报文体节点 如果节点存在，则修改节点值。如果不存在，则新增节点
     * @param nodeName 节点全路径
     * @param value 节点值
     */
    public void setRequestMsgParameter(String nodeName, String value) {
        if (StringUtils.isBlank(nodeName)) {
            return;
        }
        Node node = root.selectSingleNode(MsgConstants.REQ_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName);
        if (null != node) {
            node.setText((value != null) ? value:"" );
            return;
        }
        if (requestMsg == null) {
            requestMsg = root.addElement(MsgConstants.REQ_BODY_TAG);
        }
        this.setTextbyXpath(nodeName, value, requestMsg, MsgConstants.REQ_BODY_TAG+MsgConstants.SPLIT_CHAR,null,null);
    }

    /**
     * 获取响应报文中报头里某一节点值
     * @param nodeName
     * @return
     */
    public String getResponseHeaderParameter(String nodeName) {
        return XMLHandleUtil.getSelectSingleNode(root, MsgConstants.RESP_HEADER_TAG + MsgConstants.SPLIT_CHAR + nodeName);
    }

    /**
     * 设置响应报文头
     * @param node
     * @param value
     */
    public void setResponseHeaderParameter(String node, String value) {
        if (StringUtils.isBlank(node)) {
            return;
        }
        String str = XMLHandleUtil.getSelectSingleNode(root, MsgConstants.RESP_HEADER_TAG + MsgConstants.SPLIT_CHAR + node);
        if (null != str) {
            Node nodeEle = root.selectSingleNode(MsgConstants.RESP_HEADER_TAG + MsgConstants.SPLIT_CHAR + node);
            nodeEle.setText((value != null) ? value:"" );
        } else {
            Element ele = responseHeader.addElement(node);
            ele.setText(value);
        }
    }

    /**
     * 获取响应报文Msg报体某一节点值
     * @param nodeName
     * @return
     */
    public String getResponseMsgParameter(String nodeName) {
        return XMLHandleUtil.getSelectSingleNode(root, MsgConstants.RESP_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName);
    }

    /**
     * 设置响应报文内容节点
     * @param nodeName 节点名
     * @param value 节点值
     */
    public void setResponseMsgParameter(String nodeName, String value) {
        if (StringUtils.isBlank(nodeName)) {
            return;
        }
        Node node = root.selectSingleNode(MsgConstants.RESP_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName);
        if (node != null) {
            node.setText((value != null) ? value:"" );
            return;
        }
        if (responseMsg == null) {
            responseMsg = root.addElement(MsgConstants.RESP_BODY_TAG);
        }
        this.setTextbyXpath(nodeName, value, responseMsg, MsgConstants.RESP_BODY_TAG + MsgConstants.SPLIT_CHAR,null,null);
    }

    /**
     * 通过xpath  set some node value
     * @param nodeName
     * @param value
     * @param startElement
     * @param prePath
     * @param attrName
     * @param attrValue
     */
    private void setTextbyXpath(String nodeName,String value,Element startElement,String prePath,
                                String attrName,String attrValue){
        String[] nodeArr = nodeName.split(MsgConstants.SPLIT_CHAR);
        StringBuilder xpath = new StringBuilder().append(prePath);
        Node parentNode = (Node)startElement;
        for (int i = 0; i < nodeArr.length; i++) {
            String childNodename = nodeArr[i];
            Node childNode = root.selectSingleNode((xpath.append(childNodename).toString()));
            if (childNode == null) {
                Element parentElement = (Element)parentNode;
                if(parentElement != null){
                    childNode = (Node)parentElement.addElement(childNodename);
                }
            }
            if (null != childNode && i == nodeArr.length-1) {
                Element element = (Element)childNode;
                element.setText((value != null) ? value:"" );
                if (attrName != null && !"".equals(attrName)) {
                    element.addAttribute(attrName,attrValue);
                }
                break;
            }
            parentNode = childNode;
            xpath.append(MsgConstants.SPLIT_CHAR);
        }
    }


    /**
     * 获取请求报文中，某个节点的属性值
     * @param nodeName 节点名
     * @param attributeName 节点属性名
     * @return String
     */
    public String getRequestMsgAttributer(String nodeName, String attributeName) {
        return XMLHandleUtil.getSelectSingleNodeArr(root, MsgConstants.REQ_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName,
                attributeName);

    }

    /**
     * 设置请求报文节点的属性值
     * @param nodeName 节点路径
     * @param attributeName 节点属性名
     * @param value 节点值
     */
    public void setRequestMsgAttributer(String nodeName, String attributeName, String value) {
        if (StringUtils.isBlank(nodeName) || StringUtils.isBlank(attributeName) || StringUtils.isBlank(value)) {
            return;
        }
        Node node = root.selectSingleNode(MsgConstants.REQ_BODY_TAG + MsgConstants.SPLIT_CHAR + nodeName);
        if (null != node) {
            Element element = (Element)node;
            element.addAttribute(attributeName,value);
            return;
        }
        if (requestMsg == null) {
            requestMsg = root.addElement(MsgConstants.REQ_BODY_TAG);
        }
        this.setTextbyXpath(nodeName, null, requestMsg, MsgConstants.REQ_BODY_TAG+MsgConstants.SPLIT_CHAR,attributeName,value);
    }

    public String formatXMLMessage() {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setSuppressDeclaration(true);
        StringWriter sw = new StringWriter();
        XMLWriter writer = new XMLWriter(sw, format);
        try {
            writer.write(this.document);
        } catch (IOException e) {
            LogUtil.getInstance().error(e);
            LogUtil.getInstance().debug("formatXMLMessage method format document object occuring exception !");
        }
        return sw.toString();
    }

    /**
     * 设置响应报文头的响应码
     * @param value
     */
    public void setResponseCode(String value) {
        Element ele = responseHeader.addElement("code");
        ele.setText(value);
    }

    /**
     * 设置响应报文头的响应描述
     * @param value
     */
    public void setResponseDesc(String value) {
        Element ele = responseHeader.addElement("desc");
        ele.setText(value);
    }

    /**
     * 设置响应报文头的错误类型值
     * @param value
     */
    public void setResponseErrorType(String value) {
        Element ele = responseHeader.addElement("errorType");
        ele.setText(value);
    }

    /**
     * 设置响应报文头的错误类型值
     * @param value
     */
    public void setResponseStatus(String value) {
        Element ele = responseHeader.addElement("status");
        ele.setText(value);
    }

    /**
     * 取接收到的原始报文
     * @return
     */
    public String getMessageRevBody() {
        return messageRevBody;
    }

    public void setMessageRevBody(String messageRevBody) {
        this.messageRevBody = messageRevBody;
    }

    /**
     * 取处理后，要发送出去的报文
     */
    public String getMessageSendBody() {
        return this.formatXMLMessage();
    }

    /**
     * 将XML转成字符串
     *
     * @return
     */
    public String XmltoString() {
        return document.asXML();
    }

    @Override
    public String toString() {
        try {
            String string = this.formatXMLMessage();
            return string;
        } catch (Exception e) {
            LogUtil.getInstance().error(e);
        }
        return super.toString();

    }

    /**
     * 设置
     * @param code 状态码值
     * @param status 状态描述
     * @param desc 描述内容
     */
    public void setServiceResponse(String code, String status, String desc) {
        setResponseCode(code);
        this.setResponseStatus(status);
        this.setResponseDesc(desc);
    }

    /**
     * 克隆Mo
     * @param endType
     * @return
     * @throws Exception
     */
    public MsgObject clone(int endType){
        MsgObject copy = new MsgObject();
        copy.document = (Document) this.document.clone();
        copy.messageRevBody = this.messageRevBody;
        copy.root = copy.document.getRootElement();
        copy.requestHeader = copy.root.element(MsgConstants.REQ_HEADER_TAG);
        copy.requestMsg = copy.root.element(MsgConstants.REQ_BODY_TAG);
        return copy;
    }

    /******************报文转换工具方法************************/

    /**
     * 转换报文DOM对象为相关实体Bean对象， 被转换对象要通过注解配置对应报文XML的路径
     * @param clazz
     * @return
     */
    public <T> T getMessge4Bean(Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e1) {
            LogUtil.getInstance().error(e1);
        } catch (IllegalAccessException e1) {
            LogUtil.getInstance().error(e1);
        }
        Field[] fileds = clazz.getDeclaredFields();

        // 便利bean属性进行赋值
        for (int j = 0; j < fileds.length; j++) {
            try {
                StringBuffer methodName = new StringBuffer("set");
                String filedName = fileds[j].getName();
                methodName.append(filedName.substring(0, 1).toUpperCase()).append(filedName.substring(1, filedName.length()));
                Object[] objs = new Object[1];
                Class[] classType = null;
                String requiredType = fileds[j].getType().getName();
                OXMapping c = fileds[j].getAnnotation(OXMapping.class);
                DFormat dfa = fileds[j].getAnnotation(DFormat.class);
                if (c != null) {// 如果配置了注解，取值
                    objs[0] = XMLHandleUtil.getSelectSingleNode(root, c.xpath());
                    if (objs[0] != null) {// 报文文件有值时设置
                        if ("java.lang.String".equals(requiredType)) {// 类型判断及赋值
                            // String
                            try {
                                classType = new Class[] { Class.forName("java.lang.String") };
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("java.lang.Long".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Long") };
                                objs[0] = new Long((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("java.util.Date".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.util.Date") };
                                SimpleDateFormat df = new SimpleDateFormat(dfa.format());
                                objs[0] = df.parseObject((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("java.math.BigDecimal".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.math.BigDecimal") };
                                objs[0] = new BigDecimal((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("java.lang.Integer".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Integer") };
                                objs[0] = new Integer((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("java.lang.Boolean".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Boolean") };
                                objs[0] = new Boolean((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("java.lang.Double".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Double") };
                                objs[0] = new Double((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("java.lang.Float".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Float") };
                                objs[0] = new Float((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        }
                        Method method = null;
                        try {
                            method = clazz.getMethod(methodName.toString(), classType);
                        } catch (SecurityException e) {
                            LogUtil.getInstance().error(e);
                        } catch (NoSuchMethodException e) {
                            LogUtil.getInstance().error(e);
                        }
                        try {
                            if(method != null) {
                                method.invoke(obj, objs);
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println("1"+fileds[j].toString());
                            LogUtil.getInstance().error(e);
                        } catch (IllegalAccessException e) {
                            System.out.println("2"+fileds[j].toString());
                            LogUtil.getInstance().error(e);
                        } catch (InvocationTargetException e) {
                            System.out.println("3"+fileds[j].toString());
                            LogUtil.getInstance().error(e);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("4"+fileds[j].toString());
                LogUtil.getInstance().error("4"+fileds[j].toString()+e);
                continue;
            }
        }
        return obj;
    }

    /**
     * 转换报文DOM对象为相关实体Bean对象， 被转换对象要通过注解配置对应报文XML的路径
     * 注解加在set方法上（支持父类中的set方法）
     * @param clazz
     * @param <T>
     * @return T
     */
    public <T> T getMessgeToBean(Class<T> clazz) {
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (InstantiationException e1) {
            LogUtil.getInstance().error(e1);
        } catch (IllegalAccessException e1) {
            LogUtil.getInstance().error(e1);
        }
        Method[] methods = clazz.getMethods();
        // 便利bean的set方法进行赋值
        for (int j = 0; j < methods.length; j++) {
            try {
                Object[] objs = new Object[1];
                Class<?>[] classType = null;
                OXMapper c = methods[j].getAnnotation(OXMapper.class);
                if (c != null) {// 如果配置了注解，取值
                    String methodName = methods[j].getName();
                    DFormat dfa = methods[j].getAnnotation(DFormat.class);
                    String requiredType = c.paraType();
                    objs[0] = XMLHandleUtil.getSelectSingleNode(root, c.xpath());

                    if (objs[0] != null) {// 报文文件有值时设置
                        if ("String".equals(requiredType)) {// 类型判断及赋值
                            try {
                                classType = new Class[] { Class.forName("java.lang.String") };
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("Long".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Long") };
                                objs[0] = new Long((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("Date".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.util.Date") };
                                SimpleDateFormat df = new SimpleDateFormat(dfa.format());
                                objs[0] = df.parseObject((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("BigDecimal".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.math.BigDecimal") };
                                objs[0] = new BigDecimal((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("Integer".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Integer") };
                                objs[0] = new Integer((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("Boolean".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Boolean") };
                                objs[0] = new Boolean((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("Double".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Double") };
                                objs[0] = new Double((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        } else if ("Float".equals(requiredType)) {
                            try {
                                classType = new Class[] { Class.forName("java.lang.Float") };
                                objs[0] = new Float((String) objs[0]);
                            } catch (ClassNotFoundException e) {
                                LogUtil.getInstance().error(e);
                            }
                        }
                        Method method = null;
                        try {
                            method = clazz.getMethod(methodName, classType);
                        } catch (SecurityException e) {
                            LogUtil.getInstance().error(e);
                        } catch (NoSuchMethodException e) {
                            LogUtil.getInstance().error(e);
                        }
                        try {
                            if(method != null)
                                method.invoke(obj, objs);
                        } catch (IllegalArgumentException e) {
                            LogUtil.getInstance().error(e);
                        } catch (IllegalAccessException e) {
                            LogUtil.getInstance().error(e);
                        } catch (InvocationTargetException e) {
                            LogUtil.getInstance().error(e);
                        }
                    }
                }
            } catch (Exception e) {
                LogUtil.getInstance().error(e);
                continue;
            }
        }
        return obj;
    }

    /**
     * 实体Bean转成Dom对象
     * @param t
     * @throws IllegalAccessException
     */
    public <T> T getBean4Message(T t) throws IllegalAccessException {
        if (null == t){
            throw new IllegalArgumentException("转换对象不能为空!");
        }
        Class<?> clazz = t.getClass();

        Field[] fileds = clazz.getDeclaredFields();
        for (int i = 0; i < fileds.length; i++){
            Field f = fileds[i];
            XOMapping c = f.getAnnotation(XOMapping.class);
            String value = "";//报文节点值
            if(null != c){
                String prepath = c.preNode();
                f.setAccessible(true);
                Object obj = f.get(t);
                DFormat df = f.getAnnotation(DFormat.class);
                if(null != df){
                    if(null != obj){
                        SimpleDateFormat sdf = new SimpleDateFormat(df.format());
                        value = sdf.format((Date)obj);
                    }
                }else{
                    if(obj != null) {
                        //将值赋予对应的报文节点上
                        value = obj.toString();
                        String xpath = c.xpath();//报文节点
                        //如果是报文头则赋值head，如果是报文体则使用body
                        if (MsgConstants.REQ_BODY_TAG.equals(prepath)) {
                            setRequestMsgParameter(xpath, value);
                        }
                        if (MsgConstants.REQ_HEADER_TAG.equals(prepath)) {
                                setRequestHeaderParameter(xpath, value);
                        }
                    }
                }
            }
        }
        return t;
    }

}
