package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @Description : Combinations 组合（不排列）
 * n=4, k=2
 * [[1, 2], [1, 3], [1, 4], [2, 3], [2, 4], [3, 4]]
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月13日
 */
public class Combinations {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(combine(4, 2));
	}

	public static List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> combs = new ArrayList<List<Integer>>();
		dfs(combs, new ArrayList<Integer>(), 1, n, k);
		return combs;
	}

	/**
	 * 深度优先搜索的思想
	 * @param combs
	 * @param comb
	 * @param start
	 * @param n
	 * @param k
	 */
	public static void dfs(List<List<Integer>> combs, List<Integer> comb, int start, int n, int k) {
		if (k == 0) {
			combs.add(new ArrayList<Integer>(comb));
			return;
		}
		for (int i = start; i <= n; i++) {
			comb.add(i);
			dfs(combs, comb, i + 1, n, k - 1);
			comb.remove(comb.size() - 1);
		}
	}
}
