package jsoup;

/*
* selector:选择器
 * 使用的方法：Elements	select​(String cssQuery)
 * 语法：参考Selector类中定义的语法
* */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class DemoSelector {

    public static void main(String[] args) throws IOException {
        //获取student.xml的path
        String path = Demo01.class.getClassLoader().getResource("student.xml").getPath();
        //解析xml文档，加载文档进内存，获取dom树————>Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        //查询name标签
        Elements elements = document.select("name");
        //System.out.println(elements);

        //查询id值为s1的元素
        Elements elements1 = document.select("#s1");
        //System.out.println(elements1);

        //获取student标签number属性值为student_0001的age的子标签
        Elements elements2 = document.select("student[number=\"student_0001\"] > age");
        System.out.println(elements2);
    }

}
