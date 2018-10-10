package questions.leetcode.questions.google.chase;

import java.util.Random;

public class RandomSelectFromRec {

	Random random = new Random();
	public Point randomPickFromASingleRec(Rec rec) {
		return new Point(rec.top + random.nextInt(rec.bottom - rec.top) + 1, rec.left + random.nextInt(rec.right - rec.left) + 1);
	}
	
	public Point randomPickFromRecs(Rec[] recs) {
		int total = 0;
		int selected = 0;
		for (int i = 0; i < recs.length; i++) {
			int area = (recs[i].bottom - recs[i].top) * (recs[i].right - recs[i].left);
			if (random.nextInt(total + area) >= total) {
				selected = i;
			}
			
			total += area;
		}
		
		return randomPickFromASingleRec(recs[selected]);
	}
	
	// TODO:(chqi): what if the rectangles can have overlaps
	// if the bottom line is on the x - axis, skyline problem
	
	private class Point {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	private class Rec {
		int top;
		int bottom;
		int left;
		int right;
		
		public Rec(int top, int bottom, int left, int right) {
			this.top = top;
			this.bottom = bottom;
			this.left = left;
			this.right = right;
		}
	}
}
