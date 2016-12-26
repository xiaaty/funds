package com.gqhmt.util;

import org.apache.log4j.Logger;
import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * XML文件操作类
 * @author zhaoenyue
 *
 */
public class DataHelper {
	private static Logger logger = Logger.getLogger(DataHelper.class);
	
	/*
	 * 构造函数
	 */
	public static DataHelper getInstance() {
		return new DataHelper();
	}
	
	/**
	 * 转换text为Doc
	 * @param text
	 * @return
	 */
	public static Document transTextToDoc(String text) {
		try {
			Document document = DocumentHelper.parseText(text);
			return document;
		}
		catch (Exception ex) {
			logger.debug(ex);
			return null;
		}
	}
	
	/**
	 * 将DOC对象内容转换为文本
	 * @param branch Branch Branch对象
	 * @return String 
	 */
	public static String transDocToText(Branch branch) {
		String text = "";
		if (branch != null) {
			text = branch.asXML();
		}
		return text;
	}
	
	/**
	 * 读取XML文件
	 * @param file String 全文件名路径
	 * @return Document DOM4J
	 */
	public static Document read(String file) {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(new File(file));
			return document;
		}
		catch (Exception e) {
			logger.debug(e);
			return null;
		}
	}
	
	/**
	 * 读取XML文件
	 * @param file String 全文件名路径
	 * @return Document DOM4J
	 */
	public static Document read(File file) {
		try {
			SAXReader reader = new SAXReader();
			Document document = reader.read(file);
			return document;
		}
		catch (Exception e) {
			logger.debug(e);
			return null;
		}
	}
	
	/**
	 * 创建DOM4J Document对象
	 * @return Document DOM4J
	 */
	public static Document createDocument() {
		Document document = DocumentHelper.createDocument();
		return document;
	}
	
	public static Element getRootElement(Document document) {
		return document.getRootElement();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Element> findElements(Document document, String XPath) {
		List<Element> list = document.selectNodes(XPath);
		if (list != null) {
			int size = list.size();
			if (size == 0) {
				list = null;
			}
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static List<Element> findElements(Element element, String XPath) {
		List<Element> list = element.selectNodes(XPath);
		if (list != null) {
			int size = list.size();
			if (size == 0) {
				list = null;
			}
		}
		return list;
	}
	
	/**
	 * 返回符合条件的节点
	 * @param document Document
	 * @param XPath String
	 * @return
	 */
	public static Node findNode(Document document, String XPath) {
		Node node = document.selectSingleNode(XPath);
		return node;
	}
	
	/**
	 * 返回符合条件的节点
	 * @param element Element
	 * @param XPath String
	 * @return Node
	 */
	public static Node findNode(Element element, String XPath) {
		Node node = element.selectSingleNode(XPath);
		return node;
	}
	
	
	/**
	 * 得到节点TEXT值
	 * @param branch Branch
	 * @param nodeName 节点名字
	 * @return String 节点Text
	 */
	public static String getNodeValue(Branch branch) {
		String text = "";
		if (branch != null) {
			text = branch.getText();
		}
		return text;
	}
	
	/**
	 * 得到Element中属性值
	 * @param element Element
	 * @param attributeID String属性名
	 * @return
	 */
	public static String getAttributeValue(Element element, String attributeID) throws Exception {
		//System.out.println(element.asXML());
		String text = element.attributeValue(attributeID);
		return text;
	}
	
	/**
	 * 将Node转化为Element
	 * @param node Node
	 * @return Element
	 */
	public static Element transNodeToElement(Node node) {
		Node node1 = node;
		Element element = (Element)node1;
		return element;
	}
	
	public static String[] splitStringToArray(String value, String delim) {
		return value.split(delim);
	}
	
	/**
	 * 文件COPY功能
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static boolean fileCopy(String oldPath , String newPath) throws Exception{
		try {
			
			File file = new File(oldPath) ;
			
			FileInputStream fis = new FileInputStream(file);

			FileOutputStream fos = new FileOutputStream(newPath);
			
//			byte[] iobuff = new byte[(int) file.length()];
			byte[] iobuff = new byte[2048];
			int bytes;

			while ((bytes = fis.read(iobuff)) != -1) {
				fos.write(iobuff, 0, bytes);
			}
			fis.close();
			fos.close();
		} catch (Exception e) {
			throw new Exception(e);
		}
		return true ;
	}
	
	/**
	 * 删除当前根目录下的指定文件
	 * @param fileName 指定目录下的文件名
	 * @return boolean
	 */
	public static boolean deleteFile(String fileName) {
		try {
			File file = new File(fileName);
			file.delete();
		}
		catch (Exception ex) {
			return false;
		}
    	return true;
    }
}
