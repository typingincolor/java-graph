package com.losd.tw;

import com.losd.tw.exceptions.DuplicateRouteException;
import com.losd.tw.exceptions.InvalidLineException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Graph Tests
 *
 * Created by andrew on 19/07/2014.
 */
public class GraphTest {
    @Test
    public void addRoute() {
        Graph graph = new Graph();

        Town A = new Town("A");
        Town B = new Town("B");

        Line line = new Line(A, B, 5);

        graph.addRoute(line);

        assertThat(graph.numberOfRoutes(A), is(1));
    }

    @Test(expected = DuplicateRouteException.class)
    public void failsForDuplicateRoute() {
        Graph graph = new Graph();

        Town A = new Town("A");
        Town B = new Town("B");

        Line line = new Line(A, B, 5);

        graph.addRoute(line);
        graph.addRoute(line);
    }

    @Test(expected = InvalidLineException.class)
    public void failsForRouteFromAToA() {
        Graph graph = new Graph();

        Town A = new Town("A");
        Line line = new Line(A, A, 5);

        graph.addRoute(line);
    }
}
