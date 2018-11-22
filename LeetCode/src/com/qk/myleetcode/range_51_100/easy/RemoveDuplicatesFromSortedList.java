package com.qk.myleetcode.range_51_100.easy;

import org.junit.Test;

import com.qk.myleetcode.common.ListNode;


public class RemoveDuplicatesFromSortedList {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		//1->1->2
//		ListNode listNode = new ListNode(1);
//		listNode.next = new ListNode(1);
//		listNode.next.next = new ListNode(2);
//		System.out.println(deleteDuplicates(listNode).toString());
		
		//1->1->2->3->3
//		ListNode listNode2 = new ListNode(1);
//		listNode2.next = new ListNode(1);
//		listNode2.next.next = new ListNode(2);
//		listNode2.next.next.next = new ListNode(3);
//		listNode2.next.next.next.next = new ListNode(3);
//		System.out.println(deleteDuplicates(listNode2).toString());
		
		System.out.println(deleteDuplicates(null));
		System.out.println(deleteDuplicates(new ListNode(1)).toString());
	}

	public ListNode deleteDuplicates(ListNode head) {
		if(head == null) {
			return null;
		}
		
		ListNode temp = new ListNode(0);
		temp.next = head;
		int lastVal = head.val;
		while(head.next != null) {
			if(head.next.val != lastVal) {
				head = head.next;
			}else {
				head.next = head.next.next;
			}
			lastVal = head.val;
		}
		
		return temp.next;
		
	}
}
