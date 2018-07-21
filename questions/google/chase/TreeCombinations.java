package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.List;

// a{1,2}b{3,4}d --> a1b3d, a1b4d, a2b3d, a2b4d
// http://www.1point3acres.com/bbs/thread-434363-1-1.html
public class TreeCombinations {
	public List<String> convert(String input) {
		List<String> result = new ArrayList<>();
		
		dfs(input, 0, "", result);
		
		return result;
	}
	
	private void dfs(String str, int index, String result, List<String> results) {
		if (index == str.length()) {
			results.add(result);
			return;
		}
		
		if (str.charAt(index) == '{') {
			int startIndex = index;
			while (str.charAt(index) != '}') {
				index++;
			}
			
			String[] nums = str.substring(startIndex + 1, index).split(",");
			
			for (String num : nums) {
				dfs(str, index + 1, result + num, results);
			}
		} else {
			dfs(str, index + 1, result + str.charAt(index), results);
		}
	}
	
	public static final void main(final String[] args) {
		TreeCombinations t = new TreeCombinations();
		List<String> result = t.convert("a{1,2}b{3,4}d");
		
		for (String str : result) {
			System.out.println(str);
		}
	}
}
