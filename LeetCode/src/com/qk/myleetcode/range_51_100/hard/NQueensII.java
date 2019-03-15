package com.qk.myleetcode.range_51_100.hard;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * N皇后问题，将n个皇后放入n*n的棋盘中（皇后的攻击范围是经过所在位置的任何直线和对角线），输出可能情况的数量
 * @Description : 
 *  Input: 4
	Output: 2
	Explanation: There are two distinct solutions to the 4-queens puzzle as shown below.
	[
	 [".Q..",  // Solution 1
	  "...Q",
	  "Q...",
	  "..Q."],
	
	 ["..Q.",  // Solution 2
	  "Q...",
	  "...Q",
	  ".Q.."]
	]
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class NQueensII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(totalNQueens(4));
	}

	int count = 0;
	public int totalNQueens(int n) {
		return dfs(n, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], 0);
	}

	/**
	 * 深度优先搜索
	 * @param n
	 * @param cols
	 * @param left
	 * @param right
	 * @param row
	 * @return
	 */
	private int dfs(int n, boolean[] cols, boolean[] left, boolean[] right, int row) {
		for (int col = 0; col < n; col++) {
			if (cols[col] || left[col + row] || right[n - 1 - row + col]) {
				continue;
			}
			if (row == n - 1) {
				count++;
			} else {
				cols[col] = true;
				left[col + row] = true;
				right[n - 1 - row + col] = true;
				count = dfs(n, cols, left, right, row + 1);
				cols[col] = false;
				left[col + row] = false;
				right[n - 1 - row + col] = false;
			}
		}
		return count;
	}

	/**
	 * don't need to actually place the queen,
	 * instead, for each row, try to place without violation on
	 * col/ diagonal1/ diagnol2.
	 * trick: to detect whether 2 positions sit on the same diagnol:
	 * if delta(col, row) equals, same diagnol1;
	 * if sum(col, row) equals, same diagnal2.
	 */
	private final Set<Integer> occupiedCols = new HashSet<Integer>(); // 被占领的列
	private final Set<Integer> occupiedDiag1s = new HashSet<Integer>(); // 被占领的对角线1 （一个点对应有两条对角线）
	private final Set<Integer> occupiedDiag2s = new HashSet<Integer>(); // 被占领的对角线2

	public int totalNQueensBySet(int n) {
		return totalNQueensHelper(0, 0, n);
	}

	private int totalNQueensHelper(int row, int count, int n) {
		// 循环匹配列是否满足
		for (int col = 0; col < n; col++) {
			if (occupiedCols.contains(col))
				continue;
			int diag1 = row - col;
			if (occupiedDiag1s.contains(diag1))
				continue;
			int diag2 = row + col;
			if (occupiedDiag2s.contains(diag2))
				continue;
			// we can now place a queen here（说明匹配到最后一行了，可以放置女王）
			if (row == n - 1)
				count++;
			else {
				occupiedCols.add(col);
				occupiedDiag1s.add(diag1);
				occupiedDiag2s.add(diag2);
				// 递归匹配行是否满足
				count = totalNQueensHelper(row + 1, count, n);
				// recover
				occupiedCols.remove(col);
				occupiedDiag1s.remove(diag1);
				occupiedDiag2s.remove(diag2);
			}
		}

		return count;
	}
}
