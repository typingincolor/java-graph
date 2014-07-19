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

        Town A = new Town("A");
        Town B = new Town("B");

        graph.addRoute(A, B, 5);

        assertThat(graph.numberOfRoutes(A), is(1));
    }

    @Test(expected = DuplicateRouteException.class)
    public void failsForDuplicateRoute() {
        Graph graph = new Graph();

        Town A = new Town("A");
        Town B = new Town("B");

        graph.addRoute(A, B, 5);
        graph.addRoute(A, B, 5);
    }

    @Test(expected = InvalidRouteException.class)
    public void failsForRouteFromAToA() {
        Graph graph = new Graph();

        Town A = new Town("A");

        graph.addRoute(A, A, 5);
    }
}
