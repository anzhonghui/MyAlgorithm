package com.qk.swordfingeroffer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @Description : 面试题34：二叉树中和为某一值的路径
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions34 {

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

		findPath(treeNode1, new ArrayList<Integer>(), 0, 22);

	}

	/**
	 * @param treeNode
	 * @param target
	 */
	public void findPath(TreeNode treeNode, int target) {
		if (treeNode == null) {
			return;
		}

		findPath(treeNode, new ArrayList<Integer>(), 0, target);
	}

	/**
	 * 通过分析我们可以发现：
	 * 		1.当节点为叶子节点的时候，整条路径才是有效的（除了判断和，还需要判断叶子节点）
	 * 		2.往path中添加节点后，如果调到上一级，需要移除刚才添加的节点
	 * 		3.可以通过递归调到升一级节点
	 * @param treeNode
	 * @param path
	 * @param curValue
	 * @param target
	 */
	public void findPath(TreeNode treeNode, List<Integer> path, int curValue, int target) {
		curValue += treeNode.val;
		path.add(treeNode.val);

		boolean isLeaf = treeNode.left == null && treeNode.right == null;
		if (curValue == target && isLeaf) {
			System.out.println("找到一条路径：" + path.toString());
		}

		if (treeNode.left != null) {
			findPath(treeNode.left, path, curValue, target);
		}
		if (treeNode.right != null) {
			findPath(treeNode.right, path, curValue, target);
		}

		path.remove(path.size() - 1);
	}

}
