package com.qk.myleetcode.range_1_50.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 41.First Missing Positive    
 *  ┏┓　　┏┓
 * ┏┛┻━━━━┛┻┓
 * ┃　　　　　┃
 * ┃　　　━　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　 ┃
 * ┃　　　┻　　┃
 * ┃　　　　　 ┃
 * ┗━━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　┣┓
 * 　　┃　　　　　　┏┛
 * 　　┗┓┓┏━┳┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 从无序数组中找到最小的丢失的正整数
 * @Programme：利用hash的思想，找最小缺失的正整数，将正整数放到他们该处的坐标位置，值大于数组长度的或是负数的不做处理
 * 例如：3, 4, -1, 1
 * 交换：1, -1, 3, 4
 * 从头开始遍历，可以确认缺失的最小正整数是2
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月22日
 */
public class FirstMissingPositive {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(firstMissingPositive(new int[] { 1, 2, 0 }));
//		System.out.println(firstMissingPositive(new int[] { 3, 4, -1, 1 }));
		System.out.println(firstMissingPositive(new int[] { 7, 8, 11, 9, 12 }));
	}

	/**
	 * 找最小的缺失正整数，可以将连续的，在长度范围内的数据放在指定的位置上：
	 * 如： 3,4,-1,1  -> 1,-1,3,4 
	 * 这样就很简单找出确实的最小正整数是2
	 * （简单易于理解）
	 * @param nums
	 * @return
	 */
	public int firstMissingPositive(int[] nums) {

		int i = 0;
		while (i < nums.length) {
			// 交换条件：正整数，在长度范围内，不在指定的位置
			if (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
				swap(nums, i, nums[i] - 1);
			} else {
				i++;
			}
		}
		
		// 从头遍历，如果坐标和数据不同，则缺失
		for (int j = 0; j < nums.length; j++) {
			if (nums[j] != j + 1) {
				return j + 1;
			}
		}

		return nums.length + 1;

	}

	/**
	 * 交换数组中的两个元素
	 * @param nums
	 * @param i
	 * @param j
	 */
	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	/**
	 * 自己的想法，利用java自带的排序，简单
	 * @param nums
	 * @return
	 */
	public int firstMissingPositiveByMe(int[] nums) {
		int min = 1;

		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == min) {
				min += 1;
			}
		}

		return min;
	}
}
