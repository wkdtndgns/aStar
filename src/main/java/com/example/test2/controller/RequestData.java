package com.example.test2.controller;

public class RequestData {
    private int[][] world;
    private int[] pathStart;
    private int[] pathEnd;

    // Getter와 Setter 생략

    // 생성자
    public RequestData() {
    }

    public RequestData(int[][] world, int[] pathStart, int[] pathEnd) {
        this.world = world;
        this.pathStart = pathStart;
        this.pathEnd = pathEnd;
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
}