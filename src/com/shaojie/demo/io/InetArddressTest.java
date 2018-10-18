package com.shaojie.demo.io;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import static com.shaojie.demo.util.Print.*;

public class InetArddressTest {

	public static void main(String[] args) {
		try {
			InetAddress ad = InetAddress.getByName("www.yeekao.cn");
			println(ad.getHostName()+"/"+ad.getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void getAllInterface() throws SocketException {
		Enumeration<NetworkInterface> ins = NetworkInterface.getNetworkInterfaces();
		if(ins == null) {
			println("not found any interface");
		}
		while(ins.hasMoreElements()) {
			NetworkInterface in = ins.nextElement();
			println("interfaceName:"+in.getName());
			Enumeration<InetAddress> ads = in.getInetAddresses();
			while(ads.hasMoreElements()) {
				InetAddress ad = ads.nextElement();
				print("add: ");
				println(ad.getHostName()+"/"+ad.getHostAddress());
			}
		}
	}
}
