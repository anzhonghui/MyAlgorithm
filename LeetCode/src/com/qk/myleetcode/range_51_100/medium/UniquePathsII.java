package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

/**
 * 
 * 63. Unique Paths II
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
 * @Description : 唯一路径，找到从左上角到右下角的所有路径，但中间会有障碍
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月6日
 */
public class UniquePathsII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {

	}

	/**
	 * 同样使用DP的思想，对路线进行了优化，如果有障碍，直接跳出，换路径，因为只能right和down
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstaclesOptimize(int[][] obstacleGrid) {
		int rows = obstacleGrid.length;
		int cols = obstacleGrid[0].length;
		int[][] res = new int[rows][cols];
		for (int i = 0; i < cols; i++) {
			// 优化了障碍的发现，如果有障碍，跳出
			if (obstacleGrid[0][i] == 1) {
				break;
			}

			res[0][i] = 1;
		}

		for (int i = 0; i < rows; i++) {
			// 优化了障碍的发现，如果有障碍，跳出
			if (obstacleGrid[i][0] == 1) {
				break;
			}

			res[i][0] = 1;
		}

		/**
		 * 从(1,1)开始计算，满足公式：obstacleGrid[i][j] = res[i - 1][j] + res[i][j - 1];
		 */
		for (int i = 1; i < rows; i++) {
			for (int j = 1; j < cols; j++) {
				res[i][j] = obstacleGrid[i][j] == 1 ? 0 : res[i - 1][j] + res[i][j - 1];
			}
		}

		return res[rows - 1][cols - 1];
	}

	/**
	 * 使用DP的思想
	 * @param obstacleGrid
	 * @return
	 */
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {

		int R = obstacleGrid.length;
		int C = obstacleGrid[0].length;

		// If the starting cell has an obstacle, then simply return as there would be
		// no paths to the destination.
		// 如果起始点的位置为1，障碍，没有路径
		if (obstacleGrid[0][0] == 1) {
			return 0;
		}

		// Number of ways of reaching the starting cell = 1.
		// 初始单元初始化为1
		obstacleGrid[0][0] = 1;

		// Filling the values for the first column
		// 填充第一列的数据，不全部初始化为1，主要考虑到有障碍的问题，障碍设置为0
		for (int i = 1; i < R; i++) {
			obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
		}

		// Filling the values for the first row
		// 填充第一行的数据，不全部初始化为1，主要考虑到有障碍的问题，障碍设置为0
		for (int i = 1; i < C; i++) {
			obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
		}

		// Starting from cell(1,1) fill up the values
		// No. of ways of reaching cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
		// i.e. From above and left.
		// 从(1,1)单元开始初始化数值，还是满足公式cell[i][j] = cell[i - 1][j] + cell[i][j - 1]
		// 如果是障碍，设置为0，忽略
		for (int i = 1; i < R; i++) {
			for (int j = 1; j < C; j++) {
				if (obstacleGrid[i][j] == 0) {
					obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
				} else {
					obstacleGrid[i][j] = 0;
				}
			}
		}

		// Return value stored in rightmost bottommost cell. That is the destination.
		// 返回DP计算的结果
		return obstacleGrid[R - 1][C - 1];
	}
}
