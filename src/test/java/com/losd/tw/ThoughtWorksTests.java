package com.losd.tw;

import org.junit.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The all important ThoughtWorks test
 * <p/>
 * Created by andrew on 19/07/2014.
 */
public class ThoughtWorksTests extends UnitTest {
    Graph graph = GraphBuilder.build("AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7");

    @Test
    public void test1() {
        assertThat(distance(A, B, C), is("9"));
    }

    @Test
    public void test2() {
        assertThat(distance(A, D), is("5"));
    }

    @Test
    public void test3() {
        assertThat(distance(A, D, C), is("13"));
    }

    @Test
    public void test4() {
        assertThat(distance(A, E, B, C, D), is("22"));
    }

    @Test
    public void test5() {
        assertThat(distance(A, E, D), is("NO SUCH ROUTE"));
    }

    @Test
    public void test6() {
        Set<Route> result = graph.searchByNumberOfStops(C, C, 3);
        result.addAll(graph.searchByNumberOfStops(C, C, 2));

        Route expectedRoute1 = new Route(C, D, C);
        Route expectedRoute2 = new Route(C, E, B, C);

        assertThat(result.size(), is(2));
        assertThat(result, hasItem(expectedRoute1));
        assertThat(result, hasItem(expectedRoute2));
    }

    @Test
    public void test6a() {
        Set<Route> result = graph.searchForRoutesWithLessThanXStops(C, C, 3);

        Route expectedRoute1 = new Route(C, D, C);
        Route expectedRoute2 = new Route(C, E, B, C);

        assertThat(result.size(), is(2));
        assertThat(result, hasItem(expectedRoute1));
        assertThat(result, hasItem(expectedRoute2));
    }

    @Test
    public void test7() {
        Set<Route> result = graph.searchByNumberOfStops(A, C, 4);

        Route expectedRoute1 = new Route(A, B, C, D, C);
        Route expectedRoute2 = new Route(A, D, C, D, C);
        Route expectedRoute3 = new Route(A, D, E, B, C);

        assertThat(result.size(), is(3));
        assertThat(result, hasItem(expectedRoute1));
        assertThat(result, hasItem(expectedRoute2));
        assertThat(result, hasItem(expectedRoute3));
    }

    @Test
    public void test8() {
        Route result = graph.getShortestRouteBetween(A, C);

        Route expectedRoute = new Route(A, B, C);
        assertThat(result, is(expectedRoute));
    }

    @Test
    public void test9() {
        Route result = graph.getShortestRouteBetween(B, B);

        Route expectedRoute = new Route(B, C, E, B);
        assertThat(result, is(expectedRoute));
    }

    @Test
    public void test10() {
        Set<Route> result = graph.searchForLessThanDistance(C, C, 30);

        Route expectedRoute1 = new Route(C, D, C);
        Route expectedRoute2 = new Route(C, E, B, C);
        Route expectedRoute3 = new Route(C, E, B, C, D, C);
        Route expectedRoute4 = new Route(C, D, C, E, B, C);
        Route expectedRoute5 = new Route(C, D, E, B, C);
        Route expectedRoute6 = new Route(C, E, B, C, E, B, C);
        Route expectedRoute7 = new Route(C, E, B, C, E, B, C, E, B, C);

        assertThat(result.size(), is(7));
        assertThat(result, hasItem(expectedRoute1));
        assertThat(result, hasItem(expectedRoute2));
        assertThat(result, hasItem(expectedRoute3));
        assertThat(result, hasItem(expectedRoute4));
        assertThat(result, hasItem(expectedRoute5));
        assertThat(result, hasItem(expectedRoute6));
        assertThat(result, hasItem(expectedRoute7));
    }

    private String distance(Town... towns) {
        Route testRoute = new Route(towns);
        return graph.distance(testRoute);
    }
}
