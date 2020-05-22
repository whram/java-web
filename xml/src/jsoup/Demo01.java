package jsoup;

/*
* Jsoup快速入门
* 步骤：
					1. 导入jar包
					2. 获取Document对象
					3. 获取对应的标签Element对象
					4. 获取数据
对象的使用：
			1. Jsoup：工具类，可以解析html或xml文档，返回Document
			2. Document：文档对象。代表内存中的dom树
			3. Elements：元素Element对象的集合。可以当做 ArrayList<Element>来使用
			4. Element：元素对象
			5. Node：节点对象
* */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Demo01 {

    public static void main(String[] args) throws IOException {
        //获取Document对象，根据xml文档获取
        //获取student.xml的path
        String path = Demo01.class.getClassLoader().getResource("student.xml").getPath();
        //解析xml文档，加载文档进内存，获取dom树————>Document
        Document document = Jsoup.parse(new File(path), "utf-8");
        //获取元素对象 Element
        Elements elements = document.getElementsByTag("name");
        //获取第一个element对象
        Element element = elements.get(0);
        //获取数据
        String name = element.text();
        System.out.println(name);
    }

}
