
package graph_template;

public class SuperDuperGraphTester {
	private static DiGraph graph;
	
	public static void main(String[] args) {
		
		
		//EX1
		
		graph = new ListBasedDiGraph();

		TESTaddNode("Austin");
		TESTaddNode("Houston");
		TESTaddNode("Dallas");
		TESTaddNode("Atlanta");
		TESTaddNode("Washington");
		TESTaddNode("Denver");
		TESTaddNode("Chicago");
		
		TESTaddEdge("Austin", "Dallas", 200);
		TESTaddEdge("Dallas", "Austin", 200);
		TESTaddEdge("Austin", "Houston", 160);
		TESTaddEdge("Houston", "Atlanta", 180);
		TESTaddEdge("Atlanta", "Houston", 180); 
		TESTaddEdge("Washington", "Dallas", 1300);
		TESTaddEdge("Dallas", "Denver", 780);		
		TESTaddEdge("Dallas", "Chicago", 900);		
		TESTaddEdge("Denver", "Chicago", 1000);
		
		TESTaddEdge("Denver", "Atlanta", 1400);	
		TESTaddEdge("Chicago", "Denver", 1000);	
	
		
		TESTdescribeGraph();
		TESThasCycles();
		TESTnodeIsReachable("Austin", "Washington");
		TESTnodeIsReachable("Chicago", "Houston");
		
		TESTfewestHops("Austin", "Chicago");
		TESTshortestPath("Washington", "Atlanta");
	
		
		// 
		//ADD MORE TESTS HERE!!!
		//
				
		
		
	}
	
	
	public static void TESTfewestHops(String nodeFrom, String nodeTo) {
		System.out.println("The graph has " + graph.fewestHops(new GraphNode(nodeFrom), new GraphNode(nodeTo)) + " hops");
	
	}
	
	public static void TESTshortestPath(String nodeFrom, String nodeTo) {
		System.out.println("The graph has a shortest path of: " + graph.shortestPath(new GraphNode(nodeFrom), new GraphNode(nodeTo)) + " from " + nodeFrom + " to " + nodeTo);
	
	}
	
	
	public static void TESThasCycles() {
		System.out.println("The graph has " + 
			       (graph.hasCycles() ? "" : "NO") + " cycles");
	
	}
	
	public static void TESTnodeIsReachable(String nodeFrom, String nodeTo) {
		System.out.println("There is " + 
					       (graph.nodeIsReachable(new GraphNode(nodeFrom), new GraphNode(nodeTo)) ? "a" : "NO") +
				           " path from [" + nodeFrom + "] to [" + nodeTo + "]");
	}
	
	
	public static void TESTdescribeGraph() {
		for (GraphNode thisNode : graph.getNodes()) {
			System.out.print("Node [" + thisNode.getValue() + "] ");
			if (thisNode.getNeighbors().isEmpty()) {
				System.out.print("has no outgoing connections");
			}
			else {
				System.out.print("is connected to ");
				for (GraphNode neighbor : thisNode.getNeighbors()) {
					System.out.print("[" + neighbor.getValue() + "] by " + thisNode.getDistanceToNeighbor(neighbor).toString() + " ");
				}
			}
			System.out.println();
		}
	}
	
	
	public static void TESTaddEdge(String from, String to, Integer weight) {
		say("Attempting to addEdge from [" + from + "] to [" + to + "] with weight [" + weight.toString() + "]" );
		say(graph.addEdge(new GraphNode(from),  new GraphNode(to), weight) ? "SUCCESS" : "FAIL");
	}
	
	public static void TESTaddNode(String value) {
		say("Attempting to addNode [" + value + "]");
		say(graph.addNode(new GraphNode(value)) ? "SUCCESS" : "FAIL");
	}
	
	public static void say(String s) {
		System.out.println(s);
	}

}
