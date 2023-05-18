package com.example.test2.vo;

public class RequestData {
    private int[][] world;
    private int[] pathStart;
    private int[] pathEnd;
    private int direction;

    // Getter와 Setter 생략

    // 생성자
    public RequestData() {
    }

    public RequestData(int[][] world, int[] pathStart, int[] pathEnd, int i ) {
        this.world = world;
        this.pathStart = pathStart;
        this.pathEnd = pathEnd;
        this.direction = i;
    }

    public int[][] getWorld() {
        return world;
    }

    public void setWorld(int[][] world) {
        this.world = world;
    }

    public int[] getPathStart() {
        return pathStart;
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
}