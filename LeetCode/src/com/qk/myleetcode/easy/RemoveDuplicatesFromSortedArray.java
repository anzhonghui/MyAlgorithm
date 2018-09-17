package com.qk.myleetcode.easy;

import java.util.Arrays;

import org.junit.Test;

public class RemoveDuplicatesFromSortedArray {

	@Test
	public void MyTest() {
		int[] arr = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		System.out.println(removeDuplicates(arr));
		System.out.println(removeDuplicates(new int[] { 1, 1, 2 }));
		System.out.println(removeDuplicates(new int[] { 1 }));
	}

	/**
	 * 思路三：移动前进的坐标
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// 不重复值的移动坐标
		int i = 0;
		// 数组的移动坐标
		int j = 1;

		while (j < nums.length) {
			// 小于，说明不重复，移到前面
			if (nums[i] < nums[j]) {
				i++;
				nums[i] = nums[j];
			}

			j++;
		}

		System.out.println(Arrays.toString(nums));

		return i + 1;
	}

	/**
	 * 思路二：通过借用中间值的方法，移动前进的坐标
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		// 当前值（中间值，记录是否重复）
		int curValue = nums[0];
		// 结束值
		int endValue = nums[nums.length - 1];
		// 当前坐标
		int curIndex = 1;
		// 遍历
		for (int i = 1; i < nums.length; i++) {
			// 如果有不相同的值，移到前面
			if (nums[i] != curValue) {
				curValue = nums[i];
				nums[curIndex] = curValue;
				curIndex++;
			}
			// 剔除完成，跳出循环
			if (endValue == curValue) {
				break;
			}
		}

		System.out.println(Arrays.toString(nums));
		return curIndex;
	}

	/**
	 * 思路一：通过java自带的复制数组的方法移除元素（仿照ArrayList的remove方法）
	 * 
	 * @param nums
	 * @return
	 */
	public int removeDuplicates1(int[] nums) {

		if (nums == null || nums.length == 0) {
			return 0;
		}

		// 记录总数
		int count = 1;
		// 记录当前值（用于剔除重复的）
		int curValue = nums[0];
		// 记录最后的值，用于跳出循环
		int endValue = nums[nums.length - 1];
		// 遍历
		for (int i = 1; i < nums.length; i++) {
			// 如果当前值是重复的值
			if (nums[i] == curValue) {
				// copy
				System.arraycopy(nums, i + 1, nums, i, nums.length - 1 - i);
				i--;
			} else {
				// 不重复，累加
				count++;
				curValue = nums[i];
			}

			// 剔除完成，跳出循环
			if (endValue == curValue) {
				break;
			}
		}

		System.out.println(Arrays.toString(nums));
		return count;
	}
}
