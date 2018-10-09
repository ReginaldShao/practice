package com.shaojie.demo.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DirList {

	public static void main(String[] args) {
		File path = new File(args[0]);
		String[] list;
		if(args.length == 1) {
			list = path.list();
		} else {
			System.out.println("regex:"+args[1]);
			list = path.list(new DirFilter(args[1]));
		}
		if(list != null) {
			Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
			for(String item:list) {
				System.out.println(item);
			}
			System.out.println(list.length);
		}
	}

}
class DirFilter implements FilenameFilter {
	private Pattern pattern;
	public DirFilter(String regex) {
		this.pattern = Pattern.compile(regex);
	}
	@Override
	public boolean accept(File dir, String name) {
		return pattern.matcher(name).find();
	}
	
}
