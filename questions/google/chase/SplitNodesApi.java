package questions.leetcode.questions.google.chase;

import java.util.ArrayList;
import java.util.List;

public class SplitNodesApi {

	List<Node> nodes;
	public SplitNodesApi() {
		nodes = new ArrayList<>();
	}
	
	/**
	 * Adds a new Node.
	 * @param node the new Node;
	 */
	public void addNode(Node node) {
		
	}
	
	/**
	 * Splits the nodes into clusters
	 * @return the clusters
	 */
	public List<List<Node>> split() {
		return null;
	}
	
	/**
	 * Find the list of nodes on the boundary 
	 * @param nodes
	 * @return
	 */
	public List<Node> getBoundary(List<Node> nodes) {
		return null;
	}
	
	/**
	 * Checks if the boundaries intersect with each other
	 * @param edge1
	 * @param edge2
	 * @return
	 */
	public boolean hasIntersection(List<Node> boundary1, List<Node> boundary2) {
		return true;
	}
	
	public class Node {
		int x;
		int y;
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
