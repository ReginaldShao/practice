package com.shaojie.demo.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
import static com.shaojie.demo.util.Print.*;

public class DirList2 {

	public static void main(String[] args) {
		if(args.length == 0) {
			print("Usage: DirList2 目录 [正则表达式]\n");
			System.exit(0);
		}
		File path = new File(args[0]);
		String[] list;
		if(args.length == 1) {
			list = path.list();
		} else {
			System.out.println("regex:"+args[1]);
			list = path.list(filter(args[1]));
		}
		if(list != null) {
			Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
			for(String item:list) {
				println(item);
			}
			println(list.length);
		}
	}
	
	public static FilenameFilter filter(String regex) {
		return new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).find();
			}
		};
	}

}
