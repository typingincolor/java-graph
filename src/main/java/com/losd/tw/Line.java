package com.losd.tw;

/**
 * Represenation of a railway line
 */
public class Line {
    private Town startTown;
    private Town endTown;
    private int distance;

    public Town getStartTown() {
        return startTown;
    }

    public Town getEndTown() {
        return endTown;
    }

    public int getDistance() {
        return distance;
    }

    public Line(Town startTown, Town endTown, int distance) {
        if (startTown.equals(endTown)) {
            throw new InvalidRouteException();
        }

        this.startTown = startTown;
        this.endTown = endTown;
        this.distance = distance;
    }
}
