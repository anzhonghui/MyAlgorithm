package com.qk.myleetcode.range_51_100.hard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

public class BinaryTreePostorderTraversal {

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

		treeNode1.right = treeNode2;
		treeNode2.left = treeNode3;

		System.out.println(postorderTraversal(treeNode1));
	}

	/**
	 * 通过linklist的特性解决
	 * @param root
	 * @return
	 */
	public List<Integer> postorderTraversal(TreeNode root) {
		LinkedList<TreeNode> stack = new LinkedList<>();
		LinkedList<Integer> output = new LinkedList<>();
		if (root == null) {
			return output;
		}

		stack.add(root);
		while (!stack.isEmpty()) {
			TreeNode node = stack.pollLast();
			// 往链表的头部添加子节点
			output.addFirst(node.val);
			// 是否有左子树
			if (node.left != null) {
				stack.add(node.left);
			}
			// 是否有右子树
			if (node.right != null) {
				stack.add(node.right);
			}
		}
		return output;
	}

	/**
	 * 通过队列的方式解决（跟->右->左）
	 * @param root
	 * @return
	 */
	public List<Integer> postorderTraversalByQueue(TreeNode root) {
		LinkedList<Integer> result = new LinkedList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode p = root;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				result.addFirst(p.val); // Reverse the process of preorder
				p = p.right; // Reverse the process of preorder
			} else {
				TreeNode node = stack.pop();
				p = node.left; // Reverse the process of preorder
			}
		}
		return result;
	}

	/**
	 * 中序遍历：左根右
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode p = root;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				p = p.left;
			} else {
				TreeNode node = stack.pop();
				result.add(node.val); // Add after all left children
				p = node.right;
			}
		}
		return result;
	}

	/**
	 * 前序遍历：根左右
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		Deque<TreeNode> stack = new ArrayDeque<>();
		TreeNode p = root;
		while (!stack.isEmpty() || p != null) {
			if (p != null) {
				stack.push(p);
				result.add(p.val); // Add before going to children
				p = p.left;
			} else {
				TreeNode node = stack.pop();
				p = node.right;
			}
		}
		return result;
	}
}
