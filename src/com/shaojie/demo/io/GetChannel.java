package com.shaojie.demo.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import static com.shaojie.demo.util.Print.*;

public class GetChannel {
	private static final int BSIZE = 1024;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String file = "channnel.txt";
		FileChannel fc = new FileOutputStream(file).getChannel();
		fc.write(ByteBuffer.wrap("some text".getBytes()));
		fc.close();
		//add to the end of file
		fc = new RandomAccessFile(file, "rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("add to end 结束".getBytes()));
		fc.close();
		fc = new FileInputStream(file).getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		fc.close();
		buff.flip();
		byte[] data;
		data = buff.array();
		println(new String(data));
//		while(buff.hasRemaining()) {
//			data = buff.array();
//		}
	}

}
