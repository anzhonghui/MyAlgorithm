package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

import com.qk.myleetcode.common.ListNode;

/**
 * @Description : Reverse a linked list from position m to n. Do it in one-pass.
 * example:
 * Input: 1->2->3->4->5->NULL, m = 2, n = 4
 * Output: 1->4->3->2->5->NULL
 * @Author : huihui
 * @Date : Create in 2019年3月8日
 */
public class ReverseLinkedListII {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);
		reverseBetween(listNode, 2, 4);
	}

	/**
	 * 这是最优解，也可以基于栈实现
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
		dummy.next = head;
		ListNode pre = dummy; // make a pointer pre as a marker for the node before reversing
		for (int i = 0; i < m - 1; i++)
			pre = pre.next;

		ListNode start = pre.next; // a pointer to the beginning of a sub-list that will be reversed
		ListNode then = start.next; // a pointer to a node that will be reversed

		// 1 - 2 -3 - 4 - 5 ; m=2; n =4 ---> pre = 1, start = 2, then = 3
		// dummy-> 1 -> 2 -> 3 -> 4 -> 5
		// first reversing : dummy->1 - 3 - 2 - 4 - 5; pre = 1, start = 2, then = 4
		// second reversing: dummy->1 - 4 - 3 - 2 - 5; pre = 1, start = 2, then = 5
		// (finish)
		// pre 不同，start 一直是pre的next, then是交换后的下一个
		for (int i = 0; i < n - m; i++) {
			start.next = then.next;
			then.next = pre.next;
			pre.next = then;
			then = start.next;
			System.out.println(pre.toString());
		}

		return dummy.next;
	}
}
