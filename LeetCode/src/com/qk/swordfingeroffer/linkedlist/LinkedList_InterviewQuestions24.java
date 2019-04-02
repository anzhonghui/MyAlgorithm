package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 
 * 面试题24：
 * 		1.反转链表
.. * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions24 {

	/**
	 * @Description:
	 * @Programme：
	 * 1.方案1：通过栈解决，时间复杂度有点高
	 * 2.方案2：使用多个指针控制
	 */
	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(0);
		listNode.next = new ListNode(1);
		listNode.next.next = new ListNode(2);
		listNode.next.next.next = new ListNode(3);
		
		System.out.println(reverseList(listNode).toString());

	}

	/**
	 * 过程分析：
	 * 0  ->  1  ->  2  ->  3
	 * pre   curr
	 * 0  <-  1      2 -> 38u
	 *       pre    curr     
	 * @param pHead
	 * @return
	 */
	public ListNode reverseList(ListNode pHead) {
		// 记录反转后后的头指针
		ListNode pReversedHead = null;
		// 记录当前节点
		ListNode pCurrNode = pHead;
		// 记录当期节点的上一个节点
		ListNode pPrevNode = null;

		while (pCurrNode != null) {
			// 获取下一个节点
			ListNode pNextNode = pCurrNode.next;
			// 说明到尾部，最后一个节点为反转后的指针
			if (pNextNode == null) {
				pReversedHead = pCurrNode;
			}

			pCurrNode.next = pPrevNode;
			pPrevNode = pCurrNode;
			pCurrNode = pNextNode;
		}

		return pReversedHead;
	}

}
