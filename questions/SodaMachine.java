package questions.leetcode.questions;

import java.util.ArrayList;
import java.util.List;

// The vending machine has 3 buttons A, B and C.
// There is a range of volume of drink you will get. For example: A: 200 ml - 210 ml B: 500 ml - 510ml C: 700 ml - 710 ml
// You have a bottle that has a lower range and upper range. You could press any buttons many times, 
// but you have to make sure that once you got a soda you pour it into the bottle. 
// Makes sure that all the soda you got fall into the range of the bottle. 
// Find all the combinations of buttons to press that allow you to fill in your bottle
public class SodaMachine {
	
	public List<List<String>> getCombinations (Range[] ranges, int lowerBound, int upperBound) {
		List<List<String>> results = new ArrayList<>();
		List<String> result = new ArrayList<>();
		
		dfs(ranges, 0, 0, 0, lowerBound, upperBound, result, results);
		return results;
	}
	
	private void dfs(Range[] ranges, int start, int volumeLower, int volumeUpper, int lowerBound, int upperBound, 
					 List<String> result, List<List<String>> results) {
		
		if (volumeLower >= lowerBound && volumeUpper <= upperBound) {
			List<String> temp = new ArrayList<>(result);
			results.add(temp);
		}
		
		if (volumeUpper >= upperBound) {
			return;
		}
		
		for (int i = start; i < ranges.length; i++) {
			result.add(ranges[i].id);
			dfs(ranges, i, volumeLower + ranges[i].start, volumeUpper + ranges[i].end, lowerBound, upperBound, result, results);
			result.remove(result.size() - 1);
		}
	}
	
	private class Range {
		String id;
		int start;
		int end;
		
		public Range(String id, int start, int end) {
			this.id = id;
			this.start = start;
			this.end = end;
		}
	}
}
