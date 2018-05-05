package questions.leetcode.questions;

// 3,3,2,2,6,6,1,9,9,0,0 让找到1，logN时间
public class FindSingleNumber {
	
	public int find(int[] arr) {
		int n = arr.length;
		if (n == 1) {
			return 0; 
		}
		
		int left = 0;
		int right = arr.length - 1;
		
		// At least, there are three elements
		while (left + 2 < right) {
			int mid = (left + right) / 2;
			
			if (arr[mid] != arr[mid - 1] && arr[mid] != arr[mid + 1]) {
				return mid;
			}
			
			// first element in the pair.
			if (arr[mid] != arr[mid - 1]) {
				if (mid % 2 == 0) {
					left = mid;
				} else {
					right = mid;
				}
			} else {
				// second element in the pair
				if (mid % 2 == 1) {
					left = mid;
				} else {
					right = mid;
				}
			}
		}
		
		if (arr[(left + right) / 2] != arr[left] && arr[(left + right) /2] != arr[right]) {
			return (left + right) / 2;
		}
		
		if (arr[left] != arr[left + 1]) {
			return left;
		}
		
		return right;
	}
}
