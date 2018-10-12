package com.qk.myleetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PermutationsII {

	@Test
	public void MyTest() {
//		System.out.println(permuteUnique(new int[] { 1, 1, 2 }));
		System.out.println(permuteUnique(new int[] { 1, 2, 2, 2 }));
//		boolean[] isUsed = new boolean[3];
//		System.out.println(Arrays.toString(isUsed));
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		boolean[] used = new boolean[nums.length];
		backtrack(result, new ArrayList<Integer>(), nums, used);
		return result;
	}

	/**
	 * 采用递归的方式
	 * @param result 最终结果
	 * @param temp 临时用的结果存储
	 * @param nums 数组
	 * @param used 是否被用
	 */
	public void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, boolean[] used) {
		// 如果集合达到数组的长度，满足要求，添加
		if (temp.size() == nums.length) {
			result.add(new ArrayList<>(temp));
			return;
		}

		for (int i = 0; i < nums.length; i++) {
			// 如果元素已经用过，设为true
			if (used[i] == true) {
				continue;
			}
			// 如果元素跟前面的元素相同，并且前面的元素没有被用过，说明有相同的元素，跳过（第一次递归的循环跳过）
			if (i > 0 && nums[i - 1] == nums[i] && !used[i - 1]) {
				continue;
			}
			used[i] = true;
			temp.add(nums[i]);
			backtrack(result, temp, nums, used);
			used[i] = false;
			temp.remove(temp.size() - 1);
		}
	}
}
