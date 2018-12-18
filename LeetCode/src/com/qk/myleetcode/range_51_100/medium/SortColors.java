package com.qk.myleetcode.range_51_100.medium;

import java.util.Arrays;

import org.junit.Test;

/**
 * @Description : Sort Colors
 * Given an array with n objects colored red, white or blue, sort them in-place so that objects of 
 * the same color are adjacent, with the colors in the order red, white and blue.
 * Input: [2,0,2,1,1,0]
 * Output: [0,0,1,1,2,2]
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月12日
 */
public class SortColors {

	/**
	 * @Description:
	 * @Exaomple:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		int[] nums = new int[] { 2, 0, 2, 1, 1, 0, 2 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] {};
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] { 0 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] { 0, 1 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] { 1, 0 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] { 1, 2 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] { 2, 1 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] { 2, 1, 0 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
		nums = new int[] { 0, 1, 2 };
		sortColors(nums);
		System.out.println(Arrays.toString(nums));
	}

	/**
	 * 只拿一个索引值做比较
	 * 2 0 2 1 1 0 2
	 * 2 0 2 1 1 0 2
	 * 0 0 2 1 1 2 2
	 * 0 0 2 1 1 2 2 
	 * 0 0 2 1 1 2 2
	 * 0 0 1 1 2 2 2
	 * 0 0 1 1 2 2 2
	 * 
	 * @param nums
	 */
	public void sortColors(int[] nums) {
		int p1 = 0, p2 = nums.length - 1, index = 0;
		while (index <= p2) {
			if (nums[index] == 0) {
				nums[index] = nums[p1];
				nums[p1] = 0;
				p1++;
			}
			if (nums[index] == 2) {
				nums[index] = nums[p2];
				nums[p2] = 2;
				p2--;
				index--;
			}
			index++;
		}
	}

	public void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
}
