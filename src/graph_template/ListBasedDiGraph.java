
package graph_template;

import java.util.ArrayList;
import java.util.List;
 

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
		return null;
	}

	@Override
	public Boolean setNodeValue(GraphNode node, String newNodeValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNodeValue(GraphNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addEdge(GraphNode fromNode, GraphNode toNode, Integer weight) {

		//BAD
		fromNode.addNeighbor(toNode, weight);
		
		//GOOD
		GraphNode targetFromNode = getNode(fromNode.getValue());
		GraphNode targetToNode = getNode(toNode.getValue());
	 	 
		targetFromNode.addNeighbor(targetToNode, weight);
	
		return true;
	}

	@Override
	public Boolean removeEdge(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean setEdgeValue(GraphNode fromNode, GraphNode toNode, Integer newWeight) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getEdgeValue(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<GraphNode> getAdjacentNodes(GraphNode node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean nodesAreAdjacent(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean nodeIsReachable(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hasCycles() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int shortestPath(GraphNode fromNode, GraphNode toNode) {
		// TODO Auto-generated method stub
		return 0;
	}

 
	 
	
}
