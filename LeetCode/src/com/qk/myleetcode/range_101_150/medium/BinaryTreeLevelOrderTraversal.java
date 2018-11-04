package com.qk.myleetcode.range_101_150.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * 
 * 102.Binary Tree Level Order Traversal
 *  ┏┓　　┏┓
 * ┏┛┻━━━━┛┻┓
 * ┃　　　　　┃
 * ┃　　　━　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　 ┃
 * ┃　　　┻　　┃
 * ┃　　　　　 ┃
 * ┗━━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　┣┓
 * 　　┃　　　　　　┏┛
 * 　　┗┓┓┏━┳┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 二叉树的层级遍历
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class BinaryTreeLevelOrderTraversal {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode root = new TreeNode(3);
		TreeNode node9 = new TreeNode(9);
		TreeNode node20 = new TreeNode(20);
		node20.left = new TreeNode(15);
		node20.right = new TreeNode(7);
		root.left = node9;
		root.right = node20;
		
		System.out.println(levelOrder(root).toString());
	}

	/**
	 * 通过队列（queue）的方式实现BFS(广度优先搜索)
	 * @param root
	 * @return
	 * @TimeComplexity: O(n)
	 */
	public List<List<Integer>> levelOrder(TreeNode root) {
		// 存放节点的队列
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		// 存放结果的集合
		List<List<Integer>> wrapList = new LinkedList<List<Integer>>();

		if (root == null)
			return wrapList;

		// 将制定的元素插入队列
		queue.offer(root);
		while (!queue.isEmpty()) {
			int levelNum = queue.size();
			List<Integer> subList = new LinkedList<Integer>();
			for (int i = 0; i < levelNum; i++) {
				// 取出头部元素并不删除（用于判断）
				if (queue.peek().left != null)
					// 将该节点的左右节点继续插入到队列中
					queue.offer(queue.peek().left);
				if (queue.peek().right != null)
					queue.offer(queue.peek().right);
				// 出去头部元素，将值添加到集合中，并删除
				subList.add(queue.poll().val);
			}
			wrapList.add(subList);
		}
		return wrapList;
	}

	/**
	 * 通过递归的方式实现BFS(广度优先搜索)
	 * @param root
	 * @return
	 * @TimeComplexity: O(n)
	 */
	public List<List<Integer>> levelOrderBFS(TreeNode root) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		levelHelper(res, root, 0);
		return res;
	}

	public void levelHelper(List<List<Integer>> res, TreeNode root, int height) {
		if (root == null)
			return;
		if (height >= res.size()) {
			res.add(new LinkedList<Integer>());
		}
		res.get(height).add(root.val);
		levelHelper(res, root.left, height + 1);
		levelHelper(res, root.right, height + 1);
	}
}
