package com.qk.myleetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinationSumII {

	@Test
	public void MyTest() {
		System.out.println(combinationSum2(new int[] { 10, 1, 2, 7, 6, 1, 5 }, 8));
	}
	
//	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
//		List<List<Integer>> result = new ArrayList<>();
//		Arrays.sort(candidates);
//		
//		for (int i = 0; i < candidates.length; i++) {
//			int sum = candidates[i];
//			List<Integer> temp = new ArrayList<>();
//			for (int j = i; j < candidates.length; j++) {
//				sum += candidates[j];
//				temp.add(candidates[i]);
//				if(sum == target) {
//					result.add(temp);
//				}else if(sum > target) {
//					break;
//				}
//			}
//		}
//		
//		return result;
//	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(candidates);
		search(result, new ArrayList<Integer>(), candidates, target, 0);
		return result;
	}

	/**
	 * 跟前一个方法基本相同，知识start的值不是i，而是i+1，因为每个数字只能用一次；另外需要处理重复的情况
	 * @param result
	 * @param temp
	 * @param nums
	 * @param target
	 * @param start
	 */
	public void search(List<List<Integer>> result, List<Integer> temp, int[] nums, int target, int start) {
		if (target == 0) {
			result.add(new ArrayList<>(temp));
			return;
		}

		// i=1（循环后+1的值）,start=0(第一次递归的值)
		for (int i = start; i < nums.length; i++) {
			// 处理重复的情况
			if(i > start && nums[i] == nums[i-1]) {
				continue;
			}
			if(target - nums[i] >= 0) {
				temp.add(nums[i]);
				search(result, temp, nums, target - nums[i], i + 1);
				temp.remove(temp.size() - 1);
			}else {
				break;
			}
		}
	}
}
