package com.qk.myleetcode.range_51_100.easy;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;
/**
 * 
 * 111. Minimum Depth of Binary Tree
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
 * @Description : 求二叉树的最小深度（区分：求二叉树的深度）
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年11月27日
 */
public class MinimumDepthOfBinaryTree {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		TreeNode treeNode1 = new TreeNode(3);
		TreeNode treeNode2 = new TreeNode(9);
		TreeNode treeNode3 = new TreeNode(20);
		TreeNode treeNode4 = new TreeNode(15);
		TreeNode treeNode5 = new TreeNode(7);
		TreeNode treeNode6 = new TreeNode(1);

		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode3.left = treeNode4;
		treeNode3.right = treeNode5;
		treeNode4.left = treeNode6;
	
		System.out.println(minDepth(treeNode1));
		System.out.println(minDepth(null));
		System.out.println(minDepth(treeNode4));
		System.out.println(minDepth(treeNode6));
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		TreeNode treeNode1 = new TreeNode(3);
		TreeNode treeNode2 = new TreeNode(9);
		TreeNode treeNode3 = new TreeNode(20);
		treeNode1.left = treeNode2;
//		treeNode2.left = treeNode3;
		System.out.println(minDepth(treeNode1));
	}

	/**
	 * 采用递归的方式，dfs
	 * 1.如果求树的深度，取出中间的两个if，return的 “<” 改成 “>” 即可
	 * 2.求树的最小深度，除以需要处理只有一条路径的情况，比如：1只有左子树2
	 * @param root
	 * @return
	 */
	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int leftD = minDepth(root.left);
		int rightD = minDepth(root.right);
		
		if(leftD == 0 && rightD != 0) {
			return rightD + 1;
		}
		if(rightD == 0 && leftD != 0) {
			return leftD + 1;
		}

		return leftD < rightD ? leftD + 1 : rightD + 1;
	}
}
