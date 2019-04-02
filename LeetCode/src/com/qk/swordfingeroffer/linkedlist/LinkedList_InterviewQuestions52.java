package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 
 * 面试题52：
 * 		1.输入两个两边，找到他们的第一个公共节点（公共节点，以为着是同一个对象，而不是值相同；即从第一个公共节点往后，所有的节点内容都相同）
.. * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions52 {

	/**
	 * @Description:
	 * @Programme：
	 */
	@Test
	public void MyTest() {
		ListNode common = new ListNode(6);
		common.next = new ListNode(7);
		
		ListNode listNode1 = new ListNode(1);
		listNode1.next = new ListNode(2);
		listNode1.next.next = new ListNode(3);
		listNode1.next.next.next = common;
		
		ListNode listNode2 = new ListNode(4);
		listNode2.next = new ListNode(5);
		listNode2.next.next = common;

		System.out.println(findFirstCommonNode(listNode1, listNode2).toString());
	}

	/**
	 * 思路：
	 * 1.采用蛮力的解决方式，时间复杂度 O(m*n)
	 * 2.采用辅助栈的方式，需要额外的空间 O(m+n)
	 * 3.通过比较长度 O(m+n)
	 * 	3.1 获取长度
	 * 	3.2 长度较长的链表先移动
	 * 	3.3 两个链表同时移动
	 * @param listNode1
	 * @param listNode2
	 * @return
	 */
	public ListNode findFirstCommonNode(ListNode listNode1, ListNode listNode2) {
		int listLength1 = getListLength(listNode1);
		int listLength2 = getListLength(listNode2);
		int def = listLength1 - listLength2;

		ListNode listNodeLong = listNode1;
		ListNode listNodeSort = listNode2;
		if (def < 0) {
			listNodeLong = listNode2;
			listNodeSort = listNode1;
		}

		for (int i = 0; i < def; ++i) {
			listNodeLong = listNodeLong.next;
		}

		while (listNodeLong != null && listNodeSort != null && listNodeLong != listNodeSort) {
			listNodeLong = listNodeLong.next;
			listNodeSort = listNodeSort.next;
		}

		return listNodeLong;
	}

	/**
	 * 获取链表的长度
	 * @param listNode
	 * @return
	 */
	public int getListLength(ListNode listNode) {
		int length = 0;
		while (listNode != null) {
			++length;
			listNode = listNode.next;
		}

		return length;
	}
}
