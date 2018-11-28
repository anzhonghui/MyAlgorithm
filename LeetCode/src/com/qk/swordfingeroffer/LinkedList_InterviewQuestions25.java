package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 
 * 面试题25：
 * 		1.合并两个排好序的数组
.. * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions25 {

	/**
	 * @Description:
	 * @Programme：
	 * 1.方案1：通过循环的方式
	 * 2.方案2：通过递归的方式
	 */
	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(3);
		listNode.next.next = new ListNode(5);
		listNode.next.next.next = new ListNode(7);
		ListNode listNode2 = new ListNode(2);
		listNode2.next = new ListNode(4);
		listNode2.next.next = new ListNode(6);
		listNode2.next.next.next = new ListNode(8);

		System.out.println(mergeByLoop(listNode, null).toString());
		System.out.println(mergeByLoop(null, listNode2).toString());
		System.out.println(mergeByLoop(listNode, listNode2).toString());

		ListNode listNode3 = new ListNode(1);
		listNode3.next = new ListNode(1);
		listNode3.next.next = new ListNode(2);
		ListNode listNode4 = new ListNode(3);
		listNode4.next = new ListNode(4);
		listNode4.next.next = new ListNode(6);
		System.out.println(mergeByLoop(listNode3, listNode4).toString());

		ListNode listNode5 = new ListNode(1);
		listNode5.next = new ListNode(1);
		listNode5.next.next = new ListNode(2);
		ListNode listNode6 = new ListNode(3);
		listNode6.next = new ListNode(4);
		listNode6.next.next = new ListNode(6);
		System.out.println(mergeByLoop(listNode6, listNode5).toString());

	}
	
	@Test
	public void MyTestByRecursion() {
		ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(3);
		listNode.next.next = new ListNode(5);
		listNode.next.next.next = new ListNode(7);
		ListNode listNode2 = new ListNode(2);
		listNode2.next = new ListNode(4);
		listNode2.next.next = new ListNode(6);
		listNode2.next.next.next = new ListNode(8);

		System.out.println(mergeByRecursion(listNode, null).toString());
		System.out.println(mergeByRecursion(null, listNode2).toString());
		System.out.println(mergeByRecursion(listNode, listNode2).toString());

		ListNode listNode3 = new ListNode(1);
		listNode3.next = new ListNode(1);
		listNode3.next.next = new ListNode(2);
		ListNode listNode4 = new ListNode(3);
		listNode4.next = new ListNode(4);
		listNode4.next.next = new ListNode(6);
		System.out.println(mergeByRecursion(listNode3, listNode4).toString());

		ListNode listNode5 = new ListNode(1);
		listNode5.next = new ListNode(1);
		listNode5.next.next = new ListNode(2);
		ListNode listNode6 = new ListNode(3);
		listNode6.next = new ListNode(4);
		listNode6.next.next = new ListNode(6);
		System.out.println(mergeByRecursion(listNode6, listNode5).toString());

	}

	/**
	 * 通过循环的方式实现
	 * @param pHead
	 * @return
	 */
	public ListNode mergeByLoop(ListNode p, ListNode q) {

		// 1.单独为空的情况
		if (p == null) {
			return q;
		}
		if (q == null) {
			return p;
		}

		// 记录反转后后的头指针
		ListNode head = new ListNode(0);
		ListNode temp = head;
		// 2.比价
		while (p != null && q != null) {
			while (p != null && p.val < q.val) {
				temp.next = p;
				temp = temp.next;
				p = p.next;
			}

			if (p == null) {
				break;
			}

			while (q != null && q.val <= p.val) {
				temp.next = q;
				temp = temp.next;
				q = q.next;
			}
		}

		// 3.如果没有遍历的的情况，即两个链表的长度不相等
		while (p != null) {
			temp.next = p;
			temp = temp.next;
			p = p.next;
		}

		while (q != null) {
			temp.next = q;
			temp = temp.next;
			q = q.next;
		}

		return head.next;
	}

	/**
	 * 通过递归的方式实现
	 * @param p
	 * @param q
	 * @return
	 */
	public ListNode mergeByRecursion(ListNode p, ListNode q) {
		// 单独为空的情况
		if (p == null) {
			return q;
		}
		if (q == null) {
			return p;
		}

		ListNode pMergeHead = null;
		if (p.val < q.val) {
			pMergeHead = p;
			pMergeHead.next = mergeByRecursion(p.next, q);
		} else {
			pMergeHead = q;
			pMergeHead.next = mergeByRecursion(p, q.next);
		}

		return pMergeHead;
	}

}
