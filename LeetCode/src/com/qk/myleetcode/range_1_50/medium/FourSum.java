package com.qk.myleetcode.range_1_50.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * 18.4Sum
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
 * @Description : 求给定数组中四个数字和是目标值的子数组
 * 
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class FourSum {

	@Test
	public void MyTest() {
		System.out.println(fourSum(new int[] { 1, 0, -1, 0, -2, 2 }, 0));
		System.out.println(fourSum(new int[] { -3, -2, -1, 0, 0, 1, 2, 3 }, 0));
//		System.out.println(fourSumByMe(new int[] { 0, 1, 5, 0, 1, 5, 5, -4 }, 11));
		System.out.println(fourSum(new int[] { 0, 1, 5, 0, 1, 5, 5, -4 }, 11));
		System.out.println(fourSum(new int[] { 0, 0, 0, 0 }, 0));
	}

	/**
	 * 对第一种方法的优化（通过固定前两个元素，找后面两个元素），添加各种跳出条件 三种：大于，跳出；小于，跳过；等于，跳过（去重）
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> lists = new ArrayList<>();

		// 先排序
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 3; i++) {

			// 大于，跳出
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
				break;
			}

			// 小于，跳过
			if (nums[i] + nums[nums.length - 1] + nums[nums.length - 2] + nums[nums.length - 3] < target) {
				continue;
			}

			// 相等，从第二个元素开始去重
			if (i > 0 && nums[i - 1] == nums[i]) {
				continue;
			}

			for (int j = i + 1; j < nums.length - 2; j++) {

				// 大于，跳出
				if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
					break;
				}

				// 小于，跳过
				if (nums[i] + nums[j] + nums[nums.length - 1] + nums[nums.length - 2] < target) {
					continue;
				}

				// 相等，从第三个元素开始去重
				if (j - 1 > i && nums[j - 1] == nums[j]) {
					continue;
				}

				int val = target - (nums[i] + nums[j]);
				int start = j + 1;
				int end = nums.length - 1;
				while (start < end) {
					if (nums[start] + nums[end] == val) {
						List<Integer> temp = new ArrayList<>();
						temp.add(nums[i]);
						temp.add(nums[j]);
						temp.add(nums[start]);
						temp.add(nums[end]);
						lists.add(temp);

						// 避免重复
						while (start < end && nums[start] == nums[start + 1]) {
							start++;
						}

						// 避免重复
						while (start < end && nums[end] == nums[end - 1]) {
							end--;
						}

						// 如果有相等值，那只能往上找
						start++;
					}

					if (start < end && nums[start] + nums[end] < val) {
						start++;
					}

					if (start < end && nums[start] + nums[end] > val) {
						end--;
					}
				}
			}
		}

		return lists;
	}

	/**
	 * 自己想的：通过固定前两个元素，移动后两个元素
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSumByMe(int[] nums, int target) {
		List<List<Integer>> lists = new ArrayList<>();

		// 先排序
		Arrays.sort(nums);

		for (int i = 0; i < nums.length - 3; i++) {

			// 从第二个元素开始去重
			if (i > 0 && nums[i - 1] == nums[i]) {
				continue;
			}

			for (int j = i + 1; j < nums.length - 2; j++) {

				// 从第三个元素开始去重
				if (j - 1 > i && nums[j - 1] == nums[j]) {
					continue;
				}

				int val = target - (nums[i] + nums[j]);
				int start = j + 1;
				int end = nums.length - 1;
				while (start < end) {
					if (nums[start] + nums[end] == val) {
						List<Integer> temp = new ArrayList<>();
						temp.add(nums[i]);
						temp.add(nums[j]);
						temp.add(nums[start]);
						temp.add(nums[end]);
						lists.add(temp);

						// 避免重复
						while (start < end && nums[start] == nums[start + 1]) {
							start++;
						}

						// 避免重复
						while (start < end && nums[end] == nums[end - 1]) {
							end--;
						}

						// 如果有相等值，那只能往上找
						start++;
					}

					if (start < end && nums[start] + nums[end] < val) {
						start++;
					}

					if (start < end && nums[start] + nums[end] > val) {
						end--;
					}
				}
			}
		}

		return lists;
	}
}
