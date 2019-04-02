package com.qk.swordfingeroffer;

import org.junit.Test;

/**
 * @Description : 
 * 面试题22：
 * 		1.链表中倒数第k个节点
 * 		2.求链表的中间节点（没有用代码实现）
 * 			思路：两个指针，一个指针每次走一步，另一个每次走两步，当第二个节点到达尾部时，说明第一个指针到了中间节点
 * @Author : huihui
 * @Date : Create in 2018年11月7日
 */
public class LinkedList_InterviewQuestions18 {

	/**
	 * @Description:
	 * @Programme：方案：通过多创建一个临时链表的方法
	 * 1.链表a移动k个后
	 * 2.链表a跟临时链表b同时移动，直到链表a为空
	 */
	@Test
	public void MyTest() {
		ListNode listNode = new ListNode(0);
		listNode.next = new ListNode(1);
		listNode.next.next = new ListNode(2);
		listNode.next.next.next = new ListNode(3);
		listNode.next.next.next.next = new ListNode(4);
		listNode.next.next.next.next.next = new ListNode(5);
		
		System.out.println(findKthToTail(listNode, 3).val);
		System.out.println(findKthToTail(listNode, 7));
		System.out.println(findKthToTail(listNode, 6).val);
		System.out.println(findKthToTail(listNode, 1).val);

	}
	
	public ListNode findKthToTail(ListNode listNode, int k) {
		if(listNode == null) {
			return null;
		}
		
		ListNode temp = listNode;
		for (int i = 0; i < k; ++i) {
			if(listNode == null) {
				return null;
			}
			listNode = listNode.next;
		}
		
		while(listNode != null) {
			temp = temp.next;
			listNode = listNode.next;
		}
		
		return temp;
	}
}
