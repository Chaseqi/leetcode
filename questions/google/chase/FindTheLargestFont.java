package questions.leetcode.questions.google.chase;

public class FindTheLargestFont {
	public int findLargestFont(String s, int height, int width, int largestFont, int smallestFont) {
		// trial and error algo
		int left = smallestFont;
		int right = largestFont;
		
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (canFit(s, height, width, mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		if (canFit(s, height, width, right)) {
			return right;
		}
		
		if (canFit(s, height, width, left)) {
			return left;
		}
		
		return -1;
	}
	
	private boolean canFit(String s, int height, int width, int font) {
		int currentHeight = 0;
		int currentWidth = 0;
		int currentIndex = 0;
		
		while (currentIndex < s.length() && currentHeight < height) {
			while (currentIndex < s.length() && currentWidth + getWidth(s.charAt(currentIndex), font)<= width) {
				currentWidth += getWidth(s.charAt(currentIndex++), font);
			}
			
			currentWidth = 0;
			currentHeight += getHeight(font);
		}
		
		return currentIndex == s.length();
	}
	
	// follow up to see if a sentence can fit on the screen
	private boolean canFitSentence(String s, int height, int width, int font) {
		String[] words = s.split(" ");
		
		int currentHeight = 0;
		int currentWidth = 0;
		int currentIndex = 0;
		
		while (currentIndex < words.length && currentHeight < height) {
			while (currentIndex < words.length && currentWidth + getWidth(words[currentIndex], font) <= width) {
				currentWidth += getWidth(words[currentIndex++], font) + getWidth(' ', font); // append an empty space
			}
			
			currentWidth = 0;
			currentHeight += getHeight(font);
		}
		
		return currentIndex == words.length;
	}
	
	private int getWidth(String s, int font) {
		int width = 0;
		for (char c : s.toCharArray()) {
			width += getWidth(c, font);
		}
		
		return width;
	}
	
	private int getHeight(int fontSize) {
		return 0;
	}
	
	private int getWidth(char c, int fontSize) {
		return 0;
	}
}
