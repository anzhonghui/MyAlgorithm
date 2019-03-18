package com.qk.myleetcode.range_101_150.easy;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * 将排好序的数组转换成二叉搜索树
 * @Description : 
 * @Author : huihui
 * @Date : Create in 2019年3月18日
 */
public class ConvertSortedArrayToBinarySearchTree {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		System.out.println(sortedArrayToBST(new int[] { -10, -3, 0, 5, 9 }));
	}

	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		TreeNode treeNode = recursiveHelper(nums, 0, nums.length - 1);
		return treeNode;
	}

	public TreeNode recursiveHelper(int[] nums, int low, int high) {
		if (low > high) {
			return null;
		}

		int mid = (low + high) / 2;
		TreeNode treeNode = new TreeNode(nums[mid]);
		treeNode.left = recursiveHelper(nums, low, mid - 1);
		treeNode.right = recursiveHelper(nums, mid + 1, high);
		return treeNode;
	}
}
