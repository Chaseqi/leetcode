package questions.leetcode.questions;

// Given the preorder and postorder traversal of a tree to reconstruct a tree
// Assuming the each node always have a left substree
// Time Complexity: T(n) = T(n - 1) + 1  --> O(n)
public class PreorderAndPostOrderReconstructTree {
	
	public static TreeNode reconstructTree(int[] preorder, int[] postorder) {
		int n = preorder.length;
		
		TreeNode root = helper(preorder, 0, n - 1, postorder, 0, n - 1);
		return root;
	}
	
	private static TreeNode helper(int[] preorder, int left1, int right1, int[] postorder, int left2, int right2) {
		if (left1 > right1) {
			return null;
		}
		
		if (left1 == right1) {
			return new TreeNode(preorder[left1]);
		}
		
		TreeNode root = new TreeNode(preorder[left1]);
		int leftChild = preorder[left1 + 1];
		int index = left2;
		for (; index < right2; index++) {
			if (postorder[index] == leftChild) {
				break;
			}
		}
		
		int leftSize = index - left2 + 1;
		root.left = helper(preorder, left1 + 1, left1 + leftSize, postorder, left2, index);
		root.right = helper(preorder,  left1 + leftSize + 1, right1, postorder, index + 1, right2 - 1);
		
		return root;
	}
	
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
}
