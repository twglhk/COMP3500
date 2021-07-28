package academy.pocu.comp3500.lab11;

import academy.pocu.comp3500.lab11.data.Point;

public final class Edge implements Comparable<Edge> {
    private final Point point1;
    private final Point point2;
    private final int sqrDistance;

    public Edge(final Point point1, final Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.sqrDistance = (int)(Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() - point2.getY(), 2));;
    }

    public Point getNode1() {
        return this.point1;
    }

    public Point getNode2() {
        return this.point2;
    }

    public int getDistance() {
        return this.sqrDistance;
    }

    @Override
    public int compareTo(Edge e) {
        return this.sqrDistance - e.sqrDistance;
    }
}
