package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题50：第一次只出现一次的字符（第一次出现的不重复的字符）
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class InterviewQuestions50 {

	/**
	 * @Description:
	 */
	@Test
	public void MyTest() {
		System.out.println(firstNotRepeatingChar("abaccdef"));
		System.out.println(firstNotRepeatingChar("aa"));
		System.out.println(firstNotRepeatingChar("abcde"));
		System.out.println(firstNotRepeatingChar(null));
	}
	
	/**
	 * 采用简单hash表的算法,使用数组：key（数组下标）-value（值）
	 * @return
	 */
	public char firstNotRepeatingChar(String str) {
		
		if(str == null || str.length() == 0 || "".equals(str)) {
			return '\0';
		}
		
		int[] hashTable = new int[128];
		for (int i = 0; i < str.length(); i++) {
			
			hashTable[str.charAt(i)]++;
		}
		
		for (int i = 0; i < str.length(); i++) {
			if(hashTable[str.charAt(i)] == 1) {
				return str.charAt(i);
			}
		}
	
		return '\0';
	}
}
