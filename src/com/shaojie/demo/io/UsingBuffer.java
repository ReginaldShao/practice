package com.shaojie.demo.io;

import java.nio.ByteBuffer;

import static com.shaojie.demo.util.Print.*;

public class UsingBuffer {

	private static final int BSIZE=1024;
	public static void main(String[] args) {
		ByteBuffer bb = ByteBuffer.allocate(BSIZE);
		println(bb);
		bb.asCharBuffer().put("abcdefg");
		bb.limit(7);
		bb.rewind();
		println(bb);
		while(bb.hasRemaining()) {
			char c = (char) bb.get();
			print(c);
		}
	}
}
