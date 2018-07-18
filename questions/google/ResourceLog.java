package questions.leetcode.questions.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceLog {
	
	Map<Integer, Map<Integer, List<Interval>>> map;
	public ResourceLog(List<Log> logs) {
		for (Log log : logs) {
			int rId = log.resourceId;
			int uId = log.userId;
			
			Map<Integer, List<Interval>> userToIntervals = map.get(rId);
			if (userToIntervals == null) {
				userToIntervals = new HashMap<>();
				map.put(rId, userToIntervals);
			}
			
			userToIntervals.computeIfAbsent(uId, k -> new ArrayList<>()).add(new Interval(log.startTime, log.endTime, log.usage));
		}
	}
	
	/**
	 * Find the top user of the given resource.
	 * @param resourceId the resource id.
	 * @return the id of the top user.
	 */
	public int findTopUser(int resourceId) {
		Map<Integer, List<Interval>> userToIntervals = map.get(resourceId);
		
		int max = 0;
		int peakUser = 0;
		
		for (Map.Entry<Integer, List<Interval>> entry : userToIntervals.entrySet()) {
			int usage = findPeakUsage(entry.getValue());
			if (max < usage) {
				max = usage;
				peakUser = entry.getKey();
			}
		}
		
		return peakUser;
	}
	
	private int findPeakUsage(List<Interval> intervals) {
		List<Node> nodes = new ArrayList<>();
		for (Interval interval : intervals) {
			nodes.add(new Node(interval.startTime, true, interval.usage));
			nodes.add(new Node(interval.endTime, false, interval.usage));
		}
		
		Collections.sort(nodes, (n1, n2) -> {
			int diff = n1.time - n2.time;
			if (diff != 0) {
				return diff;
			}
			
			if (n1.start) {
				return -1;
			}
			
			return 1;
		});
		
		int maxUsage = 0;
		int current = 0;
		for (Node node : nodes) {
			if (node.start) {
				current += node.count;
				maxUsage = Math.max(maxUsage, current);
			} else {
				current -= node.count;
			}
		}
		
		return maxUsage;
	}

	private class Node {
		int time;
		boolean start;
		int count;
		
		public Node(int time, boolean start, int count) {
			this.time = time;
			this.start = start;
			this.count = count;
		}
	}
	
	private class Interval {
		int startTime;
		int endTime;
		int usage;
		
		public Interval(int startTime, int endTime, int usage) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.usage = usage;
		}
	}
	
	private class Log {
		int userId;
		int resourceId;
		int startTime;
		int endTime;
		int usage;
	}
}

