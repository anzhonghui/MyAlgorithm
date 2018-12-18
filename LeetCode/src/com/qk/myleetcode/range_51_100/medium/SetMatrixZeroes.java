package com.qk.myleetcode.range_51_100.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
/**
 * @Description : Set Matrix Zeroes
 * 二维数组中如果某一位置值为0，他的行和列都设置成0
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月12日
 */
public class SetMatrixZeroes {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		int[][] matrix = new int[4][4];
		matrix[0] = new int[] { 1, 1, 1, 1 };
		matrix[1] = new int[] { 1, 0, 1, 1 };
		matrix[2] = new int[] { 1, 1, 0, 0 };
		matrix[3] = new int[] { 0, 0, 0, 1 };
		setZeroes(matrix);
		for (int i = 0; i < matrix.length; ++i) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		int[][] matrix = new int[3][3];
		matrix[0] = new int[] { 1, 1, 1 };
		matrix[1] = new int[] { 1, 0, 1 };
		matrix[2] = new int[] { 1, 1, 1 };
		setZeroes2(matrix);
		for (int i = 0; i < matrix.length; ++i) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}

	/**
	 * 1.Additional Memory Approach（附加内存发，使用额外的空间）
	 * @param matrix
	 */
	public void setZeroes(int[][] matrix) {
		int R = matrix.length;
		int C = matrix[0].length;
		Set<Integer> rows = new HashSet<Integer>();
		Set<Integer> cols = new HashSet<Integer>();

		// Essentially, we mark the rows and columns that are to be made zero
		// 基础，标记哪些行哪些列是0
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (matrix[i][j] == 0) {
					rows.add(i);
					cols.add(j);
				}
			}
		}

		// Iterate over the array once again and using the rows and cols sets, update
		// the elements.
		// 再次遍历，更新元素
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (rows.contains(i) || cols.contains(j)) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	/**
	 * 2.控制空间，取出额外空间的使用
	 * 低效率：因为会有重复设置的情况
	 * @param matrix
	 */
	public void setZeroes2(int[][] matrix) {
		int MODIFIED = -1000000;
		int R = matrix.length;
		int C = matrix[0].length;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (matrix[r][c] == 0) {
					// We modify the corresponding rows and column elements in place.
					// Note, we only change the non zeroes to MODIFIED
					// 如果该行有0值，则设置整行和整列的非0值为特殊值
					for (int k = 0; k < C; k++) {
						if (matrix[r][k] != 0) {
							matrix[r][k] = MODIFIED;
						}
					}
					for (int k = 0; k < R; k++) {
						if (matrix[k][c] != 0) {
							matrix[k][c] = MODIFIED;
						}
					}
				}
			}
		}

		// 将非0值转换为0
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				// Make a second pass and change all MODIFIED elements to 0 """
				if (matrix[r][c] == MODIFIED) {
					matrix[r][c] = 0;
				}
			}
		}
	}

	/**
	 * 1.对于每个单元，不是去(M+NM+N)单元并将其设置为零，我们只要在两个单元(M+N)中设置标志
	 * 主要判断几种情况：
	 * 1.第一列是否有0
	 * 2.第一行是否有0
	 * 3.如果中间的内容有0，设置该位置的同行第一列和同列第一行为0
	 * 4.通过判断行和列的首元素设置0
	 * @param matrix
	 */
	public void setZeroes3(int[][] matrix) {
		Boolean isCol = false;
		int R = matrix.length;
		int C = matrix[0].length;

		for (int i = 0; i < R; i++) {

			// Since first cell for both first row and first column is the same i.e.
			// matrix[0][0]
			// We can use an additional variable for either the first row/column.
			// For this solution we are using an additional variable for the first column
			// and using matrix[0][0] for the first row.
			// 判断第一列是否有0的情况
			if (matrix[i][0] == 0) {
				isCol = true;
			}

			for (int j = 1; j < C; j++) {
				// If an element is zero, we set the first element of the corresponding row and
				// column to 0
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0; // 设置列的第一行为0
					matrix[i][0] = 0; // 设置行的第一列为0
				}
			}
		}

		// Iterate over the array once again and using the first row and first column,
		// update the elements.
		for (int i = 1; i < R; i++) {
			for (int j = 1; j < C; j++) {
				// 如果某列的第一行或者某一行的第一列为0，设置为0
				if (matrix[i][0] == 0 || matrix[0][j] == 0) {
					matrix[i][j] = 0;
				}
			}
		}

		// See if the first row needs to be set to zero as well
		// 如果首元素为0，设置第一行为0
		if (matrix[0][0] == 0) {
			for (int j = 0; j < C; j++) {
				matrix[0][j] = 0;
			}
		}

		// See if the first column needs to be set to zero as well
		// 如果第一列有为0的情况，设置第一列为0
		if (isCol) {
			for (int i = 0; i < R; i++) {
				matrix[i][0] = 0;
			}
		}
	}
}
