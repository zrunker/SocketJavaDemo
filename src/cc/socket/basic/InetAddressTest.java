package cc.socket.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * InetAddress用于标识网络上硬件资源
 * 
 * @author 邹峰立
 */
public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// 获取本机的InetAddress实例
		InetAddress inetAddress = InetAddress.getLocalHost();
		System.out.println("计算机名：" + inetAddress.getHostName());
		System.out.println("IP地址：" + inetAddress.getHostAddress());
		// 获取字节数组形式的IP地址
		byte[] bytes = inetAddress.getAddress();
		System.out.println("字节数组形式的IP地址：" + Arrays.toString(bytes));
		// 直接输出InetAddress对象
		System.out.println(inetAddress);
		
		// 根据主机名获取InetAddress实例
		// InetAddress address = InetAddress.getByName("SC-201702092057");
		// 根据IP地址获取InetAddress实例
		InetAddress address = InetAddress.getByName("192.168.155.1");
		System.out.println("计算机名：" + address.getHostName());
		System.out.println("IP地址：" + address.getHostAddress());
	}
}
