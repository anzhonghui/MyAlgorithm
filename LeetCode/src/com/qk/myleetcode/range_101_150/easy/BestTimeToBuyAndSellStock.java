package com.qk.myleetcode.range_101_150.easy;

import org.junit.Test;

public class BestTimeToBuyAndSellStock {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
//		System.out.println(maxProfit(new int[] { 7, 6, 4, 3, 1 }));
//		System.out.println(maxProfit(new int[] { 2, 4, 1 }));
//		System.out.println(maxProfit(new int[] { 2, 1, 2, 1, 0, 1, 2 }));
		System.out.println(maxProfitByOptimization(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfitByOptimization(new int[] { 7, 6, 4, 3, 1 }));
		System.out.println(maxProfitByOptimization(new int[] { 2, 4, 1 }));
		System.out.println(maxProfitByOptimization(new int[] { 2, 1, 2, 1, 0, 1, 2 }));
	}

	public int maxProfit(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int result = 0;
		int min = prices[0];
		int max = 0;

		for (int i = 0; i < prices.length; ++i) {
			if (prices[i] < min) {
				min = prices[i];
			}
			if (prices[i] > min) {
				max = prices[i];
			}
			result = Math.max(result, max - min);
			max = 0;
		}

		return result;
	}

	public int maxProfitByOptimization(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}

		int result = 0;
		int min = prices[0];

		for (int i = 0; i < prices.length; ++i) {
			if (prices[i] < min) {
				min = prices[i];
			}
			result = Math.max(result, prices[i] - min);
		}

		return result;
	}
}
