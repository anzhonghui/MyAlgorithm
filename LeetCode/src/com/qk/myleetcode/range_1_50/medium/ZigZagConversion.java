package com.qk.myleetcode.range_1_50.medium;

import org.junit.Test;

public class ZigZagConversion {

	@Test
	public void MyTest() {
//		System.out.println(convert("PAYPALISHIRING",3));
//		System.out.println(convert("PAYPALISHIRING",4));
//		System.out.println(convert("ABC",2));
//		System.out.println(convert("ABA",1));
//		System.out.println(convert("ABCDE",4));
		System.out.println(convert("PAYPALISHIRING", 5));
		System.out.println(convert("01234567890", 4));
//		int[][] arr = new int[1][2];
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(Arrays.toString(arr[i]));
//		}
	}

	public String convert(String s, int numRows) {

		if (numRows == 1) {
			return s;
		}

		StringBuilder sb = new StringBuilder();
		int n = s.length();
		// 循环的长度
		int cycleLen = 2 * numRows - 2;

		// 遍历每一行（填充行数据，控制循环的长度+行数即可）
		for (int i = 0; i < numRows; i++) {
			// j + i < n 循环的长度+行数<数组的长度
			for (int j = 0; j + i < n; j += cycleLen) {
				// 挑出下一轮cycleLen的字符
				sb.append(s.charAt(j + i));
				// 中间行的情况，且还剩下字符进行挑选（不是第一行和最后一行）计算字符的位置：j + cycleLen - i
				if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
					// 挑出zigzag的字符
					sb.append(s.charAt(j + cycleLen - i));
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 自己写的时间复杂度高
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convertByMe(String s, int numRows) {

		if (s == null || s.length() == 0 || "".equals(s) || s.length() == 1 || numRows == 1) {
			return s;
		}

		// 先计算有几列
		int col = s.length() / (numRows * 2 - 2) * (numRows - 1) + s.length() % (numRows * 2 - 2) / numRows
				+ s.length() % (numRows * 2 - 2) % numRows;

		// 创建二位数组
		char[][] arr = new char[numRows][col];
		int index = 0;
		int j = 0;// 记录列数
		while (j < col) {
			// 判断列是否是指定的,整除列
			if (j == 0 || j % (numRows - 1) == 0) {
				for (int i = 0; i < numRows && index < s.length(); i++) {
					arr[i][j] = s.charAt(index);
					index++;
				}
			} else {
				// 单个的情况
				if (index < s.length()) {
					arr[numRows - j % (numRows - 1) - 1][j] = s.charAt(index);
					index++;
				}
			}
			j++;
		}

		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sBuffer.append(new String(arr[i]).replaceAll("\\u0000+", ""));
		}

		return sBuffer.toString();
	}

}
