package questions.leetcode.questions.google.chase;

public class TreeChecker {

	private boolean isFull(TreeNode root) {
		if (root == null) {
			return true;
		}
		
		if (root.left == null && root.right == null) {
			return true;
		}
		
		if (root.left != null && root.right != null) {
			return isFull(root.left) && isFull(root.right);
		}
		
		return false;
	}
	
	private boolean isComplete(TreeNode root) {
		int count = getCount(root);
		
		return dfs(root, 0, count);
	}
	
	private boolean dfs(TreeNode root, int index, int count) {
		if (root == null) {
			return true;
		}
		
		if (index >= count) {
			return false;
		}
		
		return dfs(root.left, index * 2 + 1, count) && dfs(root.right, index * 2 + 2, count);
	}
	
	private int getCount(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		return 1 + getCount(root.left) + getCount(root.right);
	}
	
	private class TreeNode {
		TreeNode left;
		TreeNode right;
	}
}
