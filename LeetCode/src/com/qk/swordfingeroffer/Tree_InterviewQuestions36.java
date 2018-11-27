package com.qk.swordfingeroffer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @Description : 面试题36：二叉搜索树和双向链表
 * 讲一个二叉搜索树转换成一个排好序的双向链表
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions36 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode1 = new TreeNode(10);
		TreeNode treeNode2 = new TreeNode(6);
		TreeNode treeNode3 = new TreeNode(14);
		TreeNode treeNode4 = new TreeNode(4);
		TreeNode treeNode5 = new TreeNode(8);
		TreeNode treeNode6 = new TreeNode(12);
		TreeNode treeNode7 = new TreeNode(16);

		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode2.right = treeNode5;
		treeNode3.right = treeNode6;
		treeNode3.right = treeNode7;

		TreeNode convertByJavaVersion = convertByJavaVersion(treeNode1);
		convertByJavaVersion.display();
		
	}

	public TreeNode convertByJavaVersion(TreeNode root) {

		TreeNode lastNode = null;
		lastNode = baseconvert(root, lastNode);
		// lastNode此时指向双向链表的为节点
		// 我们需要返回头结点
		TreeNode headNode = lastNode;
		while (headNode.left != null)
			headNode = headNode.left;
		return headNode;

	}

	/**
	 * 过程：
	 * null <=> 4
	 * 1/2
	 * 4 <= 6
	 * 3
	 * 4 <=> 6
	 * 
	 * @param root
	 * @param lastNode
	 * @return
	 */
	public TreeNode baseconvert(TreeNode root, TreeNode lastNode) {

		if (root == null)
			return lastNode;

		TreeNode current = root;
		// 1.如果左子树不为空，对左子树进行递归，第一个节点是最左边的节点
		if (current.left != null)
			lastNode = baseconvert(current.left, lastNode);

		// 2.当前节点的左节点是链表的最后一个节点，即prev
		current.left = lastNode;
		// 如果链表的最后一个节点不为空，它的right为当前节点，即next
		if (lastNode != null)
			lastNode.right = current;

		// 3.链表的最后一个节点等于当前节点
		lastNode = current;
		// 如果右子树不为空，对右子树进行递归
		if (current.right != null)
			lastNode = baseconvert(current.right, lastNode);

		return lastNode;

	}

	/**
	 * C# 版本，不可以
	 * @param treeNode
	 * @return
	 */
	public TreeNode convert(TreeNode treeNode) {
		TreeNode treeNodeList = null;
		convertNode(treeNode, treeNodeList);
		TreeNode headOfList = treeNodeList;
		while (headOfList != null && headOfList.left != null) {
			headOfList = headOfList.left;
		}

		return headOfList;
	}

	public void convertNode(TreeNode treeNode, TreeNode treeNodeList) {
		if (treeNode == null) {
			return;
		}

		TreeNode curr = treeNode;
		if (curr.left != null) {
			convertNode(curr.left, treeNodeList);
		}

		curr.left = treeNodeList;
		if (treeNodeList != null) {
			treeNodeList.right = curr;
		}

		treeNodeList = curr;
		if (curr.right != null) {
			convertNode(curr.right, treeNodeList);
		}
	}
}
