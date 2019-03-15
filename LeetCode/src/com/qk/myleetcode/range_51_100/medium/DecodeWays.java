package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

/**
 * @Description : 求解码的种类数
 * 'A' -> 1
 * 'B' -> 2
 * @Example
 * Input: "12"
 * Output: 2
 * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 * @Author : huihui
 * @Date : Create in 2019年3月7日
 */
public class DecodeWays {
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(numDecodings("136"));
	}
	
	/**
	 * 使用DP（动态规划）
	 * 实例一：
	 * 12
	 * memo
	 * [0,1,1]
	 * [2,1,1]
	 * 结果：
	 * [1,2]
	 * [12]
	 * 实例二：
	 * 136
	 * [0,0,1,1]
	 * [0,1,1,1]
	 * [2,1,1,1]
 	 * 结果：
	 * [1,3,6]
	 * [13,6]
	 * @param s
	 * @return
	 */
	public int numDecodings(String s) {
		int n = s.length();
		if (n == 0)
			return 0;

		int[] memo = new int[n + 1];
		memo[n] = 1;
		memo[n - 1] = s.charAt(n - 1) != '0' ? 1 : 0;

		for (int i = n - 2; i >= 0; i--)
			if (s.charAt(i) == '0')
				continue;
			else
				memo[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? memo[i + 1] + memo[i + 2] : memo[i + 1];

		return memo[0];
	}
}
