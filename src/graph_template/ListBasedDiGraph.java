
package graph_template;

import java.util.*;


public class ListBasedDiGraph implements DiGraph {
	private List<GraphNode> nodeList = new ArrayList<>();

	@Override
	public Boolean addNode(GraphNode node) {

		nodeList.add(node);
		return true;
	}

	@Override
	public Boolean removeNode(GraphNode node) {
		// TODO Auto-generated method stub
		//complete
		GraphNode target = getNode(node.getValue());
		if (target == null) return false;
		for (GraphNode n : nodeList) {
			n.removeNeighbor(target);
		}
		nodeList.remove(target);
		return true;
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
		// TODO Auto-generated method stub
		//complete
		GraphNode target = getNode(node.getValue());
		if (target == null) return false;
		target.setValue(newNodeValue);
		return true;
	}

	@Override
	public String getNodeValue(GraphNode node) {
		// TODO Auto-generated method stub
		//complete
		return node.getValue();
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {


		//GOOD
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		targetFromNode.addNeighbor(targetToNode, weight);

		return true;
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		//complete
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		if (targetFromNode == null || targetToNode == null) return false;

		return targetFromNode.removeNeighbor(targetToNode);
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		// TODO Auto-generated method stub
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
		if (targetFromNode == null || targetToNode == null) {
			return false;
		}
		targetFromNode.addNeighbor(targetToNode, newWeight);
		return true;
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		//complete
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());

		if (targetFromNode == null || targetToNode == null) return null;

		return targetFromNode.getDistanceToNeighbor(targetToNode);
	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {
		GraphNode target = getNode(node.getValue());
		if (target == null) return new ArrayList<>();
		return target.getNeighbors();
	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		//complete
		return getEdgeValue(fromNode, toNode) != null;
	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return fewestHops(fromNode, toNode) > 0 || fromNode.getValue().equals(toNode.getValue());
	}

	@Override
	public Boolean hasCycles() {
		List<GraphNode> visited = new ArrayList<>();
		List<GraphNode> recStack = new ArrayList<>();

		for (GraphNode node : nodeList) {
			if (checkCycle(node, visited, recStack)) return true;
		}
		return false;
	}

	private boolean checkCycle(GraphNode node, List<GraphNode> visited, List<GraphNode> recStack) {
		if (recStack.contains(node)) return true;
		if (visited.contains(node)) return false;

		visited.add(node);
		recStack.add(node);

		for (GraphNode neighbor : node.getNeighbors()) {
			if (checkCycle(neighbor, visited, recStack)) return true;
		}

		recStack.remove(node);
		return false;
	}

	@Override
	public List<GraphNode> getNodes() {
		return nodeList;
	}

	@Override

	public GraphNode getNode(String nodeValue) {
		for (GraphNode thisNode : nodeList) {
			if (thisNode.getValue().equals(nodeValue))
				return thisNode;
		}
		return null;
	}

	@Override
	public int fewestHops(GraphNode fromNode, GraphNode toNode) {
		if (fromNode.getValue().equals(toNode.getValue())) return 0;

		java.util.Queue<GraphNode> queue = new java.util.LinkedList<>();
		java.util.Map<GraphNode, Integer> distances = new java.util.HashMap<>();

		GraphNode start = getNode(fromNode.getValue());
		queue.add(start);
		distances.put(start, 0);

		while (!queue.isEmpty()) {
			GraphNode current = queue.poll();
			int dist = distances.get(current);

			for (GraphNode neighbor : current.getNeighbors()) {
				if (neighbor.getValue().equals(toNode.getValue())) return dist + 1;
				if (!distances.containsKey(neighbor)) {
					distances.put(neighbor, dist + 1);
					queue.add(neighbor);
				}
			}
		}
		return -1;
	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		PriorityQueue<NodeDistance> pq = new PriorityQueue<>();
		Map<GraphNode, Integer> minTravel = new HashMap<>();

		GraphNode start = getNode(fromNode.getValue());
		pq.add(new NodeDistance(start, 0));
		minTravel.put(start, 0);

		while (!pq.isEmpty()) {
			NodeDistance current = pq.poll();

			if (current.node.getValue().equals(toNode.getValue())) return current.dist;
			for (GraphNode neighbor : current.node.getNeighbors()) {
				int weight = getEdgeValue(current.node, neighbor);
				int newDist = current.dist + weight;

				if (newDist < minTravel.getOrDefault(neighbor, Integer.MAX_VALUE)) {
					minTravel.put(neighbor, newDist);
					pq.add(new NodeDistance(neighbor, newDist));
				}
			}
		}
		return -1;
	}

	private class NodeDistance implements Comparable<NodeDistance> {
		GraphNode node;
		int dist;
		NodeDistance(GraphNode n, int d) { node = n; dist = d; }
		public int compareTo(NodeDistance other) { return Integer.compare(this.dist, other.dist); }
	}
}
