package com.losd.tw;

import org.junit.Test;

/**
 * Created by andrew on 19/07/2014.
 */
public class GraphTest {
    @Test
    public void addEdge() {
        Graph graph = new Graph();

        graph.addEdge("A", "B", "5");
    }
}
