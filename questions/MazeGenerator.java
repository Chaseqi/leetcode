package questions.leetcode.questions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
	
	private static Random rand = new Random();
	public Cell[][] make(int height, int width, int x, int y) {
		Cell[][] maze = new Cell[height][width];
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				maze[i][j] = new Cell();
			}
		}
		
		boolean[][] visited = new boolean[height][width];
		
		dfs(maze, visited, x, y);
		
		System.out.println("TOP BOTTOM LEFT RIGHT");
		for (Cell[] row : maze) {
			for (Cell cell : row) {
				System.out.println(cell);
			}
		}
		return maze;
	}
	
	private void dfs(Cell[][] maze, boolean[][] visited, int x, int y) {
		int n = maze.length;
		int m = maze[0].length;
		
		visited[x][y] = true;
		Direction[] directions = Direction.values();
		int startIndex = rand.nextInt(4);
		
		for (int i = 0; i < 4; i++) {
			
			Direction d = directions[(startIndex + i) % 4];
			int newX = x + d.x;
			int newY = y + d.y;
			
			if (inBound(newX, newY, n, m) && !visited[newX][newY]) {
				switch (d) {
					case UP:
						maze[x][y].top = false;
						maze[newX][newY].bottom = false;
						break;
					case DOWN:
						maze[x][y].bottom = false;
						maze[newX][newY].top = false;
						break;
					case LEFT:
						maze[x][y].left = false;
						maze[newX][newY].right = false;
						break;
					case RIGHT:
						maze[x][y].right = false;
						maze[newX][newY].left = false;
						break;
				}
						
				dfs(maze, visited, newX, newY);
			}
		}
	}
	
	private boolean inBound(int x, int y, int n, int m) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}
	
	private class Cell {
		// each boolean stands for if there is a wall in that direction
		// of the cell
		boolean top;
		boolean bottom;
		boolean left;
		boolean right;
		
		public Cell() {
			top = true; bottom = true; left = true; right = true;
		}
		
		@Override
		public String toString() {
			return top + " " + bottom + " " + left + " " + right;
		}
	}
	
	private enum Direction {
		UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);
		
		private int x;
		private int y;
		Direction(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}

