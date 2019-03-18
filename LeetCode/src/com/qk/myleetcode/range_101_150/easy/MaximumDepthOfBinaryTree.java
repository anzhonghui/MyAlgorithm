package com.qk.myleetcode.range_101_150.easy;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;
/**
 * 
 * @Description : 二叉树的最大深度
 * @Author : huihui
 * @Date : Create in 2019年3月18日
 */
public class MaximumDepthOfBinaryTree {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode root = new TreeNode(3);
		TreeNode treeNode2 = new TreeNode(9);
		TreeNode treeNode3 = new TreeNode(20);
		root.left = treeNode2;
		root.right = treeNode3;
		treeNode2.left = new TreeNode(3);
		treeNode2.right = new TreeNode(4);
		treeNode3.left = new TreeNode(15);
		treeNode3.right = new TreeNode(7);
		System.out.println(maxDepth(root));

		TreeNode root2 = new TreeNode(3);
		System.out.println(maxDepth(root2));
	}

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return maxDepthByRecursive(root);
	}

	public int maxDepthByOptimization(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
	}

	/**
	 * 使用递归的方式，会超时
	 * 超时原因：递归连续调用了四次
	 * @param treeNode
	 * @return
	 */
	public int maxDepthByRecursive(TreeNode treeNode) {

		if (treeNode == null) {
			return 0;
		}

		return maxDepthByRecursive(treeNode.left) > maxDepthByRecursive(treeNode.right)
				? maxDepthByRecursive(treeNode.left) + 1
				: maxDepthByRecursive(treeNode.right) + 1;
	}

	/**
	 * 使用递归的方式，会超时
	 * 超时原因：递归连续调用了四次
	 * @param treeNode
	 * @return
	 */
	public int maxDepthByRecursive2(TreeNode treeNode) {

		if (treeNode == null) {
			return 0;
		}

		int val1 = maxDepthByRecursive(treeNode.left);
		int val2 = maxDepthByRecursive(treeNode.right);

		return 1 + (val1 > val2 ? val1 : val2);
	}
}
