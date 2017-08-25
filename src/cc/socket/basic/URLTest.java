package cc.socket.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * URL统一资源定位符，通过资源可以读取和写入网络上的数据
 * 
 * @author 邹峰立
 */
public class URLTest {

	public static void main(String[] args) throws IOException {
		// 创建URL实例
		URL baidu = new URL("http://www.baidu.com");
		// 根据已存在URL可以创建一个新的URL
		URL url = new URL(baidu, "/index.html?username=tom#test");// ?后面表示参数，#后面表示锚点
		System.out.println("协议：" + url.getProtocol());
		System.out.println("主机：" + url.getHost());
		// 如果未指定端口号，则使用默认端口号，此时url.getPort()获取的返回值为-1
		System.out.println("端口：" + url.getPort());
		System.out.println("文件路径：" + url.getPath());
		System.out.println("文件名：" + url.getFile());
		System.out.println("相对路径：" + url.getRef());
		System.out.println("查询字符串：" + url.getQuery());
		
		
		/**
		 * 使用URL获取网络内容
		 */
		// 通过URL的openStream方法获取URL对象所表示的资源的字节输入流
		InputStream is = baidu.openStream();
		// 将字节输入流转换成字符输入流（字节流与字符流之间的桥梁）
		InputStreamReader isr = new InputStreamReader(is, "utf8");
		// 为字符输入流添加缓冲（从字符输入流中读取文本）
		BufferedReader br = new BufferedReader(isr);
		String data = null;
		while ((data = br.readLine()) != null) {
			System.out.println("当前行数据：" + data);
		}
	}
}
