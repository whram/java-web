package jsoup;

/*
* Jsoup：工具类，可以解析html或xml文档，返回Document
 * parse：解析html或xml文档，返回Document
 * parse​(File in, String charsetName)：解析xml或html文件的。
 * parse​(String html)：解析xml或html字符串
 * parse​(URL url, int timeoutMillis)：通过网络路径获取指定的html或xml的文档对象
* */

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class DemoJsoup {

    public static void main(String[] args) throws IOException {
        //获取student.xml的path
        String path = Demo01.class.getClassLoader().getResource("student.xml").getPath();
        //解析xml文档，加载文档进内存，获取dom树————>Document
        Document document = Jsoup.parse(new File(path), "utf-8");
        //System.out.println(document);

        //解析html或xml文档，返回Document
        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "\n" +
                " <students>\n" +
                " \t<student number=\"student_0001\">\n" +
                " \t\t<name>tom</name>\n" +
                " \t\t<age>18</age>\n" +
                " \t\t<sex>male</sex>\n" +
                " \t</student>\n" +
                "\n" +
                "\t<student number=\"student_0002\">\n" +
                "\t\t<name>tom</name>\n" +
                "\t\t<age>18</age>\n" +
                "\t\t<sex>male</sex>\n" +
                "\t</student>\n" +
                "\t\t \n" +
                " </students>";

        Document document1 = Jsoup.parse(str);
        //System.out.println(document1);

        //parse​(URL url, int timeoutMillis)：通过网络路径获取指定的html或xml的文档对象
        URL url = new URL("http://www.baidu.com");
        Document document2 = Jsoup.parse(url, 10000);
        System.out.println(document2);
    }

}
