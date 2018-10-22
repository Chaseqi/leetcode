package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SodaMachine {
	
	public static boolean canFill(List<Soda> sodas, int lower, int upper) {
		Map<String, Boolean> memo = new HashMap<>();
		
		return dfs(sodas, 0, 0, lower, upper, memo);
	}
	
	public static boolean dfs(List<Soda> sodas, int volumeLower, int volumeUpper, 
				              int targetLower, int targetUpper, Map<String, Boolean> memo) {
		
		Boolean val = memo.get(volumeLower + "-" + volumeUpper);
		if (val != null) {
			return val;
		}
		
		if (volumeLower >= targetLower && volumeUpper <= targetUpper) {
			return true;
		}
		
		if (volumeUpper > targetUpper) {
			return false;
		}
		
		for (Soda soda : sodas) {
			if (dfs(sodas, volumeLower + soda.lower, volumeUpper + soda.upper, targetLower, targetUpper, memo)) {
				memo.put(volumeLower + "-" + volumeUpper, true);
				return true;
			}
		}
		
		memo.put(volumeLower + "-" + volumeUpper, false);
		return false;
	}
	
	public static void main(String[] args) {
		List<Soda> sodas = new ArrayList<>();
		sodas.add(new Soda(100, 120));
		sodas.add(new Soda(200, 240));
		sodas.add(new Soda(400, 410));
		
		System.out.println(canFill(sodas, 100, 110));
		System.out.println(canFill(sodas, 90, 120));
		System.out.println(canFill(sodas, 300, 360));
		System.out.println(canFill(sodas, 310, 360));
		System.out.println(canFill(sodas, 1, 9999999));
	}
	
	private static class Soda {
		int lower;
		int upper;
		
		public Soda(int lower, int upper) {
			this.lower = lower;
			this.upper = upper;
		}
	}
}
