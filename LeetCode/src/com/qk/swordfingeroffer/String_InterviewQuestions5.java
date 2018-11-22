package com.qk.swordfingeroffer;

import java.util.Arrays;

import org.junit.Test;

/**
 * @Description : 面试题5：替换字符串中的空格
 * 网络编程中，URL包含特殊字符，#，空格等，需要转义成 ‘%+ASCII码’ 的形式
 * 将给定字符串中的空格，替换成 %20, 20是空格（32 -> 0x20）的ASCII码的十六进制
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class String_InterviewQuestions5 {

	/**
	 * @Description:
	 * @Programme：方案一：使用stringbuffer或stringbuilder。方案二：只用string
	 */
	@Test
	public void MyTest() {
		System.out.println(replaceBlack("we are happy"));
	}

	/**
	 * @Programme：紧只用字符串,采用倒叙的方式；如果采用正序的方式，新插入元素后，需要后撤元素
	 * @param nums
	 * @return
	 * 时间复杂度：O(n)
	 */
	public String replaceBlack(String str) {
		int length = str.length();
		for (int i = 0; i < str.length(); ++i) {
			if(str.charAt(i) == ' ') {
				length += 2;
			}
		}
		
		char[] newStr = new char[length];
		int j = length - 1;//newstr的位置
		for (int i = str.length() - 1; i >= 0; --i) {
			if(str.charAt(i) == ' ') {
				newStr[j-2] = '%';
				newStr[j-1] = '2';
				newStr[j] = '0';
				j-=3;
			} else {
				newStr[j] = str.charAt(i);
				--j;
			}
		}
		return new String(newStr);
	}

}
