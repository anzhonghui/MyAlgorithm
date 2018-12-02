package com.qk.swordfingeroffer;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

/**
 * @Description : 面试题50：
 * 	1.第一次只出现一次的字符（第一次出现的不重复的字符）：abaccdeff -> b
 *  2.输入两个字符串，从第一个字符串中删除第二个字符串中出现的字符 ：we are students -> w r stdnts
 *  3.删除字符串中所有重复出现的字符 ：google -> gole
 *  4.判断两个给定的单词是否是变位词：silent -> listen
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Array_InterviewQuestions50 {

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
	 * 1.找出字符串中第一个出现的字符，采用简单hash表的算法,使用数组：key（数组下标）-value（值）
	 * @return
	 */
	public char firstNotRepeatingChar(String str) {

		if (str == null || str.length() == 0 || "".equals(str)) {
			return '\0';
		}

		int[] hashTable = new int[128];
		for (int i = 0; i < str.length(); i++) {
			hashTable[str.charAt(i)]++;
		}

		for (int i = 0; i < str.length(); i++) {
			if (hashTable[str.charAt(i)] == 1) {
				return str.charAt(i);
			}
		}

		return '\0';
	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		System.out.println(deleteSecondFromFirst("We are students", "aeiou"));
	}

	/**
	 * 2.从第一个字符串中删除第二个字符串中出现的字符
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String deleteSecondFromFirst(String str1, String str2) {
		// 统计第二个字符串中出现的字符
		int[] arr = new int[128];
		for (int i = 0; i < str2.length(); ++i) {
			++arr[str2.charAt(i)];
		}

		StringBuffer sBuffer = new StringBuffer(str1);
		for (int i = 0; i < sBuffer.length(); ++i) {
			if (arr[sBuffer.charAt(i)] >= 1) {
				sBuffer.replace(i, i + 1, "");
				// 移除内容后，坐标会变化
				--i;
			}
		}

		return sBuffer.toString();
	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest3() {
		System.out.println(deleteRepetitiveChar("google"));
	}

	/**
	 * 3.删除字符串中所有重复出现的字符 ：google -> gole
	 * @param str
	 * @return
	 */
	public String deleteRepetitiveChar(String str) {

		boolean[] arr = new boolean[128];
		StringBuffer sBuffer = new StringBuffer(str);
		for (int i = 0; i < sBuffer.length(); ++i) {
			if (arr[sBuffer.charAt(i)]) {
				sBuffer.replace(i, i + 1, "");
				// 移除内容后，坐标会变化
				--i;
			} else {
				arr[sBuffer.charAt(i)] = true;
			}
		}

		return sBuffer.toString();
	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest4() {
		System.out.println(isAnaphor("silent", "listen"));
		System.out.println(isAnaphor("a", "b"));
	}

	/**
	 * 4.判断两个给定的单词是否是变位词：silent -> listen
	 * @param str1
	 * @param str2
	 * @return
	 */
	public boolean isAnaphor(String str1, String str2) {
		if (str1.length() != str2.length()) {
			return false;
		}

		int[] arr = new int[128];
		// 第一个字符串中的字符+1
		for (int i = 0; i < str1.length(); ++i) {
			++arr[str1.charAt(i)];
		}
		// 第二个字符串中的字符-1
		for (int i = 0; i < str2.length(); ++i) {
			--arr[str2.charAt(i)];
		}

		// 判断字符串中中的字符是否符合,只需要判断一个即可
		for (int i = 0; i < str1.length(); ++i) {
			if (arr[str1.charAt(i)] != 0) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * @throws IOException 
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest5() throws IOException {
		System.out.println(firstNotRepeatingCharInReader(new StringReader("go")));
		System.out.println(firstNotRepeatingCharInReader(new StringReader("google")));
	}

	/**
	 * 字符流中第一个只出现一次的字符
	 * @param stringReader
	 * @return
	 * @throws IOException
	 */
	public char firstNotRepeatingCharInReader(StringReader stringReader) throws IOException {

		// 初始化hash表
		int[] arr = new int[128];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = -1;
		}
		
		char[] buffer = new char[256];
		int len = 0;
		// 记录总长
		int tempLenth = 0;
		while ((len = stringReader.read(buffer)) != -1) {
			for (int i = 0; i < len; ++i) {
				// 说明没有出现
				if (arr[buffer[i]] == -1) {
					arr[buffer[i]] = tempLenth + i;
					// 说明已经出现了
				} else if (arr[buffer[i]] >= 0) {
					arr[buffer[i]] = -2;
				}
			}
			tempLenth += len;
		}
		
		char ch = '0';
		int minIndex = Integer.MAX_VALUE;
		for (int i = 0; i < arr.length; ++i) {
			if(arr[i] < minIndex && arr[i] >= 0) {
				ch = (char)i;
				minIndex = arr[i];
			}
		}

		return ch;
	}
}
