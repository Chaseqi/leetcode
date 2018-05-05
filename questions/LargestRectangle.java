package questions.leetcode.questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Given a bunch of points, find the rectangle with the largest size.
// https://www.quora.com/From-a-given-set-of-points-how-would-you-find-the-set-of-4-points-forming-a-rectangle
public class LargestRectangle {
	public int getLargestRectangle(List<Node> nodes) {
		Map<Integer, Set<Integer>> map = new HashMap<>();
		List<Integer> nums = new ArrayList<>();
		for (Node node : nodes) {
			map.computeIfAbsent(node.x, k -> new HashSet<Integer>()).add(node.y);
			nums.add(node.x);
		}
		
		int n = nums.size();
		Collections.sort(nums);
		
		int result = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				
				int h = nums.get(j) - nums.get(i);
				Set<Integer> set1 = map.get(nums.get(i));
				Set<Integer> set2 = map.get(nums.get(j));
				
				int min = Integer.MAX_VALUE;
				int max = Integer.MIN_VALUE;
				
				for (int y : set1) {
					if (set2.contains(y)) {
						min = Math.min(min, y);
						max = Math.max(max, y);
					}
				}
				
				if (min != Integer.MAX_VALUE && max != Integer.MIN_VALUE) {
					result = Math.max(result, h * (max - min));
				}
			}
		}
		
		return result;
	}
	
	private class Node {
		int x;
		int y;
		
		public  Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
