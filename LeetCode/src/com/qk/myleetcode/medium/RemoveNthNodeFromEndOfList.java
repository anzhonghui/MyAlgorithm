package com.qk.myleetcode.medium;

import org.junit.Test;

public class RemoveNthNodeFromEndOfList {

	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(3);
		listNode.next.next.next = new ListNode(4);
		listNode.next.next.next.next = new ListNode(5);
		System.out.println(removeNthFromEnd(listNode, 2));

//		System.out.println(listNode.toString());
//		
//		listNode.next = listNode.next.next;
//		
//		System.out.println(listNode.toString());

//		removeNthFromEnd(listNode, 2);

//		ListNode listNode2 = new ListNode(1);
//		System.out.println(removeNthFromEnd(listNode2, 1));
//		ListNode listNode3 = new ListNode(1);
//		listNode3.next = new ListNode(2);
//		System.out.println(removeNthFromEnd(listNode3, 2));
	}

	/**
	 * 参考：错位移动，fast移动n，剩余的size - n为slow需要移动的距离，这样可以将时间复杂度控制在O(n)
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {

		ListNode fast = head;
		ListNode slow = head;

		int temp = n;
		while (temp > 0 && fast != null) {
			fast = fast.next;
			temp--;
		}

		if (fast == null) {
			return head.next;
		}

		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}

		slow.next = slow.next.next;

		return head;
	}

	/**
	 * 自己想的：通过先计算节点的长度，然后移动
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEndByMe(ListNode head, int n) {

		if (head.next == null) {
			return null;
		}

		int size = 0;

		ListNode result = head;
		ListNode temp = head;

		while (head != null) {
			size++;
			head = head.next;
		}

		if (size == n) {
			return temp.next;
		}

		for (int i = 0; i <= size - n - 1; i++) {
			if (i == (size - n - 1)) {
				temp.next = temp.next.next;
				break;
			}
			temp = temp.next;
		}

		System.out.println(result.toString());

		return result;
	}

}
