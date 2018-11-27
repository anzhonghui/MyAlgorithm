package com.qk.swordfingeroffer;

public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return "TreeNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}
	
	public void display(){
		TreeNode data = this;
		while(data != null){
			System.out.print(data.val + " <-> ");
			data = data.right;
		}
		System.out.print("\n");
	}
}
