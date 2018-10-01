package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.Map;

public class MinimumPathSumTreeNode {

	static int result = Integer.MAX_VALUE;
	public static int minimumPathSum(TreeNode root) {
		Map<TreeNode, Integer> rootToChildCount = new HashMap<>();
		nodeCount(root, rootToChildCount);
		int rootPathSum = rootPathSum(root, 0);
		
		System.out.println(rootPathSum);
		
		pathSum(root, rootToChildCount, rootPathSum);
		return result;
	}
	
	private static int rootPathSum(TreeNode root, int level) {
		if (root == null) {
			return 0;
		}
		
		int result = level;
		result += rootPathSum(root.left, level + 1);
		result += rootPathSum(root.right, level + 1);
		
		return result;
	}
	
	private static int nodeCount(TreeNode root, Map<TreeNode, Integer> rootToChildCount) {
		if (root == null) {
			return 0;
		}
		
		int count = 1;
		count += nodeCount(root.left, rootToChildCount);
		count += nodeCount(root.right, rootToChildCount);
		
		rootToChildCount.put(root, count);
		return count;
	}
	
	private static void pathSum(TreeNode root, Map<TreeNode, Integer> rootToChildCount, int rootPathSum) {
		if (root == null) {
			return;
		}
		
		result = Math.min(result, rootPathSum);
		if (root.left != null) {
			pathSum(root.left, rootToChildCount, rootPathSum + rootToChildCount.size() - 2 * rootToChildCount.get(root.left));
		}
		
		if (root.right != null) {
			pathSum(root.right, rootToChildCount, rootPathSum + rootToChildCount.size() - 2 * rootToChildCount.get(root.right));
		}
	}
	
	private static class TreeNode {
		TreeNode left;
		TreeNode right;
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode();
		TreeNode left = new TreeNode();
		TreeNode right = new TreeNode();
		TreeNode leftLeft = new TreeNode();
		
		root.left = left;
		root.right = right;
		root.left.left = leftLeft;
		
		System.out.println(minimumPathSum(root));
	}
}
