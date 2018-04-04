package questions.leetcode.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class GraphTraverseAlgos {
	// returns a MST
	public List<Edge> prim(int[][] edges, int source) {
		Map<Integer, List<Node>> graph = constructGraph(edges);
		int count = graph.size(); // the graph is a connected graph
		Set<Integer> visited = new HashSet<>();
		PriorityQueue<Edge> queue = new PriorityQueue<>((edge1, edge2) -> edge1.length - edge2.length);
		queue.offer(new Edge(source, source, 0));
		
		List<Edge> result = new ArrayList<>();
		while (visited.size() < count) {
			Edge edge = queue.poll();
			if (visited.contains(edge.end)) {
				continue;
			}
			
			visited.add(edge.end);
			result.add(edge);
			
			for (Node neighbor : graph.get(edge.end)) {
				if (visited.contains(neighbor.dest)) {
					continue;
				}
				
				queue.offer(new Edge(edge.end, neighbor.dest, neighbor.length));
			}
		}
		
		for (Edge edge : result) {
			System.out.println(edge);
		}
		
		return result;
	}
	
	public int dijkstra(int[][] edges, int source, int n) {
		Map<Integer, List<Node>> map = constructGraph(edges);
		Set<Integer> visited = new HashSet<>();
		PriorityQueue<Node> queue = new PriorityQueue<>((node1, node2) -> {
			return node1.length - node2.length;
		});
		
		queue.offer(new Node(source, 0));
		int result = 0;
		
		while (visited.size() < n) {
			Node node = queue.poll();
			
			if (visited.contains(node.dest)) {
				continue;
			}
			
			visited.add(node.dest);
			result = node.length;
			
			List<Node> neighbors = map.get(node.dest);
			for (Node neighbor : neighbors) {
				queue.offer(new Node(neighbor.dest, node.length + neighbor.length));
			}
		}
		
		return result;
	}
	
	private Map<Integer, List<Node>> constructGraph(int[][] edges) {
		Map<Integer, List<Node>> graph = new HashMap<>();
		
		for (int[] edge : edges) {
			int start = edge[0];
			int end = edge[1];
			
			List<Node> neighbors = graph.get(start);
			if (neighbors == null) {
				neighbors = new ArrayList<>();
				graph.put(start, neighbors);
			}
			
			neighbors.add(new Node(end, edge[2]));
			
			neighbors = graph.get(end);
			if (neighbors == null) {
				neighbors = new ArrayList<>();
				graph.put(end, neighbors);
			}
			
			neighbors.add(new Node(start, edge[2]));
		}
		
		return graph;
	}
	
	private class Node {
		final int dest;
		final int length;
		
		public Node(int dest, int length) {
			this.dest = dest;
			this.length = length;
		}
	}
	
	private class Edge {
		int start;
		int end;
		int length;
		
		public Edge(int start, int end, int length) {
			this.start = start;
			this.end = end;
			this.length = length;
		}
		
		@Override
		public String toString() {
			return start +  " " + end + " " + length;
		}
	}
}
