package com.qk.myleetcode.medium;

import org.junit.Test;
/**
 * 
 * 2. Add Two Numbers
 *  ┏┓　　┏┓
 * ┏┛┻━━━━┛┻┓
 * ┃　　　　　┃
 * ┃　　　━　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　 ┃
 * ┃　　　┻　　┃
 * ┃　　　　　 ┃
 * ┗━━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　┣┓
 * 　　┃　　　　　　┏┛
 * 　　┗┓┓┏━┳┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : You are given two non-empty linked lists representing two non-negative integers. 
 * 				  The digits are stored in reverse order and each of their nodes contain a single digit. 
 * 				  Add the two numbers and return it as a linked list.
 **               您将获得两个非空链表，表示两个非负整数。 数字以相反的顺序存储，每个节点包含一个数字。 添加两个数字并将其作为链接列表返回。
 **				   输入：（2 - > 4 - > 3）+（5 - > 6 - > 4）
 **               输出：7 - > 0 - > 8
 **               说明：342 + 465 = 807。
 *
 * @Programme: 1.Java中没有指针，所以链表的使用要注意：先用head，current=head，那样后期可以直接修改current变量的值，
 * 			      而修改current.next的值的同时，head的next也在变
 *             2.和的计算主要要处理进位
 *
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年8月26日
 */
public class AddTwoNumbers {
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

		// 头
		ListNode head = new ListNode(0);
		// 结果
		ListNode current = head;
		// 进位
		int carry = 0;

		// 遍历链表的节点
		while (l1 != null || l2 != null) {
			// 获取两个节点的值
			int x = l1 != null ? l1.val : 0;
			int y = l2 != null ? l2.val : 0;
			// 计算结果
			int result = x + y + carry;
			
			// 计算进位
			carry = result / 10;
			// 计算当期那节点的值
			current.next = new ListNode(result % 10);
			// 递推
			current = current.next;

			// 递推
			l1 = l1 != null ? l1.next : null;
			l2 = l2 != null ? l2.next : null;
		}
		
		if(carry > 0) {
			current.next = new ListNode(carry);
		}

		return head.next;
	}

	@Test
	public void solution() {
		ListNode l1 = new ListNode(2);
		ListNode l1next = new ListNode(4);
		l1next.next = new ListNode(3);
		l1.next = l1next;
		ListNode l2 = new ListNode(5);
		ListNode l2next = new ListNode(6);
		l2next.next = new ListNode(4);
		l2.next = l2next;
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	@Test
	public void solution2() {
		ListNode l1 = new ListNode(2);
		ListNode l1next = new ListNode(4);
		l1next.next = new ListNode(3);
		l1.next = l1next;
		ListNode l2 = new ListNode(5);
		ListNode l2next = new ListNode(6);
		l2.next = l2next;
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	@Test
	public void solution3() {
		ListNode l1 = new ListNode(0);
		ListNode l2 = new ListNode(7);
		ListNode l2next = new ListNode(3);
		l2.next = l2next;
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	@Test
	public void solution4() {
		ListNode l1 = new ListNode(3);
		ListNode l2 = new ListNode(7);
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	@Test
	public void solution5() {
		ListNode l1 = new ListNode(1);
		ListNode l2 = new ListNode(9);
		l2.next = new ListNode(9);
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	@Test
	public void solution6() {
		ListNode l1 = new ListNode(9);
		l1.next = new ListNode(9);
		ListNode l2 = new ListNode(9);
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	@Test
	public void solution7() {
		ListNode l1 = new ListNode(9);
		ListNode l2 = new ListNode(9);
		l2.next = new ListNode(9);
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	@Test
	public void solution8() {
		ListNode l1 = new ListNode(8);
		ListNode l1next = new ListNode(9);
		l1next.next = new ListNode(9);
		l1.next = l1next;
		ListNode l2 = new ListNode(2);
		ListNode addTwoNumbers = addTwoNumbers(l1,l2);
		System.out.println(addTwoNumbers.toString());
	}
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) { val = x; }
	 * }
	 */
	private int nextVal = 0;

	/**
	 * 普遍的运行效率高
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
		
		// 头(最终结果)
		ListNode head = null;
		// 尾(用于临时记录当前节点的位置)
		ListNode tail = null;
		// 进位数
		int carry = 0;
		// 结果
		int result;
		
		while (l1 != null || l2 != null || carry != 0) {
			result = carry;
			if (l1 != null) {
				result += l1.val;
				l1 = l1.next;
			}
			if (l2 != null) {
				result += l2.val;
				l2 = l2.next;
			}
			
			carry = result / 10;
			ListNode node = new ListNode(result % 10);
			
			if (head == null) {
				head = node;
				tail = node;
			} else {
				tail.next = node;
				tail = node;
			}
			
		}
		
		return head;
	}
	
	/**
	 * 自己想的，执行效率不行
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		
		if (l1 != null && l2 != null) {
			int val = l1.val + l2.val + nextVal;
			int curVal = val % 10;
			l1.val = curVal;
			nextVal = val / 10;
			
			if (l1.next == null) {
				if (l2.next != null || nextVal != 0) {
					l1.next = new ListNode(0);
				}
			}
			addTwoNumbers3(l1.next, l2.next);
			
		} else if (l1 != null) {
			int val = l1.val + nextVal;
			int curVal = val % 10;
			l1.val = curVal;
			nextVal = val / 10;
			if (nextVal != 0 && l1.next != null) {
				addTwoNumbers3(l1.next, null);
			} else if(nextVal != 0){
				l1.next = new ListNode(nextVal);
			}
			
		} else if (l2 != null) {
			l1 = new ListNode(l2.val + nextVal);
		}
		
		return l1;
	}
	
}
