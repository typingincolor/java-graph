package com.losd.tw;

import com.losd.tw.exceptions.NoSuchRouteException;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The all important ThoughtWorks test
 * <p/>
 * Created by andrew on 19/07/2014.
 */
public class ThoughtWorksTests {
    Graph graph = GraphBuilder.build("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

    Town A = Town.getInstance("A");
    Town B = Town.getInstance("B");
    Town C = Town.getInstance("C");
    Town D = Town.getInstance("D");
    Town E = Town.getInstance("E");

    @Test
    public void test1() {
        assertThat(distance(A, B, C), is(9));
    }

    @Test
    public void test2() {
        assertThat(distance(A, D), is(5));
    }

    @Test
    public void test3() {
        assertThat(distance(A, D, C), is(13));
    }

    @Test
    public void test4() {
        assertThat(distance(A, E, B, C, D), is(22));
    }

    @Test(expected = NoSuchRouteException.class)
    public void test5() {
        distance(A, E, D);
    }

    @Test
    public void test6() {
        List<Route> result = graph.searchByMaximumSteps(C, C, 3);
        result.addAll(graph.searchByMaximumSteps(C, C, 2));

        Route expectedRoute1 = new Route(C, D, C);
        Route expectedRoute2 = new Route(C, E, B, C);

        assertThat(result.size(), is(2));
        assertThat(result, hasItem(expectedRoute1));
        assertThat(result, hasItem(expectedRoute2));
    }

    @Test
    public void test7() {
        List<Route> result = graph.searchByMaximumSteps(A, C, 4);

        Route expectedRoute1 = new Route(A, B, C, D, C);
        Route expectedRoute2 = new Route(A, D, C, D, C);
        Route expectedRoute3 = new Route(A, D, E, B, C);

        assertThat(result.size(), is(3));
        assertThat(result, hasItem(expectedRoute1));
        assertThat(result, hasItem(expectedRoute2));
        assertThat(result, hasItem(expectedRoute3));
    }

    @Test
    public void test10() {
        List<Route> result = graph.searchByMaximumDistance(C, C, 30);

        Route expectedRoute1 = new Route(C, D, C);
        Route expectedRoute2 = new Route(C, E, B, C);
        Route expectedRoute3 = new Route(C, E, B, C, D, C);
        Route expectedRoute4 = new Route(C, D, C, E, B, C);
        Route expectedRoute5 = new Route(C, D, E, B, C);
        Route expectedRoute6 = new Route(C, E, B, C, E, B, C);
        Route expectedRoute7 = new Route(C, E, B, C, E, B, C, E, B, C);

        for (Route route : result) {
            System.out.println(String.format("%s %d", route, graph.distance(route)));
        }

        assertThat(result.size(), is(7));
        assertThat(result, hasItem(expectedRoute1));
        assertThat(result, hasItem(expectedRoute2));
        assertThat(result, hasItem(expectedRoute3));
        assertThat(result, hasItem(expectedRoute4));
        assertThat(result, hasItem(expectedRoute5));
        assertThat(result, hasItem(expectedRoute6));
        assertThat(result, hasItem(expectedRoute7));
    }

    private int distance(Town... towns) {
        Route testRoute = new Route(towns);
        return graph.distance(testRoute);
    }
}
