package com.example.test2.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        int[] endPoint = {3, 3};

        AStarAlgorithm a = new AStarAlgorithm(arr);
        ArrayList<int[]> resultList = a.getResult(startPoint, endPoint);
        for (int[] array : resultList) {
            System.out.println(Arrays.toString(array));
        }

        List<List<int[]>> aa = a.getScoreMap();
        System.out.println();
        int i = 1;
        for (List<int[]> outerList : aa) {
            System.out.println("index:" + i);
            System.out.println();
            outerList.forEach((arr1) -> {

                System.out.print("       arr1: " + Arrays.toString(arr1));
                System.out.println();
            });

            System.out.println();
            i++;
        }


    }
}
