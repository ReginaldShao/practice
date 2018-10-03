package com.shaojie.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.stream.FileImageInputStream;

public class Base64Test {
	public static void main(String[] args) {
		String imgPath = "/home/shaojie/pictures/gant1.png";
		String newPath = "/home/shaojie/pictures/gant1-1.png";
		File file = new File(imgPath);
		byte[] data = new byte[(int) file.length()];
		FileImageInputStream fi = null;
		try {
			fi = new FileImageInputStream(file);
			fi.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String bs64 = Base64.getEncoder().encodeToString(data);
		System.out.println("base64 str: "+bs64);
		
		byte[] ndata = Base64.getDecoder().decode(bs64);
		File nImg = new File(newPath);
		FileOutputStream fo = null;
		try {
			fo = new FileOutputStream(nImg);
			fo.write(ndata);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { fo.close(); } catch (IOException e) { }
		}
	}
}
