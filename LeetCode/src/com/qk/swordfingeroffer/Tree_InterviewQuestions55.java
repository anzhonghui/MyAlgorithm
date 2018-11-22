package com.qk.swordfingeroffer;

import java.util.ArrayList;
import java.util.List;

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
	

}
