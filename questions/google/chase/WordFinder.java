package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// Give a dictionary of words and a character stream, save the word whenever
// the characters output from the stream consist of a word in the dictionary.
public class WordFinder {

	public static List<String> findWords(List<String> dict, Stream stream) {
		List<String> results = new ArrayList<>();
		Trie trie = new Trie();
		int maxLength = 0;
		
		// add the reversed string to the dict
		for (String word : dict) {
			trie.insert(new StringBuilder(word).reverse().toString());
			maxLength = Math.max(maxLength, word.length());
		}
		
		List<Character> chars = new LinkedList<>();
		while (stream.hasNext()) {
			if (chars.size() == maxLength) {
				chars.remove(0);
			}
			
			chars.add(stream.getNext());
			
			// search backwards to see if any substring exists in the trie
			String temp = trie.search(chars);
			
			if (temp != null) {
				results.add(new StringBuilder(temp).reverse().toString());
				for (int i = 0; i < temp.length(); i++) {
					chars.remove(chars.size() - 1);
				}
			}
		}
		
		return results;
	}
	
	private static class Trie {
		TrieNode root = new TrieNode();
		
		public void insert(String word) {
			TrieNode prev = root;
			
			for (char c : word.toCharArray()) {
				TrieNode child = prev.children.get(c);
				
				if (child == null) {
					child = new TrieNode();
					prev.children.put(c, child);
				}
				
				prev = child;
			}
			
			prev.wordEnd = true;
		}
		
		public String search(List<Character> chars) {
			TrieNode prev = root;
			
			StringBuilder sb = new StringBuilder();
			for (int i = chars.size() - 1; i >= 0; i--) {
				TrieNode child = prev.children.get(chars.get(i));
				
				if (child == null) {
					return null;
				}
				
				sb.append(chars.get(i));
				if (child.wordEnd) {
					return sb.toString();
				}
				
				prev = child;
			}
			
			return null;
		}
	}
	
	private static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<>();
		boolean wordEnd = false;
	}
	
	private static class Stream {
		char[] chars;
		int currentIndex;
		
		public Stream(char[] chars) {
			this.chars = chars;
			this.currentIndex = 0;
		}
		
		public char getNext() {
			return chars[currentIndex++];
		}
		
		public boolean hasNext() {
			return currentIndex < chars.length;
		}
	}
	
	public static void main(String[] args) {
		Stream stream = new Stream(new char[]{'e','d','c','a','b','c','s','d','e','f'});
		List<String> words = new ArrayList<>();
		words.add("edcab");
		words.add("sdef");
		
		List<String> result = findWords(words, stream);
		
		for (String str : result) {
			System.out.println(str);
		}
	}
}
