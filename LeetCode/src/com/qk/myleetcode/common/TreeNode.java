package com.qk.myleetcode.common;

/**
 * 
 * @Description : 树的结构
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月31日
 */
public class TreeNode {

	public int val;
	public TreeNode left;
	public TreeNode right;

	public TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}

}
