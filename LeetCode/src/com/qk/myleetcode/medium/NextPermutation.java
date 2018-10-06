package com.qk.myleetcode.medium;

import java.util.Arrays;

import org.junit.Test;

public class NextPermutation {

	/**
	 * 题意：找到下一个更大的数字排列
	 * 1,2,3     1,2,3 -> 1,3,2
	 * 1,3,2     1,3,4 -> 2,1,3
	 * 2,1,3
	 * 2,3,1
	 * 3,1,2
	 * 3,2,1     3,2,1 -> 1,2,3
	 * 
	 * 三种情况，为了方便更加深入的理解，采用官方给出的数组。
	 * 排好序的数组内容：1,1,3,4,5,5,6,7,8
	 * one：
	 * 1,1,3,4,5,5,6,7,8
	 * nums[i] = 7, nums[j] = 8,i和j取得都是一开始的数值，这样最终交换的事最后的两个元素，因为i到最后只有一个元素，所以不需要取反
	 * 交换后的结果：1,1,3,4,5,5,6,8,7
	 * two：
	 * 8,7,6,5,5,4,3,1,1
	 * nums[i] = 8, nums[j] = 1，从第一个元素到最后的元素，所有数据取反
	 * 交换后的结果：1,1,3,4,5,5,6,7,8
	 * three：
	 * 1,5,8,4,7,6,5,3,1
	 * nums[i] = 4, nums[j] = 5,i和j交换，变成1,5,8,5,7,6,4,3,1，会发现，交换后，从i往后所有的数据是降序的，然后从i往后所有的数据两两交换，交换后得升序数列，得最终结果：
	 * 1,5,8,5,1,3,4,6,7
	 */
	@Test
	public void MyTest() {
		int[] nums = { 1, 5, 8, 4, 6, 7, 5, 3, 1 };
		nextPermutation(nums);
		System.out.println(Arrays.toString(nums));
	}

	public void nextPermutation(int[] nums) {
		int i = nums.length - 2;
		// i取得是前一个元素比后一个元素小的数
		while (i >= 0 && nums[i + 1] <= nums[i]) {
			i--;
		}
		if (i >= 0) {
			int j = nums.length - 1;
			// j取得是从后往前找，比i大的元素
			while (j >= 0 && nums[j] <= nums[i]) {
				j--;
			}
			// 交换两个元素
			swap(nums, i, j);
		}
		// i到最后的所有元素取反
		reverse(nums, i + 1);
	}

	/**
	 * 从交换数字往后的序列互换
	 * 
	 * @param nums
	 * @param start
	 */
	private void reverse(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
