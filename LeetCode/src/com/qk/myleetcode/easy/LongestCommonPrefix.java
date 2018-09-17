package com.qk.myleetcode.easy;

import org.junit.Test;

public class LongestCommonPrefix {

	@Test
	public void MyTest() {
//		String[] strs = {"flower","flow","flight"};
//		System.out.println(longestCommonPrefix(strs));
//		String[] strs2 = {"dog","racecar","car"};
//		System.out.println(longestCommonPrefix(strs2));
//		String[] strs3 = {"racdog","racecar"};
//		System.out.println(longestCommonPrefix(strs3));
		
//		String[] strs = {"flower","flow","flight"};
//		System.out.println(longestCommonPrefixByFirst(strs));
//		String[] strs2 = {"dog","racecar","car"};
//		System.out.println(longestCommonPrefixByFirst(strs2));
//		String[] strs3 = {"racdog","racecar"};
//		System.out.println(longestCommonPrefixByFirst(strs3));
		
		System.out.println("flower".indexOf("flight"));
	}
	
	public String longestCommonPrefixByFirst(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        
        String prefix = strs[0];
        for (int i = 1; i < strs.length; ++i)
            while (strs[i].indexOf(prefix) != 0) {
            	//从后往前裁切
                prefix = prefix.substring(0, prefix.length() - 1);
                
                if (prefix.isEmpty()) {
                    return "";
                }
            }        
        
        return prefix;
    }

	//["flower","flow","flight"] "fl"
	public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0) {
			return "";
		}
		boolean flag = true;
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < strs[0].length(); i++) {
			for (int j = 1; j < strs.length; j++) {
				if(i >= strs[j].length()) {
					return sBuffer.toString();
				}
				if(strs[0].charAt(i) != strs[j].charAt(i)) {
					flag = false;
				}
			}
			
			if(flag) {
				sBuffer.append(strs[0].charAt(i));
			}
		}
		
		return sBuffer.toString();
	}
}
