package com.qk.myleetcode.medium;

import org.junit.Test;
/**
 * 
 * 48.Rotate Image
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
 * @Description : 二位数组，顺时针旋转90度
 * @Programme：反转上下，左上右下斜线左右两侧对称交换（或反转左右，对称交换）
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class RotateImage {

	/**
	 *Given input matrix = 
	 *[
	 *	[1,2,3],
	 *	[4,5,6],
	 *	[7,8,9]
	 *],
	 *
	 *rotate the input matrix in-place such that it becomes:
	 *[
	 *	[7,4,1],
	 *	[8,5,2],
	 *	[9,6,3]
	 *]
	 */
	@Test
	public void MyTest() {
		int[][] arr = new int[3][];
		arr[0] = new int[] { 1, 2, 3 };
		arr[1] = new int[] { 4, 5, 6 };
		arr[2] = new int[] { 7, 8, 9 };
		rotate(arr);
		int[][] arr2 = new int[4][];
		arr2[0] = new int[] { 5, 1, 9, 11 };
		arr2[1] = new int[] { 2, 4, 8, 10 };
		arr2[2] = new int[] { 13, 3, 6, 7 };
		arr2[3] = new int[] { 15, 14, 12, 16 };
		rotate(arr2);
	}

	/**
	 * 反转上下，左上右下斜线左右两侧对称交换；或者反转左右，对称交换
	 * 1,2,3       7,8,9       7,4,1
	 * 4,5,6  -->  4,5,6  -->  8,5,2
	 * 7,8,9       1,2,3       9,6,3
	 * @param matrixs
	 */
	public void rotate(int[][] matrix) {
		reverse(matrix);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix.length; j++) {
				swap(matrix, i, j);
			}
		}
//		for (int[] is : matrix) {
//			System.out.println(Arrays.toString(is));
//		}
	}

	public void swap(int[][] arr, int i, int j) {
		int temp = arr[i][j];
		arr[i][j] = arr[j][i];
		arr[j][i] = temp;
	}

	public void reverse(int[][] arr) {
		int[] temp = new int[arr.length];
		for (int i = 0; i < arr.length / 2; i++) {
			temp = arr[i];
			arr[i] = arr[arr.length - i - 1];
			arr[arr.length - i - 1] = temp;
		}
	}

}
