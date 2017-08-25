package cc.socket.tcp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * TCP-客户端
 * 
 * @author 邹峰立
 */
public class TcpSocketClient2 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 1、创建客户端Socket，指定服务端地址和端口
		Socket socket = new Socket("localhost", 2500);
		
		// 2、创建输出流，向服务端发送消息
		OutputStream os = socket.getOutputStream();
		PrintWriter pw = new PrintWriter(os);// 将输出流包装为打印流
		pw.write("我是客户端，你好啊");
		pw.flush();// 刷新缓存
//		pw.close();// 不能关闭输出流，会导致socket也关闭
		socket.shutdownOutput();// 关闭输出流
		
		// 3、获取输入流，并读取服务端信息
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);// 转换成字符输入流
		BufferedReader br = new BufferedReader(isr);// 字符输入流缓冲
		String info=null;
		while((info=br.readLine())!=null){// 循环读取客户端的信息
			System.out.println("*******服务端信息：" + info);
		}
		socket.shutdownInput();// 关闭输入流
				
		// 4、关闭资源
		br.close();
		isr.close();
		is.close();
		pw.close();
		os.close();
		socket.close();
	}
}
