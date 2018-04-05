package questions.leetcode.questions;

import java.util.HashSet;
import java.util.Set;

// Write code for a robot cleaner algorithm with 4 given APIs and an unknown starting position in an unknown space (with obstacles in random locations)
//
//  The 4 APIs are:
//
//     clean(): clean the current location.
//
//     turnleft(k): turn left k*90 degrees.
//
//     turnright(k): turn right k*90 degrees.
//
//     move(): move forward for 1 position and return true, otherwise return false if that’s not possible.
//
// How do you clean the entire space?

public class CleaningRobot {
	
	// up -> right -> down -> left 
	// clock wise
	int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
	
	public void clean(int x, int y, int d) {
		Set<Coordinate> visited = new HashSet<>();
		dfs(x, y, d, visited);
	}
	
	// use the index in the directions array to represent the directions
	private void dfs(int x, int y, int d, Set<Coordinate> visited) {
		visited.add(new Coordinate(x, y));
		clean();
		
		for (int i = 0; i <= 3; i++) {
			
			int[] newDirection = directions[(d + i) % 4];
			int newX = x + newDirection[0];
			int newY = y + newDirection[1];
			
			if (!visited.contains(new Coordinate(newX, newY)) && move()) {
				dfs(newX, newY, (d + i) % 4, visited);
			}
			
			turnLeft(1);
		}
		
		// go back to the previous location
		goBack();
	}
	
	private void goBack() {
		turnLeft(2);
		move();
		turnLeft(2);
	}
	
	private void clean() {}
	
	private void turnLeft(int k) {}
	
	private void turnRight(int k) {}
	
	private boolean move() {
		return true;
	}
	
	private class Coordinate {
		int x;
		int y;
		
		public Coordinate(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			
			if (!(obj instanceof Coordinate)) {
				return false;
			}
			
			Coordinate temp = (Coordinate) obj;
			return this.x == temp.x && this.y == temp.y;
		}
		
		@Override
		public int hashCode() {
			return x + y;
		}
	}
}
