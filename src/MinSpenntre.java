
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import Graph.*;
import Graph.Graph.Node;
import Graph.Graph.Edge;

public class MinSpenntre {

    public int numberOfNodesFromSource = 0;
    private final List<Node> nodes;
    private final List<Edge> edges;
    public Set<Node> settledNodes;
    public Set<Node> unSettledNodes;
    public Map<Node, Node> predecessors;
    public Map<Node, Integer> distance;

    public MinSpenntre(Graph graph) {
        nodes = graph.getNodes();
        edges = graph.getEdges();
    }

    public void execute(Node source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();

        distance.put(source, 0);
        unSettledNodes.add(source);
        Map<Node, Integer> usedNodes = new HashMap<>();
for (Node n : nodes) System.out.println(nodes.get(Integer.parseInt(n.getId())).getId());
        System.out.println("edg");
for (Edge e : edges) System.out.println(edges.get(e.getWeight()).getSourceNode().getId() + " -> " + edges.get(e.getWeight()).getDestinationNode().getId());
        
    }
        //        while (!unSettledNodes.isEmpty()) {
//            Node node = getUnSettledNodeWithLowestDistance();
//            settleNode(node);
//            usedNodes = new HashMap<>();
//            usedNodes.put(node, 0);
//            if (getCumulativeNodeDistance(node) == Integer.MAX_VALUE) {
//                break;
//            }
//            int lowestDistance = Integer.MAX_VALUE;
//            Node bestNeighbor = null;
//            for (Node neighbor : getNeighbors(node)) {
////                if (isSettled(neighbor)) {
////                    continue;
////                }
//                if (usedNodes.containsKey(neighbor)){
//                    continue;
//                }
//                if (getDistanceBetweenNeighborNodes(node, neighbor) < lowestDistance){
//                    lowestDistance = getDistanceBetweenNeighborNodes(node, neighbor);
//                    bestNeighbor = neighbor;
//                    
//                }  
//            }
//            usedNodes.put(bestNeighbor, lowestDistance);
//            
//        }
//        for(Node n : nodes){
//            System.out.println(usedNodes.get(n));
//        }
//        
//        
//        
//    }
    
            // Sjekker om naboen har en kortere distanse fra source enn noden + distansen mellom noden og naboen
//                if (getCumulativeNodeDistance(neighbor)
//                        > getCumulativeNodeDistance(node) + getDistanceBetweenNeighborNodes(node, neighbor)) {
//
//                    distance.put(neighbor, getCumulativeNodeDistance(node)
//                            + getDistanceBetweenNeighborNodes(node, neighbor));
//                    predecessors.put(neighbor, node);
//                    unSettledNodes.add(neighbor);
//
//                } // MinSpenntre algoritmen
//                else if (getCumulativeNodeDistance(neighbor) > getCumulativeNodeDistance(node) + getDistanceBetweenNeighborNodes(node, neighbor)) {
//                    distance.put(neighbor, getCumulativeNodeDistance(node)
//                            + getDistanceBetweenNeighborNodes(node, neighbor));
//                    predecessors.put(neighbor, node);
//                    unSettledNodes.add(neighbor);
//                }
                // Stopper når han finner målet
//                if (neighbor.equals(target)) {
//                    return getCumulativeNodeDistance(neighbor);
//                }
//            } 

    private Node getUnSettledNodeWithLowestDistance() {
        Node lowest = null;
        for (Node node : unSettledNodes) {
            if (lowest == null) {
                lowest = node;
            } else if (getCumulativeNodeDistance(node) < getCumulativeNodeDistance(lowest)) {
                lowest = node;
            }
        }
        return lowest;
    }

    public Integer getCumulativeNodeDistance(Node node) {
        Integer distance = this.distance.get(node);
        if (distance == null) {
            return Integer.MAX_VALUE;
        } else {
            return distance;
        }
    }

    private int getDistanceBetweenNeighborNodes(Node source, Node target) {
        for (Edge edge : source.getOutEdges()) {
            if (edge.getSourceNode().equals(source) && edge.getDestinationNode().equals(target)) {
                return edge.getWeight();
            }
        }
        throw new RuntimeException("Source and target are not neighbors");
    }

    public double getTotalDistance(Node a, Node b) {

        return 0;
    }

    private void settleNode(Node node) {
        unSettledNodes.remove(node);
        settledNodes.add(node);
    }

    private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }

    private List<Node> getNeighbors(Node node) {
        List<Node> neighbors = new ArrayList<>();
        for (Edge edge : node.getOutEdges()) {
            neighbors.add(edge.getDestinationNode());
        }
        return neighbors;
    }

    public static double rad(double x) {
        return x * Math.PI / 180;
    }

    public double getDistanceFromCoords(Node a, Node b) {
        int earthRadius = 6371;

        return earthRadius * Math.acos(
                Math.sin(rad(a.getBreddegrad()))
                * Math.sin(rad(b.getBreddegrad()))
                + Math.cos(rad(a.getBreddegrad()))
                * Math.cos(rad(b.getBreddegrad()))
                * Math.cos(Math.abs(rad(b.getLengdegrad() - a.getLengdegrad()))));
    }

    public double getTimeUsedFromDistance(double distance) {
        return distance / 100 * 60 * 60 * 100; // time -> minutt -> sekund -> millisekund
    }

    public void dump() {
        System.out.println("From \t To \t\t Weight");
        for (Node node : nodes) {
            String nodeID = node.getId();
            String predecessorID = "Out of reach";
            String runtime = "";

            // If null; no predecessors -> no in node
            Node predecessor = predecessors.get(node);
            if (predecessor != null) {
                predecessorID = predecessor.getId();
                runtime = distance.get(node).toString();
            } else if (predecessor == null && distance.get(node) != null) {
                predecessorID = "Start node";
                runtime = "0";
            }

            if (predecessorID.length() < 5) {
                predecessorID += "\t";
            }

            System.out.println(nodeID + "\t " + predecessorID + " \t " + runtime);
        }
    }
}