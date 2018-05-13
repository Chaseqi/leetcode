package questions.leetcode.questions;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Bikes {
	/**
	 * Returns the location of nearest bike to the given person
	 * @param grid The grid
	 * @param x x-coordinate of the person
	 * @param y y-coordinate of the person
	 * @return the location of the bike
	 */
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
	public int[] getNearestBike(int[][] grid, int x, int y) {
		// bike -- 1
		// person -- 2
		// empty space -- 0
		int n = grid.length;
		int m = grid[0].length;
		
		Set<Integer> visitedPerson = new HashSet<>();
		Set<Node> visited = new HashSet<>();
		Queue<Node> queue = new LinkedList<>();
		
		// Add in the target person first, so we can make sure
		// he can get the bike when there is another person who is away from the bike the same distance
		queue.offer(new Node(x, y, x * n + y));
		visited.add(new Node(x, y, x * n + y));
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (grid[i][j] == 2 && i != x && j != y) {
					queue.offer(new Node(i, j, i * n + j));
					visited.add(new Node(i, j, i * n + j));
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (visitedPerson.contains(node.source)) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int newX = node.x + dirs[i][0];
				int newY = node.y + dirs[i][1];
				Node newNode = new Node(newX, newY, node.source);
				
				if (newX > 0 && newX < n && newY > 0 && newY < m && !visited.contains(newNode)) {
					
					if (grid[newX][newY] == 1) {
						visitedPerson.add(node.source);
						
						if (node.source == x * n + y) {
							int[] result = {newX, newY};
							return result;
						}
						
						grid[newX][newY] = 0;
					} 
					
					visited.add(newNode);
					queue.offer(newNode);
				}
			}
		}
		
		return new int[2];
	}
	
	private class Node {
		int x;
		int y;
		int source;
		
		public Node(int x, int y, int source) {
			this.x = x;
			this.y = y;
			this.source = source;
		}
		
		@Override
		public int hashCode() {
			return x + y + source;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj == this) {
				return true;
			}
			
			if (!(obj instanceof Node)) {
				return false;
			}
			
			Node node = (Node)obj;
			return this.x == node.x && this.y == node.y && this.source == node.source;
		}
	}
}
