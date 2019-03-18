package com.qk.myleetcode.range_101_150.easy;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 帕斯卡三角
 * @Description : 
 * @Author : huihui
 * @Date : Create in 2019年3月18日
 */
public class PascalsTriangle {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(generate(5).toString());
	}
	
	/**
	 * 使用DP动态规划的思想解决
	 * @param numRows
	 * @return
	 */
	public List<List<Integer>> generate(int numRows) {
		List<List<Integer>> triangle = new ArrayList<List<Integer>>();

		// First base case; if user requests zero rows, they get zero rows.
		if (numRows == 0) {
			return triangle;
		}

		// Second base case; first row is always [1].
		triangle.add(new ArrayList<>());
		triangle.get(0).add(1);

		for (int rowNum = 1; rowNum < numRows; rowNum++) {
			List<Integer> row = new ArrayList<>();
			// 上一行的list
			List<Integer> prevRow = triangle.get(rowNum - 1);

			// The first row element is always 1.
			row.add(1);

			// Each triangle element (other than the first and last of each row)
			// is equal to the sum of the elements above-and-to-the-left and
			// above-and-to-the-right.
			// 叠加和
			for (int j = 1; j < rowNum; j++) {
				// 前一个元素+后一个元素
				row.add(prevRow.get(j - 1) + prevRow.get(j));
			}

			// The last row element is always 1.
			row.add(1);

			triangle.add(row);
		}

		return triangle;
	}
}
