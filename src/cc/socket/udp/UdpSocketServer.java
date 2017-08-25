package cc.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 基于UDP协议实现Socket通信-服务端
 * 
 * @author 邹峰立
 */
public class UdpSocketServer {

	public static void main(String[] args) throws IOException {
		/**
		 * 接受客户端发送的数据
		 */
		// 1、创建服务端DatagramSocket，指定端口
		DatagramSocket datagramSocket = new DatagramSocket(2501);
		// 2、创建数据报用来接收客户端传递过来的数据
		byte[] data = new byte[1024];// 创建字节数组，指定接受数据包大小
		DatagramPacket datagramPacket = new DatagramPacket(data, data.length);
		// 3、接受客户端发送的数据
		System.out.println("*******服务端已开启*******");
		datagramSocket.receive(datagramPacket);// 此方法在接收到数据报之前会处于阻塞
		// 4、读取数据
		String info = new String(data, 0, datagramPacket.getLength());
		System.out.println("****客户端数据*****" + info);
		
		/**
		 * 响应客户端
		 */
		// 1、定义客户端地址，端口号，数据
		InetAddress inetAddress = datagramPacket.getAddress();
		int port = datagramPacket.getPort();
		byte[] data2 = "我是服务端，你好".getBytes();
		// 2、创建数据报，包含发送信息
		DatagramPacket datagramPacket2 = new DatagramPacket(data2, data2.length, inetAddress, port);
		// 3、向客户端发送数据报
		datagramSocket.send(datagramPacket2);
		
		// 关闭资源
		datagramSocket.close();
	}
}
