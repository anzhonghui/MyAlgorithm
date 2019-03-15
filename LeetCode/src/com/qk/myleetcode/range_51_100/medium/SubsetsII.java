package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @Description : 求给定数组的子集，数组包含重复元素 
 * @Author : huihui
 * @Date : Create in 2019年3月7日
 */
public class SubsetsII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(subsetsWithDup(new int[] { 1, 2, 2 }));
	}

	public List<List<Integer>> subsetsWithDup(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> each = new ArrayList<>();
		helper(res, each, 0, nums);
		return res;
	}

	/**
	 * 采用递归的方式
	 * 过程：
	 * []
	 * [1]
	 * [1,2]
	 * [1,2,2]
	 * [2]
	 * [2,2]
	 * @param res
	 * @param each
	 * @param pos
	 * @param n
	 */
	public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
		if (pos <= n.length) {
			res.add(each);
		}
		int i = pos;
		// 此处最好用while循环，用for不太方便
		while (i < n.length) {
			each.add(n[i]);
			helper(res, new ArrayList<>(each), i + 1, n);
			each.remove(each.size() - 1);
			i++;
			// 如果发现前后元素相同，直接++
			while (i < n.length && n[i] == n[i - 1]) {
				i++;
			}
		}
	}

}
