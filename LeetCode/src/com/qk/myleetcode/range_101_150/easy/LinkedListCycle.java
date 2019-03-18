package com.qk.myleetcode.range_101_150.easy;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.qk.myleetcode.common.ListNode;

public class LinkedListCycle {

	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {

	}
	
	/**
	 * 不适用额外空间的解法，循环重复的元素，一个走一步，一个走两步，会有碰面的时候
	 * @param head
	 * @return
	 */
	public boolean hasCycleByTwoPointers(ListNode head) {
	    if (head == null || head.next == null) {
	        return false;
	    }
	    ListNode slow = head;
	    ListNode fast = head.next;
	    while (slow != fast) {
	        if (fast == null || fast.next == null) {
	            return false;
	        }
	        slow = slow.next;
	        fast = fast.next.next;
	    }
	    return true;
	}

	public boolean hasCycleBySet(ListNode head) {
		Set<ListNode> nodesSeen = new HashSet<>();
	    while (head != null) {
	        if (nodesSeen.contains(head)) {
	            return true;
	        } else {
	            nodesSeen.add(head);
	        }
	        head = head.next;
	    }
	    return false;
	}
}
