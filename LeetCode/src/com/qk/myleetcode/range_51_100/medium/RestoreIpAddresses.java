package com.qk.myleetcode.range_51_100.medium;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @Description : Given a string containing only digits, restore（使复原） it by returning all possible valid IP address combinations.
 * @Example：
 * 		Input: "25525511135"
 * 		Output: ["255.255.11.135", "255.255.111.35"]
 * @Author : huihui
 * @Date : Create in 2019年3月12日
 */
public class RestoreIpAddresses {
	
	/**
	 * @Description:
	 * @Example:
	 * @Pragramme:
	 */
	@Test
	public void MyTest() {
		restoreIpAddresses("25525511135");
	}

	public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        int len = s.length();
        for(int i = 1; i<4 && i<len-2; i++){
            for(int j = i+1; j<i+4 && j<len-1; j++){
                for(int k = j+1; k<j+4 && k<len; k++){
                	// 依次截取四段串
                    String s1 = s.substring(0,i), s2 = s.substring(i,j), s3 = s.substring(j,k), s4 = s.substring(k,len);
                    if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
                        res.add(s1+"."+s2+"."+s3+"."+s4);
                    }
                }
            }
        }
        return res;
    }
	
	/**
	 * 验证网段是否正确，长度不能大于3和等于0，不能以0开头，转换到值不能大于255
	 * @param s
	 * @return
	 */
    public boolean isValid(String s){
        if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255)
            return false;
        return true;
    }
}
