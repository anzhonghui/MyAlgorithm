package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题4：二位数组中的查找
 * 二位数组中每行每列都是递增顺讯，查找target值是否存在
 * 1 2 8 9 
 * 2 3 9 12
 * 4 7 10 13
 * 6 8 11 15
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Array_InterviewQuestions4 {

	/**
	 * @Description:
	 */
	@Test
	public void MyTest() {
		int[][] matrix = new int[4][];
		matrix[0] = new int[] {1, 2, 8, 9};
		matrix[1] = new int[] {2, 4, 9, 12};
		matrix[2] = new int[] {4, 7, 10, 13};
		matrix[3] = new int[] {6, 8, 11, 15};
		
		// 正常测试
		System.out.println(find(matrix, 7));
		System.out.println(find(matrix, 4));
		System.out.println(find(matrix, 5));
		
		// 边界值测试
		System.out.println(find(matrix, 0));
		System.out.println(find(matrix, 16));
		
		// 非法情况测试
		System.out.println(find(null, 16));
		int[][] matrix2 = new int[4][];
		System.out.println(find(matrix2, 16));
	}

	/**
	 * @Programme：找规律，比较target和右上角的值。大于，剔除列；小于，剔除行
	 * @param nums
	 * @return
	 */
	public boolean find(int[][] matrix, int target) {
		
		if(matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return false;
		}
		
		int rows = matrix.length;
		int columns = matrix[0].length;
		int row = 0;
		int column = columns - 1;
		
		while(row < rows && column >= 0) {
			// 比较右上角，大于，剔除列；小于，剔除行
			if(matrix[row][column] == target) {
				return true;
			}
			// 大于，剔除列
			while(column >= 0 && matrix[row][column] > target) {
				--column;
			}
			// 如果列值小于0，说明列中的数字都大于target
			if(column < 0) {
				return false;
			}
			// 小于，剔除行
			while(row < rows && matrix[row][column] < target) {
				++row;
			}
			
		}
		
		return false;
	}

}
