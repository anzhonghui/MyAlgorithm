package com.qk.myleetcode.range_51_100.easy;

import java.util.Arrays;

import org.junit.Test;

public class PlusOne {

	/**
	 * @Description:最后一位数+1，如果是9，+1，进位上0：
	 * @Example: 1 2 3 -> 1 2 4; 1 2 9 -> 1 3 0
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(Arrays.toString(plusOne(new int[] { 1, 2, 3 })));
//		System.out.println(Arrays.toString(plusOne(new int[] { 4, 3, 2, 1 })));
//		System.out.println(Arrays.toString(plusOne(new int[] { 9 })));
		System.out.println(Arrays.toString(plusOne(new int[] { 1, 2, 9 })));
	}

	public int[] plusOne(int[] digits) {
		int n = digits.length;
		for (int i = n - 1; i >= 0; i--) {
			// 如果最后一个数小于9，最后一个数+1
			if (digits[i] < 9) {
				digits[i]++;
				return digits;
			}
			// 如果大于9，说明需要进1，让前面一个数字进1
			digits[i] = 0;
		}

		int[] newNumber = new int[n + 1];
		newNumber[0] = 1;

		return newNumber;
	}
}
