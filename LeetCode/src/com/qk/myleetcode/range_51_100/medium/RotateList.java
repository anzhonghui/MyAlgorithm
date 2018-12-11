package com.qk.myleetcode.range_51_100.medium;

import org.junit.Test;

import com.qk.myleetcode.common.ListNode;

public class RotateList {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		// 1->2->3->4->5->NULL
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);

		System.out.println(rotateRight(listNode, 2));
	}

	/**
	 * 过程：
	 * 将1->2->3->4->5 链表生成环形链表
	 * fast = 5, dummy 0->1
	 * 5 -> 1 后生成了环形链表
	 * show = 3, dummy 0->4
	 * 切断3的next生成新链表
	 * 4->5->1->2->3
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || head.next == null)
			return head;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode fast = dummy, slow = dummy;

		int i;
		for (i = 0; fast.next != null; i++)// Get the total length
			fast = fast.next;

		for (int j = i - k % i; j > 0; j--) // Get the i-n%i th node
			slow = slow.next;

		fast.next = dummy.next; // Do the rotation
		dummy.next = slow.next;
		slow.next = null;

		return dummy.next;
	}

}
