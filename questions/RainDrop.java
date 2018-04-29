package questions.leetcode.questions;

import java.util.Random;

// you have 1 meter walkroad, and randomly generate rain, the rain is 1 cm. simulate how many rain drops to cover all the 1 meter

public class RainDrop {
	
	private static Random random = new Random();
	public static int simulate() {
		int count = 0;
		int result = 0;
		Interval[] intervals = new Interval[101];
		
		for (int i = 0; i <= 100; i++) {
			intervals[i] = new Interval(i, i + 1);
		}
		
		while (true) {
			
			if (count == 100) {
				break;
			}
			
			double randomValue = 100 * random.nextDouble();
			double left = Math.max(0, randomValue - 0.5);
			double right = Math.min(100, randomValue + 0.5);
			
			Interval leftInterval = intervals[(int)left];
			Interval rightInterval = intervals[(int)right];
			
			if (leftInterval.start < leftInterval.end && left <= leftInterval.start) {
				count++;
			}
			
			leftInterval.end = left;
			
			if (rightInterval.start < rightInterval.end && right >= rightInterval.end) {
				count++;
			}
			
			rightInterval.start = right;
			
			result++;
		}
		
		return result;
	}
	
	private static class Interval {
		double start;
		double end;
		
		public Interval(double start, double end) {
			this.start = start;
			this.end = end;
		}
	}
}
