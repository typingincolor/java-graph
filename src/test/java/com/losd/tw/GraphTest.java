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

        assertThat(graph.numberOfLinesStartingAt(A), is(1));
    }

    @Test
    public void addTwoRoutes() {
        Graph graph = new Graph();

        Town A = new Town("A");
        Town B = new Town("B");
        Town C = new Town("C");

        Line lineAB = new Line(A, B, 5);
        Line lineAC = new Line(A, C, 1);


        graph.addRoute(lineAB);
        graph.addRoute(lineAC);

        assertThat(graph.numberOfLinesStartingAt(A), is(2));
    }

    @Test
    public void triangle() {
        Graph graph = new Graph();

        Town A = new Town("A");
        Town B = new Town("B");
        Town C = new Town("C");

        Line lineAB = new Line(A, B, 5);
        Line lineAC = new Line(A, C, 1);
        Line lineBC = new Line(B, C, 1);

        graph.addRoute(lineAB);
        graph.addRoute(lineAC);
        graph.addRoute(lineBC);

        assertThat(graph.numberOfLinesStartingAt(A), is(2));
        assertThat(graph.numberOfLinesStartingAt(B), is(1));
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
