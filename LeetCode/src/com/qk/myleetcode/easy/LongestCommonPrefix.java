package com.qk.myleetcode.easy;

import org.junit.Test;

/**
 * 
 * 14.Longest Common Prefix
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
 * @Description : 最长公共前缀
 * @Programme：通过先匹配成功一对，找出公共部分，然后拿公共部分继续往下匹配（公共部分会一直缩减，最终结果为所有字符串的相同部分）
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class LongestCommonPrefix {

	@Test
	public void MyTest() {
//		String[] strs = {"flower","flow","flight"};
//		System.out.println(longestCommonPrefix(strs));
//		String[] strs2 = {"dog","racecar","car"};
//		System.out.println(longestCommonPrefix(strs2));
//		String[] strs3 = {"racdog","racecar"};
//		System.out.println(longestCommonPrefix(strs3));
		
		String[] strs = {"flower","flow","flight"};
		System.out.println(longestCommonPrefixByBest(strs));
		String[] strs2 = {"dog","racecar","car"};
		System.out.println(longestCommonPrefixByBest(strs2));
		String[] strs3 = {"racdog","racecar"};
		System.out.println(longestCommonPrefixByBest(strs3));
		
	}
	
	/**
	 * 取第一个字符串为标准，依次跟后面的字符串匹配
	 * flower  
	 * flower -> flow ==> flow
	 * flow -> flight ==> fl
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefixByBest(String[] strs) {
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
