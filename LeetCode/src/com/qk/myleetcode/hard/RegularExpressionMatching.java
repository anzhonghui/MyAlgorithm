package com.qk.myleetcode.hard;

import org.junit.Test;

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
//		System.out.println(isMatch("aa","a*"));
//		System.out.println(isMatch("ab",".*"));
//		System.out.println(isMatch("aab","c*a*b"));
//		System.out.println(isMatch("abc", "a*b*"));
//		System.out.println(isMatch("mississippi", "mis*is*p*."));
//		System.out.println(isMatch("ab", ".*c"));
//		System.out.println(isMatch("aa", "aaa"));
//		System.out.println(isMatch("aaa", "a.a"));
//		System.out.println(isMatch("a", "ab*"));
//		System.out.println(isMatch("bbbba", ".*a*a"));
//		System.out.println(isMatch("a", ".*..a*"));
		System.out.println(isMatch("a", ".*."));
		
	}

	public boolean isMatch(String s, String p) {

		if(s == null || p == null || s.length() == 0 || p.length() == 0 || "".equals(s) || "".equals(p)) {
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
					if ((i == s.length()-1 ||j == p.length() - 1) && p.charAt(p.length()-1) != '*' && p.charAt(p.length()-1) != '.' && s.charAt(s.length()-1) != p.charAt(p.length()-1)) {
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
			if( i == s.length()-1) {
				int temp = 0;
				for (int j2 = 0; j2 < p.length(); j2++) {
					if(p.charAt(j2) == '.' && j2 < p.length()-1) {
						if(p.charAt(j2+1) != '*') {
							temp++;
						}
					}
				}
				if(s.length() < temp) {
					return false;
				}
			}
			
			// 当匹配到最后长度不够时(母串长度到头，匹配串没匹配完；)
			if ((i == s.length() - 1 && j < p.length() - 1 && p.charAt(p.length()-1) != '*') || (i < s.length() - 1 && j == p.length() - 1)) {
				return false;
			}
			
			j++;
		}
		return true;
	}
}
