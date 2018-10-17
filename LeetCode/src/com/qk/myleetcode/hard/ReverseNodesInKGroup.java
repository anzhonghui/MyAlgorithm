package com.qk.myleetcode.hard;

import java.util.Stack;

import org.junit.Test;

import com.qk.myleetcode.medium.ListNode;

public class ReverseNodesInKGroup {

	@Test
	public void MyTest() {
		// 1->2->3->4->5
		ListNode node = new ListNode(1);
		node.next = new ListNode(2);
		node.next.next = new ListNode(3);
		node.next.next.next = new ListNode(4);
		node.next.next.next.next = new ListNode(5);
		System.out.println(reverseKGroup(node, 2).toString());
		System.out.println(reverseKGroup(node, 3).toString());
	}

	/**
	 * 暂时还没看懂
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
		if (k == 1 || k == 0)
			return head;
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode first = head;
		ListNode testNode, second, third, prev;
		int i = 0;
		first = dummy;
		second = dummy.next;
		third = second.next;
		while (true) {
			i = 0;
			testNode = second;
			while ((i++) < k) {
				if (testNode == null) {
					return dummy.next;
				}
				testNode = testNode.next;
			}
			prev = first;
			i = 0;
			while ((i++) < k - 1) {
				first = second;
				second = third;
				third = third.next;
				second.next = first;

			}
			
			ListNode tempNode = prev.next;
			first = second;
			second = third;

			prev.next.next = second;
			prev.next = first;
			first = tempNode;

			if (third == null || third.next == null)
				return dummy.next;
			third = third.next;

		}
	}

	/**
	 * 通过栈的结构特性，但是效率有点慢
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroupByMe(ListNode head, int k) {
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		Stack<ListNode> stack = new Stack<>();
		boolean tailMark = false;

		while (head != null) {
			ListNode temp = head;
			for (int i = 1; i <= k; i++) {
				stack.add(new ListNode(head.val));
				head = head.next;
				if (head == null && i != k) {
					tailMark = true;
					break;
				}
			}

			while (!tailMark && !stack.isEmpty()) {
				tail.next = stack.pop();
				tail = tail.next;
			}

			if (tailMark && temp != null) {
				tail.next = temp;
			}
		}

//		while(!stack.isEmpty()) {
//			tail.next = stack.pop();
//			tail = tail.next;
//		}
		return dummy.next;
	}
}
