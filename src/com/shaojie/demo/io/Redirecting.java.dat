package com.shaojie.demo.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import static com.shaojie.demo.util.Print.*;

public class Redirecting {

	public static void main(String[] args) throws IOException {
		String path = "/home/shaojie/workspace/demo/src/com/shaojie/demo/io/Redirecting.java";
		String o = "/home/shaojie/workspace/demo/src/com/shaojie/demo/io/Redirecting.java.dat";
		PrintStream console = System.out;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
		PrintStream out = new PrintStream(new FileOutputStream(o));
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s=br.readLine()) != null) {
			println(s);
		}
		try {
			throw new RuntimeException("test");
		}catch (Exception e) {
			throw new RuntimeException("test");
		} finally {
			out.close();
			System.setOut(console);
		}
	}

}
