package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.Map;

public class PreOrderAndPostOrderReconstructTree {
	
	// reconstruct tree with preorder and postorder traversal
	// You can assume there always exists a left subtree
	public TreeNode construct(int[] preorder, int[] postorder) {
		int n = preorder.length;
		Map<Integer, Integer> labelToIndex = new HashMap<>();
		
		for (int i = 0; i < postorder.length; i++) {
			labelToIndex.put(postorder[i], i);
		}
		
		return helper(preorder, 0, n - 1, postorder, 0, n - 1, labelToIndex);
	}
	
	private TreeNode helper(int[] preorder, int preorderLeft, int preorderRight, 
					        int[] postorder, int postorderLeft, int postorderRight, Map<Integer, Integer> labelToIndex) {
		if (preorderLeft > preorderRight) {
			return null;
		}
		
		TreeNode root = new TreeNode(preorder[0]);
		if (preorderLeft == preorderRight) {
			return root;
		}
		
		int leftChildIndex = labelToIndex.get(preorder[preorderLeft + 1]);
		int leftTreeSize = leftChildIndex - postorderLeft + 1;
		
		root.left = helper(preorder, preorderLeft + 1, preorderLeft + leftTreeSize, 
					       postorder, postorderLeft, leftChildIndex, labelToIndex);
		root.right = helper(preorder, preorderLeft + leftTreeSize + 1, preorderRight,
				            postorder, leftChildIndex + 1, postorderRight - 1, labelToIndex);
		return root;
	}
	
	private class TreeNode {
		TreeNode left;
		TreeNode right;
		int label;
		
		public TreeNode(int lable) {
			this.label = label;
		}
	}
}
