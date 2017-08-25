package cc.socket.tcp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 定义服务端线程
 * 
 * @author 邹峰立
 */
public class TcpServerThread extends Thread {
	// 和本线程相关的socket
	private Socket socket = null;

	public TcpServerThread(Socket socket) {
		this.socket = socket;
	}

	// 线程执行的操作，响应客户端的请求
	@Override
	public void run() {
		super.run();
		if (socket != null) {
			InputStream is = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			OutputStream os = null;
			PrintWriter pw = null;
			try {
				// 获取输入流，并读取客户端信息
				is = socket.getInputStream();
				isr = new InputStreamReader(is);// 转换成字符输入流
				br = new BufferedReader(isr);// 字符输入流缓冲
				String info = null;
				while ((info = br.readLine()) != null) {// 循环读取客户端的信息
					System.out.println("*******客户端信息：" + info);
				}
				socket.shutdownInput();// 关闭输入流

				// 获取输出流，相应客户端
				os = socket.getOutputStream();
				pw = new PrintWriter(os);// 将输出流包装为打印流
				pw.write("我是服务端，你好");
				pw.flush();// 刷新缓存，将缓存输出
				socket.shutdownOutput();// 关闭输出流
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				// 关闭资源
				try {
					if (pw != null)
						pw.close();
					if (os != null)
						os.close();
					if (br != null)
						br.close();
					if (isr != null)
						isr.close();
					if (is != null)
						is.close();
					if (socket != null)
						socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
