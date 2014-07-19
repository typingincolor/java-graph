package com.losd.tw;

/**
 * Created by andrew on 19/07/2014.
 */
public class Route {
    private Town node;
    private int distance;

    public Route(Town node, int distance) {
        this.node = node;
        this.distance = distance;
    }

    public Town getNode() {
        return node;
    }

    public int getDistance() {
        return distance;
    }
}
