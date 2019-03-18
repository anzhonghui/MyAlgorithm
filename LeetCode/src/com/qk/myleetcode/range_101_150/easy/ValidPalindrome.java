package com.qk.myleetcode.range_101_150.easy;

import org.junit.Test;

/**
 * 有效回文数
 * @Description : 
 * @Author : huihui
 * @Date : Create in 2019年3月18日
 */
public class ValidPalindrome {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(isPalindrome("a man, a plan, a canal: panama"));
		System.out.println(isPalindrome(".,"));
	}

	public boolean isPalindrome(String s) {
		if (s.isEmpty()) {
			return true;
		}

		int start = 0;
		int end = s.length() - 1;

		while (start <= end) {
			// 忽略符号和空格
			if (!Character.isLetterOrDigit(s.charAt(start))) {
				start++;
			} else if (!Character.isLetterOrDigit(s.charAt(end))) {
				end--;
			} else {
				// 统一大小写比较
				if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
					return false;
				}

				start++;
				end--;
			}

		}

		return true;
	}
}
