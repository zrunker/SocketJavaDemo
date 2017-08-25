package cc.socket.tcp2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 基于TCP协议实现Socket通信-服务端
 * ObjectInputStream 对象
 * ObjectOutputStream
 * FileInputStream 文件
 * FileOutputStram
 * @author 邹峰立
 */
public class TcpSocketServer2 {

	public static void main(String[] args) throws IOException {
		// 1、创建一个服务器端Socket，即ServerSocket，指定绑定的端口，并监听此端口
		// （0~65535，使用1023之后的端口，0~1023一般为系统默认端口）
		ServerSocket serverSocket = new ServerSocket(2500);
		Socket socket = null;
		// 记录客户端的数量
		int count = 0;
		System.out.println("*****服务端已开启******");
		// 循环监听等待客户端的连接
		while (true) {
			// 调用accept()方法开始监听，等待客户端的连接
			socket = serverSocket.accept();
			// 创建一个新的线程
			TcpServerThread tcpServerThread = new TcpServerThread(socket);
			tcpServerThread.setPriority(4);// 设置线程优先级，[0,10]，默认5
			// 启动线程
			tcpServerThread.start();

			count++;// 统计客户端的数量
			System.out.println("客户端的数量：" + count);
			InetAddress address = socket.getInetAddress();
			System.out.println("当前客户端的IP：" + address.getHostAddress());
		}
		// serverSocket.close();
	}
}
