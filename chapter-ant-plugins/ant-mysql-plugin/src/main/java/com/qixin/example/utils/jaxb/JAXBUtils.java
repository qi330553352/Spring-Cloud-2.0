package com.qixin.example.utils.jaxb;

import com.qixin.example.entity.FileSizes;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;

/**
 * 创 建 时 间: 2019/4/9
 * 版       本: V1.0
 * 作       者: qixin
 * 版 权 所 有: 版权所有(C)2019-2029
 */
public class JAXBUtils {


    //对象转报文
    public static String toXML(Object obj){
        Class c = obj.getClass();
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(marshaller.JAXB_ENCODING,"utf-8");//设置编码
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);// 是否格式化生成的xml串
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);// 是否省略xm头声明信息
            StringWriter sw = new StringWriter();
            marshaller.marshal(obj,sw);
            return sw.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    //报文转对象
    public static <T> T fromXml(Class<T> type,String xml){
        try {
            JAXBContext context = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        String str = "D:\\360Downloads\\jaxb.xml";
        FileInputStream fis = new FileInputStream(new File(str));
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        StringBuffer sb = new StringBuffer();
        String temp = "";
        while ((temp = br.readLine()) != null) {
            sb.append(temp);
        }
        FileSizes fileSizes = JAXBUtils.fromXml(FileSizes.class,sb.toString());
        System.out.println();
        System.out.println(fileSizes);
    }
}
