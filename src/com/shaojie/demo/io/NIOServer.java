package com.shaojie.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.shaojie.demo.concurrent.ServerResource;

import static com.shaojie.demo.util.Print.println;

public class NIOServer {

	public static void main(String[] args) {
		try(ServerSocketChannel sc = ServerSocketChannel.open()
				) {
			sc.configureBlocking(false);
			sc.bind(new InetSocketAddress(8088));
			//reactor方式
			//主线程死循环读取事件，调用处理器
			Selector selector = Selector.open();
			sc.register(selector, SelectionKey.OP_READ);
			ExecutorService exec = Executors.newCachedThreadPool();
			while(true){
				int n = selector.select();
				if (n > 0) {
					Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						if (key.isAcceptable()) {

						} else if (key.isConnectable()) {
						} else if (key.isReadable()) {
							ServerSocketChannel s = (ServerSocketChannel) key.channel();
							exec.execute(new ConnectIOnHandler(s.accept()));
						} else if (key.isWritable()) {

						}
						iter.remove();
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	private static Map<String,Class<? extends ServerResource>> map = new ConcurrentHashMap<>();
//	public static void register(String key,Class<? extends ServerResource> clazz) {
//		map.put(key, clazz);
//	}
	
}
