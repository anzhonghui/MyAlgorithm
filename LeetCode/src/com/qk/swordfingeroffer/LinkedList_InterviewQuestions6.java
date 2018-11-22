package com.qk.swordfingeroffer;

import java.util.Stack;

import org.junit.Test;

/**
 * @Description : 面试题6：从尾到头打印链表（单项链表）
 * 倒叙打印链表
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions6 {

	/**
	 * @Description:
	 * @Programme：方案一：使用栈的结构。方案二：使用递归（实际上就是栈）
	 */
	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(0);
		listNode.next = new ListNode(1);
		listNode.next.next = new ListNode(2);
		listNode.next.next.next = new ListNode(3);

		System.out.println("方案一：使用栈");
		printListReversingly_Stack(listNode);
		System.out.println("方案二：使用递归");
		printListReversingly_Recursively(listNode);

	}

	/**
	 * 方案一：使用栈的结构。
	 * @param listNode
	 */
	public void printListReversingly_Stack(ListNode listNode) {

		Stack<ListNode> stack = new Stack<>();
		while(listNode != null) {
			stack.add(listNode);
			listNode = listNode.next;
		}
		
		while(!stack.isEmpty()) {
			System.out.println(stack.pop().val);
		}
		
	}
	
	/**
	 * 方案二：使用递归（实际上就是栈）
	 * @param listNode
	 */
	public void printListReversingly_Recursively(ListNode listNode) {
		if(listNode != null) {
			// 递归条件
			if(listNode.next != null) {
				printListReversingly_Recursively(listNode.next);
			}
			System.out.println(listNode.val);
		}
	}
}
