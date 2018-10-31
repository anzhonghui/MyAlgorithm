package com.qk.myleetcode.range_1_50.hard;

import java.util.Stack;

import org.junit.Test;

import com.qk.myleetcode.range_1_50.medium.ListNode;
/**
 * 
 * 25.Reverse Nodes In K Group
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
 * @Description : 每k个链表节点进行倒置
 * @Programme：采用循环的方式，一次交换k个节点；记录head和tail，当head=tail时，说明k个节点交换完成了
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
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
	 * 采用循环的方式
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        int i = 1;
        while (head != null) {
            if (i%k == 0) {
            	// 交换
                pre = reverse(pre, head.next);
                // 存储剔除了交换后的数据
                head = pre.next;
            } else {
                head = head.next;
            }
            i++;
        }
        return dummy.next;
    }
    
	/**
	 * pre：包含尾部和要交换的数据
	 * tail：尾部，不包含要交换的数据
	 * 交换k个节点，跳出条件是next等于tail
	 * @param pre
	 * @param tail
	 * @return
	 */
    private ListNode reverse(ListNode pre, ListNode tail) {
    	// 取要交换的当前元素
        ListNode cur = pre.next;
        ListNode next = pre.next.next;
        while (next != tail) {
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
            next = cur.next;
        }
        return cur;
    }

	/**
	 * 通过栈的结构特性，但是效率有点慢；放入k个节点，弹出追加到结果集中；再继续
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
