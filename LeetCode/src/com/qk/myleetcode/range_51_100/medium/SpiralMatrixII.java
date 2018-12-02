package com.qk.myleetcode.range_51_100.medium;

import java.util.Arrays;

import org.junit.Test;

/**
 * 59. Spiral Matrix II
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
 * @Description : 螺旋数组，给定一个数字生成指定大小的螺旋二位数据
 * @Example：
 * Input: 3
 * Output:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年12月2日
 */
public class SpiralMatrixII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		generateMatrix(0);
		int[][] generateMatrix1 = generateMatrix(1);
		for (int i = 0; i < generateMatrix1.length; ++i) {
			System.out.println(Arrays.toString(generateMatrix1[i]));
		}
		int[][] generateMatrix2 = generateMatrix(2);
		for (int i = 0; i < generateMatrix2.length; ++i) {
			System.out.println(Arrays.toString(generateMatrix2[i]));
		}
		
		int[][] generateMatrix3 = generateMatrix(3);
		for (int i = 0; i < generateMatrix3.length; ++i) {
			System.out.println(Arrays.toString(generateMatrix3[i]));
		}
		
	}

	public int[][] generateMatrix(int n) {

		if (n <= 0) {
			return null;
		}

		int top = 0, bottom = n - 1, left = 0, right = n - 1;
		int[][] matrix = new int[n][n];
		int val = 1;

		while (top <= bottom && left <= right) {
			for (int i = left; i <= right; i++) {
				matrix[top][i] = val;
				++val;
			}
			top++;
			for (int i = top; i <= bottom; i++) {
				matrix[i][right] = val;
				++val;
			}
			right--;
			if (top <= bottom) {
				for (int i = right; i >= left; i--) {
					matrix[bottom][i] = val;
					++val;
				}
				bottom--;
			}
			if (left <= right) {
				for (int i = bottom; i >= top; i--) {
					matrix[i][left] = val;
					++val;
				}
				left++;
			}
		}

		return matrix;
	}
}
