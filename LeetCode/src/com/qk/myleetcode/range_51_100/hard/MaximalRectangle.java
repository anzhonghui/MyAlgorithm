package com.qk.myleetcode.range_51_100.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * 最大矩形
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's 
 * and return its area.
 * @Description : 找到只包含1的最大矩形，并返回面积
 * @Example:
	Input:
	[
	  ["1","0","1","0","0"],
	  ["1","0","1","1","1"],
	  ["1","1","1","1","1"],
	  ["1","0","0","1","0"]
	]
	Output: 6
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class MaximalRectangle {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		char[][] matrix = new char[4][];
		matrix[0] = new char[] { '1', '0', '1', '0', '0' };
		matrix[1] = new char[] { '1', '0', '1', '1', '1' };
		matrix[2] = new char[] { '1', '1', '1', '1', '1' };
		matrix[3] = new char[] { '1', '0', '0', '1', '0' };

		maximalRectangle(matrix);
	}

	/**
		DP解决方案从第一行开始逐行进行。让第i行和第j列的最大矩形面积按:
			[right(i,j) - left(i,j)]*height(i,j)
		左、右和高度这三个变量都可以由前一行的信息以及当前行的信息来确定。因此，它可以看作是一个dp解。过渡方程为：
			left(i,j) = max(left(i-1,j), cur_left), cur_left 可以从当前行确定
			right(i,j) = min(right(i-1,j), cur_right), cur_right 可以从当前行确定
			height(i,j) = height(i-1,j) + 1, if matrix[i][j]=='1'; // 如果当前坐标的值是1，高度+1
			height(i,j) = 0, if matrix[i][j]=='0' // 如果当前坐标的值是0，高度无效，归0
	 */
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return 0;
		}
		int m = matrix.length, n = matrix[0].length;
		int[] left = new int[n], right = new int[n], height = new int[n];
		Arrays.fill(right, n);// 初始化为界外的值
		int res = 0;
		for (int i = 0; i < m; i++) {
			// 最左侧和最右侧的值
			int curLeft = 0, curRight = n;
			for (int j = 0; j < n; j++) {
				// 如果当前的坐标值是1，高度++，更新左侧的坐标，取最大左坐标值
				if (matrix[i][j] == '1') {
					height[j]++;
					left[j] = Math.max(left[j], curLeft);// compute left (from left to right)
				} else {
					// 如果是0，高度归0，左归0
					height[j] = 0;
					left[j] = 0;
					// curLeft 前移
					curLeft = j + 1;
				}

				// 对应的右侧的坐标
				int k = n - 1 - j;
				// 如果是1，更新right的值，去最小右坐标值
				if (matrix[i][k] == '1') {
					right[k] = Math.min(right[k], curRight); // compute right (from right to left)
				} else {
					// 类似上面，right值为长度，也就是不在范围内
					right[k] = n;
					curRight = k;
				}

			}
			// compute the area of rectangle (can do this from either side)
			// 一行结束后计算面积，以后累加值
			System.out.println(Arrays.toString(left));
			System.out.println(Arrays.toString(right));
			System.out.println(Arrays.toString(height));

			// **** 注意： right相对于起所在的坐标值大1，这样就直接省去了面积计算中的-1的操作    *******
			for (int j = 0; j < n; j++) {
				res = Math.max(res, (right[j] - left[j]) * height[j]);
			}
		}
		return res;
	}
}
