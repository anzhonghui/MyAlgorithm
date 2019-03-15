package com.qk.myleetcode.range_51_100.medium;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 89. Gray Code
 * @Description : 格雷码
 * Input: 2
 * Output: [0,1,3,2]
 * Explanation:(排列组合)
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * @Author : huihui
 * @Date : Create in 2019年1月22日
 */
public class GrayCode {

	/**
	 * 运算符的优先级：>>(右位移)大于^(异或运算)
	 * n = 2的计算过程：i ^ i >> 1
	 * i = 0, 0 ^ 0 >> 1 = 0 ^ 0 = 0
	 * i = 1, 1 ^ 1 >> 1 = 1 ^ 0 = 1
	 * i = 2, 2 ^ 2 >> 1 = 10 ^ 01 = 11 = 3
	 * i = 3, 3 ^ 3 >> 1 = 11 ^ 01 = 10 = 2  
	 * 
	 * @param n
	 * @return
	 */
	public List<Integer> grayCode(int n) {
		List<Integer> result = new LinkedList<>();
		for (int i = 0; i < 1 << n; i++) {
			result.add(i ^ i >> 1);
		}

		return result;
	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(grayCode(2).toString());
	}
}
