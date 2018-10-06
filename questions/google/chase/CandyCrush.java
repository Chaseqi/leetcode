package questions.leetcode.questions.google.chase;

import java.util.Random;

public class CandyCrush {
	
	int[][] board;
	Random random;
	
	public CandyCrush(int n, int m) {
		this.board = new int[n][m];
		this.random = new Random();
	} 
	
	// initialize the board with three numbers, 1, 2, 3
	// make the initial board randomized
	// make sure there is at least 1 swap
	public void initialize() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				int color = random.nextInt(3) + 1;
				
				if (i >= 2 && j >= 2) {
					while ((color == board[i - 1][j] && color == board[i - 2][j]) ||
						   (color == board[i][j - 1] && color == board[i][j - 2])) {
						color = random.nextInt(3) + 1;
					}
				} else if (i >= 2) {
					while (color == board[i - 1][j] && color == board[i - 2][j]) {
						color = random.nextInt(3) + 1;
					}
				} else if (j >= 2) {
					while (color == board[i][j - 1] && color == board[i][j - 2]) {
						color = random.nextInt(3) + 1;
					}
				}
				
				board[i][j] = color;
			}
		}
	}
	
	public static void main(String[] args) {
		CandyCrush cc = new CandyCrush(10, 10);
		
		cc.initialize();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(cc.board[i][j]);
			}
			
			System.out.println();
		}
	}
}
