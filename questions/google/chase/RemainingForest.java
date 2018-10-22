package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Given a binary tree, where each node has a "left" and "right" pointers,  and a predicate shouldBeErased(Node n) function, output the forest (collection of trees) created by erasing the nodes indicated by shouldBeErased().
//Example:
//    F
//   / \
//  /   \  
//[B]    G 
/// \     \
//A   D   [I]
//   / \   / 
//   C  E H  
//
//In this example shouldBeErased() returns true for nodes B & I and false for the other nodes, the resulting forest is : [ A, D, F, H ]
//A  F    D   H
//   \   / \
//    G  C  E

public class RemainingForest {

	public static List<TreeNode> getRemainingForest(TreeNode root) {
		List<TreeNode> result = new ArrayList<>();
		if (root == null) {
			return result;
		}
		
		postOrder(root, result);
		if (!shouldBeErased(root)) {
			result.add(root);
		}
		
		return result;
	}
	
	private static TreeNode postOrder(TreeNode root, List<TreeNode> result) {
		if (root == null) {
			return root;
		}
		
		TreeNode leftResult = postOrder(root.left, result);
		TreeNode rightResult = postOrder(root.right, result);
		
		if (shouldBeErased(root)) {
			if (leftResult != null) {
				result.add(leftResult);
			}
			
			if (rightResult != null) {
				result.add(rightResult);
			}
			
			return null;
		}
		
		root.left = leftResult;
		root.right = rightResult;
		return root;
	}
	
	private static boolean shouldBeErased(TreeNode node) {
		Set<Character> set = new HashSet<>();
		set.add('B');
		set.add('I');
		set.add('H');
		set.add('F');
		if (set.contains(node.label)) {
			return true;
		}
		
		return false;
	}
	
	private static class TreeNode {
		char label;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(char label) {
			this.label = label;
		}
	}
	
	public static void main(String[] args) {
		TreeNode A = new TreeNode('A');
		TreeNode B = new TreeNode('B');
		TreeNode C = new TreeNode('C');
		TreeNode D = new TreeNode('D');
		TreeNode E = new TreeNode('E');
		TreeNode F = new TreeNode('F');
		TreeNode G = new TreeNode('G');
		TreeNode H = new TreeNode('H');
		TreeNode I = new TreeNode('I');
		
		F.left = B;
		F.right = G;
		B.left = A;
		B.right = D;
		G.right = I;
		D.left = C;
		D.right = E;
		I.left = H;
		
		List<TreeNode> result = getRemainingForest(F);
		for (TreeNode node : result) {
			System.out.print(node.label + " ");
		}
	}
}
