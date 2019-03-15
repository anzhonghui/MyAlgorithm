package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.qk.myleetcode.common.TreeNode;

/**
 * Given an integer n, generate all structurally unique BST's (binary search trees) that store values 1 ... n.
 * @Description : 给定一个数字，生成所有唯一的二叉搜索树
 * binary search trees ： 如果树不为空，则他一定满足，左节点 < 根节点 < 右节点
 * @Example:
 *  Input: 3
	Output:
	[
	  [1,null,3,2],
	  [3,2,null,1],
	  [3,1,null,null,2],
	  [2,1,3],
	  [1,null,2,null,3]
	]
	Explanation:
	The above output corresponds to the 5 unique BST's shown below:
	
	   1         3     3      2      1
	    \       /     /      / \      \
	     3     2     1      1   3      2
	    /     /       \                 \
	   2     1         2                 3
 * @Author : huihui
 * @Date : Create in 2019年3月13日
 */
public class UniqueBinarySearchTreesII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
//		System.out.println(generateTrees(3));
		System.out.println(generateTrees(0));
	}

	public List<TreeNode> generateTrees(int n) {
		if(n < 1){
            return new ArrayList<TreeNode>();
        }
		return genTrees(1, n);
	}

	/*
	 * 我首先注意到1..n是具有节点1到n的任何BST的顺序遍历。因此，如果我选择第i个节点作为根，左子树将包含元素1到（i-1），
	 * 右子树将包含元素（i+1）到n。我使用递归调用来获取左子树和右子树的所有可能的树，并以尝试所有可能的情况。
	 */
	public List<TreeNode> genTrees(int start, int end) {
		List<TreeNode> list = new ArrayList<TreeNode>();
		if (start > end) {
			list.add(null);
			return list;
		}
		if (start == end) {
			list.add(new TreeNode(start));
			return list;
		}
		List<TreeNode> left, right;
		for (int i = start; i <= end; i++) {
			left = genTrees(start, i - 1);
			right = genTrees(i + 1, end);
			for (TreeNode lnode : left) {
				for (TreeNode rnode : right) {
					TreeNode root = new TreeNode(i);
					root.left = lnode;
					root.right = rnode;
					list.add(root);
				}
			}
		}

		return list;
	}
}
