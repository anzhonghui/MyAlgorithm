package com.qk.myleetcode.easy;

import org.junit.Test;

public class RomanToInteger {

	@Test
	public void MyTest() {
//		System.out.println(romanToInt("III"));
//		System.out.println(romanToInt("IV"));
//		System.out.println(romanToInt("IX"));
//		System.out.println(romanToInt("LVIII"));
//		System.out.println(romanToInt("MCMXCIV"));
//		System.out.println(romanToInt(""));
		System.out.println(romanToInt("MCDLXXVI"));
	}

	/**
	 * 方法1，使用hash算法
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		if (s == null || s.length() == 0 || "".equals(s)) {
			return 0;
		}

		// 定义罗马数字数组
		int[] roman = new int[91];
		roman['I'] = 1;
		roman['V'] = 5;
		roman['X'] = 10;
		roman['L'] = 50;
		roman['C'] = 100;
		roman['D'] = 500;
		roman['M'] = 1000;

		int count = 0;
		for (int i = s.length() - 1; i >= 0; i -= 2) {
			// 判断前后顺序
			if (i <= 0) {
				count += roman[s.charAt(i)];
				break;
			}
			if (roman[s.charAt(i - 1)] < roman[s.charAt(i)]) {
				count += (roman[s.charAt(i)] - roman[s.charAt(i - 1)]);
				continue;
			}

			count += roman[s.charAt(i)] + roman[s.charAt(i - 1)];
		}

		return count;
	}
}
