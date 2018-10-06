package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

// Maze Generator 1 is path and O is the wall
public class MazeGenerator {

	private static int[][] dirs = {{0, 2}, {2, 0}, {0, -2}, {-2, 0}};
	private static Random random = new Random();
	public static int[][] generate(int height, int width, int x, int y) {
		int[][] maze = new int[height][width];
		
		Set<Integer> visited = new HashSet<>();
		
		dfs(maze, x, y, visited, null);
		return maze;
	} 
	
	private static void dfs(int[][] maze, int x, int y, Set<Integer> visited, int[] dir) {
		if (x >= maze.length || x < 0 || y >= maze[0].length || y < 0 || visited.contains(maze[0].length * x + y)) {
			return;
		}
		
		maze[x][y] = 1;
		// remove the wall
		if (dir != null) {
			maze[x - dir[0] / 2][y - dir[1] / 2] = 1;
		}
		
		visited.add(maze[0].length * x + y);
		int index = random.nextInt(4);
		for (int i = 0; i < 4; i++) {
			int newX = x + dirs[(index + i) % 4][0];
			int newY = y + dirs[(index + i) % 4][1];
			
			dfs(maze, newX, newY, visited, dirs[(index + i) % 4]);
		}
	}
	
	public static int[][] primGenerate(int height, int width, int x, int y) {
		int[][] maze = new int[height][width];
		List<int[]> walls = new ArrayList<>();
		
		maze[x][y] = 1;
		for (int i = 0; i < 4; i++) {
			int newX = x + dirs[i][0];
			int newY = y + dirs[i][1];
			
			if (newX >= 0 && newX < height && newY >= 0 && newY < width) {
				walls.add(new int[]{x, y, newX, newY});
			}
		}
		
		while (!walls.isEmpty()) {
			int index = random.nextInt(walls.size());
			int[] wall = walls.get(index);
			
			if (maze[wall[0]][wall[1]] + maze[wall[2]][wall[3]] == 1) {
				// remove the wall
				maze[(wall[0] + wall[2]) / 2][(wall[1] + wall[3]) / 2] = 1;
				maze[wall[2]][wall[3]] = 1;
				
				for (int i = 0; i < 4; i++) {
					int newX = wall[2] + dirs[i][0];
					int newY = wall[3] + dirs[i][1];
					
					if (newX >= 0 && newX < height && newY >= 0 && newY < width && maze[newX][newY] == 0) {
						walls.add(new int[]{wall[2], wall[3], newX, newY});
					}
				}
			}
			
			walls.remove(index);
		}
		
		return maze;
	}
	
	public static void main(String[] args) {
		int[][] maze = primGenerate(9, 9, 0, 0);
		
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[0].length; j++) {
				System.out.print(maze[i][j]);
			}
			
			System.out.println();
		}
	}
}
