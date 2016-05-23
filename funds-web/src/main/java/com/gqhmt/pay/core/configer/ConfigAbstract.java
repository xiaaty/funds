package com.gqhmt.pay.core.configer;


import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Filename:    com.thirdparty.configer
 * Copyright:   Copyright (c)2014
 * Company:     冠群驰骋投资管理(北京)有限公司
 *
 * @author 于泳
 * @version: 1.0
 * @since: JDK 1.7
 * Create at:   2015/3/29 16:12
 * Description:
 * <p>
 *     配置文件读取抽象类
 * </p>
 * Modification History:
 * Date    Author      Version     Description
 * -----------------------------------------------------------------
 * 2015/3/29  于泳      1.0     1.0 Version
 */
public abstract class ConfigAbstract {

    protected  final  Map<String,Object> apiMap = new ConcurrentHashMap<>();
    protected  final  Map<String,String> propertiesMap = new ConcurrentHashMap<>();


    protected  boolean isConnection;
    protected  String url;

    /**
     * 解析xml文件
     * @param element
     */
    public void parseXml(Element element){
        if(element.getName().equals("propertieses")){
            parsePropertids(element);
        }else if(element.getName().equals("url")){
            parseXmlToUrl(element);
        }else if(element.getName().equals("public")){
            parseXmlToPublic(element);
        }else if(element.getName().equals("api")){
            parseXmlToApi(element);
        }else{
            parseXmlToFtp(element);
        }
    }

    protected void parsePropertids(Element element){
        List<Element> elements = element.elements();
        for(Element element1: elements){
            String propertiesFile = element1.getTextTrim();
            ResourceBundle resource = ResourceBundle.getBundle(propertiesFile, Locale.SIMPLIFIED_CHINESE);
            Enumeration<String> e = resource.getKeys();
            while (e.hasMoreElements()){
                String key = e.nextElement();
                String value = resource.getString(key);
                propertiesMap.put(key,value);
            }
        }
    }

    protected void parseXmlToFtp(Element element){
        List<Element> elements = element.elements();
        for(Element element1: elements){
            parXmlToSource(element.getName() + "." + element1.getName(),element1);
        }
    }

    /**
     * 解析url
     * @param element
     */
    public  void parseXmlToUrl(Element element){
        List<Element> elements = element.elements();
        for(Element element1: elements){
            parXmlToSource(element.getName() + "." + element1.getName(),element1);
        }
//        parXmlToSource("url" ,element);
//        url = element.getText();
//        String isCon = element.attribute("isConnection").getValue();
//        isConnection = Boolean.valueOf(isCon);
    }

    /**
     * 解析api
     * @param element
     */
    public void parseXmlToApi(Element element){
        Set set = (Set)apiMap.get("api");
        if(set == null){
            set = new ConcurrentSkipListSet();
            apiMap.put("api",set);
        }
        Attribute attribute = element.attribute("api_name");
        String apiName = attribute.getValue();
        set.add(apiName);
        Attribute attributeUrl = element.attribute("url");
        if(attributeUrl != null){
            apiMap.put("api."+apiName+".url",attributeUrl.getValue());
        }
        List<Element> elements = element.elements();

        if(elements == null){
            return;
        }
        Set<String> childSet= new ConcurrentSkipListSet();
        for(Element element1:elements){
            childSet.add(element1.getName());
            parXmlToSource("api."+apiName+"."+element1.getName(),element1);
            String source = (String)apiMap.get("api."+apiName+"."+element1.getName()+".source");
            if("list".equals(source) || "mapKey".equals(source) || "map".equals(source)){
                parseXmlToApiList("api."+apiName+"."+element1.getName(),element1);
            }
        }
        apiMap.put("api."+apiName,childSet);
//        parseXmlToApi();
    }

    public void parseXmlToApiList(String name,Element element){
        List<Element> elements = element.elements();
        parXmlToSource(name,element);
        Set<String> childSet= new ConcurrentSkipListSet();
        for(Element element1:elements){
            childSet.add(element1.getName());
            parXmlToSource(name+"."+element1.getName(),element1);
        }

        apiMap.put(name,childSet);
    }

    /**
     * 解析公共参数
     * @param element
     */
    public void parseXmlToPublic(Element element){
        List<Element> elements = element.elements();
        Set<String> publicSet = new ConcurrentSkipListSet<>();
        for(Element element1: elements){
            publicSet.add(element1.getName());
            parXmlToSource(element.getName() + "." + element1.getName(),element1);
        }
        apiMap.put("public",publicSet);
    }


    /**
     * 解析 节点source属性
     * @param name
     * @param element
     */
    private void parXmlToSource(String name,Element element){
        List<Attribute> attributes = element.attributes();
        if(attributes == null){
            return;
        }
        for(Attribute attribute:attributes){
            String key = attribute.getName();
            apiMap.put(name+"."+key,attribute.getValue());
            if(!"source".equals(key)){
                continue;
            }
            if(attribute.getValue().equals("fixed") && element.isTextOnly()){
                apiMap.put(name+".value",element.getTextTrim());
                continue;
            }

            if(attribute.getValue().equals("properties") && element.isTextOnly()){
                String value = element.getTextTrim();
                String keyProperties = value.replace("$","").replace("{","").replace("}","");
                Object valueProperties  = propertiesMap.get(keyProperties);
                if(valueProperties != null){
                    apiMap.put(name+".value",valueProperties);
                }


                continue;
            }
        }


    }



    /**
     * 获取root节点
     * @return
     */
    public Element getRoot(){
        Document document  =  getDocument();
        return document.getRootElement();
    }


    /**
     * 获取xml文件
     * @return
     */
    public Document getDocument(){
        String classPath = getClassPath();
        if(classPath == null){
            throw new RuntimeException("file is not exist");
        }
        String os = System.getenv("os");
        if(os != null && os.equals("Windows_NT") && classPath.indexOf("/") == 0){
            classPath = classPath.substring(1);
        }

        try {
            InputStream is = new FileInputStream(classPath+getXmlPath());
            SAXReader saxReader = new SAXReader();
            return saxReader.read(is);
        } catch (DocumentException e) {
//            Logger.debug(e);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 获取应用路径
     * @return
     */
    public static String getClassPath(){
        String path = ConfigAbstract.class.getResource("").getPath();
        String className  = ConfigAbstract.class.getName();
        String simName = ConfigAbstract.class.getSimpleName();
        String packge = className.substring(0,className.lastIndexOf(".")).replace(".","/");
        if(path.lastIndexOf(packge)>0){
            return path.substring(0,path.lastIndexOf(packge));
        }
        return path;
    }

    public abstract String getXmlPath();

    public void  init(){
//        Logger.debug("fund:"+apiMap);
        Element root = getRoot();
        List<Element> elements = root.elements();
        for(Element element:elements){
            parseXml(element);
        }
//        Logger.debug("fund:"+apiMap);
    }

    public Object getValue(String key){
        return apiMap.get(key)
                ;    }


    public String getURL(){
        return (String)apiMap.get("url.connection.value");
    }

    public boolean isConnection(){
        String connection = (String)apiMap.get("url.isConnection.value");
        if(connection == null ){
            return true;
        }

        Boolean flag = new Boolean(connection);

        return flag;


    }
}
