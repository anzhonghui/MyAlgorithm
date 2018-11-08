package com.qk.myleetcode.range_51_100.easy;

import org.junit.Test;

/**
 * 58. Length Of Last Word
 * @Description : 找出最后一个单词的长度
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月8日
 */
public class LengthOfLastWord {

	/**
	 * @Description:
	 * @Pragramme:
	 * 方案一：正向查找法
	 * 方案二：反向查找法
	 */
	@Test
	public void MyTest() {
		System.out.println(lengthOfLastWordTuning1("Hello World"));
		System.out.println(lengthOfLastWordTuning1("HelloWorld"));
		System.out.println(lengthOfLastWordTuning1(""));
		System.out.println(lengthOfLastWordTuning1(" "));
		System.out.println(lengthOfLastWordTuning1("        "));
		System.out.println(lengthOfLastWordTuning1("a "));
		System.out.println(lengthOfLastWordTuning1(" a "));
	}

	/**
	 * 对方案一的优化：采用反向法，找到结果直接返回，避免遍历其他的不必要的数据
	 * @param s
	 * @return
	 */
	public int lengthOfLastWordTuning1(String s) {
		if(s == null || s.length() == 0 || "".equals(s)) {
			return 0;
		}
		String str = s.trim();
		for (int i = str.length() - 1; i >= 0; --i) {
			if(str.charAt(i) == ' ') {
				return str.length() - i - 1;
			}
		}
		
		return str.length();
	}
	
	/**
	 * 方案一：采用正向的方法
	 * @param s
	 * @return
	 */
	public int lengthOfLastWord(String s) {
		if(s == null || s.length() == 0 || "".equals(s)) {
			return 0;
		}
		int preStart = 0;
		String string = s.trim();
		for (int i = 0; i < string.length(); ++i) {
			if (string.charAt(i) == ' ') {
				preStart = i + 1;
			}
		}
		return string.length() - preStart;
	}
	
}
