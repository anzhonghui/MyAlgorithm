package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 面试题26：树的子结构
 * 输入两棵二叉树A、B，判断B是不是A的子结构
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class Tree_InterviewQuestions26 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		DBTreeNode dbTreeNode1 = new DBTreeNode(8);
		DBTreeNode dbTreeNode2 = new DBTreeNode(8);
		DBTreeNode dbTreeNode3 = new DBTreeNode(7);
		DBTreeNode dbTreeNode4 = new DBTreeNode(9);
		DBTreeNode dbTreeNode5 = new DBTreeNode(2);
		DBTreeNode dbTreeNode6 = new DBTreeNode(4);
		DBTreeNode dbTreeNode7 = new DBTreeNode(7);

		DBTreeNode dbTreeNode8 = new DBTreeNode(8);
		DBTreeNode dbTreeNode9 = new DBTreeNode(9);
		DBTreeNode dbTreeNode10 = new DBTreeNode(2);
		
		
		dbTreeNode1.left = dbTreeNode2;
		dbTreeNode1.right = dbTreeNode3;
		dbTreeNode2.left = dbTreeNode4;
		dbTreeNode2.right = dbTreeNode5;
		dbTreeNode5.left = dbTreeNode6;
		dbTreeNode5.right = dbTreeNode7;
		
		dbTreeNode8.left = dbTreeNode9;
		dbTreeNode8.right = dbTreeNode10;
		
		System.out.println(hasSubTree(dbTreeNode1, dbTreeNode8));
		System.out.println(hasSubTree(dbTreeNode1, dbTreeNode7));
	}

	/**
	 * 1.判断子树的根节点是否在树中存在
	 * 		通过递归实现，采用dfs（深度优先搜索的方式）
	 * 2.递归判断是否是子结构
	 * @param dbTreeNode1
	 * @param dbTreeNode2
	 * @return
	 */
	public boolean hasSubTree(DBTreeNode dbTreeNode1, DBTreeNode dbTreeNode2) {
		boolean result = false;
		if (dbTreeNode1 != null && dbTreeNode2 != null) {
			if (equal(dbTreeNode1.dbVal, dbTreeNode2.dbVal)) {
				result = doesTree1HaveTree2(dbTreeNode1, dbTreeNode2);
			}
			if (!result) {
				result = hasSubTree(dbTreeNode1.left, dbTreeNode2);
			}
			if (!result) {
				result = hasSubTree(dbTreeNode1.right, dbTreeNode2);
			}
		}
		return result;
	}

	/**
	 * 同样采用dfs的方式判断树的结构是否相同
	 * @param dbTreeNode1
	 * @param dbTreeNode2
	 * @return
	 */
	public boolean doesTree1HaveTree2(DBTreeNode dbTreeNode1, DBTreeNode dbTreeNode2) {

		// 如果dbTreeNode2为空，说明判断完了
		if (dbTreeNode2 == null) {
			return true;
		}
		// 如果dbTreeNode2不为空，dbTreeNode1为空，说明不是子结构
		if (dbTreeNode1 == null) {
			return false;
		}

		// 判断跟节点的值是否相同
		if (!equal(dbTreeNode1.dbVal, dbTreeNode2.dbVal)) {
			return false;
		}

		// 递归判断所有节点的值
		return doesTree1HaveTree2(dbTreeNode1.left, dbTreeNode2.left)
				&& doesTree1HaveTree2(dbTreeNode1.right, dbTreeNode2.right);
	}
	
	/**
	 * 计算机表示小数都有误差，我们不能直接用等号（==）判断是哦福相等。
	 * 如果两个小数的差的绝对值很小，如小于0.0000001，就可以认为他们相等。
	 * @param num1
	 * @param num2
	 * @return
	 */
	public boolean equal(double num1, double num2) {
		if ((num1 - num2 > -0.0000001) && (num1 - num2 < 0.0000001)) {
			return true;
		} else {
			return false;
		}
	}

}
