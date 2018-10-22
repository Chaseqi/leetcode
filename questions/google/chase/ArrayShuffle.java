package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class ArrayShuffle {

	public int[] shuffleWithoutDuplicates(int[] arr1, int[] arr2, int[] arr3) {
		int n = arr1.length;
		Map<Integer, Integer> numToIndex = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			numToIndex.put(arr1[i], i);
		}
		
		int[] indices = new int[n];
		for (int i = 0; i < n; i++) {
			indices[i] = numToIndex.get(arr2[i]);
		}
		
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = arr3[indices[i]];
		}
		
		return result;
	}
	
	public int[] shuffleWithDuplicates(int[] arr1, int[] arr2, int[] arr3) {
		int n = arr1.length;
		Map<Integer, Queue<Integer>> numToIndices = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			numToIndices.computeIfAbsent(arr1[0], k -> new LinkedList<>()).add(i);
		}
		
		int[] indices = new int[n];
		for (int i = 0; i < n; i++) {
			indices[i] = numToIndices.get(arr2[i]).poll();
		}
		
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = arr3[indices[i]];
		}
		
		return result;
	}
}
