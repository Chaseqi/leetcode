package questions.leetcode.questions.google.chase;

import java.util.Arrays;

// 一个map分为n层，每层都m个node， 每个node有值，每一层跟下一层的node是full connected，edge的值不一定相同，求从第一层到最后一层的mini cost DP解决，
// 也是高频题（区别的需要自己定义input data）我最后写的是 一维DP
// http://www.1point3acres.com/bbs/thread-434363-1-1.html
public class MLevelMap {
	// input[i][j][k] the length of the edge from the jth element on row i to kth element on row i + 1 
	public static int minCost(int[][][] input) {
		int n = input.length + 1;
		int m = input[0].length;
		
		int[][] dp = new int[n][m];
		for (int i = 1; i < n; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}
		
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < m; k++) {
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + input[i - 1][k][j]);
				}
			}
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < m; i++) {
			result = Math.min(result, dp[n - 1][i]);
		}
		
		return result;
	}
	
	public static void main(final String[] args) {
		int[][][] input = {{{1, 2, 3}, {3, 4, 5},{5, 6, 7}}, {{1, 2, 3}, {3, 4, 5},{5, 6, 7}}};
		
		System.out.println(minCost(input));
	}
}
