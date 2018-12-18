package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

/**
 * @Description : 80. Remove Duplicates from Sorted Array II
 * 移除给定排好序的数组中重复的元素，元素至多出现两次
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月16日
 */
public class RemoveDuplicatesFromSortedArrayII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(removeDuplicates(new int[] { 1, 1, 1, 2, 2, 3 }));
	}

	/**
	 * 对方法1进行优化，单独处理长度小于2的情况
	 * @param nums
	 * @return
	 */
	public int removeDuplicatesBest(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length <= 2) {
			return nums.length;
		}
		int count = 2;
		for (int i = 2; i < nums.length; i++) {
			if (nums[i] != nums[count - 2]) {
				nums[count++] = nums[i];
			}
		}
		return count;
	}

	public int removeDuplicates(int[] nums) {
		int i = 0;
		for (int n : nums)
			// 查看是否重复或者i长度不足2的情况
			if (i < 2 || n > nums[i - 2])
				nums[i++] = n;
		return i;
	}
}
