package questions.leetcode.questions.google.chase;

// Give a word that matches regex B*A*C*, find out the number of As in the word
public class CountOfA {
	
	public static int solve(String word) {
		if (word == null) {
			return 0;
		}
		
		int n = word.length();
		if (n == 0) {
			return 0;
		}
		
		int left = 0;
		int right = n - 1;
		
		// find the leftmost A
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (word.charAt(mid) == 'B') {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		int leftEnd;
		if (word.charAt(left) == 'A') {
			leftEnd = left;
		} else if (word.charAt(right) == 'A') {
			leftEnd = right;
		} else {
			return 0;
		}
		
		left = 0;
		right = n - 1;
		
		while (left + 1 < right) {
			int mid = (left + right) / 2;
			if (word.charAt(mid) == 'C') {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		int rightEnd;
		if (word.charAt(right) == 'A') {
			rightEnd = right;
		} else {
			rightEnd = left;
		}
		
		return rightEnd - leftEnd + 1;
	}
	
	public static void main(String[] args) {
		System.out.println(solve("BBBACCC"));
	}
}
