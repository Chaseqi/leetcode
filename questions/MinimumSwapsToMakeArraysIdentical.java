package questions.leetcode.questions;

import java.util.HashMap;
import java.util.Map;

// Given two arrays which have same values but in different order, 
// we need to make second array same as first array using minimum number of swaps.

// Input  : arrA[] = {3, 6, 4, 8}, 
// arrB[] = {4, 6, 8, 3}
// Output : 2
// we can make arrB to same as arrA in 2 swaps 
// which are shown below,
// swap 4 with 8,   arrB = {8, 6, 4, 3}
// swap 8 with 3,   arrB = {3, 6, 4, 8}

// https://www.geeksforgeeks.org/minimum-swaps-to-make-two-array-identical/
public class MinimumSwapsToMakeArraysIdentical {
	
	public static int minimumSwaps(int[] arrA, int[] arrB) {
		
		// generate a new array positions, where pos[i] stands for the index of arrB[i] in arrA
		int n = arrA.length;
		
		int[] positions = new int[n];
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			map.put(arrA[i], i);
		}
		
		for (int i = 0; i < n; i++) {
			positions[i] = map.get(arrB[i]);
		}
		
		// Get the minimum number of swaps to make positions[] sorted
		// Visualize the problem as a graph. There is an edge from i to j, if the element at index i is supposed to be put at index j
		int result = 0;
		
		boolean[] visited = new boolean[n];
		
		for (int i = 0; i < n; i++) {
			int count = 0;
			int index = i;
			
			while (!visited[index]) {
				visited[index] = true;
				index = positions[index];
				count++;
			}
			
			result += count - 1 > 0 ? count - 1 : 0;
		}
		
		return result;
	}
}
