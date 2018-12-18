package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Subsets {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(subsetsBest(new int[] { 1, 2, 3, 4 }));
	}

	/**
	 * 使用循环的方式
	 * [
	 * 		[], 
	 * 		[1], 
	 * 		[2], [1, 2], 
	 * 		[3], [1, 3], [2, 3], [1, 2, 3], 
	 * 		[4], [1, 4], [2, 4], [1, 2, 4], [3, 4], [1, 3, 4], [2, 3, 4], [1, 2, 3, 4]]
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> subsetsBest(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> original = new ArrayList<>();
		result.add(original);
		for (int i = 0; i < nums.length; i++) {
			int size = result.size();
			for (int j = 0; j < size; j++) {
				List<Integer> tmp = new ArrayList<>(result.get(j));
				tmp.add(nums[i]);
				result.add(tmp);
			}
		}
		return result;
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack(list, new ArrayList<>(), nums, 0);
		return list;
	}

	/**
	 * 使用递归的方式
	 * [
	 * 		[], 
	 * 		[1], [1, 2], [1, 2, 3], [1, 2, 3, 4], 
	 * 		[1, 2, 4], [1, 3], [1, 3, 4], [1, 4], 
	 * 		[2], [2, 3], [2, 3, 4], [2, 4], 
	 * 		[3], [3, 4], 
	 * 		[4]
	 * ]
	 * @param list
	 * @param tempList
	 * @param nums
	 * @param start
	 */
	private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			tempList.add(nums[i]);
			backtrack(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrackWithDup(list, new ArrayList<>(), nums, 0);
		return list;
	}

	private void backtrackWithDup(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
		list.add(new ArrayList<>(tempList));
		for (int i = start; i < nums.length; i++) {
			if (i > start && nums[i] == nums[i - 1])
				continue; // skip duplicates
			tempList.add(nums[i]);
			backtrackWithDup(list, tempList, nums, i + 1);
			tempList.remove(tempList.size() - 1);
		}
	}
}
