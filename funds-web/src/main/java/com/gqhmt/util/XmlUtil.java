package com.gqhmt.util;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * xml 转换工具
 *
 * @author zhaoenyue
 * @create 2016年7月25日
 */
public class XmlUtil {
    private static final String DEFAULT_ENCODING = "utf-8";

    /**
     * 指定对象和编码方式将对象解析为xml字符串
     *
     * @param obj
     * @param encoding
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    public static String objectToXmlString(Object obj, String encoding) throws JAXBException, IOException {
        return objectToXmlString(obj, true, false, encoding);
    }

    /**
     * 指定对象和编码方式将对象解析为xml字符串
     *
     * @param obj
     * @param encoding
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    public static String objectToXmlString(Object obj, boolean isFormat, String encoding) throws JAXBException, IOException {
        return objectToXmlString(obj, isFormat, false, encoding);
    }

    /**
     * 按照默认的编码方式将对象解析为xml字符串
     *
     * @param obj
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    public static String objectToXmlString(Object obj) throws JAXBException, IOException {
        return objectToXmlString(obj, null);
    }

    /**
     * @param obj
     * @param isFormat 是否格式化
     * @param cancelXMLHead 是否省略xml文件头
     * @param encoding 编码方式， 默认为“utf-8”
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public static String objectToXmlString(Object obj, boolean isFormat, boolean cancelXMLHead, String encoding) throws JAXBException, IOException {
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, isFormat);
        m.setProperty(Marshaller.JAXB_FRAGMENT, cancelXMLHead);
        m.setProperty(Marshaller.JAXB_ENCODING, encoding);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        m.marshal(obj, out);
        String xmlString = new String(out.toByteArray());
        out.flush();
        out.close();
        return xmlString.replace("standalone=\"yes\"", "");
    }

    /**
     * 将xml字符串解析为对象
     *
     * @param xmlString
     * @param clazz
     * @return
     * @throws DataParseException
     */
    @SuppressWarnings("unchecked")
    public static <T> T xmlStringToObject(String xmlString, Class<T> clazz) throws Exception {
        try {
            ByteArrayInputStream stream = new ByteArrayInputStream(xmlString.getBytes());
            JAXBContext context = JAXBContext.newInstance(clazz);
            Unmarshaller u = context.createUnmarshaller();
            Object object = u.unmarshal(stream);
            T t = null;
            if (object != null) {
                t = (T) object;
            }
            stream.close();
            return t;
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
