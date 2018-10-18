package com.shaojie.demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static com.shaojie.demo.util.Print.*;

public class OldIODemo {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(100);// 线程池

		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(8088);
			while (!Thread.interrupted()) {// 主线程死循环等待新连接到来
				Socket socket = serverSocket.accept();
				executor.submit(new ConnectIOnHandler(socket));// 为新的连接创建新的线程
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class ConnectIOnHandler extends Thread {
	private Socket socket;

	public ConnectIOnHandler(Socket socket) {
		this.socket = socket;
	}

	private static final int BSIZE=512;
	public void run() {
//		while (!Thread.interrupted() && !socket.isClosed()) {// 死循环处理读写事件
			try {
//				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				int length=0;
				String l;
				do {
					l = readLine(socket.getInputStream());
					if(l.startsWith("Content-Length")) {
						length = Integer.parseInt(l.split(":")[1].trim());
					}
					print(l);
				}while(!l.equals("\r\n"));
				
				println("header is end.Content-length:"+length);
				println(readLine(socket.getInputStream(),length));
				String body = "<html>\n" + 
						"<head>\n" + 
						"<title>Wrox Homepage</title>\n" + 
						"</head>\n" + 
						"<body>\n" + 
						"<!-- body goes here -->hello world!\n" + 
						"</body>\n" + 
						"</html>";
				String resp = "HTTP/1.1 200 OK\n" + 
						"Server: nginx/1.10.1\n" + 
						"Date: Fri, 12 Oct 2018 02:50:07 GMT\n" + 
						"Last-Modified: Tue, 25 Sep 2018 08:38:44 GMT\n" + 
						"Connection: keep-alive\n" + 
						"Content-Type: text/html\n"+
						"Content-Length: "+body.getBytes("utf-8").length+"\n"+
						"\n"+body;
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				bw.write(resp);
				bw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*SocketChannel sc = socket.getChannel();
			ByteBuffer bb = ByteBuffer.allocate(512);
			byte[] by = new byte[BSIZE];
			try {
				while(sc.read(bb) != -1) {
					while(bb.hasRemaining()) {
						bb.flip();
						bb.get(by);
						String s = new String(by);
						println(s);
					}
					bb.clear();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}*/
//		}
	}
	private String readLine(InputStream in,int length) {
		byte[] data = new byte[length];
		try {
			in.read(data);
			return new String(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private String readLine(InputStream in) {
		List<Byte> line = new LinkedList<>();
		byte c = 0;
		do {
			try {
				c = (byte) in.read();
				line.add(c);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(c != 10);
		byte[] ld = new byte[line.size()];
		for(int k=0;k<line.size();k++) {
			ld[k] = line.get(k);
		}
		return new String(ld);
	}
	/*
	 * 这里我们自己模拟读取一行，因为如果使用API中的BufferedReader时，它是读取到一个回车换行后
	 * 才返回，否则如果没有读取，则一直阻塞，直接服务器超时自动关闭为止，如果此时还使用BufferedReader
	 * 来读时，因为读到最后一行时，最后一行后不会有回车换行符，所以就会等待。如果使用服务器发送回来的
	 * 消息头里的Content-Length来截取消息体，这样就不会阻塞
	 * 
	 * contentLe 参数 如果为0时，表示读头，读时我们还是一行一行的返回；如果不为0，表示读消息体，
	 * 时我们根据消息体的长度来读完消息体后，客户端自动关闭流，这样不用先到服务器超时来关闭。
	 *
	private static String readLine(InputStream is, int contentLe) throws IOException {
		ArrayList<Byte> lineByteList = new ArrayList<>();
		byte readByte;
		int total = 0;
		if (contentLe != 0) {
			do {
				readByte = (byte) is.read();
				lineByteList.add(Byte.valueOf(readByte));
				total++;
			} while (total < contentLe);//消息体读还未读完
		} else {
			do {
				readByte = (byte) is.read();
				lineByteList.add(Byte.valueOf(readByte));
			} while (readByte != 10);
		}

		byte[] tmpByteArr = new byte[lineByteList.size()];
		for (int i = 0; i < lineByteList.size(); i++) {
			tmpByteArr[i] = ((Byte) lineByteList.get(i)).byteValue();
		}
		lineByteList.clear();

		return new String(tmpByteArr);
	}*/
}