package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// The Robot can only move to right, top right and bottom right, three directions
// Find the number of ways to move from [0, 0] to [0, m]
// You will have go through the list of positions
public class UniquePathHard {
	public static int countOfUniquePath(int n, int m, List<int[]> postions) {
		Map<Integer, Integer> colToRow = new HashMap<>();
		
		for (int[] position : postions) {
			Integer row = colToRow.get(position[1]);
			if (row != null) {
				return 0;
			}
			
			colToRow.put(position[1], position[0]);
		}
		
		int[][] dp = new int[n][m];
		
		dp[0][0] = 1;
		
		for (int i = 1; i < m; i++) {
			Integer row = colToRow.get(i);
			
			if (row != null) {
				dp[row][i] = dp[row][i - 1] + 
						    (row > 0 ? dp[row - 1][i - 1] : 0) + 
						    (row < n - 1 ? dp[row + 1][i - 1] : 0);
				continue;
			}
			
			for (int j = 0; j < n; j++) {
				dp[j][i] = dp[j][i - 1];
				
				if (j > 0) {
					dp[j][i] += dp[j - 1][i - 1];
				}
				
				if (j < n - 1) {
					dp[j][i] += dp[j + 1][i - 1];
				}
			}
		}
		
		// follow up: what if the robot has to go under ith row
		return dp[0][m - 1];
	}
	
	public static void main(String[] args) {
		List<int[]> positions = new ArrayList<>();
//		positions.add(new int[]{1, 1});
		System.out.println(countOfUniquePath(3, 3, positions));
	}
}
