package cc.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * UDP-客户端
 * 
 * @author 邹峰立
 */
public class UdpSocketClient {

	public static void main(String[] args) throws IOException {
		/**
		 * 向服务端发送数据
		 */
		// 1、定义服务端地址，端口号，数据
		InetAddress inetAddress = InetAddress.getByName("localhost");
		int port = 2501;
		byte[] data = "我是客户端，你好".getBytes();
		// 2、创建数据报，包含发送信息
		DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, port);
		// 3、创建DataGramSocket对象
		DatagramSocket datagramSocket = new DatagramSocket();
		// 4、向服务端发送数据报
		datagramSocket.send(datagramPacket);
		
		/**
		 * 接受服务端响应的数据
		 */
		// 1、创建数据报用来接收服务端传递过来的数据
		byte[] data2 = new byte[1024];// 创建字节数组，指定接受数据包大小
		DatagramPacket datagramPacket2 = new DatagramPacket(data2, data2.length);
		// 2、接受服务端发送的数据
		datagramSocket.receive(datagramPacket2);// 此方法在接收到数据报之前会处于阻塞
		// 2、读取数据
		String info = new String(data2, 0, datagramPacket2.getLength());
		System.out.println("****服务端数据*****" + info);
		
		// 关闭资源
		datagramSocket.close();
	}
}
