package com.qk.myleetcode.range_51_100.hard;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * N皇后问题，将n个皇后放入n*n的棋盘中（皇后的攻击范围是经过所在位置的任何直线和对角线）
 * @Description : 
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class NQueens {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(solveNQueens(4).toString());
	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> ret = new ArrayList<>();
		dfs(n, 0, new int[n], ret);
		return ret;
	}

	/**
	 * 通过递归尝试所有可能的情况
	 * @param n
	 * @param row
	 * @param colForRows
	 * @param ret
	 */
	private void dfs(int n, int row, int[] colForRows, List<List<String>> ret) {
		if (row == n) {
			List<String> item = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				StringBuilder sb = new StringBuilder();
				for (int j = 0; j < n; j++) {
					if (colForRows[i] == j) {
						sb.append('Q');
					} else {
						sb.append('.');
					}
				}
				item.add(sb.toString());
			}
			ret.add(item);
			return;
		}
		for (int i = 0; i < n; i++) {
			colForRows[row] = i;
			if (isValid(row, colForRows)) {
				dfs(n, row + 1, colForRows, ret);
			}
		}
	}

	/**
	 * 判断皇后之间是否能攻击到
	 * @param row 行数
	 * @param colForRows 女王所在的位置，坐标是行，值是列
	 * @return
	 */
	private boolean isValid(int row, int[] colForRows) {
		for (int i = 0; i < row; i++) {
			if (colForRows[i] == colForRows[row] || Math.abs(colForRows[row] - colForRows[i]) == row - i) {
				return false;
			}
		}
		return true;
	}
}
