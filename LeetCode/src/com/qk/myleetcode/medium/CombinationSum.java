package com.qk.myleetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CombinationSum {

	@Test
	public void MyTest() {
		System.out.println(combinationSum(new int[] { 2, 3, 5 }, 8));
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		if (candidates == null || candidates.length == 0) {
			return new ArrayList<>();
		}
		List<Integer> list = new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(candidates);
		backtrack(res, list, candidates, 0, target);
		return res;
	}

	/**
	 * 递归的方式，start，起始元素；对每个元素进行递归，比如对当取2时，递归计算；然后挨个移除，看谁家其他元素看是否有满足条件的；
	 * 移除所有的2后，添加3，以相同的方式递归
	 * @param list 存放所有结果的集合
	 * @param tempList 存放一组结果的集合
	 * @param nums 数组
	 * @param remain 剩余的值
	 * @param start 起始元素
	 */
	public void backtrack(List<List<Integer>> res, List<Integer> list, int[] candidates, int start, int target) {
		// 说明递归减最终的和为target
		if (target == 0) {
			res.add(new ArrayList<>(list));
			return;
		}

		// 对数组进行遍历，每次递归从start开始
		/**
		 * 过程简单描述： 
		 * 第一次递归后的结果是：       2 2 2 2 
		 *移除最后一个元素继续递归： 2 2 2 3(5 ...)
		 */
		for (int i = start; i < candidates.length; i++) {
			if (target - candidates[i] >= 0) {
				list.add(candidates[i]);
				// 执行完，可能有一组解
				backtrack2(res, list, candidates, i, target - candidates[i]);
				// 依次移除最后一个元素，尝试跟其他元素匹配的结果
				list.remove(list.size() - 1);
			} else {
				// 当递归相减的值小于0是，跳出循环，这组数据无效
				break;
			}
		}
	}

	public List<List<Integer>> combinationSum2(int[] nums, int target) {
		List<List<Integer>> list = new ArrayList<>();
		Arrays.sort(nums);
		backtrack2(list, new ArrayList<>(), nums, target, 0);
		return list;
	}

	/**
	 * 递归的方式，start，起始元素；对每个元素进行递归，比如对当取2时，递归计算；然后挨个移除，看谁家其他元素看是否有满足条件的；
	 * 移除所有的2后，添加3，以相同的方式递归
	 * @param list 存放所有结果的集合
	 * @param tempList 存放一组结果的集合
	 * @param nums 数组
	 * @param remain 剩余的值
	 * @param start 起始元素
	 */
	private void backtrack2(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
		if (remain < 0)
			return;
		else if (remain == 0)
			list.add(new ArrayList<>(tempList));
		else {
			for (int i = start; i < nums.length; i++) {
				tempList.add(nums[i]);
				backtrack2(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
				// 递归结束，移除最后一个追加的元素
				tempList.remove(tempList.size() - 1);
			}
		}
	}

	/**
	 * 只想到了如何处理由一个或两个组成的情况
	 * @param candidates
	 * @param target
	 * @return
	 */
	public List<List<Integer>> combinationSumByMe(int[] candidates, int target) {
		List<List<Integer>> lists = new ArrayList<>();

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < candidates.length; i++) {
			list.add(candidates[i]);
		}

		for (int i = 0; i < candidates.length; i++) {
			// 处理倍数的情况
			if (target % candidates[i] == 0) {
				List<Integer> temp = new ArrayList<>();
				for (int j = 0; j < target / candidates[i]; j++) {
					temp.add(candidates[i]);
				}
				lists.add(temp);
			}
			// 处理两个的情况
			if (list.contains(target % candidates[i])) {
				List<Integer> temp = new ArrayList<>();
				temp.add(target % candidates[i]);
				for (int j = 0; j < target / candidates[i]; j++) {
					temp.add(candidates[i]);
				}
				lists.add(temp);
			}
		}

		System.out.println(lists.toString());

		return lists;
	}
}
