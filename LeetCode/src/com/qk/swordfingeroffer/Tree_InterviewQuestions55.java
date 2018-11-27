package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题55：二叉树的深度
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点一次经过的节点形成一条树的路径，最长的路径即为树的深度
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions55 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode1 = new TreeNode(10);
		TreeNode treeNode2 = new TreeNode(5);
		TreeNode treeNode3 = new TreeNode(12);
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode5 = new TreeNode(7);

		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode2.right = treeNode5;

		System.out.println(treeDepth(treeNode1));
	}
	
	public int treeDepth(TreeNode treeNode) {
		if(treeNode == null) 
			return 0;
		
		// 计算左子树的深度
		int nLeft = treeDepth(treeNode.left);
		// 计算右子树的深度
		int nRight = treeDepth(treeNode.right);
		
		// 返回左右子树深度较大的一个
		return (nLeft > nRight) ? (nLeft + 1) : (nRight + 1);
	}
	
	/**
	 * @Description:检查一棵树是否是平衡二叉树（任意节点的左右子树的深度相差不超过1）
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		TreeNode treeNode1 = new TreeNode(10);
		TreeNode treeNode2 = new TreeNode(5);
		TreeNode treeNode3 = new TreeNode(12);
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode5 = new TreeNode(7);

		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode2.right = treeNode5;

		System.out.println(isBalancedBest(treeNode1));
		
		TreeNode treeNode6 = new TreeNode(10);
		TreeNode treeNode7 = new TreeNode(5);
		TreeNode treeNode8 = new TreeNode(12);

		treeNode6.left = treeNode7;
		treeNode7.left = treeNode8;
		System.out.println(isBalancedBest(treeNode6));
	}
	
	/**
	 * 接续上一题题的思路，每个左右子树都求一遍深度，判断差值（存在重复操作的问题）
	 * @param treeNode
	 * @return
	 */
	public boolean isBalanced(TreeNode treeNode) {
		if(treeNode == null) {
			return true;
		}
		
		int left = treeDepth(treeNode.left);
		int right = treeDepth(treeNode.right);
		int diff = left - right;
		if(Math.abs(diff) > 1) {
			return false;
		}
		
		return (isBalanced(treeNode.right)) && (isBalanced(treeNode.right));
	}
	
	public boolean isBalancedBest(TreeNode root) {
		return dfsHeight(root) != -1;
	}
	/**
	 * 采用dfs的方式，递归查找每棵子树的最大深度
	 * @param root
	 * @return
	 */
	public int dfsHeight(TreeNode root) {
		// 为null，是叶子节或为null，高度返回0
		if (root == null)
			return 0;
		// 查找左子树的深度
		int leftHeight = dfsHeight(root.left);
		// 子树违反平衡二叉树的条件，递归往上层返回-1
		if (leftHeight == -1)
			return -1;
		// 查找右子树的深度
		int rightHeight = dfsHeight(root.right);
		// 子树违反平衡二叉树的条件，递归往上层返回-1
		if (rightHeight == -1)
			return -1;

		// 如果两颗子树的高度差大于1，返回-1
		if (Math.abs(leftHeight - rightHeight) > 1)
			return -1;
		// 返回树的深度+自身
		return Math.max(leftHeight, rightHeight) + 1;
	}


	/**
	 * 可以采用后续遍历的方式，没有实现成功
	 * @param treeNode
	 * @param depth
	 * @return
	 */
	public boolean isBalancedBest2(TreeNode treeNode, int depth) {
		if(treeNode == null) {
			depth = 0;
			return true;
		}
		
		int left = 0, right = 0;
		if(isBalancedBest2(treeNode.left, left) && isBalancedBest2(treeNode.right, right)) {
			int diff = left - right;
			if(Math.abs(diff) <= 1) {
				depth = 1+(left > right ? left : right);
				return true;
			}
		}
		
		return false;
	}
}
