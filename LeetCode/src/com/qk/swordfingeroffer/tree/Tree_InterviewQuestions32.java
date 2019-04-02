package com.qk.swordfingeroffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

/**
 * @Description : 面试题32：从上到下打印二叉树
 * 给定一颗二叉树，从上打印，类似二叉树的广度优先搜索遍历
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions32 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode1 = new TreeNode(8);
		TreeNode treeNode2 = new TreeNode(6);
		TreeNode treeNode3 = new TreeNode(10);
		TreeNode treeNode4 = new TreeNode(5);
		TreeNode treeNode5 = new TreeNode(7);
		TreeNode treeNode6 = new TreeNode(9);
		TreeNode treeNode7 = new TreeNode(11);

		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode2.right = treeNode5;
		treeNode3.left = treeNode6;
		treeNode3.right = treeNode7;

		printFromTopToBottom(treeNode1);
		printFromTopToBottom(treeNode7);
		printFromTopToBottomByBranch(treeNode1);
	}

	/**
	 * tree的广度优先搜索遍历
	 * 借助队列
	 * @param sequenece
	 * @param length
	 * @return
	 */
	public void printFromTopToBottom(TreeNode treeNode) {

		if (treeNode == null) {
			return;
		}

		List<Integer> lists = new ArrayList<>();

		Queue<TreeNode> queue = new LinkedList<>();
		// 添加元素
		queue.add(treeNode);
		while (!queue.isEmpty()) {
			// 取出顶部元素
			TreeNode poll = queue.peek();
			lists.add(poll.val);
			if (poll.left != null) {
				queue.add(poll.left);
			}
			if (poll.right != null) {
				queue.add(poll.right);
			}
			// 移除顶部元素
			queue.poll();
		}

		System.out.println(lists.toString());
	}
	
	
	/**
	 * tree分行从上到下打印树
	 **添加两个坐标：
	 *  nextLevel -- 下一行的节点数
	 * 	toBePrinted -- 要打印的节点数，为了换行
	 * @param treeNode
	 */
	public void printFromTopToBottomByBranch(TreeNode treeNode) {
		if (treeNode == null) {
			return;
		}
		
		int nextLevel = 0;
		int toBePrinted = 1;
		
		Queue<TreeNode> queue = new LinkedList<>();
		// 添加元素
		queue.add(treeNode);
		while (!queue.isEmpty()) {
			// 取出顶部元素
			TreeNode poll = queue.peek();
			System.out.print(poll.val + "  ");
			if (poll.left != null) {
				queue.add(poll.left);
				++nextLevel;
			}
			if (poll.right != null) {
				queue.add(poll.right);
				++nextLevel;
			}
			// 移除顶部元素
			queue.poll();
			--toBePrinted;
			if(toBePrinted == 0) {
				System.out.println();
				toBePrinted = nextLevel;
				nextLevel = 0;
			}
		}
	}

}
