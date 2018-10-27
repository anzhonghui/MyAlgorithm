package com.qk.myleetcode.hard;

import java.util.Arrays;

import org.junit.Test;
/**
 * Wildcard Matching
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
 * @Description : Wildcard Matching
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月27日
 */
public class WildcardMatching {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(isMatchDP("aa", "a*"));
//		System.out.println(isMatchDP("abc", "abc"));
//		System.out.println(isMatchDP("abc", "a*"));
//		System.out.println(isMatchDP("abc", "a?*"));
//		System.out.println(isMatchDP("ab", "a?"));
//		System.out.println(isMatchDP("bc", "a*"));
//		System.out.println(isMatchDP("bc", "?*"));
//		System.out.println(isMatch("aaaaaa", "?*"));
//		System.out.println(isMatchBest("abacdedf", "aba?*"));
//		System.out.println(isMatchBest("abcde", "aba?*"));
		System.out.println(isMatchBest("abadefg", "aba***"));
//		System.out.println(isMatchDP("abc", "*c"));
	}

	/**
	 * 使用二维数组的DP问题解决方案
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
		match[s.length()][p.length()] = true;
		for (int i = p.length() - 1; i >= 0; i--) {
			if (p.charAt(i) != '*')
				break;
			else
				match[s.length()][i] = true;
		}
		for (int i = s.length() - 1; i >= 0; i--) {
			for (int j = p.length() - 1; j >= 0; j--) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')
					match[i][j] = match[i + 1][j + 1];
				else if (p.charAt(j) == '*')
					match[i][j] = match[i + 1][j] || match[i][j + 1];
				else
					match[i][j] = false;
			}
		}
		for (boolean[] bs : match) {
			System.out.println(Arrays.toString(bs));
		}
		return match[0][0];
	}

	/**
	 * 使用二维数组的DP问题解决方案
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatchDP(String s, String p) {
		int m = s.length();
		int n = p.length();
		boolean[][] f = new boolean[m + 1][n + 1];
		f[0][0] = true;
		for (int i = 0; i < n && p.charAt(i) == '*'; i++) {
			f[0][i + 1] = true;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				char ch1 = s.charAt(i - 1);
				char ch2 = p.charAt(j - 1);
				if (ch1 == ch2 || ch2 == '?') {
					f[i][j] = f[i - 1][j - 1];
				} else if (Character.isLetter(ch2)) {
					f[i][j] = false;
				} else {
					f[i][j] = f[i][j - 1] || f[i - 1][j - 1] || f[i - 1][j];
				}
			}
		}
		for (boolean[] bs : f) {
			System.out.println(Arrays.toString(bs));
		}
		
		return f[m][n];
	}

	/**
	 * 通过移动文本串和模式串的坐标，来匹配，时间复杂度低
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatchBest(String s, String p) {
		int sl = 0,  // 文本串s的坐标 
			pl = 0,  // 模式串p的坐标（最终通过查看模式串的匹配长度，判断是否成功）
			match = 0, // 匹配的字符的坐标
			startidx = -1; // 标识模式串是否有*，并标识*的位置
		while (sl < s.length()) {
			// 处理文本串和模式串字符相等或模式串字符为？
			if ((pl < p.length()) && (p.charAt(pl) == '?' || s.charAt(sl) == p.charAt(pl))) {
				sl++;
				pl++;

			// 处理模式串为* 的情况，包括含有多个*
			} else if (pl < p.length() && p.charAt(pl) == '*') {
				startidx = pl; // 不断更新* 的位置
				match = sl; // 更新匹配到的字符串的位置
				pl++; // 模式串坐标+1
			
			// 模式串中含*
			} else if (startidx != -1) {
				// 计算最终*号匹配后，模式串匹配到的坐标
				pl = startidx + 1;
				// 匹配了的坐标+1，*可以匹配任意字符
				match++;
				// 更新文本串的坐标
				sl = match;
				
			// 不匹配的情况，反馈false
			} else {
				return false;
			}
		}
		
		// 如果最后还有未匹配完的情况，继续++，处理模式串只有*的情况，但文本串为空的情况
		while (pl < p.length() && p.charAt(pl) == '*') {
			pl++;
		}
		return pl == p.length();
	}
}
