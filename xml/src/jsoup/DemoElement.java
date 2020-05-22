package jsoup;

/*
* Element：元素对象
				1. 获取子元素对象
					* getElementById​(String id)：根据id属性值获取唯一的element对象
					* getElementsByTag​(String tagName)：根据标签名称获取元素对象集合
					* getElementsByAttribute​(String key)：根据属性名称获取元素对象集合
					* getElementsByAttributeValue​(String key, String value)：根据对应的属性名和属性值获取元素对象集合

				2. 获取属性值
					* String attr(String key)：根据属性名称获取属性值
				3. 获取文本内容
					* String text():获取文本内容
					* String html():获取标签体的所有内容(包括字标签的字符串内容)
* */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class DemoElement {

    public static void main(String[] args) throws IOException {
        //获取student.xml的path
        String path = Demo01.class.getClassLoader().getResource("student.xml").getPath();
        //解析xml文档，加载文档进内存，获取dom树————>Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        //通过Document对象获取name标签，获取所有的name标签的值
        Elements elements = document.getElementsByTag("name");
        //System.out.println(elements.size());

        //通过Element对象获取子标签对象
        Element element_student = document.getElementsByTag("student").get(0);
        Elements ele_name = element_student.getElementsByTag("name");
        //System.out.println(ele_name.size());

        //获取student对象的属性值
        String number = element_student.attr("number");
        //System.out.println(number);

        //获取文本内容
        String text = ele_name.text();
        System.out.println(text);
        String html = ele_name.html();
        System.out.println(html);
    }

}
