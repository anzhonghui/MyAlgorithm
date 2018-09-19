package com.qk.myleetcode.easy;

import org.junit.Test;

public class ImplementStrMethod {

	@Test
	public void MyTest() {
		System.out.println(strStr("hello", "ll"));
		System.out.println(strStr("aaaaa", "bba"));
		System.out.println(strStr("bbbbaaaaa", "bba"));
		System.out.println(strStr("a", ""));
		System.out.println(strStr("", "a"));
		System.out.println(strStr("", ""));
	}

	/**
	 * Input: haystack = "hello", needle = "ll" Output: 2
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr(String haystack, String needle) {
		
		if("".equals(needle)) {
			return 0;
		}
		
		if (haystack == null || haystack.length() == 0 || "".equals(haystack)) {
			return -1;
		}

		int i = 0;
		int j = 0;

		while (i < haystack.length()) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				j++;
			} else if (j != 0) {
				i -= j;
				j = 0;
			}
			
			i++;
			
			if(j == needle.length()) {
				return i - j;
			}
			
		}
		
		return -1;
	}
}
