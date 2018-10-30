package com.qk.myleetcode.medium;

import org.junit.Test;

/**
 * 
 * 12.Integer To Roman
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
 * @Description : 
 * ---------------------------------
 * @Author : huihui
 * @Date : Create in 2018年10月30日
 */
public class IntegerToRoman {

	@Test
	public void MyTest() {
		// leetcode test
		System.out.println(intToRoman(3));
		System.out.println(intToRoman(4));
		System.out.println(intToRoman(9));
		System.out.println(intToRoman(58));
		System.out.println(intToRoman(1994));
		System.out.println(intToRoman(1676));
	}
	
	// 提前存好整数和罗马数字的对应，包括各种情况
    private static final int[] INTEGERS = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMANS = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    
    /**
     * 思路：通过提前提取出各种情况，然后通过循环遍历（代码简洁，比较容易理解）
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        if (num <= 0) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        // 从大到小往后遍历
        for (int i = 0; i < INTEGERS.length; i++) {
            // 一直想见，处理罗马数字出现多次的情况，如II
        	while (num >= INTEGERS[i]) {
                sb.append(ROMANS[i]);
                num -= INTEGERS[i];
            }
        }
        
        return sb.toString();
    }

	/**
	 * 思路：自己的思路，通过判断各种情况（优点：写起来简单，易于理解；缺点：代码冗余）
	 * @param num
	 * @return
	 */
	public String intToRomanByMe(int num) {

		StringBuilder sBuilder = new StringBuilder();

		int temp = num;

		while (temp != 0) {
			// 处理1000的情况
			if (temp / 1000 != 0) {
				for (int i = 0; i < temp / 1000; i++) {
					sBuilder.append('M');
				}
				temp %= 1000;
			}

			// 处理百的情况（两种400 900）
			int hundred = temp / 100;
			if (hundred != 0) {
				// 900
				if (hundred == 9) {
					sBuilder.append("CM");
				}
				// 500-900
				if (hundred >= 5 && hundred < 9) {
					sBuilder.append('D');
					for (int i = 0; i < hundred - 5; i++) {
						sBuilder.append('C');
					}
				}
				// 400
				if (hundred == 4) {
					sBuilder.append("CD");
				}
				if (hundred > 0 && hundred < 4) {
					for (int i = 0; i < hundred; i++) {
						sBuilder.append('C');
					}
				}

				temp %= 100;
			}
			
			// 处理<100的情况
			int lessHundred = temp / 10;
			if(lessHundred != 0) {
				// 90
				if(lessHundred == 9) {
					sBuilder.append("XC");
				}
				
				// >= 50 && < 90
				if(lessHundred >= 5 && lessHundred < 9) {
					sBuilder.append('L');
					for (int i = 0; i < lessHundred - 5; i++) {
						sBuilder.append('X');
					}
				}
				// 40
				if(lessHundred == 4) {
					sBuilder.append("XL");
				}
				
				// >10 && <40
				if(lessHundred >0 && lessHundred < 4) {
					for (int i = 0; i < lessHundred; i++) {
						sBuilder.append('X');
					}
				}
				
				temp %= 10;
			}
			
			if(temp != 0) {
				// 9
				if(temp == 9) {
					sBuilder.append("IX");
				}
				
				// >= 5 && < 9
				if(temp >= 5 && temp < 9) {
					sBuilder.append('V');
					for (int i = 0; i < temp - 5; i++) {
						sBuilder.append('I');
					}
				}
				// 4
				if(temp == 4) {
					sBuilder.append("IV");
				}
				
				// >1 && <4
				if(temp >0 && temp < 4) {
					for (int i = 0; i < temp; i++) {
						sBuilder.append('I');
					}
				}
				
				temp = 0;
			}
		}

		return sBuilder.toString();
	}
}
