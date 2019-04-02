package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 
 * 面试题18：
 * 		1.删除指定的节点（单项链表）
 *      2.删除重复的节点
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions22 {

	/**
	 * @Description:
	 * @Programme：方案：通过复制的方式，剔除，是时间复杂度为O(1)
	 * 处理三种情况：
	 * 1.要删除的节点不是尾节点
	 * 2.只有一个节点（Java无法处理这种情况，需要加一个头结点）
	 * 3.要删除的节点是尾节点
	 */
	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(0);
		listNode.next = new ListNode(1);
		listNode.next.next = new ListNode(2);
		listNode.next.next.next = new ListNode(3);

		deleteNode(listNode, listNode.next);
		System.out.println(listNode.toString());
		deleteNode(listNode, listNode.next);
		System.out.println(listNode.toString());
		deleteNode(listNode, listNode.next);
		System.out.println(listNode.toString());
		deleteNode(listNode, listNode);
		System.out.println(listNode.toString());
	}

	/**
	 * 删除指定的节点
	 * @param listNode
	 * @param deleteNode
	 */
	public void deleteNode(ListNode listNode, ListNode deleteNode) {

		// 处理各种非法情况
		if (listNode == null || deleteNode == null) {
			return;
		}

		// 1.要删除的节点不是尾节点
		if (deleteNode.next != null) {
			System.out.println("1.要删除的节点不是尾节点");
			deleteNode.val = deleteNode.next.val;
			deleteNode.next = deleteNode.next.next;
		}

		// 2.只有一个节点
		else if (listNode == deleteNode) {
			System.out.println("2.只有一个节点");
			listNode = null;
		}

		// 3.要删除的节点是尾节点
		else {
			System.out.println("3.要删除的节点是尾节点");
			while (listNode.next != deleteNode) {
				listNode = listNode.next;
			}

			listNode.next = null;
		}

	}

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest2() {
		// 测试正常情况
		ListNode listNode = new ListNode(0);
		listNode.next = new ListNode(1);
		listNode.next.next = new ListNode(2);
		listNode.next.next.next = new ListNode(2);
		listNode.next.next.next.next = new ListNode(3);
		
		System.out.println(deleteDuplication(listNode).toString());
		
		// 测试重复的节点在头部
		ListNode listNode2 = new ListNode(0);
		listNode2.next = new ListNode(0);
		listNode2.next.next = new ListNode(2);
		listNode2.next.next.next = new ListNode(2);
		listNode2.next.next.next.next = new ListNode(3);
		
		System.out.println(deleteDuplication(listNode2).toString());
		
		// 测试重复的节点在尾部
		ListNode listNode3 = new ListNode(0);
		listNode3.next = new ListNode(1);
		listNode3.next.next = new ListNode(2);
		listNode3.next.next.next = new ListNode(2);
		listNode3.next.next.next.next = new ListNode(3);
		listNode3.next.next.next.next.next = new ListNode(3);
		
		System.out.println(deleteDuplication(listNode3).toString());
		
		// 测试相隔的重复节点
		ListNode listNode4 = new ListNode(0);
		listNode4.next = new ListNode(0);
		listNode4.next.next = new ListNode(1);
		listNode4.next.next.next = new ListNode(2);
		listNode4.next.next.next.next = new ListNode(2);
		listNode4.next.next.next.next.next = new ListNode(3);
		
		System.out.println(deleteDuplication(listNode4).toString());
	}
	
	/**
	 * 删除重复的节点
	 * 1.记录上一个节点（关键，上一个节点指向一开始的head）
	 * 2.获取下一个节点，判断是否需要删除
	 * 3.不需要，跳过
	 * 4.需要，删除
	 * @param listNode
	 */
	public ListNode deleteDuplication(ListNode listNode) {
		ListNode head = new ListNode(0);
		head.next = listNode;
		// 用于记录上一节点（一开始指向头指针）
		ListNode pre = head;
		
		while (listNode != null) {
			// 当前节点的下一个节点
			ListNode next = listNode.next;
			// 是否删除
			boolean needDelete = false;
			
			// 判断是否需要删除节点
			if(next != null && next.val == listNode.val) {
				needDelete = true;
			}
			
			// 不需要，跳过
			if(!needDelete) {
				// 记录上一个节点，当前节点后移
				pre = listNode;
				listNode = listNode.next;
			}
			// 需要，删除节点，直到没有重复节点
			else {
				// 记录重复节点的值
				int val = listNode.val;
				// 递归删除
				while(listNode != null && listNode.val == val) {
					listNode = listNode.next;
				}
				pre.next = listNode;
			}
		}
		
		return head.next;
		
	}

}
