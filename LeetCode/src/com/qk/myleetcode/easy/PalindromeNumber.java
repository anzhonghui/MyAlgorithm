package com.qk.myleetcode.easy;

import org.junit.Test;

public class PalindromeNumber {

	@Test
	public void MyTest() {
		System.out.println(isPalindrome(123));
		System.out.println(isPalindrome(121));
		System.out.println(isPalindrome(12344321));
		System.out.println(isPalindrome(11));
		System.out.println(isPalindrome(12));
		System.out.println(isPalindrome(-121));
		System.out.println(isPalindrome(10));
		System.out.println(isPalindrome(0));
		System.out.println(isPalindrome(1));
		System.out.println(isPalindrome(-1));
//		System.out.println(Math.log10(100)+1);
//		System.out.println(Math.log10(123)+1);
//		System.out.println(Math.log10(121)+1);
//		System.out.println(Math.log10(12344321)+1);
//		System.out.println(Math.log10(11)+1);
//		System.out.println(Math.log10(10)+1);
//		System.out.println(Math.log10(1)+1);
	}

	// 反向思路
	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int temp = x;
		int result = 0;
		while (temp > 0) {
			result = result * 10 + temp % 10;
			temp /= 10;
		}
		return result == x;
	}

	// 自己的思路
	public boolean isPalindromeByMe(int x) {
		if (x < 0) {
			return false;
		}
		if (x == 0) {
			return true;
		}
		int numberOfDigits = (int) (Math.log10(x) + 1);
		if (numberOfDigits == 1) {
			return true;
		}

		int[] arr = new int[numberOfDigits];
		for (int i = 0; i < numberOfDigits; i++) {
			arr[i] = x % 10;
			x = x / 10;
			if (arr[i] != arr[numberOfDigits - i - 1] && i >= numberOfDigits / 2) {
				if (numberOfDigits % 2 == i) {
					continue;
				}
				return false;
			}
		}

		return true;
	}
}
