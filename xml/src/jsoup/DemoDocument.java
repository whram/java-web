package jsoup;

/*
* Document：文档对象。代表内存中的dom树
 * 获取Element对象
 * getElementById​(String id)：根据id属性值获取唯一的element对象
 * getElementsByTag​(String tagName)：根据标签名称获取元素对象集合
 * getElementsByAttribute​(String key)：根据属性名称获取元素对象集合
 * getElementsByAttributeValue​(String key, String value)：根据对应的属性名和属性值获取元素对象集合
* */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class DemoDocument {
    public static void main(String[] args) throws IOException {
        //获取student.xml的path
        String path = Demo01.class.getClassLoader().getResource("student.xml").getPath();
        //解析xml文档，加载文档进内存，获取dom树————>Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        //获取所有student对象
        Elements elements = document.getElementsByTag("student");
        //System.out.println(elements);

        //获取属性名为id的对象
        Elements elements1 = document.getElementsByAttribute("id");
        //System.out.println(elements1);

        //获取number属性值为student_0001的元素对象
        Elements elements2 = document.getElementsByAttributeValue("number", "student_0001");
        //System.out.println(elements2);

        //根据id的属性值获取
        Element element = document.getElementById("s1");
        System.out.println(element);
    }
}
