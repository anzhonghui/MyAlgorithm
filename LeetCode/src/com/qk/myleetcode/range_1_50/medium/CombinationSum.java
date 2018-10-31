package com.qk.myleetcode.range_1_50.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
/**
 * 
 * 39.Combination Sum
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
 * @Description : 找出给定数组中所有能够达到目标元素的解（元素可以重复）
 * @Programme：使用递归的方式：递归尝试数组中每个数字的各种个数达到的目标元素的情况，然后依次移除最后一个元素，装入新元素尝试
 * 过程简单描述： 
 * 1：    2 2 2 2 满足
 * 2：    2 2 2 3 不满足
 * 3:   2 2 3  不满足
 * 4：    2 3 3 满足
 * 5：    2 5 不满足
 * 6：    3 3 不满足
 * 7：    3 5 满足
 * 
 * 递归：
 * 1.基线条件：当目标元素的值被减到0，添加结果，跳出递归；循环条件即没有被减到0
 * 2.循环数组中的元素，目标元素减去当前的数组元素
 *  2.1 >=0
 *  	2.1.1 将当前元素添加到list中
 *  	2.1.2 递归（因为可以重复，所以起始坐标不变，target减掉当前元素）
 *  	2.1.3 移除list中最后一个元素
 *  2.2 <0，跳出循环
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
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
		// 对数组进行排序
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
		 * 1：    2 2 2 2 满足
		 * 2：    2 2 2 3 不满足
		 * 3:   2 2 3  不满足
		 * 4：    2 3 3 满足
		 * 5：    2 5 不满足
		 * 6：    3 3 不满足
		 * 7：    3 5 满足
		 */
		for (int i = start; i < candidates.length; i++) {
			if (target - candidates[i] >= 0) {
				list.add(candidates[i]);
				// 执行完，可能有一组解
				backtrack(res, list, candidates, i, target - candidates[i]);
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
