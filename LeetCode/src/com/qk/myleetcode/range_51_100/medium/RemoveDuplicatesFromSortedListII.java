package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

import com.qk.myleetcode.common.ListNode;

public class RemoveDuplicatesFromSortedListII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		// 1->2->3->3->4->4->5
		ListNode listNode1 = new ListNode(1);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3_1 = new ListNode(3);
		ListNode listNode3 = new ListNode(3);
		ListNode listNode4_1 = new ListNode(4);
		ListNode listNode4 = new ListNode(4);
		ListNode listNode5 = new ListNode(5);
		
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode3_1;
		listNode3_1.next = listNode4;
		listNode4.next = listNode4_1;
		listNode4_1.next = listNode5;
		
		System.out.println(deleteDuplicates(listNode1));
	}

	/**
	 * 采用额外两个变量记录的方法，pre记录上一个节点，cur是当前节点的方式移动
	 * 采用同时跳过重复元素的方式，避免了多次的跳过
	 * @param head
	 * @return
	 */
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return null;
		// 假头
		ListNode fakeHead = new ListNode(0);
		fakeHead.next = head;
		// 记录上一个节点
		ListNode pre = fakeHead;
		// 记录当前节点
		ListNode cur = head;
		while (cur != null) {
			// 如果当前节点的值跟下一个节点的值相等，往前移动
			while (cur.next != null && cur.val == cur.next.val) {
				cur = cur.next;
			}
			// 如果没有移除节点，说明没有相同的节点，上一个节点往下移动
			if (pre.next == cur) {
				pre = pre.next;
				
				// 其他情况说明有重复节点的情况，pre的next直接移除到cur的next
			} else {
				pre.next = cur.next;
			}
			// 当前节点往前移动
			cur = cur.next;
		}
		return fakeHead.next;
	}
}
