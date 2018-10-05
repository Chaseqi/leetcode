package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CharacterSubstitution {
	
	// Given a substitute(a, b) API, which substitute the character a in source with b
	// Write a function to check if source can convert to target by calling substitute()
	public static boolean canSubstitue(String source, String target) {
		// Make sure the char in source is not mapped to multiple chars in target
		int n = source.length();
		Map<Character, Character> sourceCharToTargetChar = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			Character targetChar = sourceCharToTargetChar.get(source.charAt(i));
			
			if (targetChar != null && target.charAt(i) != targetChar) {
				return false;
			}
			
			sourceCharToTargetChar.put(source.charAt(i), target.charAt(i));
		}
		
		// check if there is a cycle in the graph
		Map<Character, Set<Character>> graph = new HashMap<>();
		for (int i = 0; i < n; i++) {
			graph.computeIfAbsent(source.charAt(i), k -> new HashSet<>()).add(target.charAt(i));
		}
		
		Set<Character> visited = new HashSet<>();
		for (int i = 0; i < n; i++) {
			char c = source.charAt(i);
			if (visited.contains(c)) {
				continue;
			}
			
			if (hasCircle(c, graph, visited)) {
				return false;
			}
		}
		
		return true;
	}
	
	private static boolean hasCircle(char start, Map<Character, Set<Character>> graph, Set<Character> visited) {
		if (visited.contains(start)) {
			return true;
		}
		
		visited.add(start);
		Set<Character> neighbors = graph.get(start);
		if (neighbors == null) {
			return false;
		}
		
		for (char next : neighbors) {
			if (hasCircle(next, graph, visited)) {
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(canSubstitue("abc", "def"));
		System.out.println(canSubstitue("abc", "bca"));
		System.out.println(canSubstitue("abc", "bca"));
		System.out.println(canSubstitue("abc", "ecb"));
	}
}
