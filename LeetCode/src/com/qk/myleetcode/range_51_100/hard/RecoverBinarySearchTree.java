package com.qk.myleetcode.range_51_100.hard;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * 恢复二叉搜索树
 * @Description :二叉搜索树的两个元素被错误的交换了，找到并恢复其结构
 * 
 *  Example:
	Input: [3,1,4,null,null,2]
	  3
	 / \
	1   4
	   /
	  2
	Output: [2,1,4,null,null,3]
	  2
	 / \
	1   4
	   /
	  3
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class RecoverBinarySearchTree {
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode = new TreeNode(3);
		treeNode.left = new TreeNode(1);
		TreeNode treeNode2 = new TreeNode(4);
		treeNode2.left = new TreeNode(2);
		treeNode.right = treeNode2;
		recoverTree(treeNode);
		System.out.println(treeNode.toString());
	}

	// 存储错误的两个元素
	TreeNode firstElement = null;
	TreeNode secondElement = null;
	// The reason for this initialization is to avoid null pointer exception in the
	// first comparison when prevElement has not been initialized 初始化的原因是为了第一次比价出现空指针的问题
	TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

	public void recoverTree(TreeNode root) {
		// In order traversal to find the two elements 为了找到错误的两个元素
		traverse(root);
		// Swap the values of the two nodes 交换这两个元素
		int temp = firstElement.val;
		firstElement.val = secondElement.val;
		secondElement.val = temp;
	}

	private void traverse(TreeNode root) {
		if (root == null)
			return;
		traverse(root.left);
		// Start of "do some business",
		// 如果找不到第一个元素，请将其分配给prevElement
		// If first element has not been found, assign it to prevElement (refer to 6 in the example above)
		// 如果之前的值有大于根节点的值，说明改元素所在的位置不正确
		if (firstElement == null && prevElement.val >= root.val) {
			firstElement = prevElement;
		}
		// 如果第一个元素找到，将root复制给第二个元素
		// If first element is found, assign the second element to the root  
		// 如果之前元素的最大值大于之后元素的值，说明该元素的所在的位置不正确
		if (firstElement != null && prevElement.val >= root.val) {
			secondElement = root;
		}
		// 根节点与左节点，根节点的值大，复制给pre
		prevElement = root;
		// End of "do some business"
		traverse(root.right);
	}
}
