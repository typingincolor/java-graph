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
    Town A = new Town("A");
    Town B = new Town("B");
    Town C = new Town("C");

    @Test
    public void addRoute() {
        Graph graph = new Graph();
        Line line = new Line(A, B, 5);

        graph.addTrip(line);

        assertThat(graph.numberOfLinesStartingAt(A), is(1));
    }

    @Test
    public void addTwoRoutes() {
        Graph graph = new Graph();

        Line lineAB = new Line(A, B, 5);
        Line lineAC = new Line(A, C, 1);

        graph.addTrip(lineAB);
        graph.addTrip(lineAC);

        assertThat(graph.numberOfLinesStartingAt(A), is(2));
    }

    @Test
    public void triangle() {
        Graph graph = triangleGraph();

        assertThat(graph.numberOfLinesStartingAt(A), is(2));
        assertThat(graph.numberOfLinesStartingAt(B), is(1));
    }

    @Test(expected = DuplicateRouteException.class)
    public void failsForDuplicateRoute() {
        Graph graph = new Graph();

        Line line = new Line(A, B, 5);

        graph.addTrip(line);
        graph.addTrip(line);
    }

    @Test(expected = InvalidLineException.class)
    public void failsForRouteFromAToA() {
        Graph graph = new Graph();

        Line line = new Line(A, A, 5);

        graph.addTrip(line);
    }

    private Graph triangleGraph() {
        Graph graph = new Graph();

        Line lineAB = new Line(A, B, 5);
        Line lineAC = new Line(A, C, 1);
        Line lineBC = new Line(B, C, 1);

        graph.addTrip(lineAB);
        graph.addTrip(lineAC);
        graph.addTrip(lineBC);

        return graph;
    }
}
