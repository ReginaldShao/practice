package com.shaojie.demo.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import static com.shaojie.demo.util.Print.*;

public class IntBufferDemo {
	private static final int BSIZE = 1024;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		CharBuffer cb = bb.asCharBuffer();
		cb.put(new char[] {48,49,50,51,52});
		cb.flip();
		while(cb.hasRemaining())
			print(cb.get());
		
		println();
		cb.put(2, (char) 40);
		cb.flip();
		while(cb.hasRemaining())
			print(cb.get());
	}

}
