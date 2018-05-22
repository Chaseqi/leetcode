package questions.leetcode.questions;

import java.util.ArrayList;
import java.util.List;

import chase.ParentTreeNode;

// https://www.jiuzhang.com/solution/binary-tree-path-sum-iii/
public class BinaryTreePathSum_III {
	
	public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
		List<List<Integer>> results = new ArrayList<>();
		dfs(root, target, results);
		
		return results;
	}
	
	private void dfs(ParentTreeNode root, int target, List<List<Integer>> results) {
		if (root == null) {
			return;
		}
		
		helper(root, null, target, new ArrayList<>(), results);
		dfs(root.left, target, results);
		dfs(root.right, target, results);
	}
	
	private void helper(ParentTreeNode root, ParentTreeNode prev, int target, List<Integer> result, List<List<Integer>> results) {
		result.add(root.val);
		target -= root.val;
		
		if (target == 0) {
			List<Integer> temp = new ArrayList<>(result);
			results.add(temp);
		}
		
		if (root.left != null && prev != root.left) {
			helper(root.left, root, target, result, results);
		}
		
		if (root.right != null && prev != root.right) {
			helper(root.right, root, target, result, results);
		}
		
		if (root.parent != null && prev != root.parent) {
			helper(root.parent, root, target, result, results);
		}
		
		result.remove(result.size() - 1);
	}
}
