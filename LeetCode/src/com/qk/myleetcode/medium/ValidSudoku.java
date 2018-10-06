package com.qk.myleetcode.medium;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class ValidSudoku {

	@Test
	public void MyTest() {
		char[][] board = new char[9][];
		board[0] = new char[] { '5', '3', '.', '.', '7', '.', '.', '.', '.' };
		board[1] = new char[] { '6', '.', '.', '1', '9', '5', '.', '.', '.' };
		board[2] = new char[] { '.', '9', '8', '.', '.', '.', '.', '6', '.' };
		board[3] = new char[] { '8', '.', '.', '.', '6', '.', '.', '.', '3' };
		board[4] = new char[] { '4', '.', '.', '8', '.', '3', '.', '.', '1' };
		board[5] = new char[] { '7', '.', '.', '.', '2', '.', '.', '.', '6' };
		board[6] = new char[] { '.', '6', '.', '.', '.', '.', '2', '8', '.' };
		board[7] = new char[] { '.', '.', '.', '4', '1', '9', '.', '.', '5' };
		board[8] = new char[] { '.', '.', '.', '.', '8', '.', '.', '7', '9' };

		System.out.println(isValidSudoku(board));
	}

	/**
	 * 可以节省由于一直set里面添加导致的时间浪费
	 * @param board
	 * @return
	 */
	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < 9; i++) {
			HashSet<Character> rows = new HashSet<Character>(); // 记录rows[i]到目前为止已经出现过的数字
			HashSet<Character> columns = new HashSet<Character>(); // 记录col[i]到目前为止已经出现过的数字
			HashSet<Character> cube = new HashSet<Character>();
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.' && !rows.add(board[i][j])) // 依次check row[i]的每一列的数字, [i][j]是数字，且当前row已经出现过
					return false; // 行不能重复
				if (board[j][i] != '.' && !columns.add(board[j][i])) // 依次check col[i]的每一行的数字，[j][i]是数字，且当前col已经出现过
					return false; // 列不能重复

				// 把9个小九宫格编号，则为
				// 0 1 2
				// 3 4 5
				// 6 7 8
				// 确定[i][j]所在的九宫格编号，然后前面乘以3，得到对应九宫格左上角的坐标，通过计算添加到集合中某一个块的值
				// [RowIndex][ColIndex] 为每个九宫格左上角的坐标，
				// [RowIndex + j / 3][ColIndex + j % 3]: 
				// 每三个一行，比如当j=0,1,2时，得到的坐标为[0][0],[0][1],[0][2];j=3,4,5时，得到的坐标为[1][0],[1][1],[1][2]
				int RowIndex = 3 * (i / 3);
				int ColIndex = 3 * (i % 3);
				if (board[RowIndex + j / 3][ColIndex + j % 3] != '.'
						&& !cube.add(board[RowIndex + j / 3][ColIndex + j % 3]))
					return false;
			}
		}
		return true;
	}

	/**
	 * 只需要判断现在已有的元素是否有冲突，不需要判断是否可解
	 * 思路，每个元素都遍历一下
	 * @param board
	 * @return
	 */
	public boolean isValidSudoku1(char[][] board) {
		Set<String> seen = new HashSet<String>();
		for (int i = 0; i < 9; ++i) {
			for (int j = 0; j < 9; ++j) {
				char number = board[i][j];
				if (number != '.')
					// 有任意条件不满足，则false
					if (!seen.add(number + " in row " + i) // 判断元素在哪一行
							|| !seen.add(number + " in column " + j) // 判断元素在哪一列
							|| !seen.add(number + " in block " + i / 3 + "-" + j / 3)) // 判断元素在哪一个3*3方块
						return false;
			}
		}
		return true;
	}
}
