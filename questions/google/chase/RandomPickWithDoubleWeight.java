package questions.leetcode.questions.google.chase;

import java.util.Arrays;
import java.util.Random;

public class RandomPickWithDoubleWeight {
	
	double[] prefixSum;
	Random random;
	
	public RandomPickWithDoubleWeight(double[] weights) {
		this.prefixSum = new double[weights.length + 1];
		
		for (int i = 1; i <= weights.length; i++) {
			prefixSum[i] = prefixSum[i - 1] + weights[i - 1];
		}
		
		this.random = new Random();
	}
	
	public int randomPick() {
		double randomNumber = random.nextDouble() * prefixSum[prefixSum.length - 1];
		// find the index of the largest number that is smaller than the randomNumber
		// binarySearch() returns -insertionPoint - 1
		return -(Arrays.binarySearch(prefixSum, randomNumber) + 1) - 1;
	}
	
	public static void main(String[] args) {
		RandomPickWithDoubleWeight rpdw = new RandomPickWithDoubleWeight(new double[]{0.5, 0.5});
		
		for (int i = 0; i < 20; i++) {
			System.out.println(rpdw.randomPick());
		}
	}
}
