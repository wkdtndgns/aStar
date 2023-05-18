package com.example.test2.vo;

public class RequestData {
    private int[][] world;
    private int[] pathStart;
    private int[] pathStart1;
    private int[] pathStart2;
    private int[] pathEnd;
    private int direction;

    private int Heuristic;

    public RequestData(int[][] world, int[] pathStart, int[] pathEnd, int direction, int heuristic) {
        this.world = world;
        this.pathStart = pathStart;
        this.pathEnd = pathEnd;
        this.direction = direction;
        Heuristic = heuristic;
    }

    // Getter와 Setter 생략
    public int[][] getWorld() {
        return world;
    }

    public void setWorld(int[][] world) {
        this.world = world;
    }

    public int[] getPathStart() {
        return pathStart;
    }
    public int[] getPathStart1() {
        return pathStart1;
    }
    public int[] getPathStart2() {
        return pathStart2;
    }
    public void setPathStart(int[] pathStart) {
        this.pathStart = pathStart;
    }

    public int[] getPathEnd() {
        return pathEnd;
    }

    public void setPathEnd(int[] pathEnd) {
        this.pathEnd = pathEnd;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getHeuristic() {
        return Heuristic;
    }

    public void setHeuristic(int heuristic) {
        Heuristic = heuristic;
    }
}