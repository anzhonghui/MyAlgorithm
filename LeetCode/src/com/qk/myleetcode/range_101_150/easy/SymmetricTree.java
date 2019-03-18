package com.qk.myleetcode.range_101_150.easy;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * 对称树
 * @Description : 
 * @example：
 * For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	But the following [1,2,2,null,3,null,3] is not:
	    1
	   / \
	  2   2
	   \   \
	   3    3
 * @Author : huihui
 * @Date : Create in 2019年3月16日
 */
public class SymmetricTree {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode root = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(2);
		TreeNode treeNode3 = new TreeNode(2);
		root.left = treeNode2;
		root.right = treeNode3;
		treeNode2.left = new TreeNode(3);
		treeNode2.right = new TreeNode(4);
		treeNode3.left = new TreeNode(4);
		treeNode3.right = new TreeNode(3);
		System.out.println(isSymmetric(root));
		System.out.println(isSymmetricIterative(root));
	}

	/**
	 * recursive 递归方法,O(n)
	 * @param root
	 * @return
	 */
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return false;
		}

		return isMirror(root, root);
	}

	public boolean isMirror(TreeNode treeNode1, TreeNode treeNode2) {
		if (treeNode1 == null && treeNode2 == null)
			return true;// TT
		if (treeNode1 == null || treeNode2 == null)
			return false;// 剩下的情况有 TF 和 FT
		return treeNode1.val == treeNode2.val && isMirror(treeNode1.left, treeNode2.right)
				&& isMirror(treeNode1.right, treeNode2.left);
	}
	
	/**
	 * 对递归的一些代码优化
	 * @param root
	 * @return
	 */
	public boolean isSymmetricByOptimization(TreeNode root) {
        if(root == null){
            return true;
        }
        return helper(root.left, root.right);
    }
    
    public boolean helper(TreeNode left, TreeNode right){
        if(left == null || right == null){
            return (left == right);
        }
        if(left.val != right.val){
            return false;
        }
        return helper(left.left, right.right) && helper(left.right, right.left);
    }

	/**
	 * 借助队列通过迭代的方式
	 * @param root
	 * @return
	 */
	public boolean isSymmetricIterative(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);
		queue.add(root);

		while (!queue.isEmpty()) {
			TreeNode treeNode1 = queue.poll();
			TreeNode treeNode2 = queue.poll();
			if (treeNode1 == null && treeNode2 == null)
				continue;
			if (treeNode1 == null || treeNode2 == null)
				return false;
			if (treeNode1.val != treeNode2.val)
				return false;

			queue.add(treeNode1.left);
			queue.add(treeNode2.right);
			queue.add(treeNode1.right);
			queue.add(treeNode2.left);
		}
		return true;
	}

}
