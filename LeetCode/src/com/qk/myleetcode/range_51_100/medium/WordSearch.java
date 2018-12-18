package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

/**
 * @Description : 79.Word Search
 * 给定一个二维数组的字典和一个单词，查找这个单词是否存在于网格中。
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月16日
 */
public class WordSearch {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		char[][] board = new char[3][4];
		board[0] = new char[] {'A', 'B', 'C', 'E'};
		board[1] = new char[] {'S', 'F', 'C', 'S'};
		board[2] = new char[] {'A', 'D', 'E', 'E'};
		System.out.println(exist(board, "ABCCED"));
	}

	public boolean exist(char[][] board, String word) {
		char[] w = word.toCharArray();
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				if (exist(board, y, x, w, 0))
					return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param board 二维数组字典
	 * @param y 行
	 * @param x 列
	 * @param word 查找的单词
	 * @param i 匹配的字符的位置
	 * @return
	 */
	private boolean exist(char[][] board, int y, int x, char[] word, int i) {
		// 如果匹配的字符位置和要超找的单词长度相同，反馈true
		if (i == word.length)
			return true;
		// 匹配到最后，还是没有匹配完成，字典数组越界
		if (y < 0 || x < 0 || y == board.length || x == board[y].length)
			return false;
		// 如果字典中的字符跟查找的单词字符不同
		if (board[y][x] != word[i])
			return false;
		// 字符两次异或256后，变为原来的值，一次异或值比价特殊，用于区分
		board[y][x] ^= 256;
		// 按照当前元素的右、左、下、上的顺序查找字符是否匹配
		boolean exist = exist(board, y, x + 1, word, i + 1) || exist(board, y, x - 1, word, i + 1)
				|| exist(board, y + 1, x, word, i + 1) || exist(board, y - 1, x, word, i + 1);
		board[y][x] ^= 256;
		return exist;
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		char c = 'A';
		System.out.println((int)c);
		System.out.println(c ^ 256);
		System.out.println(c ^ 256 ^ 256 );
		System.out.println((char)c ^ 256);
	}
}
