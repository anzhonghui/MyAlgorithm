package com.qk.myleetcode.easy;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class RomanToInteger {

	@Test
	public void MyTest() {
//		System.out.println(romanToInt("III"));
//		System.out.println(romanToInt("IV"));
//		System.out.println(romanToInt("IX"));
		System.out.println(romanToInt("LVIII"));
		System.out.println(romanToInt("MCMXCIV"));
//		System.out.println(romanToInt(""));
//		System.out.println(romanToInt("MCDLXXVI"));//MDCLXXVI
	}
	
	/**
	 * 思路：利用map集合的方式
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		if (s == null || s.length() == 0 || "".equals(s)) {
			return 0;
		}
		
		Map<String, Integer> maps = new HashMap<>(13);
		maps.put("I", 1);
		maps.put("IV", 4);
		maps.put("V", 5);
		maps.put("IX", 9);
		maps.put("X", 10);
		maps.put("XL", 40);
		maps.put("L", 50);
		maps.put("XC", 90);
		maps.put("C", 100);
		maps.put("CD", 400);
		maps.put("D", 500);
		maps.put("CM", 900);
		maps.put("M", 1000);
		
		int result = 0;
		int len = s.length();
		
		for (int i = 0; i < len; i++) {
			
			if(i + 1 < len) {
				String temp = s.charAt(i) + "" + s.charAt(i+1);
				Integer value = maps.get(temp);
				if(value != null) {
					result += value;
					i++;
					continue;
				}
			}
			
			result += maps.get(s.charAt(i) + "");
		}
		
		return result;
	}

	/**
	 * 方法1，使用hash算法（未成功 - 原来理解的是从后往前统计）
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt1(String s) {
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
