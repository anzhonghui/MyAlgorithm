package com.qk.swordfingeroffer;

public class DBTreeNode {
	double dbVal;
	DBTreeNode left;
	DBTreeNode right;

	DBTreeNode(int dbVal) {
		this.dbVal = dbVal;
	}

	@Override
	public String toString() {
		return "DBTreeNode [dbVal=" + dbVal + ", left=" + left + ", right=" + right + "]";
	}
	
}
