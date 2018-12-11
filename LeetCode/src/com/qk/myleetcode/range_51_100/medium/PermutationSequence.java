package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PermutationSequence {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(getPermutation(4, 9));
	}

	/**
	 * 思路：
	 * 拿n=4，k=9举例
	 * 1. 9 / !(4-1) = 1 .. 3 > 0 ==> 2
	 * 2. 3 / !(3-1) = 1 .. 1 > 0 ==> 3
	 * 3. 1 / !(2-1) = 1 .. 0 = 0 ==> 1
	 * 4.                         ==> 4
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {
		List<Integer> numbers = new ArrayList<>();
		// 存放阶乘的数组
		int[] factorial = new int[n + 1];
		StringBuilder sb = new StringBuilder();

		// create an array of factorial lookup
		int sum = 1;
		factorial[0] = 1;
		for (int i = 1; i <= n; i++) {
			sum *= i;
			factorial[i] = sum;
		}
		// factorial[] = {1, 1, 2, 6, 24, ... n!}

		// 将全排列的数字放入list中
		// create a list of numbers to get indices
		for (int i = 1; i <= n; i++) {
			numbers.add(i);
		}
		// numbers = {1, 2, 3, 4}

		/**
		 * 为了处理最后两位数的情况
		 * 3. 1 / !(2-1) = 1 .. 0 = 0 ==> 1 【此时的坐标为1的数为4】
		 * 4.                         ==> 4
		 */
		k--;
		for (int i = 1; i <= n; i++) {
			int index = k / factorial[n - i];
			sb.append(String.valueOf(numbers.get(index)));
			numbers.remove(index);
			k = k % factorial[n - i];
		}

		return String.valueOf(sb);
	}
}
