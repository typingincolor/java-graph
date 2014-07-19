package com.losd.tw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by andrew on 19/07/2014.
 */
public class Graph {
    private HashMap<String, List<Edge>> nodes = new HashMap();

    public void addEdge(String startNode, String endNode, int distance) {
         List<Edge> edges = new ArrayList<Edge>();

        Edge edge = new Edge(new Node(endNode), distance);
        edges.add(edge);

        nodes.put(startNode, edges);
    }

    public int numberOfEdges(String node) {
        return nodes.get(node).size();
    }
}
