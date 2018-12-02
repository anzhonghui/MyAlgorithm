package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SpiralMatrix {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {

		/**
		 *  [ 1, 2, 3 ],
		 *  [ 4, 5, 6 ],
		 *  [ 7, 8, 9 ]
		 */
		int[][] arr = new int[3][3];
		arr[0] = new int[] { 1, 2, 3 };
		arr[1] = new int[] { 4, 5, 6 };
		arr[2] = new int[] { 7, 8, 9 };
		System.out.println(spiralOrder(arr));

	}

	/**
	 * 思想相同，与下面的解法；比较更容易理解，在变量的起名字方面
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrderBest(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		if (matrix.length < 1 || matrix[0].length < 1)
			return res;

		int top = 0, bottom = matrix.length - 1, left = 0, right = matrix[0].length - 1;
		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++) {
				res.add(matrix[top][i]);
			}
			top++;
			for (int i = top; i <= bottom; i++) {
				res.add(matrix[i][right]);
			}
			right--;
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					res.add(matrix[bottom][i]);
				}
				bottom--;
			}
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					res.add(matrix[i][left]);
				}
				left++;
			}
		}
		return res;
	}

	/**
	 *     
	 *  [ 1, 2, 3 ],
	 *  [ 4, 5, 6 ],  
	 *  [ 7, 8, 9 ]
	 *                                                  r1=0, c2=2, r2=2, c1=0          r1=1
	 *  top:      c from c1.....c2        [r1][c]       1, 2, 3                         4, 5
	 *  right:    r from r1+1.....r2      [r][c2]       6, 9
	 *  bottom:   c from c2+1.....c1+1    [r2][c]       8
	 *  left:     r from r2+1.....r1+1    [r][c1]       7
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		if (matrix.length == 0)
			return ans;
		// 行
		int r1 = 0, r2 = matrix.length - 1;
		// 列
		int c1 = 0, c2 = matrix[0].length - 1;
		// 循环跳出条件
		while (r1 <= r2 && c1 <= c2) {
			for (int c = c1; c <= c2; c++)
				ans.add(matrix[r1][c]);
			for (int r = r1 + 1; r <= r2; r++)
				ans.add(matrix[r][c2]);
			if (r1 < r2 && c1 < c2) {
				for (int c = c2 - 1; c > c1; c--)
					ans.add(matrix[r2][c]);
				for (int r = r2; r > r1; r--)
					ans.add(matrix[r][c1]);
			}
			r1++;
			r2--;
			c1++;
			c2--;
		}
		return ans;
	}

	public List<Integer> spiralOrder2(int[][] matrix) {
		List<Integer> ans = new ArrayList<>();
		if (matrix.length == 0)
			return ans;
		int R = matrix.length, C = matrix[0].length;
		boolean[][] seen = new boolean[R][C];
		int[] dr = { 0, 1, 0, -1 };
		int[] dc = { 1, 0, -1, 0 };
		int r = 0, c = 0, di = 0;
		for (int i = 0; i < R * C; i++) {
			ans.add(matrix[r][c]);
			seen[r][c] = true;
			int cr = r + dr[di];
			int cc = c + dc[di];
			if (0 <= cr && cr < R && 0 <= cc && cc < C && !seen[cr][cc]) {
				r = cr;
				c = cc;
			} else {
				di = (di + 1) % 4;
				r += dr[di];
				c += dc[di];
			}
		}
		return ans;
	}
}
