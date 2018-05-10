package questions.leetcode.questions;

import java.util.ArrayList;
import java.util.List;

public class FindMultipleTargetsInCharsStream {
	
	public List<String> findTargets(List<String> dict, char[] chars) {
		Trie trie = new Trie();
		
		for (String word : dict) {
			trie.insert(word);
		}
		
		List<String> result = new ArrayList<>();
		List<TrieNode> nodes = new ArrayList<>();
		nodes.add(trie.root);
		
		for (char c : chars) {
			int index = c - 'a';
			
			List<TrieNode> temp = new ArrayList<>();
			for (TrieNode node : nodes) {
				if (node.children[index] != null) {
					
					if (node.children[index].wordEnd) {
						result.add(node.children[index].word);
					}
					temp.add(node.children[index]);
				}
			}
			
			temp.add(trie.root);
			nodes = temp;
		}
		
		return result;
	}
	
	private class Trie {
		TrieNode root = new TrieNode();
		
		public void insert(String str) {
			char[] chars = str.toCharArray();
			TrieNode prev = root;
			
			for (char c : chars) {
				int index = c - 'a';
				
				if (prev.children[index] == null) {
					prev.children[index] = new TrieNode();
				}
				
				prev = prev.children[index];
			}
			
			prev.wordEnd = true;
			prev.word = str;
		}
	}
	
	private class TrieNode {
		TrieNode[] children;
		boolean wordEnd;
		String word;
		
		public TrieNode() {
			children = new TrieNode[26];
		}
	}
}
