package com.qk.myleetcode.range_101_150.easy;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * Binary Tree Level Order Traversal II
 * @Description : 广度优先搜索遍历的一种变形，从根部开始
 * @Example:
 *  Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its bottom-up level order traversal as:
	[
	  [15,7],
	  [9,20],
	  [3]
	]
 * @Author : huihui
 * @Date : Create in 2019年3月18日
 */
public class BinaryTreeLevelOrderTraversalII {

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
		treeNode3.left = new TreeNode(15);
		treeNode3.right = new TreeNode(7);
		System.out.println(levelOrderBottomByDFS(root));
	}

	/**
	 * BFS的解法，广度优先搜索遍历
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
		levelMaker(wrapList, root, 0);
		return wrapList;
	}

	/**
	 * 位置公式解释：list.size()-level-1
	 * root层为零层，当递归到第一层，2-1-1=0，此时改成元素应该放到list中的第一个元素；当递归到第二层，list往后移动，新插入一个list
	 * 3-1-1=1，此时第二层的右节点应该插入下标为1的linkedlist中
	 * @param list
	 * @param root
	 * @param level 记录当前的层数 
	 */
	public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
		if (root == null)
			return;
		if (level >= list.size()) {
			// 根据层的深度，一次往后推，root是最后一个linkedlist
			list.add(0, new LinkedList<Integer>());
		}
		levelMaker(list, root.left, level + 1);
		levelMaker(list, root.right, level + 1);
		// 计算元素应该放置的位置，debug查看
		list.get(list.size() - level - 1).add(root.val);
	}

	/**
	 * 深度优先搜索遍历
	 * @param root
	 * @return
	 */
	public List<List<Integer>> levelOrderBottomByDFS(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

		if (root == null)
			return wrapList;

		// 往队列中追加元素
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 通过队列的大小，计算当前层有几个root节点
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			for (int i = 0; i < levelNum; i++) {
				// 判断顶层元素是否有左节点
				if (queue.peek().left != null)
					queue.offer(queue.peek().left);
				// 判断顶层元素是否有右节点
				if (queue.peek().right != null)
					queue.offer(queue.peek().right);
				// 弹出元素追加
				subList.add(queue.poll().val);
			}
			// 添加元素
			wrapList.add(0, subList);
		}
		return wrapList;
	}
}
