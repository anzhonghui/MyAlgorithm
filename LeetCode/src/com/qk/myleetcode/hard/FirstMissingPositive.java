package com.qk.myleetcode.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * First Missing Positive    
 * code is far away from bug with the animal protecting
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
 * @Description : 
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

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

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
