package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

public class FlattenBinaryTreeToLinkedList {

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
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode5 = new TreeNode(5);
		TreeNode treeNode6 = new TreeNode(6);
		
		treeNode1.left = treeNode2;
		treeNode1.right = treeNode5;
		treeNode2.left = treeNode3;
		treeNode2.right = treeNode4;
		treeNode5.right = treeNode6;
		
		flatten(treeNode1);
		System.out.println(treeNode1.toString());
	}

	/**
	 * prev 中间变量
	 * 从后往前变动，右左跟
	 */
	private TreeNode prev = null;
	public void flatten(TreeNode root) {
		if (root == null)
			return;
		flatten(root.right);
		flatten(root.left);
		root.right = prev;
		root.left = null;
		prev = root;
	}
	
	/**
	 * 过程
	 *      2          2        2
	 *     / \    =>    \   =>   \
	 *    3   4          3        3
	 *                             \
	 *                              4 
	 * @param root
	 */
	public void flatten2(TreeNode root) {
        if (root == null)
            return;
        flatten(root.left);
        flatten(root.right);
        // 核心步骤
        // 1.临时变量记录右节点
        TreeNode right = root.right;
        // 根节点的右节点改为左节点
        root.right = root.left;
        // 左节点至空
        root.left = null;
        // 如果右节点不为空，说明该根节点有左节点，一直往下找
        while(root.right != null){
            root = root.right;
        }
        // 将右节点连接到原左节点的下面
        root.right = right;
    }
}
