package questions.leetcode.questions.google.chase;

import java.util.Collections;
import java.util.List;

public class BeAMan {
	public int shortestPath(int x, int y, List<Deck> decks) {
		Collections.sort(decks, (d1, d2) -> {
			int diff = d2.y - d1.y;
			if (diff != 0) {
				return diff;
			}
			
			return d1.x - d2.x;
		});
		
		TreeNode root = new TreeNode(0);
		constructTree(root, x, y, decks);
		
		// find the shortest path from root to leaf node
		return dfs(root);
	}
	
	private int dfs(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		return root.weight + Math.min(dfs(root.left), dfs(root.right));
	}
	
	private void constructTree(TreeNode root, int x, int y, List<Deck> decks) {
		Deck nextDeck = findNextDeck(x, y, decks);
		
		if (nextDeck == null) {
			return;
		}
		
		root.left = new TreeNode(x - nextDeck.x);
		root.right = new TreeNode(nextDeck.x + nextDeck.length - x);
		
		constructTree(root.left, nextDeck.x, nextDeck.y, decks);
		constructTree(root.right, nextDeck.x + nextDeck.length, nextDeck.y, decks);
	}
	
	private Deck findNextDeck(int x, int y, List<Deck> decks) {
		for (Deck deck : decks) {
			if (deck.y < y && deck.x <= x && deck.x + deck.length >= x) {
				return deck;
			}
		}
		
		return null;
	}
	
	private class TreeNode {
		TreeNode left;
		TreeNode right;
		int weight;
		
		public TreeNode(int weight) {
			this.weight = weight;
		}
	}
	
	private class Deck {
		int x;
		int y;
		int length;
		
		public Deck(int x, int y, int length) {
			this.x = x;
			this.y = y;
			this.length = length;
		}
	}
}
