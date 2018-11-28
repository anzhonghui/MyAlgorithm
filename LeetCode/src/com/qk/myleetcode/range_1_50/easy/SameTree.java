package com.qk.myleetcode.range_1_50.easy;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

public class SameTree {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode1 = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);
		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		System.out.println(isSameTree(treeNode1, treeNode1));
		System.out.println(isSameTree(null, treeNode1));
		System.out.println(isSameTree(treeNode1, treeNode2));

		TreeNode treeNode4 = new TreeNode(1);
		TreeNode treeNode5 = new TreeNode(2);
		treeNode4.left = treeNode5;
		TreeNode treeNode6 = new TreeNode(1);
		TreeNode treeNode7 = new TreeNode(2);
		treeNode6.right = treeNode7;
		System.out.println(isSameTree(treeNode4, treeNode6));
		System.out.println(isSameTree(treeNode4, treeNode4));

	}

	/**
	 * 采用递归的方式判断两棵二叉树是否相同
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		if ((p == null && q != null) || (p != null && q == null)) {
			return false;
		}

		if (p.val != q.val) {
			return false;
		}

		return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	}

	/**
	 * 更好的优化，避免判断单独为空的分支
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean isSameTreeBest(TreeNode p, TreeNode q) {
		if (p == null && q == null)
			return true;
		if (p != null && q != null) {
			if (p.val != q.val)
				return false;
			if (p.val == q.val)
				return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
		}
		return false;
	}
}
