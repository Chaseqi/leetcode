package questions.leetcode.questions.google.chase;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FindSuggestion {
	private String findPotentionFriend(String target, List<String[]> relations) {
		Map<String, Set<String>> friends = new HashMap<>();
		
		for (String[] relation : relations) {
			friends.computeIfAbsent(relation[0], k -> new HashSet<>()).add(relation[1]);
			friends.computeIfAbsent(relation[1], k -> new HashSet<>()).add(relation[0]);
		}
		
		String result = "";
		int maxCount = 0;
		Set<String> friendsOfTarget = friends.get(target);
		
		for (Map.Entry<String, Set<String>> entry : friends.entrySet()) {
			String key = entry.getKey();
			if (key.equals(target) || friendsOfTarget.contains(key)) {
				continue;
			}
			
			int countOfCommonFriend = 0;
			for (String friend : entry.getValue()) {
				if (friendsOfTarget.contains(friend)) {
					countOfCommonFriend++;
				}
			}
			
			if (countOfCommonFriend > maxCount) {
				maxCount = countOfCommonFriend;
				result = key;
			}
		}
		
		return result;
	}
}
