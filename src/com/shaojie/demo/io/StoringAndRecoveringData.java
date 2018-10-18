package com.shaojie.demo.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.shaojie.demo.util.Print.*;

public class StoringAndRecoveringData {

	public static void main(String[] args) throws IOException {
		String file = "data.txt";
		// TODO Auto-generated method stub
		DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		out.writeDouble(3.14159);
		out.writeUTF("That was pi");
		out.writeDouble(1.41413);
		out.writeUTF("Square root of 2");
		out.close();
		DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		println(in.readDouble());
		println(in.readUTF());
		println(in.readDouble());
		println(in.readUTF());
	}

}
