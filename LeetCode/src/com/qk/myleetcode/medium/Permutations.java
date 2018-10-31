package com.qk.myleetcode.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 46.Permutations
 * 
 * @Description : 排列组合 （没有重复元素）
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 * 	 [1,3,2],
 * 	 [2,1,3],
 * 	 [2,3,1],
 * 	 [3,1,2],
 * 	 [3,2,1]
 * ]
 * 
 * @Programme：使用递归的方式，类似之前的求数组中和是target的子数组
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月10日
 */
public class Permutations {

	@Test
	public void MyTest() {
		System.out.println(permute(new int[] { 1, 2, 3 }));
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		backtrack(result, new ArrayList<Integer>(), nums);
		return result;
	}

	/**
	 * 采用递归的方式
	 * @param result
	 * @param temp
	 * @param nums
	 */
	public void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums) {
		// 如果集合达到数组的长度，满足要求，添加
		if (temp.size() == nums.length) {
			result.add(new ArrayList<>(temp));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			// 如果元素已经存在，跳过
			if (temp.contains(nums[i])) {
				continue;
			}
			// 添加新元素
			temp.add(nums[i]);
			// 递归
			backtrack(result, temp, nums);
			// 移除最后一个元素
			temp.remove(temp.size() - 1);
		}
	}

	/**
	 * 跟第一种方法大同小异，就是采用了boolean确认元素是否添加到集合中，从而避免使用lsit的contain方法，遍历查找
	 * @param nums
	 * @param result
	 * @param combination
	 * @param used
	 */
	public void backtrack2(int[] nums, List<List<Integer>> result, List<Integer> combination, boolean[] used) {
		if (combination.size() == nums.length) {
			result.add(new ArrayList<Integer>(combination));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (!used[i]) {
				combination.add(nums[i]);
				used[i] = true;
				backtrack2(nums, result, combination, used);
				used[i] = false;
				combination.remove(combination.size() - 1);
			}
		}
	}
}
