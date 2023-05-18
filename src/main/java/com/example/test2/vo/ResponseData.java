package com.example.test2.vo;

import java.util.List;

public class ResponseData {
    int[][] path;
    List<List<int[]>> history;

    long time;

    public int[][] getPath() {
        return path;
    }

    public void setPath(int[][] path) {
        this.path = path;
    }

    public List<List<int[]>> getHistory() {
        return history;
    }

    public void setHistory(List<List<int[]>> history) {
        this.history = history;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
