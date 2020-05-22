package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
* 代理模式
					* 概念：
						1. 真实对象：被代理的对象
						2. 代理对象：
						3. 代理模式：代理对象代理真实对象，达到增强真实对象功能的目的
				 	* 实现方式：
					 	1. 静态代理：有一个类文件描述代理模式
					 	2. 动态代理：在内存中形成代理类
							* 实现步骤：
								1. 代理对象和真实对象实现相同的接口
								2. 代理对象 = Proxy.newProxyInstance();
								3. 使用代理对象调用方法。
								4. 增强方法
							* 增强方式：
								1. 增强参数列表
								2. 增强返回值类型
								3. 增强方法体执行逻辑
* */

public class Test {

    public static void main(String[] args) {
        //创建真实对象
        Lenovo lenovo = new Lenovo();

        //动态代理增强对象
        /*
        * 三个参数
        *   1.类加载器：真是对象.getClass().getClassLoader()
        *   2.接口数组：真是对象.getClass().getInterfaces()
        *   3.处理器：new InvocationHandler(){}
        * */
        SaleComputer proxy_lenovo = (SaleComputer)Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            /*
            * 代理逻辑编写的方法：代理对象调用的所有方法都会触发该方法执行
            * 参数：
            *   1.proxy:代理对象
            *   2.method：代理对象调用的方法，被封装为对象
            *   3.args:代理对象调用的方法时，传递的实际参数
            * */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("执行代理方法");
                System.out.println(method.getName());
                System.out.println(args[0]);
                return null;
            }
        });

        //调用方法
        String sale = proxy_lenovo.sale(10000);
        System.out.println(sale);
    }

}
