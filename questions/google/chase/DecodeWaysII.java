package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.List;

// You can decode A -> 1, B -> 2, ... Z -> 26.
// Given an encoded string that only contains digit, output all of the decode strings
public class DecodeWaysII {

	public static List<String> decode(String encode) {
		List<String> result = new ArrayList<>();
		
		if (encode == null || encode.length() == 0) {
			return result;
		}
		
		dfs(0, encode, "", result);
		return result;
	}
	
	private static void dfs(int start, String encode, String result, List<String> results) {
		if (start == encode.length()) {
			results.add(result);
			return;
		}
		
		char c = encode.charAt(start);
		if (c == '0') {
			return;
		}
		
		dfs(start + 1, encode, result + (char)(c - '1' + 'A'), results);
		
		if (start == encode.length() - 1) {
			return;
		}
		
		int temp = Integer.valueOf(encode.substring(start, start + 2));
		if (temp <= 26) {
			dfs(start + 2, encode, result + (char)(temp - 1 + 'A'), results);
		}
	}
	
	public static void main(String[] args) {
		List<String> result = decode("1010");
		
		for (String str : result) {
			System.out.print(str + " ");
		}
		
		System.out.println();
		result = decode("12");
		
		for (String str : result) {
			System.out.print(str + " ");
		}
		
		System.out.println();
		result = decode("226");
		for (String str : result) {
			System.out.print(str + " ");
		}
	}
}
