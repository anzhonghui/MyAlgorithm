package com.qk.myleetcode.range_101_150.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class SingleNumber {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(singleNumberByBitManipulation(new int[] { 2, 2, 1 }));
	}

	/**
	 * 使用异或操作
	 * a⊕0=a
	 * a⊕a=0
	 * a⊕b⊕a=(a⊕a)⊕b=0⊕b=b
	 * @param nums
	 * @return
	 */
	public int singleNumberByBitManipulation(int[] nums) {
		int result = 0;
		for (int j = 0; j < nums.length; ++j) {
			result ^= nums[j];
		}
		return result;
	}

	/**
	 * 利用数学公式：2∗(a+b+c)−(a+a+b+b+c)=c
	 * @param nums
	 * @return
	 */
//	public int singleNumberByMap(int[] nums) {
//		return 2 * sum(set(nums)) - sum(nums);
//	}

	/**
	 * 使用list集合的方式
	 * @param nums
	 * @return
	 */
	public int singleNumberByList(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; ++i) {
			if (list.contains(nums[i])) {
				list.remove((Integer) nums[i]);
			} else {
				list.add(nums[i]);
			}
		}

		return list.get(0);
	}
}
