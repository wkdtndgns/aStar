package com.example.test2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        int size = 9;
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
            }
        }

        int[] startPoint = {0, 0};
        int[] endPoint = {2, 2};

        AStarAlgorithm a = new AStarAlgorithm(arr);
        ArrayList<int[]> resultList = a.getResult(startPoint, endPoint);
        for (int[] array : resultList) {
            System.out.println(Arrays.toString(array));
        }

        Map<Integer, int[]> aa = a.getScoreMap();

//        aa.forEach((x, y) -> {
//            System.out.print("score : " + x);
//            System.out.print("       arr : " + Arrays.toString(y));
//            System.out.println();
//        });


    }
}
