package com.lqs;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * freemarker单独使用的时候
 * 1、导入坐标
 * 2、创建配置对象
 * 3、配置模板文件的目录和编码字符集
 * 4、加载模板
 * 5、准备数据
 * 6、创建Writer对象，这个对象用于设置写入的目标文件
 * 7、开始融合模板和数据到目标文件
 * 8、关闭Writer对象
 *
 *freemarker的模板文件一般定义为.flt
 *
 *
 * freemarker模板文件的占位符：
 *
 * ${}:预留位置插值
 * {#--注释--}：注释语句
 * {#assign 变量名=值}:定义变量，在页面上定义一个变量，可以是对象,如果Java代码和assign都有这个变量的值，那么按照assign的值为准
 * {#include "xxx.flt"}:引入其他的模板文件
 * {#if xxx=xx}xxxx{#else}xxxx{/#if}:条件判断
 * {#list goodsList as goods}xxx{/#list}:遍历对象
 *
 *
 *
 */
public class AppTest 
{
    /**
     * freemarker入门案例
     * Rigorous Test :-)
     */
   @Test
    public void test1() throws IOException, TemplateException {
       // 创建freemarker的配置对象
       Configuration configuration = new Configuration(Configuration.getVersion());
       // 设置模板文件的位置,不带文件名，只是指定目录
       configuration.setDirectoryForTemplateLoading(new File("/home/lqs/flt"));
       // 设置编码,字符集
       configuration.setDefaultEncoding("utf-8");
       // 加载静态文件模板
       Template template = configuration.getTemplate("test.flt");

       // 准备数据
       Map map = new HashMap();
       map.put("name", "李奇凇");

       // 创建writer对象
       Writer out = new FileWriter(new File("/home/lqs/flt/test.html"));
        // 输出
       template.process(map,out);
       // 关闭writer
       out.close();
   }
}
