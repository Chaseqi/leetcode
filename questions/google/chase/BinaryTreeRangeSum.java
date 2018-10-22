package questions.leetcode.questions.google.chase;

import chase.TreeNode;

public class BinaryTreeRangeSum {

	public int rangeSum(TreeNode root, int low, int high) {
		if (root == null) {
			return 0;
		}
		
		if (root.val >= low && root.val <= high) {
			return root.val + rangeSum(root.left, low, high) + rangeSum(root.right, low, high);
		}
		
		if (root.val < low) {
			return rangeSum(root.right, low, high);
		} 
		
		return rangeSum(root.left, low, high);
	}
}
