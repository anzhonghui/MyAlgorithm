package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题8：二叉树的下一个节点
 * 给定一棵树和其中的一个节点，如何找出  ** 中序遍历序列  ** 的下一个节点（树中的节点除了有两个分别指向左右子节点的指针，还有一个指向父节点的指针）
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions8 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		TreeNodeContainParent a = new TreeNodeContainParent("a");
		TreeNodeContainParent b = new TreeNodeContainParent("b");
		TreeNodeContainParent c = new TreeNodeContainParent("c");
		TreeNodeContainParent e = new TreeNodeContainParent("e");
		TreeNodeContainParent d = new TreeNodeContainParent("d");
		TreeNodeContainParent f = new TreeNodeContainParent("f");
		TreeNodeContainParent g = new TreeNodeContainParent("g");
		TreeNodeContainParent h = new TreeNodeContainParent("h");
		TreeNodeContainParent i = new TreeNodeContainParent("i");
		
		a.left = b;
		a.right = c;
		b.root = a;
		b.left = d;
		b.right = e;
		c.root = a;
		c.left = f;
		c.right = g;
		e.root = b;
		e.left = h;
		e.right = i;
		f.root = c;
		g.root = c;
		h.root = e;
		i.root = e;
		
//		System.out.println(getNext(b).toString());
		System.out.println(getNext(f).toString());
		
	}
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		TreeNodeContainParent a = new TreeNodeContainParent("a");
		TreeNodeContainParent b = new TreeNodeContainParent("b");
		TreeNodeContainParent c = new TreeNodeContainParent("c");
		TreeNodeContainParent e = new TreeNodeContainParent("e");
		TreeNodeContainParent d = new TreeNodeContainParent("d");
		TreeNodeContainParent f = new TreeNodeContainParent("f");
		TreeNodeContainParent g = new TreeNodeContainParent("g");
		TreeNodeContainParent h = new TreeNodeContainParent("h");
		TreeNodeContainParent i = new TreeNodeContainParent("i");
		TreeNodeContainParent j = new TreeNodeContainParent("j");
		TreeNodeContainParent k = new TreeNodeContainParent("k");
		
		a.left = b;
		a.right = c;
		b.root = a;
		b.left = d;
		b.right = e;
		c.root = a;
		c.left = f;
		c.right = g;
		e.root = b;
		e.left = h;
		e.right = i;
		f.root = c;
		f.left = j;
		f.right = k;
		g.root = c;
		h.root = e;
		i.root = e;
		j.root = f;
		k.root = f;
		
		System.out.println(getNext(k).toString());
	}
	
	/**
	 * 寻找二叉树节点的下一个节点
	 *           a
	 *          / \
	 *         /   \
	 *        /     \
	 *       /       \
	 *      b         c
	 *     / \       / \
	 *    d   e     f   g
	 *       / \   / \
	 *      h   i j   k
	 *      
	 * dbheiajfkcg
	 * 
	 * 两种情况：
	 * 	1.查询的结果有右子树（右节点）（b -> h）
	 * 		使用循环一直向下找右子树的左子树，直到找到他的叶子节点
	 * 		例子：查找b节点的下一个节点（向下查找）
	 * 			1.1. b的右节点为e，不为空
	 * 			1.2. 将e作为父节点查找他的左节点
	 * 			1.3. 如果节点为叶子节点，得到结果，h
	 *  2.右子树为空，但父节点不为空的情况（k -> c）
	 *  	如果父节点不为空和当前节点是父节点的右子节点，向上遍历（当前节点变为父节点，父节点变为当前节点的父节点），直到有不符合条件的结束
	 *  	例子：查找k节点的下一个节点（向上查找）
	 *  		2.1. k是f的右节点，f变为当前节点，c为他的父节点
	 *  		2.2. 如果f是c的右子节点，重复2.1
	 *  		2.3. 如果f不是c的右子节点，得到结果c
	 * @param treeNode
	 * @return
	 */
	public TreeNodeContainParent getNext(TreeNodeContainParent treeNode) {
		if (treeNode == null) {
			return null;
		}

		TreeNodeContainParent next = null;
		// 右子树不为空的情况（b -> h）
		if (treeNode.right != null) {
			TreeNodeContainParent right = treeNode.right;
			// 使用循环一直向下找右子树的左子树，直到找到他的叶子节点
			while (right.left != null) {
				right = right.left;
			}
			next = right;
			
		// 右子树为空，但父节点不为空的情况（k -> c）
		} else if (treeNode.root != null) {
			// 当期节点
			TreeNodeContainParent current = treeNode;
			// 当前节点的根节点
			TreeNodeContainParent root = treeNode.root;
			// 父节点不为空和当前节点是父节点的右子节点
			while (root != null && current == root.right) {
				// 向上遍历（当前节点变为父节点，父节点变为当前节点的父节点）
				current = root;
				root = root.root;
			}
			// 最终结果为找到的父节点
			next = root;
		}

		return next;
	}


}
