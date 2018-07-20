package questions.leetcode.questions.google.chase;

import java.util.Arrays;

public class Connected4 {

	int[] stones;
	int[] fathers;
	int[] size; // the size of the component the stone is in.
	int k;
	
	public Connected4(int k, int n) {
		this.stones = new int[n];
		this.fathers = new int[n];
		Arrays.fill(fathers, -1);
		this.size = new int[n];
		this.k = k;
	}
	
	public boolean play(int i) {
		stones[i] = 1;
		size[i] = 1;
		
		if (i > 0 && stones[i - 1] == 1) {
			if (union(i, i - 1) >= k) {
				return true;
			}
		}
		
		if (i < stones.length - 1 && stones[i + 1] == 1) {
			if (union(i, i + 1) >= k) {
				return true;
			}
		}
		
		return false;
	}
	
	private int union(int a, int b) {
		int fatherA = find(a);
		int fatherB = find(b);
		
		if (fatherA != fatherB) {
			fathers[fatherA] = fatherB;
			int total = size[fatherA] + size[fatherB];
			size[fatherA] = total;
			size[fatherB] = total;
			return total;
		}
		
		return size[fatherA];
	}
	
	private int find(int a) {
		if (fathers[a] == -1) {
			return a;
		}
		
		fathers[a] = find(fathers[a]);
		return fathers[a];
	}
	
	public static void main(String[] args) {
		Connected4 c = new Connected4(3, 6);
        System.out.println(c.play(2));
        System.out.println(c.play(3));
        System.out.println(c.play(5));
        System.out.println(c.play(4));
	}
}
