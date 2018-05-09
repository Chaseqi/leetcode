package questions.leetcode.questions;

// Given the preorder and postorder traversal of a tree to reconstruct a tree
// Assuming the each node always have a left substree
// Time Complexity: T(n) = T(n - 1) + 1  --> O(n)
public class PreorderAndPostOrderReconstructTree {
	
	public TreeNode reconstructTree(int[] preorder, int[] postorder) {
		int n = preorder.length;
		
		return helper(preorder, 0, n, postorder, 0, n);
	}
	
	private TreeNode helper(int[] preorder, int left1, int right1, int[] postorder, int left2, int right2) {
		if (left1 > right1) {
			return null;
		}
		
		if (left1 == right1) {
			return new TreeNode(preorder[left1]);
		}
		
		TreeNode root = new TreeNode(preorder[left1]);
		root.left = helper(preorder, left1 + 1, right1, postorder, left2, right2 - 1);
		
		return root;
	}
	
	private class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
}
