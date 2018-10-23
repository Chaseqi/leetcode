package questions.leetcode.questions.google.chase;

import chase.Solution;

// A worrier starts from top left and tries to reach the bottom left corner
// negative cell means it needs cost worrier that much health, positive cell mean the worrier can restore that much health
// find out the the minimum initial health the worrier needs to reach the destination

// 

//-10    5      0
//-20    4    -20     ---> 22    -10 -> 5 -> 4 -> -20 -> 0
// 50    -20    0

public class WorrierGo {
	public static int solve(int[][] board) {
		int n = board.length;
		int m = board[0].length;
		
		int[][] dp = new int[n][m];
		
		dp[n - 1][m - 1] = 1;
		
		// last row
		for (int i = m - 2; i >= 0; i--) {
			dp[n - 1][i] = dp[n - 1][i + 1] - board[n - 1][i];
			if (dp[n - 1][i] < 0) {
				dp[n - 1][i] = 1;
			}
		}
		
		// last column
		for (int i = n - 2; i >= 0; i--) {
			dp[i][m - 1] = dp[i + 1][m - 1] - board[i][m - 1];
			if (dp[i][m - 1] < 0) {
				dp[i][m - 1] = 1;
			}
		}
		
		for (int i = n - 2; i >= 0; i--) {
			for (int j = m - 2; j >= 0; j--) {
				dp[i][j] = Math.min(dp[i][j + 1], dp[i + 1][j]) - board[i][j];
				
				if (dp[i][j] < 0) {
					dp[i][j] = 1;
				}
			}
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(dp[i][j] + " ");
			}
			
			System.out.println();
		}
		
		return dp[0][0];
	}
	
	public static void main(String[] args) {
		System.out.println(solve(new int[][]{{-10, 5, 0}, {-20, 4, -20}, {50, -20, 0}}));
	}
}
