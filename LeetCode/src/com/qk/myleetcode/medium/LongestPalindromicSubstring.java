package com.qk.myleetcode.medium;

import java.util.Arrays;

import org.junit.Test;

/**
 * 5.Longest Palindromic Substring
 * code is far away from bug with the animal protecting
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
 * @Description : 最长回文数
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class LongestPalindromicSubstring {

	@Test
	public void MyTest() {
		System.out.println(longestPalindromeByDP("babad"));
//		System.out.println(longestPalindromeL("cbbd"));
//		System.out.println(longestPalindromeL("a"));
//		System.out.println(longestPalindromeL("ac"));
//		System.out.println(longestPalindromeL("bb"));
//		System.out.println(longestPalindromeL("baaab"));
//		System.out.println(longestPalindromeL("abcda"));
//		System.out.println(longestPalindromeL(""));
	}

	/**
	 ** 中心展开法 时间复杂度为O(n^2),空间复杂度为O(1)， 通过返回回文数字符串（也可以通过返回回文数的长度）
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0 || "".equals(s)) {
			return "";
		}
		String ret = null;
		int len = s.length();
		int max = 0;
		for (int i = 0; i < len; i++) {
			// 当回文数中间只有一个字符的情况
			String s1 = getLongest(s, i, i);
			// 当回文数中间不止有有一个字符的情况
			String s2 = getLongest(s, i, i + 1);
			if (s1.length() > max) {
				max = Math.max(max, s1.length());
				ret = s1;
			}
			if (s2.length() > max) {
				max = Math.max(max, s2.length());
				ret = s2;
			}
		}
		return ret;
	}

	// 从中心向两边扩展，反馈以每个字符为中心的回文数
	public String getLongest(String s, int left, int right) {
		int len = s.length();
		while (left >= 0 && right < len && s.charAt(left) == s.charAt(right)) {
			// 当在中心的时候或者不匹配的时候
//			if (s.charAt(left) != s.charAt(right)) {
//				break;
//			}
			left--;
			right++;
		}
		return s.substring(left + 1, right);
	}

	/**
	 * 动态规划：采用二维数组解决问题（只比较一般，从中间线网上的一部分三角）
	 * babad ==>
	 *    b  a  b  a  d  (j)
	 * b [T, F, T, F, F] 
	 * a [ , T, F, T, F] 
	 * b [ ,  , T, F, F] 
	 * a [ ,  ,  , T, F] 
	 * d [ ,  ,  ,  , T] 
	 *(i)
	 * @param s
	 * @return
	 */
	public String longestPalindromeByDP(String s) {
		if (s == null) {
			return null;
		}

		String ret = null;
		int len = s.length();
		int max = 0;
		boolean[][] D = new boolean[len][len];
		for (int j = 0; j < len; j++) {
			for (int i = 0; i <= j; i++) {
				D[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || D[i + 1][j - 1]);
				if (D[i][j]) {
					if (j - i + 1 > max) {
						max = j - i + 1;
						ret = s.substring(i, j + 1);
					}
				}
			}
		}
		for (boolean[] bs : D) {
			System.out.println(Arrays.toString(bs));
		}
		return ret;
	}

	// 类似中心扩展法（通过全局变量计算长度值和起始值）
	int maxLen = 0, startIdx = 0;

	public String longestPalindromeL(String s) {
		if (s == null || s.length() < 2)
			return s;
		for (int i = 0; i < s.length() - 1; i++) {
			checkPalindrome(s, i, i);
			checkPalindrome(s, i, i + 1);
		}
		return s.substring(startIdx, startIdx + maxLen);
	}

	public void checkPalindrome(String s, int i, int j) {
		while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
			i--;
			j++;
		}
		if (maxLen < j - i - 1) {
			maxLen = j - i - 1;
			startIdx = i + 1;
		}

	}

	// Time Limit Exceeded
	public String longestPalindromeByMe2(String s) {
		if (s == null || s.length() == 0 || "".equals(s)) {
			return "";
		}

		if (s.length() == 1) {
			return s;
		}

		String longestPalindromic = "";
		for (int i = 0; i < s.length(); i++) {
			for (int j = i; j < i - s.length() - 1; j++) {
				String sub = s.substring(i, j + 1);
				if (isPalindrome(sub)) {
					if (sub.length() > longestPalindromic.length()) {
						longestPalindromic = sub;
					}
				}
			}
		}

		return longestPalindromic;
	}

	public boolean isPalindrome(String s) {
		if (s.length() == 1) {
			return true;
		}
		boolean flag = false;
		for (int i = 0; i < s.length() / 2; i++) {
			if (s.charAt(i) == s.charAt(s.length() - i - 1)) {
				flag = true;
			} else {
				flag = false;
				break;
			}
		}
		return flag;
	}

	@Test
	public void MyTest3() {
		System.out.println(isPalindrome("aba"));
		System.out.println(isPalindrome("bb"));
		System.out.println(isPalindrome("abcba"));
		System.out.println(isPalindrome("babad"));
	}

	// 错误，人家让求的是回文数
	public String longestPalindromeError(String s) {

		if (s == null || s.length() == 0 || "".equals(s)) {
			return "";
		}

		if (s.length() == 1) {
			return s;
		}

		String longestPalindromic = "";

		// 1.创建数组
		int[] arr = new int[128];

		// 2.遍历
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (arr[c] == 0 && c != s.charAt(0)) {
				arr[c] = i;
			}
		}

		// 3.第二次遍历 bb
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (arr[c] <= i) {
				String sub = s.substring(arr[c], i + 1);
//				System.out.println(arr[c]);
//				System.out.println(i);
				if (sub.length() > longestPalindromic.length()) {
					longestPalindromic = sub;
				}
			}
		}

		return longestPalindromic;
	}

	/**
	 * 自己写的，不好
	 * @param s
	 * @return
	 */
	public String longestPalindromeByMe(String s) {
		// 1.判断空的条件
		if (s == null || s.length() == 0 || "".equals(s)) {
			return "";
		}

		// 2.最长回文数
		String longestPalindromic = "";

		// 3.遍历，获取以每个字符为中心最长的回文数
		for (int i = 0; i < s.length(); i++) {

			// 4.考虑回文数中间有一位数或两位数的情况（获取最长的回文数）
			String str1 = getLongestPalindrome(s, i, i);
			String str2 = getLongestPalindrome(s, i, i + 1);

			// 5.比较返回的字符串
			if (str1.length() > longestPalindromic.length()) {
				longestPalindromic = str1;
			}
			if (str2.length() > longestPalindromic.length()) {
				longestPalindromic = str2;
			}
		}

		return longestPalindromic;
	}

	public String getLongestPalindrome(String s, int left, int right) {
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			left--;
			right++;
		}
		// substring 含头不含尾，所以头部需要+1，尾部不需要-1（因为进入循环多减了，多加了）
		return s.substring(left + 1, right);
	}

}
