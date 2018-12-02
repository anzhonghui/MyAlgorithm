package com.qk.swordfingeroffer;
/**
 * @Description : 复杂链表
 * @Author : huihui
 * @Date : Create in 2018年12月1日
 */
public class ComplexListNode {

	public String val;
	// 指向下一个节点
	public ComplexListNode next;
	// 指向任意节点
	public ComplexListNode sibling;
	
	public ComplexListNode() {
	}
	
	public ComplexListNode(String val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "ComplexListNode [val=" + val + ", next=" + next + ", sibling=" + (sibling != null ? sibling.val : null) + "]";
	}
	
}
