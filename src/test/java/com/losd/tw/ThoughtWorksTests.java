package com.losd.tw;

import com.losd.tw.exceptions.NoSuchRouteException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The all important ThoughtWorks test
 *
 * Created by andrew on 19/07/2014.
 */
public class ThoughtWorksTests {
    static Graph graph = new Graph();

    static Town A = new Town("A");
    static Town B = new Town("B");
    static Town C = new Town("C");
    static Town D = new Town("D");
    static Town E = new Town("E");

    @BeforeClass
    public static void setup() {
        Line AB = new Line(A, B, 5);
        Line BC = new Line(B, C, 4);
        Line CD = new Line(C, D, 8);
        Line DC = new Line(D, C, 8);
        Line DE = new Line(D, E, 6);
        Line AD = new Line(A, D, 5);
        Line CE = new Line(C, E, 2);
        Line EB = new Line(E, B, 3);
        Line AE = new Line(A, E, 7);

        graph.addTrip(AB);
        graph.addTrip(BC);
        graph.addTrip(CD);
        graph.addTrip(DC);
        graph.addTrip(DE);
        graph.addTrip(AD);
        graph.addTrip(CE);
        graph.addTrip(EB);
        graph.addTrip(AE);
    }

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
        graph.ride(C, C, 3);
        graph.ride(C, C, 2);
    }

    @Test
    public void test7() {
        graph.ride(A, C, 4);
    }

    private int distance(Town ... towns) {
        Route testRoute = new Route(towns);
        return graph.distance(testRoute);
    }

}
