package com.qk.myleetcode.medium;

import org.junit.Test;

public class DivideTwoIntegers {

	@Test
	public void MyTest() {

//		System.out.println(10 >> 2);
//		int a = 2147483647;
//		System.out.println(Integer.MAX_VALUE+1);

//		System.out.println(-1 ^ 1);
//		System.out.println(-1 ^ -1);
//		System.out.println(1 ^ 1);
//		System.out.println(1 | 1);
//		System.out.println(1 | -1);
//		System.out.println(-1 | -1);

//		System.out.println(divide(10, 3));
//		System.out.println(divide(7, -3));
//		System.out.println(divide(10, 5));
//		System.out.println(divide(1, 1));
//		System.out.println(divide(-1, -1));
		System.out.println(divide(-2147483648, -1));
//		System.out.println(divide(2147483647, -1));
//		System.out.println(divide(-2147483648, 1));
//		System.out.println(divide(-2147483647, -2));
//		System.out.println(Integer.MAX_VALUE >> 31);
//		System.out.println(Integer.MIN_VALUE << 31);
	}

	/**
	 * 通过扩增倍数的方式，由原来的每次减1个，改为每次减2^i个
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide(int dividend, int divisor) {

		if(dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		
		long a = Math.abs((long)dividend);
		long b = Math.abs((long)divisor);

		long result = 0;
		while (a >= b) {
			for (int i = 0; i < a && a >= b << i; i++) {
				a -= b << i;
				result += 1 << i;
			}
		}
		return ((dividend ^ divisor) >> 31) >= 0? (int)result : (int)-result;
	}
	
	/**
	 * Time Limit Exceeded
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide1(int dividend, int divisor) {

		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		if (dividend == Integer.MIN_VALUE && divisor == 1) {
			return Integer.MIN_VALUE;
		}

		int dev = 0;
		if (dividend == Integer.MIN_VALUE) {
			dev = 1;
			dividend = -Integer.MAX_VALUE;
		}

		int pn = 1;
		if (dividend < 0 && divisor > 0) {
			pn = -1;
			dividend = -dividend;
		}
		if (dividend > 0 && divisor < 0) {
			pn = -1;
			divisor = -divisor;
		}
		if (dividend < 0 && divisor < 0) {
			dividend = -dividend;
			divisor = -divisor;
		}

		int result = 0;
		while (dividend >= divisor) {
			dividend -= divisor;
			result++;
			if (result == Integer.MAX_VALUE) {
				break;
			}
		}

		if (dev != 0 && result != Integer.MAX_VALUE) {
			if (dividend + dev - divisor >= 0) {
				result++;
			}
		}

		if (pn < 0) {
			result = -result;
		}
		return result;
	}
}
