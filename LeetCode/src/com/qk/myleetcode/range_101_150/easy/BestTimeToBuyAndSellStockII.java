package com.qk.myleetcode.range_101_150.easy;

import org.junit.Test;

public class BestTimeToBuyAndSellStockII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(maxProfit(new int[] { 7, 1, 5, 3, 6, 4 }));
		System.out.println(maxProfit(new int[] { 1, 7, 2, 3, 6, 7, 6, 7 }));
	}

	/**
	 * 将数组化成折线图，算每个峰的和，谷略过（简化了峰谷的方法）
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int maxprofit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] > prices[i - 1])
				maxprofit += prices[i] - prices[i - 1];
		}
		return maxprofit;
	}

	/**
	 * 峰谷方法，通过不断更新峰和谷两个数值，计算收益
	 * @param prices
	 * @return
	 */
	public int maxProfitByPeakValleyApproach(int[] prices) {
		int i = 0;
		int valley = prices[0];
		int peak = prices[0];
		int maxprofit = 0;
		while (i < prices.length - 1) {
			while (i < prices.length - 1 && prices[i] >= prices[i + 1])
				i++;
			// 更新谷
			valley = prices[i];
			while (i < prices.length - 1 && prices[i] <= prices[i + 1])
				i++;
			// 更新峰
			peak = prices[i];
			// 计算收益
			maxprofit += peak - valley;
		}
		return maxprofit;
	}

	/**
	 * 蛮力解
	 * @param prices 
	 * @return
	 */
	public int maxProfitByBruteForce(int[] prices) {
		return calculate(prices, 0);
	}

	public int calculate(int prices[], int s) {
		if (s >= prices.length)
			return 0;
		int max = 0;
		for (int start = s; start < prices.length; start++) {
			int maxprofit = 0;
			for (int i = start + 1; i < prices.length; i++) {
				if (prices[start] < prices[i]) {
					int profit = calculate(prices, i + 1) + prices[i] - prices[start];
					if (profit > maxprofit)
						maxprofit = profit;
				}
			}
			if (maxprofit > max)
				max = maxprofit;
		}
		return max;
	}

	/**
	 * 自己写的，没有成功
	 * @param prices
	 * @return
	 */
	public int maxProfitByMe(int[] prices) {
		if (prices == null || prices.length == 0) {
			return 0;
		}
		int result = 0;
		int min = prices[0];
		int temp = 0;
		for (int i = 1; i < prices.length; ++i) {
			if (prices[i] < min || prices[i] < prices[i - 1]) {
				result += temp;
				min = prices[i];
				continue;
			}

			if (temp < prices[i] - min) {
				temp = prices[i] - min;
			}
		}
		return result;
	}
}
