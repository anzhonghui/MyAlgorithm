package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

public class MinimumPathSum {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		int[][] grid = new int[3][3];
		grid[0] = new int[] { 1, 3, 1 };
		grid[1] = new int[] { 1, 5, 1 };
		grid[2] = new int[] { 4, 2, 1 };
		System.out.println(minPathSum(grid));
	}

	/**
	 * 对DP方法简单的优化，减少过多的判断
	 * @param grid
	 * @return
	 */
	public int minPathSumOptimize(int[][] grid) {
		int rows = grid.length;
		int cols = grid[0].length;
		int[][] res = new int[rows][cols];
		// 1.处理原点
		res[0][0] = grid[0][0];
		// 2.处理第一行
		for (int i = 1; i < cols; i++) {
			res[0][i] = res[0][i - 1] + grid[0][i];
		}
		// 3.处理第一列
		for (int j = 1; j < rows; j++) {
			res[j][0] = res[j - 1][0] + grid[j][0];
		}
		// 4.处理其他情况
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				res[i][j] = Math.min(res[i - 1][j], res[i][j - 1]) + grid[i][j];
			}
		}

		return res[rows - 1][cols - 1];
	}

	/**
	 * 使用DP的思想解决
	 * 主要包含四种情况：
	 * 1.处理原点，为原来的值
	 * 2.处理第一行
	 * 3.处理第一列
	 * 4.处理其他情况，取 grid[i][j - 1], grid[i - 1][j]最小的值，因为最终计算的是最短路径
	 * 时间复杂度：O(m*n)
	 * @param grid
	 * @return
	 */
	public int minPathSum(int[][] grid) {
		int m = grid.length;// row
		int n = grid[0].length; // column
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 处理第一行
				if (i == 0 && j != 0) {
					grid[i][j] = grid[i][j] + grid[i][j - 1];

					// 处理第一列
				} else if (i != 0 && j == 0) {
					grid[i][j] = grid[i][j] + grid[i - 1][j];

					// 处理原点，为原来的值
				} else if (i == 0 && j == 0) {
					grid[i][j] = grid[i][j];

					// 处理正常的情况
				} else {

					// 取 grid[i][j - 1], grid[i - 1][j]最小的值，因为最终计算的是最短路径
					grid[i][j] = Math.min(grid[i][j - 1], grid[i - 1][j]) + grid[i][j];
				}
			}
		}

		return grid[m - 1][n - 1];
	}
}
