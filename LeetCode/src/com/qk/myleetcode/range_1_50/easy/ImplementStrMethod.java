package com.qk.myleetcode.range_1_50.easy;

import org.junit.Test;
/**
 * 
 * 28.Implement Str()
 *  ┏┓　　┏┓
 * ┏┛┻━━━━┛┻┓
 * ┃　　　　　┃
 * ┃　　　━　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　 ┃
 * ┃　　　┻　　┃
 * ┃　　　　　 ┃
 * ┗━━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　┣┓
 * 　　┃　　　　　　┏┛
 * 　　┗┓┓┏━┳┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
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
	 * 匹配字符串 Input: haystack = "hello", needle = "ll" Output: 2
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr(String haystack, String needle) {

		if ("".equals(needle)) {
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
			} else if (j != 0) { // 出现不匹配的情况，恢复
				i -= j;
				j = 0;
			}

			i++;
			if (j == needle.length()) {
				return i - j;
			}
		}

		return -1;
	}
}
