package questions.leetcode.questions;

import java.util.List;

public class InterleavingStrings {
	@SuppressWarnings("unused")
	private void helper(String str1, String str2, int index1, int index2, String result, List<String> results) {
		if (index1 == str1.length() && index2 == str2.length()) {
			results.add(new String(result));
			return;
		}
		
		if (index1 == str1.length()) {
			helper(str1, str2, index1, index2 + 1, result + str2.charAt(index2), results);
		} else if (index2 == str2.length()) {
			helper(str1, str2, index1 + 1, index2, result + str1.charAt(index1), results);
		} else {
			helper(str1, str2, index1 + 1, index2, result + str1.charAt(index1), results);
			helper(str1, str2, index1, index2 + 1, result + str2.charAt(index2), results);
		}
	}
}
