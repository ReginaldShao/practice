package com.shaojie.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.shaojie.demo.concurrent.ServerResource;

public class NIOServer {

	public static void main(String[] args) {
		try(ServerSocketChannel sc = ServerSocketChannel.open();
				) {
			sc.bind(new InetSocketAddress(8088));
			//reactor方式
			//主线程死循环读取事件，调用处理器
			Selector selector = Selector.open();
			selector.select();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static Map<String,Class<? extends ServerResource>> map = new ConcurrentHashMap<>();
	public static void register(String key,Class<? extends ServerResource> clazz) {
		map.put(key, clazz);
	}
	
}
