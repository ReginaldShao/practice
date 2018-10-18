package com.shaojie.demo.io;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;
import static com.shaojie.demo.util.Print.*;

public class AvilableCharsets {

	public static void main(String[] args) {
		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		Iterator<String> it = charSets.keySet().iterator();
		while(it.hasNext()) {
			String csName = it.next();
			print(csName);
			Iterator<String> aliase = charSets.get(csName).aliases().iterator();
			if(aliase.hasNext()) {
				print(": ");
			}
			while(aliase.hasNext()) {
				print(aliase.next());
				if(aliase.hasNext())
					print(",");
			}
			println();
		}
	}

}
