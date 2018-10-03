package questions.leetcode.questions.google.chase;

public class JumpStairs {

	/**
	 * There are n stairs in total and each round you can jump up to k stairs
	 * Find out how ways there are to jump to the top.
	 * @param n the total number of stairs
	 * @param k the maximum number of stairs for each jump.
	 * @return the number of ways to jump to the top.
	 */
	public static int jumpWays(int n, int k) {
		int[] dp = new int[Math.min(n, k)];
		
		dp[0] = 1;
		int sum = 1; // the total ways to jump the previous k stairs
		
		// dp[i] the number of ways to jump to the ith stair
		// dp[i] in order to get the value of dp[i], we only need to the previous k elements in the dp array
		for (int i = 1; i <= n; i++) {
			int countToSubstract = dp[i % dp.length];
			dp[i % dp.length] = sum;
			sum -= countToSubstract;
			sum += dp[i % dp.length];
		}
		
		return dp[n % dp.length];
	}
	
	public static void main(String[] args) {
		System.out.println(jumpWays(2, 4));
	}
}
