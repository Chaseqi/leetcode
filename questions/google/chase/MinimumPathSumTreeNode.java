package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.Map;

public class MinimumPathSumTreeNode {

	int result = Integer.MAX_VALUE;
	public int minimumPathSum(TreeNode root) {
		Map<TreeNode, Integer> rootToChildCount = new HashMap<>();
		nodeCount(root, rootToChildCount);
		int rootPathSum = rootPathSum(root, 0);
		
		pathSum(root, rootToChildCount, rootPathSum);
		return result;
	}
	
	private int rootPathSum(TreeNode root, int level) {
		if (root == null) {
			return 0;
		}
		
		int result = level;
		result += rootPathSum(root.left, level + 1);
		result += rootPathSum(root.right, level + 1);
		
		return result;
	}
	
	private int nodeCount(TreeNode root, Map<TreeNode, Integer> rootToChildCount) {
		if (root == null) {
			return 0;
		}
		
		int count = 1;
		count += nodeCount(root.left, rootToChildCount);
		count += nodeCount(root.right, rootToChildCount);
		
		rootToChildCount.put(root, count);
		return count;
	}
	
	private void pathSum(TreeNode root, Map<TreeNode, Integer> rootToChildCount, int rootPathSum) {
		result = Math.min(result, rootPathSum);
		
		pathSum(root.left, rootToChildCount, rootPathSum + rootToChildCount.size() - 2 * rootToChildCount.get(root.left));
		pathSum(root.right, rootToChildCount, rootPathSum + rootToChildCount.size() - 2 * rootToChildCount.get(root.right));
	}
	
	private class TreeNode {
		TreeNode left;
		TreeNode right;
	}
}
