package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WhoIsTheNewKing {
	
	Map<String, Node> nameToNode;
	Node king;
	
	public WhoIsTheNewKing() {
		this.nameToNode = new HashMap<>();
		this.king = new Node("king");
		this.nameToNode.put("king", king);
		this.king.isDead = true;
	}
	
	public void birth(String parent, String name) {
		Node parentNode = nameToNode.get(parent);
		if (parentNode == null) {
			throw new IllegalArgumentException();
		}
		
		Node childNode = new Node(name);
		parentNode.children.add(childNode);
		nameToNode.put(name, childNode);
	}
	
	public void dead(String name) {
		Node node = nameToNode.get(name);
		if (node == null) {
			throw new IllegalArgumentException();
		}
		
		node.isDead = true;
	}
	
	public List<String> getOrderOfSuccession() {
		List<String> successions = new ArrayList<>();
		dfs(king, successions);
		return successions;
	}
	
	private void dfs(Node node, List<String> successions) {
		if (!node.isDead) {
			successions.add(node.name);
		}
		
		for (Node child : node.children) {
			dfs(child, successions);
		}
	}
	
	private class Node {
		String name;
		List<Node> children;
		boolean isDead;
		
		public Node(String name) {
			this.name = name;
			this.children = new ArrayList<>();
		}
	}
}
