package com.shaojie.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestSubstring {

	public static void main(String[] args) {
//		String s = "qqwertyioplkjytddvnmhsawxffetasdfghjkll1234567890oiuytrqwertyioplkjytddvnmhsawxff";
		String s = "qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq";
		System.out.println("字符串长度：" + s.length());
		Tester.test(new Solution1(), s);
		Tester.test(new Solution2(), s);
		Tester.test(new Solution3(), s);
	}

}

class Solution3 implements Solution {

	@Override
	public int lengthOfLongestSubstring(String s) {
		int i = 0, j = 0, n = s.length();// 子串的起始位置
		int max = 0;
		int rl = 0, rh = 0;
		Map<Character,Integer> map = new HashMap<>();
		Character ch = null;
		for (; j < n;) {
			ch = s.charAt(j++);
			if (map.keySet().contains(ch)) {
				i = Math.max(map.get(ch),i);
			}
			
			map.put(ch, j);
			if (j - i > max) {
				max = j - i;
				rl = i;
				rh = j;
			}
		}
		System.out.println("起始位置: start:" + rl + ",end:" + rh);
		System.out.println(s.substring(rl, rh));
		return max;
	}
	
	@Override
	public String toString() {
		return "solution 3";
	}
	
}
class Solution2 implements Solution {
	@Override
	public int lengthOfLongestSubstring(String s) {
		int i = 0, j = 0, n = s.length();// 子串的起始位置
		int max = 0;
		int rl = 0, rh = 0;
		Set<Character> c = new HashSet<>();
		Character ch = null;
		for (; j < n;) {
			ch = s.charAt(j);
			if (!c.contains(ch)) {
				c.add(ch);
				j++;
				if (j - i > max) {
					max = j - i;
					rl = i;
					rh = j;
				}
			} else {
				c.remove(s.charAt(i++));
			}
		}
		System.out.println("起始位置: start:" + rl + ",end:" + rh);
		System.out.println(s.substring(rl, rh));
		return max;
	}

	@Override
	public String toString() {
		return "solution 2";
	}
}

class Solution1 implements Solution {
	@Override
	public int lengthOfLongestSubstring(String s) {
		int i = 0, j = 0, n = s.length();// 子串的起始位置
		int rl = 0, rh = 0;
		int max = 0;
		for (; i < n; i++) {
			for (j = i; j < n; j++) {
				if (!unique(s, i, j)) {
					if (j - i > max) {
						max = j - i;
						rl = i;
						rh = j;
					}
					break;
				}
			}
			if (j - i > max) {
				max = j - i;
				rl = i;
				rh = j;
			}
		}
		System.out.println("起始位置: start:" + rl + ",end:" + rh);
		System.out.println(s.substring(rl, rh));
		return max;
	}

	@Override
	public String toString() {
		return "solution 1";
	}

	private boolean unique(String s, int i, int j) {
		Set<Character> c = new HashSet<>();
		for (; i <= j; i++) {
			Character ch = s.charAt(i);
			if (c.contains(ch))
				return false;
			c.add(ch);
		}
		return true;
	}
}

class Tester {
	static void test(Solution sl, String s) {
		System.out.print("------");
		System.out.print(sl);
		System.out.println("------");
		long t = System.currentTimeMillis();
		System.out.println("子串最大长度:");
		System.out.println(sl.lengthOfLongestSubstring(s));
		System.out.print("时间:");
		System.out.println(System.currentTimeMillis() - t);
	}
}

interface Solution {
	int lengthOfLongestSubstring(String s);
}
