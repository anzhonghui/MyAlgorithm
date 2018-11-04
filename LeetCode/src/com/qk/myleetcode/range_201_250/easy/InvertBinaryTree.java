package com.qk.myleetcode.range_201_250.easy;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * 
 * 226.Invert binary tree.
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
 * @Description : 反转二叉树
 * @Programme：1.采用递归的方式  2.采用BFS
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class InvertBinaryTree {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode root = new TreeNode(4);
		TreeNode node2 = new TreeNode(2);
		node2.left = new TreeNode(1);
		node2.right = new TreeNode(3);

		TreeNode node7 = new TreeNode(7);
		node7.left = new TreeNode(6);
		node7.right = new TreeNode(9);

		root.left = node2;
		root.right = node7;

		System.out.println(invertTreeBFS(root).toString());

	}

	/**
	 * 采用递归的方式
	 * @param root
	 * @return
	 * @TimeComplexity: O(n)
	 */
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode right = invertTree(root.right);
		TreeNode left = invertTree(root.left);
		root.left = right;
		root.right = left;
		return root;
	}

	/**
	 * BFS:广度优先搜索，遍历每一层；（采用循环）
	 *      4
	 *     / \
	 *    2   7       1.交换2和7节点
	 *   / \ / \
	 *  1  3 6  9     2.将2和7位根节点的子树添加到队列中，继续交换
	 *  
	 * 过程描述：
	 *      4
	 *     / \
	 *    7   2       1.交换2和7节点
	 *   / \ / \
	 *  6  9 1  3    
	 *  
	 *      4
	 *     / \
	 *    7   2       
	 *   / \ / \
	 *  9  6 1  3     2.交换以7节点为根节点的子树
	 * 
	 * 
	 *      4
	 *     / \
	 *    7   2       
	 *   / \ / \
	 *  9  6 3  1     3.交换以2节点为根节点的子树
	 *  
	 * @param root
	 * @return
	 * @TimeComplexity: O(n)
	 */
	public TreeNode invertTreeBFS(TreeNode root) {
		if (root == null)
			return null;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (!queue.isEmpty()) {
			TreeNode current = queue.poll();
			TreeNode temp = current.left;
			current.left = current.right;
			current.right = temp;
			if (current.left != null)
				queue.add(current.left);
			if (current.right != null)
				queue.add(current.right);
		}
		return root;
	}
}
