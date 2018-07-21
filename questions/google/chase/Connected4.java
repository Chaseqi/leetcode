package questions.leetcode.questions.google.chase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * connected 4 game
   input n, k, n是可以放石子的位置的总数，k是连续的石子的个数
   设计 boolean play(int pos), 当连续石子的个数达到k是返回True，i是新放的石子的位置		
 * @author Chase
 * http://www.1point3acres.com/bbs/thread-434363-1-1.html
 */
public class Connected4 {

	int[] stones;
	int[] fathers;
	int[] size; // the size of the component the stone is in.
	int k;
	
	Map<Integer, Integer> map = new HashMap<>(); // index --> the number of continuous stones
	
	public Connected4(int k, int n) {
		this.stones = new int[n];
		this.fathers = new int[n];
		Arrays.fill(fathers, -1);
		this.size = new int[n];
		this.k = k;
	}
	
	public boolean play_1(int i) {
		int left = i;
		int right = i;
		
		if (i > 0 && stones[i - 1] != 0) {
			left = i - 1 - map.get(i - 1) + 1;
		}
		
		if (i < stones.length - 1 && stones[i + 1] != 0) {
			right = i + 1 + map.get(i + 1) - 1;
		}
		
		int count = right - left + 1;
		stones[i] = 1;
		
		map.put(left, count);
		map.put(right, count);
		
		return count >= k;
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
        System.out.println(c.play_1(2));
        System.out.println(c.play_1(3));
        System.out.println(c.play_1(4));
//        System.out.println(c.play_1(4));
	}
}
