package questions.leetcode.questions.google.chase;

// Two players alternatively pick 1-3 cards from a deck of cards
// Find out the highest score
public class PickCards {
	
	// dp[i] is the max score the player can get when pick from ith card
	public static int maxScore(int[] cards) {
		if (cards == null || cards.length == 0) {
			return 0;
		}
		
		int n = cards.length;
		int[] prefixSum = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			prefixSum[i] = prefixSum[i - 1] + cards[i - 1];
		}
		
		int[] dp = new int[n + 1];
		
		for (int i = n - 1; i >= 0; i--) {
			dp[i] = Integer.MIN_VALUE;
			for (int pick = i; pick < Math.min(n, i + 3); pick++) {
				int max = prefixSum[pick + 1] - prefixSum[i] + // the score this round the player gets
						  prefixSum[n] - prefixSum[pick + 1] - dp[pick + 1]; // the max score this player can get from the rest of cards
				dp[i] = Math.max(dp[i], max);
			}
		}
		
		return Math.max(dp[0], prefixSum[n] - dp[0]);
	}
	
	public static void main(String[] args) {
		int[] cards = {-1, 2, 3, 4, -2, 5};
		
		System.out.println(maxScore(cards));
	}
}
