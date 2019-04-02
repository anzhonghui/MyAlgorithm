package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题7：重建二叉树
 * 给出二叉树的前序遍历和中序遍历，重建二叉树
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions7 {

	/**
	 * @Description:
	 * @Programme：方案一：采用递归的方式处理
	 * 1.根据前序遍历第一个节点是root，中序遍历root节点分左右子树的原则，将左右子树分开
	 * 2.递归左右子树
	 */
	@Test
	public void MyTest() {
		System.out.println(buildTree(new int[] { 1, 2, 4, 7, 3, 5, 6, 8 }, new int[] { 4, 7, 2, 1, 5, 3, 8, 6 }));
	}

	/**
	 * @param preorder 前序遍历
	 * @param inorder 中序遍历
	 * @return
	 */
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return helper(0, 0, inorder.length - 1, preorder, inorder);
	}

	/**
	 * 过程详解：
	 * preorder：1, 2, 4, 7, 3, 5, 6, 8
	 * inorder：4, 7, 2, 1, 5, 3, 8, 6
	 * 
	 * 1.根据preorder知道1是root节点
	 * 2.根据1将inorder分为左右子树，递归遍历
	 * 3.preorder[2, 4, 7] 2 是1的左子树的root节点
	 * 4.inorder[4, 7, 2] 4,7是2的左子树
	 * 5.preorder[4, 7] 4是2节点的左子树
	 * 6.inorder[4, 7] 7是4节点的右节点
	 * ===================
	 * 根节点1左子树的遍历结束
	 * ===================
	 * 7.preorder[3, 5, 6, 8]  3 是右子树的root节点
	 * 8.inorder[5, 3, 8, 6] 5是3节点的左节点，inorder[8, 6]是3节点的右子树
	 * 9.preorder[6, 8] 6是3节点右子树的根节点
	 * 10.inorder[8, 6] 8是6节点的左子节点
	 * 
	 *        1
	 *       / \
	 *      2   3
	 *     /   / \
	 *    4   5   6
	 *     \      /
	 *      7    8
	 * 
	 * @param preStart 根节点（父节点）坐标
	 * @param inStart 树的起始坐标
	 * @param inEnd 树的结束坐标
	 * @param preorder 前序遍历
	 * @param inorder 中序遍历
	 * @return
	 */
	public TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder) {
		if (preStart > preorder.length - 1 || inStart > inEnd) {
			return null;
		}
		// 创建子树
		TreeNode root = new TreeNode(preorder[preStart]);
		// 当前节点的root节点在中序遍历中的坐标
		int inIndex = 0; // Index of current root in inorder
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == root.val) {
				inIndex = i;
			}
		}

		/**
		 * 对左子树进行遍历：
		 * preStart：上一个root坐标+1
		 * inStart：0
		 * inEnd：inIndex - 1（root节点的坐标-1）
		 */
		root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder);
		/**
		 * 对右子树进行遍历：
		 * preStart：前序遍历上一个root坐标 + 中序遍历root坐标 - 子树范围起始坐标 + 1
		 * inStart：inIndex + 1（根节点+1）
		 * inEnd：inEnd
		 */
		root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
		
		return root;
	}

}
