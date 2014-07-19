package com.losd.tw;

/**
 * Created by andrew on 19/07/2014.
 */
public class Route {
    private Node node;
    private int distance;

    public Route(Node node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    private Node getNode() {
        return node;
    }

    private int getDistance() {
        return distance;
    }
}
