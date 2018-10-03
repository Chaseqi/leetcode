package questions.leetcode.questions.google.chase;

public class GreatestCommonFactorAndSmallestCommonMultiple {
	
	// highest common factor
	public static int hcf(int a, int b) {
		if (a == 0 || b == 0) {
			return a + b;
		}
		
		return hcf(b, a % b);
	}
	
	// lowest common multiple
	public static int lcm(int a, int b) {
		return a * b / hcf(a, b);
	}
	
	public static void main(String[] args) {
		System.out.println(hcf(18, 12));
		System.out.println(lcm(18, 12));
	}
}
