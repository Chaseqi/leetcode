package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.List;

// Given the number of leaf nodes, find all the possible full binary trees
public class FullBinaryTrees {
	
	public static List<TreeNode> findFBT(int n) {
		List<TreeNode> result = new ArrayList<>();
		
		if (n == 1) {
			result.add(new TreeNode());
			return result;
		}
		
		for (int i = 1; i < n; i++) {
			List<TreeNode> leftChildren = findFBT(i);
			List<TreeNode> rightChildren = findFBT(n - i);
			
			for (TreeNode leftChild : leftChildren) {
				for (TreeNode rightChild : rightChildren) {
					TreeNode root = new TreeNode();
					
					root.left = leftChild;
					root.right = rightChild;
					result.add(root);
				}
			}
		}
		
		return result;
	}
	
	private static class TreeNode {
		TreeNode left;
		TreeNode right;
	}
}
