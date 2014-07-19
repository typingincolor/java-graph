package com.losd.tw;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by andrew on 19/07/2014.
 */
public class GraphTest {
    @Test
    public void addEdge() {
        Graph graph = new Graph();

        graph.addEdge("A", "B", 5);

        assertThat(graph.numberOfEdges("A"), is(1));
    }
}
