
import Tree.*;
import Tree.Tree.Edge;
import Tree.Tree.Node;
import java.util.ArrayList;
import java.util.List;

class MinSpenntre {

    public List<Node> settledNodes;
    public List<Node> unsettledNodes;
    public List<Integer> totalWeight = new ArrayList<>();
    

    public MinSpenntre(Tree tree) {
        settledNodes = new ArrayList<>();
        unsettledNodes = tree.getNodes();
    }

    public List<Node> execute(Node source) {
        totalWeight.add(0);
        settleNode(source);

        while (!unsettledNodes.isEmpty()) {
            settleNode(getLowestUnsettledNeighbor());
        }
        return settledNodes;
        
//        System.out.println("Settled: " + settledNodes);
//        System.out.println("Unsettled: " + unsettledNodes);
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (Edge edge : node.getOutEdges()) {
            neighbors.add(edge.getTargetNode());
        }
        return neighbors;
    }

    private int getDistanceBetweenNeighborNodes(Node source, Node target) {
        for (Edge edge : source.getOutEdges()) {
            if (edge.getSourceNode().equals(source) && edge.getTargetNode().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Source and target are not neighbors");
    }

    private Node getLowestUnsettledNeighbor() {
        int lowestWeight = Integer.MAX_VALUE;
        Node lowestNeighbor = null;
        for (Node node : settledNodes) {
            for (Node neighbor : getNeighbors(node)) {
                int weight = getDistanceBetweenNeighborNodes(node, neighbor);
                if (weight < lowestWeight && !isSettled(neighbor)) {
                    lowestWeight = weight;
                    lowestNeighbor = neighbor;
                }
            }
        }
        totalWeight.add(lowestWeight);
        return lowestNeighbor;
    }

    private boolean isSettled(Node node) {
        return (settledNodes.contains(node)) ? true : false;
    }

    private void settleNode(Node node) {
        unsettledNodes.remove(node);
        settledNodes.add(node);
    }
    
    public List<Integer> getWeightList(){
        return totalWeight;
    }
    
    public int getTotalWeight(){
        int result = 0;
        for (int i : totalWeight){
            result += i;
        }
        return result;
    }
}