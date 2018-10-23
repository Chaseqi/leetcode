package questions.leetcode.questions.google.chase;

import java.util.HashSet;
import java.util.Set;

public class TriangleSortedArray {
	public static int getMin(int[] arr) {
		return Math.min(arr[0], arr[arr.length - 1]);
	}
	
	public static boolean isValid(int[] arr) {
		if (arr == null || arr.length <= 2) {
			return false;
		}
		
		Set<Integer> nums = new HashSet<>();
		boolean increasing = false;
		boolean decreasing = false;
		
		for (int i = 0; i < arr.length - 1; i++) {
			if (nums.contains(arr[i])) {
				return false; // duplicate number
			}
			
			if (arr[i] < arr[i + 1]) {
				if (decreasing) {
					return false;
				}
				
				increasing = true;
			}
			
			if (arr[i] > arr[i + 1]) {
				if (!increasing) {
					return false;
				}
				
				decreasing = true;
			}
			
			nums.add(arr[i]);
		}
		
		return increasing && decreasing;
	}
	
	public static int getMax(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
				return arr[mid];
			}
			
			if (arr[mid] < arr[mid + 1]) {
				left = mid;
			} else {
				right = mid;
			}
		}
		
		return Math.max(arr[left], arr[right]);
	}
	
	public static void main(String[] args) {
		System.out.println(isValid(new int[]{1, 3, 2}));
		System.out.println(isValid(new int[]{1, 2, 3, 5, 4}));
		System.out.println(isValid(new int[]{}));
		System.out.println(isValid(new int[]{1}));
		System.out.println(isValid(new int[]{1, 2, 3, 4, 5}));
		System.out.println(isValid(new int[]{5, 4, 3, 2, 1}));
		System.out.println(isValid(new int[]{1, 3, 5, 2, 4}));
		System.out.println(isValid(new int[]{1, 2, 3, 2, 1}));
		System.out.println(getMax(new int[]{1, 2, 3, 5, 4}));
	}
}
