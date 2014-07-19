package com.losd.tw;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by andrew on 19/07/2014.
 */
public class GraphTest {
    @Test
    public void addRoute() {
        Graph graph = new Graph();

        graph.addRoute("A", "B", 5);

        assertThat(graph.numberOfRoutes("A"), is(1));
    }

    @Test(expected = DuplicateRouteException.class)
    public void failsForDuplicateRoute() {
        Graph graph = new Graph();

        graph.addRoute("A", "B", 5);
        graph.addRoute("A", "B", 5);
    }
}
