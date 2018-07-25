package questions.leetcode.questions.google.chase;

import java.util.LinkedList;
import java.util.Queue;

import chase.TreeNode;

// Construct a complete BST from a sorted array.
public class ConvertSortedArrayToCompleteBST {
	static int index = 0;
	
	public static TreeNode solve(int[] arr) {
		int n = arr.length;
		TreeNode root = new TreeNode(0);
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		n--;
		
		while(n > 0) {
			TreeNode node = queue.poll();
			
			node.left = new TreeNode(0);
			queue.offer(node.left);
			n--;
			
			if (n > 0) {
				node.right = new TreeNode(0);
				queue.offer(node.right);
				n--;
			}
		}
		
		inorder(arr, root);
		return root;
	}
	
	private static void inorder(int[] arr, TreeNode root) {
		if (root == null) {
			return;
		}
		
		inorder(arr, root.left);
		root.val = arr[index++];
		inorder(arr, root.right);
	}
	
	public static void main(String[] main) {
		int[] input = {1, 2, 3, 4, 5, 6};
		TreeNode root = solve(input);
		System.out.println(TreeNode.serialize(root));
	}
}
