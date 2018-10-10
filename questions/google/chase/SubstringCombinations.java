package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class SubstringCombinations {

	// 给两个字母串 str1 = “ABABZ”, str2 = "ABZ", 判断str1能否用str2中的字符拼成，如果能，最少的步数是多少？例子，从str2中取“A”，“B”，“A”，“B”，“Z”一共五步，
	// 但如果取“AB”，“ABZ”只需要两步。follow up，如果str2中有duplicate，BFS shortest path问题
	public static int substringCombinationsWithoutDuplicate(String source, String target) {
		Map<Character, Integer> charToIndex = new HashMap<>();
		
		for (int i = 0; i < source.length(); i++) {
			charToIndex.put(source.charAt(i), i);
		}
		
		int prevIndex = -2;
		int count = 0;
		for (char c : target.toCharArray()) {
			Integer index = charToIndex.get(c);
			
			if (index == null) {
				// target has a character that is not in source
				return -1;
			}
			
			if (index != prevIndex + 1) {
				count++;
			}
			
			prevIndex = index;
		}
		
		return count;
	}
	
	public static int substringCombinationWithDuplicate(String source, String target) {
		Map<Character, Set<Integer>> charToIndexes = new HashMap<>();
		
		for (int i = 0; i < source.length(); i++) {
			charToIndexes.computeIfAbsent(source.charAt(i), k -> new HashSet<>()).add(i);
		}
		
		Set<Integer> prevIndexes = new HashSet<>();
		int count = 0;
		
		for (char c : target.toCharArray()) {
			Set<Integer> indexes = charToIndexes.get(c);
			
			if (indexes == null) {
				return -1;
			}
			
			Set<Integer> temp = new HashSet<>();
			for (int index : indexes) {
				if (prevIndexes.contains(index - 1)) {
					temp.add(index);
				}
			}
			
			if (temp.isEmpty()) {
				count++;
				prevIndexes = indexes;
			} else {
				prevIndexes = temp;
			}
		}
		
		return count;
	}
	
	public static void main(String[] args) {
		System.out.println(substringCombinationsWithoutDuplicate("BAZ", "BZABAZ"));
		System.out.println(substringCombinationWithDuplicate("AABZ", "AABA"));
	}
}
