package com.qk.myleetcode.range_1_50.hard;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * 37.Sudoku Solver
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
 * @Description : Sudoku Solver 数独解
 * @Programme：使用递归找数独的节，在空格中尝试1-9个数字，判断插入某个数字后是否满足行、列、九宫格的唯一；
 * 			   添加之后递归检查数独是否有解，如果没有，恢复原空格的值，继续尝试。
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月23日
 */
public class SudokuSolver {

	/**
	 * @Description: 数独解，采用递归的方式，时间复杂度高
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		char[][] board = new char[9][9];
		board[0] = new char[] { '5', '3', '.', '.', '7', '.', '.', '.', '.' };
		board[1] = new char[] { '6', '.', '.', '1', '9', '5', '.', '.', '.' };
		board[2] = new char[] { '.', '9', '8', '.', '.', '.', '.', '6', '.' };
		board[3] = new char[] { '8', '.', '.', '.', '6', '.', '.', '.', '3' };
		board[4] = new char[] { '4', '.', '.', '8', '.', '3', '.', '.', '1' };
		board[5] = new char[] { '7', '.', '.', '.', '2', '.', '.', '.', '6' };
		board[6] = new char[] { '.', '6', '.', '.', '.', '.', '2', '8', '.' };
		board[7] = new char[] { '.', '.', '.', '4', '1', '9', '.', '.', '5' };
		board[8] = new char[] { '.', '.', '.', '.', '8', '.', '.', '7', '9' };

		solveSudoku(board);

		for (char[] cs : board) {
			System.out.println(Arrays.toString(cs));
		}
	}

	public void solveSudoku(char[][] board) {
		if (board == null || board.length == 0)
			return;
		solve(board);
	}

	public boolean solve(char[][] board) {
		// 从行开始遍历
		for (int i = 0; i < board.length; i++) {
			// 遍历列
			for (int j = 0; j < board[0].length; j++) {
				// 如果字符为空
				if (board[i][j] == '.') {
					// 遍历1-9个数字进行检查
					for (char c = '1'; c <= '9'; c++) {// trial. Try 1 through 9
						if (isValid(board, i, j, c)) {
							board[i][j] = c; // Put c for this cell
							// 继续用递归执行，查看该解是否是数独的解
							if (solve(board))
								return true; // If it's the solution return true
							// 不是，跳出递归，重新赋值
							else
								board[i][j] = '.'; // Otherwise go back
						}
					}

					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 检查插入的数据是否满足情况
	 * @param board
	 * @param row
	 * @param col
	 * @param c
	 * @return 是否满足
	 */
	private boolean isValid(char[][] board, int row, int col, char c) {
		for (int i = 0; i < 9; i++) {
			// check column（列不变，行号边，检查一整列）
			if (board[i][col] != '.' && board[i][col] == c)
				return false;
			// check row（行不变，列号边，检查一整行）
			if (board[row][i] != '.' && board[row][i] == c)
				return false;
			// check 3*3 block 检查block块
			if (board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] != '.'
					&& board[3 * (row / 3) + i / 3][3 * (col / 3) + i % 3] == c)
				return false;
		}
		return true;
	}
}
