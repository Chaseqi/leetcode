package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

// Given a 2d array, you can remove the 1, when the row or the col that contains that 1 have multiple 1s.
// Find a way to remove extra ones so that we can have minimum number of 1s left when we reach a safe state. 
public class RemoveExtraOnes {

	// Returns the minimum number of 1s left when we reach a safe state
	public static int remove(int[][] board) {
		int n = board.length;
		int m = board[0].length;
		
		int total = 0;
		Map<Integer, Set<Integer>> rowToOneIndexes = new HashMap<>();
		Map<Integer, Set<Integer>> colToOneIndexes = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				
				total++;
				rowToOneIndexes.computeIfAbsent(i, k -> new HashSet<>()).add(j);
				colToOneIndexes.computeIfAbsent(j, k -> new HashSet<>()).add(i);
			}
		}
		
		PriorityQueue<int[]> queue = new PriorityQueue<>((k1, k2) -> k1[2] - k2[2]);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 0) {
					continue;
				}
				
				int conflicts = rowToOneIndexes.get(i).size() + colToOneIndexes.get(j).size() - 1;
				if (conflicts > 1) {
					queue.offer(new int[]{i, j, conflicts});
				}
			}
		}
		
		while (queue.peek()[2] > 1) {
			int[] arr = queue.poll();
			
			int row = arr[0];
			int col = arr[1];
			
			if (board[row][col] == 0) {
				continue;
			}
			
			System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
			total--;
			board[row][col] = 0;
			rowToOneIndexes.get(row).remove(col);
			colToOneIndexes.get(col).remove(row);
			
			for (int i : rowToOneIndexes.get(row)) {
				queue.offer(new int[]{row, i, rowToOneIndexes.get(row).size() + colToOneIndexes.get(i).size() - 1});
			}
			
			for (int i : colToOneIndexes.get(col)) {
				queue.offer(new int[]{i, col, rowToOneIndexes.get(i).size() + colToOneIndexes.get(col).size() - 1});
			}
		}
		
		return total;
	}
	
	public static void main(String[] args) {
		int[][] grid = {{1, 0, 1, 1}, {0, 1, 1, 0}, {1, 0, 1, 1}, {0, 0, 1, 0}};
		System.out.println(remove(grid));
	}
}
