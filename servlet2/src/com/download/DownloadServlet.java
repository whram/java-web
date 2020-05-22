package com.download;

import com.utils.DownLoadUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/*
* 定义Servlet
			1. 获取文件名称
			2. 使用字节输入流加载文件进内存
			3. 指定response的响应头： content-disposition:attachment;filename=xxx
			4. 将数据写出到response输出流

* 问题：
		* 中文文件问题
			* 解决思路：
				1. 获取客户端使用的浏览器版本信息
				2. 根据不同的版本信息，设置filename的编码方式不同
* */

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求参数
        String filename = request.getParameter("filename");

        //使用字节输入流加载文件进去内存
        //找到文件的真是路径
        ServletContext context = this.getServletContext();
        String realPath = context.getRealPath("/img/" + filename);

        //使用字节流关联
        FileInputStream fis = new FileInputStream(realPath);

        //设置response的响应头
        //设置类型头content-type
        String mimeType = context.getMimeType(filename);
        response.setHeader("content-type",mimeType);

        //解决中文文件名问题
        //获取user-agent请求头
        String header = request.getHeader("user-agent");
        filename = DownLoadUtils.getFileName(header, filename);

        //设置响应头打开方式content-disposition
        response.setHeader("content-disposition","attachment;filename="+filename);

        //将输入流的数据写出到输出流中
        ServletOutputStream sos = response.getOutputStream();
        byte[] buff = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(buff)) != -1) {
            sos.write(buff,0,len);
        }

        fis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
