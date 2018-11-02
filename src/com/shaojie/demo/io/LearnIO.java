package com.shaojie.demo.io;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;
import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import static com.shaojie.demo.util.Print.*;

public class LearnIO {

	public static void main(String[] args) {
		ByteOutputStream bos = new ByteOutputStream();
		PipedWriter pw = new PipedWriter();
		PipedReader pr = new PipedReader();
		String file = "/home/shaojie/workspace/demo/src/com/shaojie/demo/io/SortDirList.java";
		printWriter(file);
//		memoryInput(file);
//		int c;
//		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//		CharBuffer cb = null;
//		try {
//			while((c=in.read(cb))!= -1) {
//				print(c);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		/*
		try {
//			println(read("/home/shaojie/workspace/demo/src/com/shaojie/demo/io/SortDirList.java"));
//			List<String> ss = readList("/home/shaojie/workspace/demo/src/com/shaojie/demo/io/SortDirList.java");
			List<String> ss = readList("/home/shaojie/workspace/demo/src/com/shaojie/demo/io/LearnIO.java","^import");
			for(int j=ss.size()-1;j>-1;j--) {
				println(ss.get(j));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
	public static void printWriter(String file) {
		String output = file+".out";
		try {
			BufferedReader in = new BufferedReader(new StringReader(read(file)));
			PrintWriter out = new PrintWriter(output);
			int nu = 0;
			String s;
			while( (s=in.readLine()) != null) {
				out.println(++nu+":\t"+s);
			}
			out.close();
			in.close();
			print(read(output));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void formattedMemoryInput(String file) {
		try {
			DataInputStream in = new DataInputStream(new ByteArrayInputStream(read(file).getBytes()));
			while(true) {
				print((char)in.readByte());
			}
		} catch (EOFException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void memoryInput(String file) {
		try {
			StringReader sr = new StringReader(read(file));
			int c;
			while( (c=sr.read()) != -1) {
				print((char)c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static List<String> readList(String filename,String regex) throws IOException{
		Pattern pattern = Pattern.compile(regex);
		BufferedReader in = new BufferedReader(new FileReader(filename));
		List<String> res = new LinkedList<>();
		String s;
		while((s=in.readLine()) != null) {
			if(pattern.matcher(s).find()) {
				res.add(s);
			}
		}
		in.close();
		return res;
	}
	
	public static List<String> readList(String filename) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		List<String> res = new LinkedList<>();
		String s;
		while((s=in.readLine()) != null) {
			res.add(s);
		}
		in.close();
		return res;
	}
	
	public static String read(String filename) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while((s=in.readLine()) != null) {
			sb.append(s+"\n");
		}
		in.close();
		return sb.toString();
	}
	
	public void dataBuffer() {
		String path = "/home/shaojie/data_buffer";
		BufferedInputStream bin = null;
		BufferedOutputStream bout = null;
		try {
			bout = new BufferedOutputStream(new FileOutputStream(new File(path)));
			bout.write(10);
			bout.write(20);
			byte[] d = new byte[]{1,2,3};
			bout.write(d);
			bout.flush();
			bin = new BufferedInputStream(new FileInputStream(new File(path)));
			byte[] d1 = new byte[4];
			while(bin.read(d1) != -1) {
				print(Arrays.toString(d1));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bin != null)
			try {
				bin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(bout != null)
				try {
					bout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public void dataReader() {
		String path = "/home/shaojie/data_reader";
		Writer w = null;
		Reader r = null;
		try {
			w = new OutputStreamWriter(new FileOutputStream(new File(path)));
			w.write("你好\n");
			w.write("世界");
			w.flush();
			r = new InputStreamReader(new FileInputStream(new File(path)));
			char[] cs = new char[1];
			while(r.read(cs) != -1) {
				print(cs[0]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(w != null)
			try {
				w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(r != null)
				try {
					r.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public void dataStream() {
		String path = "/home/shaojie/data_byte";
		DataOutputStream dout = null;
		DataInputStream din = null;
		try {
			dout = new DataOutputStream(new FileOutputStream(new File(path)));
			dout.writeInt(10);
			dout.writeInt(20);
			dout.writeDouble(3.0);
			din = new DataInputStream(new FileInputStream(path));
			int a = din.readInt();
			int b = din.readInt();
			double c = din.readDouble();
			println("a="+a);
			println("b="+b);
			println("c="+c);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(dout != null)
			try {
				dout.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(din != null)
			try {
				din.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
