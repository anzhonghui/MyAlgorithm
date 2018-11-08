package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * 
 * 98.Validate Binary Search Tree
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
 * @Description : 是否是有效的二叉查找树（BST）
 * 条件：
 * 	1.节点的左子树仅包含键小于节点键的节点。
 * 	2.节点的右子树仅包含键大于节点键的节点。
 * 	3.左右子树也必须是二叉搜索树。
 * 
 * 时间复杂度：
 * 	二叉查找树的优势在于查找、插入的时间复杂度较低。为 O(logn)
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class ValidateBinarySearchTree {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode root = new TreeNode(4);
		TreeNode node2 = new TreeNode(2);
		TreeNode node5 = new TreeNode(5);
		node2.left = new TreeNode(1);
		node2.right = new TreeNode(3);
		root.left = node2;
		root.right = node5;
		System.out.println(isValidBST(root));
	}

	/**
	 * 是否是有效的二叉查找树
	 * 1<===>#
	 * 2<===>1 
	 * 3<===>2
	 * 4<===>3
	 * 5<===>4
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode pre = null;
		while (root != null || !stack.isEmpty()) {
			// 存放节点的所有左子树
			while (root != null) {
				stack.push(root);
				root = root.left;
			}

			// 左->中->右的方式判断；先判断左节点跟根节点值的比较，pre为左，root为跟节点；在判断根节点跟右节点的比较，pre为跟，root为右
			root = stack.pop();
			if (pre != null && root.val <= pre.val)
				return false;
			pre = root;
			root = root.right;
		}
		return true;
	}

	/**
	 * 采用递归的方式
	 * @param root
	 * @return
	 */
	public boolean isValidBST2(TreeNode root) {
		return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
	}

	/**
	 * 比较明了，类似DFS
	 * @param root
	 * @param minVal
	 * @param maxVal
	 * @return
	 *     4
	 *    / \
	 *   2   5
	 *  / \
	 * 1   3
	 * min <==> 1
	 *  1  <==> 2
	 *  2  <==> 3
	 *  3  <==> 4
	 *  4  <==> 5
	 *  5  <==> max
	 */
	public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
		if (root == null) {
			return true;
		}
		if (root.val >= maxVal || root.val <= minVal)
			return false;
		return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTestToVerifySquenceOfBST() {
		System.out.println(verifySquenceOfBST(new int[] { 5, 7, 6, 9, 11, 10, 8 }, 7));
	}

	public boolean verifySquenceOfBST(int[] sequence, int length) {
		if (sequence == null || length <= 0) {
			return false;
		}
		// 获取根节点
		int root = sequence[length - 1];
		// 在二叉查找树中左子树的节点的值小于根节点的值
		int i = 0;// 结束循环后i的值为左子树的截取点
		for (; i < length - 1; i++) {
			if (sequence[i] > root) {
				break;
			}
		}
		// 在二叉查找树中右子树的节点的值大于根节点的值
		int j = i;// 结束后j的值为右子树的截取点
		for (; j < length - 1; j++) {
			if (sequence[j] < root) {
				break;
			}
		}
		// 判断左子树是不是二叉搜索树
		boolean left = true;
		if (i > 0) {
			left = verifySquenceOfBST(sequence, i);
		}
		// 判断右子树是不是二叉搜索树
		boolean right = true;
		if (i < length - 1) {
			right = verifySquenceOfBST(sequence, length - i - 1);
		}

		return (left && right);
	}

	/**
	 * 二叉搜索树的顺序遍历
	 * @param root
	 * @return
	 */
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> list = new ArrayList<>();
		if (root == null)
			return list;
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.empty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			list.add(root.val);
			root = root.right;

		}
		return list;
	}

	/**
	 * 二叉搜索树中最小的元素
	 * @param root
	 * @param k
	 * @return
	 */
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
		while (root != null || !stack.isEmpty()) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (--k == 0)
				break;
			root = root.right;
		}
		return root.val;
	}
}
