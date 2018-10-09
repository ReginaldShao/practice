package com.shaojie.demo.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;
import static com.shaojie.demo.util.Print.*;

public class SortDirList {
	public static void main(String[] args) {
		SortDirList sdl = new SortDirList(".");
		for(String i: sdl.list()) {
			print(i+" ");
		}
		print("\n...........\n");
		println("size:");
		print(sdl.size());
		println();
		
		for(String i: sdl.list("set")) {
			print(i+" ");
		}
		print("\n...........\n");
		println("size:");
		print(sdl.size("set"));
		
	}
	
	private File dir;
	public SortDirList(String dir) {
		this.dir = new File(dir);
	}
	
	public String[] list() {
		String[] list = dir.list();
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		return list;
	}
	
	public String[] list(String regex) {
		String[] list = dir.list(filter(regex));
		Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
		return list;
	}
	
	public long size(String regex) {
		long size = 0;
		for(File f : dir.listFiles(filter(regex))) {
			size += f.length();
		}
		return size;
	}
	public long size() {
		long size = 0;
		for(File f : dir.listFiles()) {
			size += f.length();
		}
		return size;
	}
	private static FilenameFilter filter(String regex) {
		return new FilenameFilter() {
			private Pattern pattern = Pattern.compile(regex);
			@Override
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).find();
			}
		};
	}
}
