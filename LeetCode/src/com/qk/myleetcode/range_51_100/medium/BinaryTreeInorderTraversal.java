package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * 
 * @Description : 二叉树的中序遍历
 * @Author : huihui
 * @Date : Create in 2019年3月13日
 */
public class BinaryTreeInorderTraversal {

	/**
	 * 通过递归的方法实现
	 * 中序遍历：左中右
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversalByRecursion(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		helper(root, res);
		return res;
	}

	// 对节点一直递归访问
	public void helper(TreeNode root, List<Integer> res) {
		if (root != null) {
			// 左
			if (root.left != null) {
				helper(root.left, res);
			}
			// 中
			res.add(root.val);
			// 右
			if (root.right != null) {
				helper(root.right, res);
			}
		}
	}

	/**
	 * 通过栈的方法实现
	 * 过程：
	 *     1
	 *    / \
	 *   2   3
	 *  / \
	 * 4   5
	 * stack     list
	 * 4,2,1     4
	 * 2,1       4,2
	 * 1         4,2,5
	 * 1         4,2,5,1
	 * 3         4,2,5,1,3
	 *
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversalByStack(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while (curr != null || !stack.isEmpty()) {
			// 将当前节点的所有左节点放入栈
			while (curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
			// 依次弹出，放入值
			curr = stack.pop();
			// 将值添加到集合中
			res.add(curr.val);
			// 处理当前节点的右节点
			curr = curr.right;
		}
		return res;
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(3);
		treeNode.left = treeNode2;
		treeNode.right = treeNode3;
		treeNode2.left = new TreeNode(4);
		treeNode2.right = new TreeNode(5);
		treeNode3.left = new TreeNode(6);
		
		inorderTraversal(treeNode);
	}

	/**
	 * Morris Traversal
	 * 用到了新的数据结构，线程二叉树
	 * 时间复杂度：O(n)
	 * 主要是空间复杂度为:O(1)
	 * 过程举例：
	 * For example:
	 * 	  1
	    /   \
	   2     3
	  / \   /
	 4   5 6
	
	First, 1 is the root, so initialize 1 as current, 1 has left child which is 2, the current's left subtree is
	
	     2
	    / \
	   4   5
	So in this subtree, the rightmost node is 5, then make the current(1) as the right child of 5. Set current = cuurent.left (current = 2). The tree now looks like:
	
	     2
	    / \
	   4   5
	        \
	         1
	          \
	           3
	          /
	         6
	For current 2, which has left child 4, we can continue with thesame process as we did above
	
	    4
	     \
	      2
	       \
	        5
	         \
	          1
	           \
	            3
	           /
	          6
	then add 4 because it has no left child, then add 2, 5, 1, 3 one by one, for node 3 which has left child 6, do the same as above. Finally, the inorder taversal is [4,2,5,1,6,3].
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		TreeNode curr = root;
		TreeNode pre;
		while (curr != null) {
			if (curr.left == null) { // 如果当前节点没有左节点，说明线程树已经完成
				res.add(curr.val);
				curr = curr.right; // move to next right node
			} else { // has a left subtree
				pre = curr.left;
				while (pre.right != null) { // find rightmost
					pre = pre.right;
				}
				pre.right = curr; // put cur after the pre node
				TreeNode temp = curr; // store cur node
				curr = curr.left; // move cur to the top of the new tree (没有破坏原来的结构) 1 的左节点还是2
				temp.left = null; // original cur left be null, avoid infinite loops
			}
		}
		return res;
	}

}
