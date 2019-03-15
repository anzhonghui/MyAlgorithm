package com.qk.myleetcode.range_51_100.hard;

import org.junit.Test;

/**
 * 有效号码
 * @Description : 
 * @Author : huihui
 * @Date : Create in 2019年3月14日 带e的是科学计数法，可以省略中间的+,2e10=2*10^10;2e-10=2*10^-10，e后面的数字是整数
	 * Some examples:
	"0" => true
	" 0.1 " => true
	"abc" => false
	"1 a" => false
	"2e10" => true
	" -90e3   " => true
	" 1e" => false
	"e3" => false
	" 6e-1" => true
	" 99e2.5 " => false
	"53.5e93" => true
	" --6 " => false
	"-+3" => false
	"95a54e53" => false
 */
public class ValidNumber {
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(isNumber("53.5e93"));
	}

	public boolean isNumber(String s) {
		s = s.trim();

		// 点是否正确
		boolean pointSeen = false;
		// 科学计数法是否正确
		boolean eSeen = false;
		// e前面的数字是否正确
		boolean numberSeen = false;
		// 后面的是否正确
		boolean numberAfterE = true;
		for (int i = 0; i < s.length(); i++) {
			if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
				// 数字是正确的，不管是前面的数字还是后面的
				numberSeen = true;
				numberAfterE = true;
			} else if (s.charAt(i) == '.') {
				// 返回false的有三种情况：
				// TT TF 都是e出现在“.”的前面的情况，这种写法是错误的，例如" 99e2.5 " => false
				// FT 说明“.”前面已经出现过一次了，现在又出现了一次
				if (eSeen || pointSeen) {
					return false;
				}
				pointSeen = true;
			} else if (s.charAt(i) == 'e') {
				// 如果e和前面的数字不正确，返回false
				if (eSeen || !numberSeen) {
					return false;
				}
				// 因为有e了，所有重新初始化e后面数字的标识符为false
				numberAfterE = false;
				eSeen = true;
			} else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
				// 满足的情况：TT 正负号不在开头，正负号前面的字符不是e	
				if (i != 0 && s.charAt(i - 1) != 'e') {
					return false;
				}
			} else {
				return false;
			}
		}

		return numberSeen && numberAfterE;
	}
}
