package questions.leetcode.questions.google.chase;

public class InorderTraversalNthElement {
	
	static int count = 0;
	static int result = -1;
	public static int getNthElement(TreeNode root, int n) {
		count = n;
		dfs(root);
		count = 0;
		return result;
	}
	
	private static void dfs(TreeNode root) {
		if (root == null) {
			return;
		}
		
		dfs(root.left);
		count--;
		
		if (count == 0) {
			result = root.val;
		}
		dfs(root.right);
	}
	
	private static int dfs(TreeNode root, int n) {
		int leftSize = root.left == null ? 0 : root.left.countOfDecendents + 1;
		if (leftSize == n - 1) {
			return root.val;
		}
		
		if (leftSize > n - 1) {
			return dfs(root.left, n - 1);
		}
		
		return dfs(root.right, n - leftSize - 1);
	}
	
	private static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		int countOfDecendents;
		
		public TreeNode(int val) {
			this.val = val;
		}
	}
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		root.left = new TreeNode(1);
		root.right = new TreeNode(3);
		
		System.out.println(getNthElement(root, 1));
		System.out.println(getNthElement(root, 2));
		System.out.println(getNthElement(root, 3));
	}
}
