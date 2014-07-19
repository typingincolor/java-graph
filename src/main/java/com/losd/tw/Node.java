package com.losd.tw;

/**
 * Created by andrew on 19/07/2014.
 */
public class Node {
    private String id;

    public Node(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public boolean equals(Node o) {
        return this.id == o.id;
    }
}
