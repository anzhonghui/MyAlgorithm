package com.qk.myleetcode.range_101_150.easy;

import java.util.LinkedList;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;
/**
 * 
 * 112. Path Sum
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
 * @Description : 找二叉树中是否包含某一条路径等于指定的值（跟剑指offer第34题类似）
 *       5
 *      / \
 *     4   8
 *    /   / \
 *   11  13  4
 *  /  \      \
 * 7    2      1
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月28日
 */
public class PathSum {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode1 = new TreeNode(5);
		TreeNode treeNode2 = new TreeNode(4);
		TreeNode treeNode3 = new TreeNode(8);
		TreeNode treeNode4 = new TreeNode(11);
		TreeNode treeNode5 = new TreeNode(13);
		TreeNode treeNode6 = new TreeNode(4);
		TreeNode treeNode7 = new TreeNode(7);
		TreeNode treeNode8 = new TreeNode(2);
		TreeNode treeNode9 = new TreeNode(1);

		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode3.left = treeNode5;
		treeNode3.right = treeNode6;
		treeNode4.left = treeNode7;
		treeNode4.right = treeNode8;
		treeNode6.right = treeNode9;

		System.out.println(hasPathSum(treeNode1, 22));
	}

	/**
	 * 通过递归的方式
	 * 时间复杂度：O(n)
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		}
		sum -= root.val;

		// 说明找到叶子节点了
		if (root.left == null && root.right == null) {
			return sum == 0;
		}

		// 判断有没有符合条件的路径
		return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
	}

	/**
	 * 采用dfs解决问题，利用了栈实现
	 *       5
	 *      / \
	 *     4   8
	 *    /   / \
	 *   11  13  4
	 *  /  \      \
	 * 7    2      1
	 * 
	 * 
	 * 实际的递归判断过程：                                                            **跳出**
	 * (5, 17)(8, 9)(4, 5)(1, 4)(13, -4)(4, 13)(11, 2)(2, 0)(7, -5)
	 * 
	 * 
	 * 详细的状态对比
	 *    链表的对应                                  判断
	 * (5, 17)                   pop(5,17)  
	 *                           push(8, 17-8)  (4, 17-4)
	 * (8, 11)(4, 13)            pop(4, 13)
	 * (8, 11)                   push(11, 13-11)
	 * (8, 11)(11, 2)            pop(11, 2)
	 * (8, 11)                   push(2, 2-2)(7, 2-7)
	 * (8, 11)(2, 0)             pop(2, 0)   true
	 * 
	 * @param root
	 * @param sum
	 * @return
	 */
	public boolean hasPathSum2(TreeNode root, int sum) {
		if (root == null)
			return false;

		// 这两个链表都是有序的
		// 存放节点
		LinkedList<TreeNode> node_stack = new LinkedList();
		// 存放剩余的值
		LinkedList<Integer> sum_stack = new LinkedList();
		node_stack.add(root);
		sum_stack.add(sum - root.val);

		TreeNode node;
		int curr_sum;
		while (!node_stack.isEmpty()) {
			// 弹出退后一个节点信息判断
			node = node_stack.pollLast();
			curr_sum = sum_stack.pollLast();
			if ((node.right == null) && (node.left == null) && (curr_sum == 0))
				return true;

			// 对右节点进行计算添加
			if (node.right != null) {
				node_stack.add(node.right);
				sum_stack.add(curr_sum - node.right.val);
			}
			// 对左节点进行计算添加
			if (node.left != null) {
				node_stack.add(node.left);
				sum_stack.add(curr_sum - node.left.val);
			}
		}
		return false;
	}
}
