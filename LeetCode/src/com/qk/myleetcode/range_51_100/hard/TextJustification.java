package com.qk.myleetcode.range_51_100.hard;

import java.util.ArrayList;
import java.util.List;

/**
 *   	给定一个单词数组和一个宽度maxWidth，格式化文本，使每行具有正确的maxWidth字符，并且完全（左和右）对齐。
	 你应该用贪婪的方式收拾你的话; 也就是说，在每一行中包装尽可能多的单词。 必要时填充额外的空格，以便每行具有正确的maxWidth字符。
	 单词之间的额外空格应尽可能均匀分布。 如果一行上的空格数不均匀分配，则左侧的空插槽将分配比右侧插槽更多的空格。
	 对于最后一行文本，它应该是左对齐的，并且在单词之间不插入额外的空格。
 * @Description : 【没有看懂】
 * @Author : huihui
 * @Date : Create in 2019年3月14日
 */
public class TextJustification {

	public List<String> fullJustify(String[] words, int L) {
		List<String> lines = new ArrayList<String>();

		int index = 0;
		while (index < words.length) {
			int count = words[index].length();
			int last = index + 1;
			while (last < words.length) {
				if (words[last].length() + count + 1 > L)
					break;
				count += words[last].length() + 1;
				last++;
			}

			StringBuilder builder = new StringBuilder();
			int diff = last - index - 1;
			// if last line or number of words in the line is 1, left-justified
			if (last == words.length || diff == 0) {
				for (int i = index; i < last; i++) {
					builder.append(words[i] + " ");
				}
				builder.deleteCharAt(builder.length() - 1);
				for (int i = builder.length(); i < L; i++) {
					builder.append(" ");
				}
			} else {
				// middle justified
				int spaces = (L - count) / diff;
				int r = (L - count) % diff;
				for (int i = index; i < last; i++) {
					builder.append(words[i]);
					if (i < last - 1) {
						for (int j = 0; j <= (spaces + ((i - index) < r ? 1 : 0)); j++) {
							builder.append(" ");
						}
					}
				}
			}
			lines.add(builder.toString());
			index = last;
		}

		return lines;
	}
}
