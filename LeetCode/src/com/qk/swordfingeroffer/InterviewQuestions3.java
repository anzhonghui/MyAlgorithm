package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题3：数组中重复的数字
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class InterviewQuestions3 {

	/**
	 * @Description:
	 */
	@Test
	public void MyTest() {
//		System.out.println(duplicate(new int[] { 2, 3, 1, 0, 2, 5, 3 }));
		System.out.println(duplicate(new int[] { 2, 3, 1, 1, 2, 5, 3 }));
	}

	/**
	 * 3.1 在长度为n的数组中，所有的数字都在0/n-1范围内，找出数组中任意重复的数字
	 * 对数组应用，第一层遍历查找的数组，直到当前值等于坐标值或当前值重复
	 * 因为while循环，但每个数字最多只要交换两次，就能找到自己的位置
	 * 时间复杂度：O(n)
	 * 空间复杂度：O(1)
	 * @param nums
	 * @return
	 */
	public int duplicate(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] != i) {
				if (nums[i] == nums[nums[i]]) {
					return nums[i];
				}

				swap(nums, nums[i], i);
			}

		}
		return -1;
	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		System.out.println(duplicate2(new int[] { 2, 3, 5, 4, 3, 2, 6, 7 }));
	}

	/**
	 * 3.2 不修改数组，找出重复的数字，长度为n+1的数组，数字的范围是1-n，所以数组中至少有一个重复的，找出重复的数字
	 * 使用二分查找的思想，根据长度进行二分查找的统计，如果统计结果大于统计的数字范围，说明有重复的
	 * 时间复杂度：nlogn
	 * 空间复杂度：O(1)
	 * @return
	 */
	public int duplicate2(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		// 1.循环，按照length进行二分
		while (start <= end) {
			int middle = (start + end) / 2;

			// 2.统计每个范围中数字出现的次数，如果范围跟次数相同，继续统计；不同，说明有重复的
			int count = countRange(nums, start, middle);

			// 3.如果start==end说明统计了的那个数字。如果cout>1，说明数字重复了；如果不大于1，就行统计。
			if (end == start) {
				if (count > 1) {
					return start;
				} else {
					break;
				}
			}

			// 2.1说明有重复的
			if (count != middle - start + 1) {
				end = middle;

			// 2.2说明没有重复的
			} else {
				
				start = middle + 1;
			}

		}

		return -1;
	}

	/**
	 * 统计范围内出现的次数
	 * @return
	 */
	public int countRange(int[] arr, int start, int end) {
		int count = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] >= start && arr[i] <= end) {
				count++;
			}
		}
		return count;
	}

	public void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}
}
