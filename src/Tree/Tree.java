package Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tree {

    public static class Node {

        private String id;
        private List<Edge> inEdges = new ArrayList<>();
        private List<Edge> outEdges = new ArrayList<>();

        private Node(String id) {
            this.id = id;
        }

        public String getId() {
            return this.id;
        }

        public List<Edge> getInEdges() {
            return new ArrayList<>(this.inEdges);
        }

        public List<Edge> getOutEdges() {
            return new ArrayList<>(this.outEdges);
        }

        @Override
        public String toString() {
            return this.id;
        }
    }
    private Map<String, Node> nodeMap = new HashMap<>();

    public void addNode(Integer node) {
        nodeMap.put(node.toString(), new Node(node.toString()));
    }

    public Node getNode(String nodeId) {
        return nodeMap.get(nodeId);
    }

    public List<Node> getNodes() {
        return new ArrayList<>(nodeMap.values());
    }

    /* EDGE */
    public static class Edge {

        private final String id;
        private final Node sourceNode;
        private final Node targetNode;
        private final int weight;

        private Edge(String id, Node source, Node target, int weight) {
            this.id = id;
            this.sourceNode = source;
            this.targetNode = target;
            this.weight = weight;
        }

        public String getId() {
            return this.id;
        }

        public Node getSourceNode() {
            return this.sourceNode;
        }

        public Node getTargetNode() {
            return this.targetNode;
        }

        public int getWeight() {
            return this.weight;
        }

        @Override
        public String toString() {
            return this.id;
        }
    }
    private Map<String, Edge> edgeMap = new HashMap<>();

    public Edge addEdge(String id, Node source, Node destination, int weight) throws RuntimeException {

        Edge edge = new Edge(id, source, destination, weight);
        edgeMap.put(edge.id, edge);

        edge.sourceNode.outEdges.add(edge);
        edge.targetNode.inEdges.add(edge);
        return edge;
    }

    public Edge getEdge(String edgeId) {
        return edgeMap.get(edgeId);
    }

    public List<Edge> getEdges() {
        return new ArrayList<>(edgeMap.values());
    }
}
