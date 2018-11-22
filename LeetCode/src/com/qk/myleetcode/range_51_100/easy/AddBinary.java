package com.qk.myleetcode.range_51_100.easy;

import org.junit.Test;

/**
 * 
 * 67. Add Binary
 * @Description : 
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月15日
 */
public class AddBinary {

	/**
	 * @Description:
	 * @Example: 11 + 1 = 100, 1010 + 1011 = 10101
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println('1'+'1');
//		System.out.println((char)1+'0');
//		System.out.println(addBinary("11", "1"));
//		System.out.println(addBinary("1010", "1011"));
//		System.out.println(addBinary("0", "0"));
//		System.out.println(addBinary("0", "1"));
//		System.out.println(addBinary("1", "1"));
//		System.out.println(addBinary("1", "11"));
//		System.out.println(addBinary("1111", "1111"));
		System.out.println(addBinary("100", "110010"));
	}

	/**
	 * 思路：采用while循环的方法，除2进1
	 * @param a
	 * @param b
	 * @return
	 */
	public String addBinary(String a, String b) {
		StringBuilder sb = new StringBuilder();
		int i = a.length() - 1, j = b.length() - 1, carry = 0;
		while (i >= 0 || j >= 0) {
			int sum = carry;
			if (j >= 0)
				sum += b.charAt(j--) - '0';
			if (i >= 0)
				sum += a.charAt(i--) - '0';
			sb.append(sum % 2);
			carry = sum / 2;
		}
		if (carry != 0)
			sb.append(carry);
		return sb.reverse().toString();
	}

}
