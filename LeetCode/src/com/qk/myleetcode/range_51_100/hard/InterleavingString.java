package com.qk.myleetcode.range_51_100.hard;

import org.junit.Test;

/**
 * 判断是否是交错的字符串
 * @Description : Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
	Example 1:
	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
	Output: true
	Example 2:
	Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
	Output: false
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class InterleavingString {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		isInterleave("aabcc", "dbbca", "aadbbcbcac");
	}

	/**
	 * 动态规划的解法，时间复杂度为O(m*n)
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleave(String s1, String s2, String s3) {
		int n = s1.length();
		int m = s2.length();
		// 长度不等，返回false
		if (n + m != s3.length())
			return false;
		boolean[][] dp = new boolean[n + 1][m + 1];

		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = true;
				} else if (i == 0) {
					if (dp[i][j - 1] == true && s2.charAt(j - 1) == s3.charAt(j - 1)) {
						dp[i][j] = true;
					}
				} else if (j == 0) {
					if (dp[i - 1][j] == true && s1.charAt(i - 1) == s3.charAt(i - 1)) {
						dp[i][j] = true;
					}
				} else {
					if ((dp[i - 1][j] == true && s1.charAt(i - 1) == s3.charAt(i + j - 1))
							|| (dp[i][j - 1] == true && s2.charAt(j - 1) == s3.charAt(i + j - 1))) {
						dp[i][j] = true;
					}
				}
			}
		}
		return dp[n][m];
	}

	/**
	 * 用以为数组的动态规划
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public boolean isInterleaveBy1D(String s1, String s2, String s3) {
		if (s3.length() != s1.length() + s2.length()) {
			return false;
		}
		boolean dp[] = new boolean[s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			for (int j = 0; j <= s2.length(); j++) {
				if (i == 0 && j == 0) {
					dp[j] = true;
				} else if (i == 0) {
					dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
				} else if (j == 0) {
					dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
				} else {
					dp[j] = (dp[j] && s1.charAt(i - 1) == s3.charAt(i + j - 1))
							|| (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
				}
			}
		}
		return dp[s2.length()];
	}
}
