package jsoup;

/*
* XPath：XPath即为XML路径语言，它是一种用来确定XML（标准通用标记语言的子集）文档中某部分位置的语言
 * 使用Jsoup的Xpath需要额外导入jar包。
 * 查询w3cSchool参考手册，使用xpath的语法完成查询
* */

import cn.wanghaomiao.xpath.exception.XpathSyntaxErrorException;
import cn.wanghaomiao.xpath.model.JXDocument;
import cn.wanghaomiao.xpath.model.JXNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DemoXpath {

    public static void main(String[] args) throws IOException, XpathSyntaxErrorException {
        //获取student.xml的path
        String path = Demo01.class.getClassLoader().getResource("student.xml").getPath();
        //解析xml文档，加载文档进内存，获取dom树————>Document对象
        Document document = Jsoup.parse(new File(path), "utf-8");

        //根据Document对象创建JXDocument对象
        JXDocument jxDocument = new JXDocument(document);

        //结合Xpath语法(w3cSchool参考手册)查询
        //查询所有的student标签
        List<JXNode> jxNodes1 = jxDocument.selN("//student");
        for (JXNode jxNode : jxNodes1) {
            //System.out.println(jxNode);
        }

        //查询所有student标签下的name标签
        List<JXNode> jxNodes2 = jxDocument.selN("//student/name");
        for (JXNode jxNode : jxNodes2) {
            //System.out.println(jxNode);
        }

        //查询student标签下带有id属性的name标签
        List<JXNode> jxNodes3 = jxDocument.selN("//student/name[@id]");
        for (JXNode jxNode : jxNodes3) {
            //System.out.println(jxNode);
        }

        //查询student标签下带有id属性的name标签。并且id属性值为s1
        List<JXNode> jxNodes4 = jxDocument.selN("//student/name[@id = 's1']");
        for (JXNode jxNode : jxNodes3) {
            System.out.println(jxNode);
        }
    }

}
