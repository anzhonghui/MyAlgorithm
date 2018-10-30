package com.qk.myleetcode.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 10.Regular Expression Matching
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
 * @Description : 字符串的匹配，'.'匹配任意单个字符；'*'匹配前一个元素的0个或多个
 * @Programme：使用动态规划解决问题，二位数组
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class RegularExpressionMatching {

	/**
	 * s = "aa" p = "a" Output: false ; 
	 * s = "aa" p = "a*" Output: true ; 
	 * s = "ab" p = ".*" Output: true ; 
	 * s = "aab" p = "c*a*b" Output: true ; 
	 * s = "mississippi" p = "mis*is*p*." Output: false
	 * s = "ab" p = ".*c" Output: false
	 * s = "a" p = ".*..a*" Output: false
	 * s = "a" p = ".*." Output: true
	 */
	@Test
	public void MyTest() {
//		System.out.println(isMatch("aa","a"));
//		System.out.println("----------------");
//		System.out.println(isMatch("aa","a*"));
//		System.out.println(isMatch("ab",".*"));
//		System.out.println(isMatch("aab","c*a*b"));
//		System.out.println(isMatch("abc", "a*b*"));
//		System.out.println(isMatch("mississippi", "mis*is*p*."));
//		System.out.println(isMatch("ab", ".*c"));
//		System.out.println(isMatch("aa", "aaa"));
//		System.out.println(isMatch("aaa", "a.a"));
//		System.out.println(isMatch("a", "ab*"));
		System.out.println(isMatch("bbbba", "b.*a"));
//		System.out.println(isMatch("a", ".*..a*"));
//		System.out.println(isMatch("a", ".*."));

	}

	/**
	 * DP,动态规划
	 * 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
	 * 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
	 * 3, If p.charAt(j) == '*': 
	 * here are two sub conditions:
	 *          1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
	 *          2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
	 *                         dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a 
	 *                      or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
	 *                      or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
	 * 举例：  
	 *       .  *  a  *  a                     
	 *   [T, F, T, F, T, F]
	 * b [F, T, T, F, T, F]
	 * b [F, F, T, F, T, F]
	 * b [F, F, T, F, T, F]
	 * b [F, F, T, F, T, F]
	 * a [F, F, T, T, T, T]
	 * 
	 * . 匹配一个字符，交际的字符
	 * * 匹配一列字符，只要前面有字符匹配成功
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		if (s == null || p == null) {
			return false;
		}
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int i = 0; i < p.length(); i++) {
			if (p.charAt(i) == '*' && dp[0][i - 1]) {
				dp[0][i + 1] = true;
			}
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (p.charAt(j) == '.') {
					dp[i + 1][j + 1] = dp[i][j];
				}
				if (p.charAt(j) == s.charAt(i)) {
					dp[i + 1][j + 1] = dp[i][j];
				}
				if (p.charAt(j) == '*') {
					if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {
						dp[i + 1][j + 1] = dp[i + 1][j - 1];
					} else {
						dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
					}
				}
			}
		}
		toString(dp);
		return dp[s.length()][p.length()];
	}
	
	public void toString(boolean[][] arrs) {
		System.out.println("遍历开始");
		for (boolean[] bs : arrs) {
			System.out.println(Arrays.toString(bs));
		}
	}
	
	
	/**
	 * 自己的思路，匹配超时
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatchByMe(String s, String p) {

		if (s == null || p == null || s.length() == 0 || p.length() == 0 || "".equals(s) || "".equals(p)) {
			return false;
		}

		// 记录*号匹配的字符
		char zeroOrMore = 0;

		for (int i = 0, j = 0; i < s.length() && j < p.length(); i++) {

			if (p.charAt(j) == '*') {
				zeroOrMore = p.charAt(j - 1);
			}

			if (zeroOrMore != 0) {
				// 是否是通配符的情况
				if (s.charAt(i) == zeroOrMore) {
					continue;
				} else if (zeroOrMore == '.') {
					if ((i == s.length() - 1 || j == p.length() - 1) && p.charAt(p.length() - 1) != '*'
							&& p.charAt(p.length() - 1) != '.'
							&& s.charAt(s.length() - 1) != p.charAt(p.length() - 1)) {
						return false;
					}
					continue;
				} else if (j + 1 < p.length() && (s.charAt(i) == p.charAt(j + 1) || p.charAt(j + 1) == '.')) {
					// 初始化通配符
					zeroOrMore = 0;
					j++;
					i--;
					continue;
				} else {
					return false;
				}
			}

			// 判断字符的情况
			if (s.charAt(i) != p.charAt(j) && p.charAt(j) != '.') {
				j++;
				i--;
				continue;
			}

			// 判断多..的情况
			if (i == s.length() - 1) {
				int temp = 0;
				for (int j2 = 0; j2 < p.length(); j2++) {
					if (p.charAt(j2) == '.' && j2 < p.length() - 1) {
						if (p.charAt(j2 + 1) != '*') {
							temp++;
						}
					}
				}
				if (s.length() < temp) {
					return false;
				}
			}

			// 当匹配到最后长度不够时(母串长度到头，匹配串没匹配完；)
			if ((i == s.length() - 1 && j < p.length() - 1 && p.charAt(p.length() - 1) != '*')
					|| (i < s.length() - 1 && j == p.length() - 1)) {
				return false;
			}

			j++;
		}
		return true;
	}
}
