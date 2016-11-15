package com.gqhmt.util;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 统一报文转换类
 * @author zhaoenyue
 *
 */
public class ConvertUtils {
	
	private static DataHelper dataHelper = DataHelper.getInstance();
	
	/**
	 * 转换为统一报文格式
	 * @param reportEnum
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String convertToUniteReport (ConvertReportEnum reportEnum, Object... obj) throws Exception {
		return convertReport(reportEnum,obj);
	}
	
	/**
	 * 转换为渠道报文格式
	 * @param reportEnum
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public static String convertToChannelReport (ConvertReportEnum reportEnum, Object... obj) throws Exception {  
		return convertReport(reportEnum,obj);
	}

	/**
	 * 转换为统一报文格式
	 * @param reportEnum 源类(需要转化的基类)
	 * @param reportEnum 引用的xml模板
	 * @param obj 模板内的参数
	 * @throws Exception
	 */
	private static String convertReport(ConvertReportEnum reportEnum, Object... obj) throws Exception {  
		String retReport = "";
		Object targetClass = null;
        try {  
        	//封装传入的各类型参数
        	Map<String,Object> paramMap = buildParamMap(obj);
        	//读映射模板，获取转换映射关系
        	Map<String,String> configMap = getRelationshipMapper(reportEnum);
        	//属性赋值,获得转换后的类
        	targetClass = doConvert(paramMap,configMap);
        	//生成报文
        	retReport = XmlUtil.objectToXmlString(targetClass);
        } catch (Exception e) {  
            LogUtil.error(ConvertUtils.class, "转换统一报文失败:" + e.getMessage());
            retReport = "";
        }  
        return retReport;
    }  
	
	/**
	 * 根据enum获得模板路径
	 */
	private static String getToUniteReportPath(ConvertReportEnum reportEnum){
		return ConvertUtils.class.getResource("/").getPath() + reportEnum.getFilePath();
	}
	
	/**
	 * 读映射模板，获取转换映射关系
	 * @param reportEnum
	 * @return
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	private static Map<String,String> getRelationshipMapper(ConvertReportEnum reportEnum) throws Exception{
		Map<String,String> map = Maps.newHashMap();
		Document document = dataHelper.read(getToUniteReportPath(reportEnum));
        Element rootElement = document.getRootElement();
        if(rootElement != null){
        	map.put("targetClass", trimString(rootElement.attribute("targetClass").getValue()));
            List<Element> elements = rootElement.elements();
            for(Element element:elements){
        		if(StringUtils.equals(element.attribute("source").getValue(), "class")){
            		map.put(element.getName(), trimString(element.attribute("class").getValue()) + "." + trimString(element.attribute("key").getValue()));
            	}else{
            		map.put(element.getName(), "paramIndex" + trimString(element.attribute("paramIndex").getValue()));
            	}
            }
        }
        return map;
	}
	
	/**
	 * 封装传入的各类型参数
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	private static Map<String,Object> buildParamMap(Object... obj) throws Exception {
		Map<String,Object> paramMap = Maps.newHashMap();
        // 获取属性  
		int index = 0;
		for(Object o:obj){
            if(o == null){
                continue;
            }else if(o instanceof String || o instanceof BigDecimal 
            		|| o instanceof Byte || o instanceof Short 
            		|| o instanceof Integer || o instanceof Long 
            		|| o instanceof Float || o instanceof Double
            		|| o instanceof Boolean || o instanceof Character){
            	paramMap.put("paramIndex" + index, trimString(o.toString()));
            	index++;
            }else if(o instanceof List){
            	paramMap.put("paramIndex" + index, o);
            	index++;
            }else{
            	BeanInfo sourceBean = Introspector.getBeanInfo(o.getClass(),Object.class);
                PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();
                String className = o.getClass().getName();
                for (int i = 0; i < sourceProperty.length; i++) {
                	paramMap.put(className + "." +sourceProperty[i].getName(), trimString(sourceProperty[i].getReadMethod().invoke(o).toString()));
                }
            }
        }

		return paramMap;
	}

	/**
	 * 属性赋值
	 * @param paramMap
	 * @param configMap
	 * @return
	 * @throws Exception
	 */
	private static Object doConvert(Map<String,Object> paramMap,Map<String,String> configMap) throws Exception{
		String targetClassName = configMap.get("targetClass");
		Object targetClass = Class.forName(targetClassName).newInstance();

    	BeanInfo targetBean = Introspector.getBeanInfo(targetClass.getClass(),Object.class);
        PropertyDescriptor[] targetProperty = targetBean.getPropertyDescriptors();
    	
        for (int i = 0; i < targetProperty.length; i++) {  
        	if(configMap.containsKey(targetProperty[i].getName())){
        		targetProperty[i].getWriteMethod().invoke(targetClass,paramMap.get(configMap.get(targetProperty[i].getName()))); 
        	}
        }  
        return targetClass;
	}
	
	private static String trimString(String str){
		if(StringUtils.isBlank(str)){
			str = "";
		}
		return str;
	}
	
}
