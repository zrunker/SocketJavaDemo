package cc.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议实现Socket通信-服务端
 * 
 * @author 邹峰立
 */
public class TcpSocketServer {

	public static void main(String[] args) throws IOException {
		// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		// （1023之后的端口，0~1023一般为系统默认端口）
		ServerSocket serverSocket = new ServerSocket(2500);
		
		// 2、调用accept()方法开始监听，等待客户端的连接
		Socket socket = serverSocket.accept();
		System.out.println("*****服务端已开启******");
		
		// 3、获取输入流，并读取客户端信息
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);// 转换成字符输入流
		BufferedReader br = new BufferedReader(isr);// 字符输入流缓冲
		String info=null;
		while((info=br.readLine())!=null){// 循环读取客户端的信息
			System.out.println("*******客户端信息：" + info);
		}
		socket.shutdownInput();// 关闭输入流
		
		// 4、获取输出流，响应客户端
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);// 将输出流包装为打印流
		pw.write("我是服务端，你好");
		pw.flush();// 刷新缓存，将缓存输出
		socket.shutdownOutput();// 关闭输出流
		
		// 5、关闭资源
		pw.close();
		os.close();
		br.close();
		isr.close();
		is.close();
		socket.close();
		serverSocket.close();
	}
}
