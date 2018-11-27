package com.qk.swordfingeroffer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @Description : 面试题33：二叉搜索树的后续遍历序列
 * 输入一个整数数组，判断是否是二叉搜索树的后续遍历结果
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions33 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		int[] sequenece = new int[] { 5, 7, 6, 9, 11, 10, 8 };
		int length = sequenece.length;
		System.out.println(verifysequeneceOfBSTBest(sequenece, 0, length));
		int[] sequenece2 = new int[] { 7, 4, 6, 5 };
		int length2 = sequenece2.length;
		System.out.println(verifysequeneceOfBSTBest(sequenece2, 0, length2));
	}

	/**
	 * 比价过程：
	 * [5, 7, 6, 9, 11, 10, 8]
	 * [5, 7, 6]
	 * [5]
	 * [7]
	 * [9, 11, 10]
	 * [9]
	 * [11]
	 * @param sequenece
	 * @param length
	 * @return
	 */
	public boolean verifysequeneceOfBST(int[] sequenece, int length) {
		if (sequenece == null && length <= 0) {
			return false;
		}
		
		int root = sequenece[length - 1];

		// 在二叉搜索树中，左子树的节点值都小于根节点
		int i = 0;
		for (; i < length - 1; ++i) {
			// 如果大于，跳出
			if (sequenece[i] > root) {
				break;
			}
		}

		//在二叉搜索树中，右子树的节点值都大于根节点
		int j = i;
		for (; j < length - 1; ++j) {
			// 如果小于，不符合条件
			if (sequenece[j] < root) {
				return false;
			}
		}

		// 判断左子树是不是二叉搜索树
		boolean left = true;
		if (i > 0) {
			left = verifysequeneceOfBST(Arrays.copyOfRange(sequenece, 0, i), i);
		}
		// 判断右子树是不是二叉搜索树
		boolean right = true;
		if (i < length - 1) {
			right = verifysequeneceOfBST(Arrays.copyOfRange(sequenece, i, length - 1), length - i - 1);
		}

		return (left && right);
	}

	/**
	 * 改进后，不需要复制数组
	 * @param sequenece
	 * @param start
	 * @param end
	 * @return
	 */
	public boolean verifysequeneceOfBSTBest(int[] sequenece, int start, int end) {
		int length = end - start;
		if (sequenece == null && length <= 0) {
			return false;
		}
		
		int root = sequenece[length - 1];

		// 在二叉搜索树中，左子树的节点值都小于根节点
		int i = start;
		for (; i < length - 1; ++i) {
			// 如果大于，跳出
			if (sequenece[i] > root) {
				break;
			}
		}

		//在二叉搜索树中，右子树的节点值都大于根节点
		int j = i;
		for (; j < length - 1; ++j) {
			// 如果小于，不符合条件
			if (sequenece[j] < root) {
				return false;
			}
		}

		// 判断左子树是不是二叉搜索树
		boolean left = true;
		if (i > 0) {
			left = verifysequeneceOfBSTBest(sequenece, 0, i);
		}
		// 判断右子树是不是二叉搜索树
		boolean right = true;
		if (i < length - 1) {
			right = verifysequeneceOfBSTBest(sequenece, i, length - 1);
		}

		return (left && right);
	}

}
