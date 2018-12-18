package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;
/**
 * @Description : Search a 2D Matrix    
 * 在二位数组中查找target
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月12日
 */
public class SearchA2DMatrix {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		int[][] matrix = new int[3][4];
		matrix[0] = new int[] { 1, 3, 5, 7 };
		matrix[1] = new int[] { 10, 11, 16, 20 };
		matrix[2] = new int[] { 23, 30, 34, 50 };
//		System.out.println(searchMatrix(matrix , 3));
//		System.out.println(searchMatrix(matrix , 13));
//		System.out.println(searchMatrix(new int[1][], 0));
//		int[][] arr = new int[1][];
//		arr[0] = new int[] { 1 };
//		System.out.println(searchMatrix(arr, 1));
		int[][] arr2 = new int[1][];
		arr2[0] = new int[] { 1, 3 };
		System.out.println(searchMatrix(arr2, 3));
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest3() {
		int[][] matrix = new int[3][4];
		matrix[0] = new int[] { 1, 3, 5, 7 };
		matrix[1] = new int[] { 10, 11, 16, 20 };
		matrix[2] = new int[] { 23, 30, 34, 50 };
		System.out.println(searchMatrix3(matrix , 3));
		System.out.println(searchMatrix3(matrix , 13));
		System.out.println(searchMatrix3(new int[1][], 0));
		int[][] arr = new int[1][];
		arr[0] = new int[] { 1 };
		System.out.println(searchMatrix3(arr, 1));
		int[][] arr2 = new int[1][];
		arr2[0] = new int[] { 1, 3 };
		System.out.println(searchMatrix3(arr2, 3));
		int[][] arr3 = new int[1][];
		arr3[0] = new int[] { 1, 3, 5 };
		System.out.println(searchMatrix3(arr3, 2));
		
		
	}

	/**
	 * 使用二分法
	 * 时间复杂度O(log(m*n))
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix3(int[][] matrix, int target) {
		if(matrix == null) {
			return false;
		}
		int m = matrix.length;
		if (m == 0 || matrix[0] == null)
			return false;
		int n = matrix[0].length;
		if (n == 0)
			return false;
		if (target > matrix[m - 1][n - 1] || target < matrix[0][0])
			return false;
		
		int l = 0, r = m * n - 1;
		while (l != r) {
			int mid = (l + r - 1) >> 1;
			if (matrix[mid / n][mid % n] < target)
				l = mid + 1;
			else
				r = mid;
		}
		
		return matrix[r / n][r % n] == target;
	}

	/**
	 * 使用普通的查找，遍历每行的首尾，再遍历某一行的所有元素
	 * 时间复杂度：O(m+n)
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix2(int[][] matrix, int target) {
		int m = matrix.length;
		if (m == 0)
			return false;
		int n = matrix[0].length;
		if (n == 0)
			return false;
		if (target > matrix[m - 1][n - 1] || target < matrix[0][0])
			return false;

		for (int i = 0; i < m; i++) {
			if (target >= matrix[i][0] && target <= matrix[i][n - 1]) {
				for (int j = 0; j < n; j++) {
					if (matrix[i][j] == target)
						return true;
				}
			}
		}

		return false;
	}

	/**
	 * 思路：遍历首尾+遍历行使用的二分法
	 * 时间复杂度：O(m+log(n))
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {

		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return false;
		}

		if (matrix[0][0] > target) {
			return false;
		}

		int row = matrix.length;
		int col = matrix[0].length;

		if (matrix[row - 1][col - 1] < target) {
			return false;
		}

		for (int i = 0; i < row; ++i) {
			if (matrix[i][0] <= target && matrix[i][col - 1] >= target) {
				int left = 0;
				int right = col - 1;
				int medium = 0;

				if (left == right) {
					if (matrix[i][0] == target) {
						return true;
					} else {
						return false;
					}
				}

				while (left < right) {
					medium = (left + right) / 2;
					if (matrix[i][medium] == target) {
						return true;
					} else if (matrix[i][medium] < target) {
						left = medium + 1;
					} else {
						right = medium;
					}
				}

				if (left == right && matrix[i][right] == target) {
					return true;
				}
			}
		}

		return false;
	}
}
