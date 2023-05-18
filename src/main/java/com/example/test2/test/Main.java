package com.example.test2.test;

import com.example.test2.controller.AStarAlgorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        int size = 10;
        int[][] arr = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
            }
        }

        int[] startPoint = {0, 0};
        List<int[]> al = new ArrayList<>();
        al.add( new int[]{3, 3});
        al.add( new int[]{1, 1});
        al.add( new int[]{9, 9});
        al.add( new int[]{9, 1});

        AStarAlgorithm a = new AStarAlgorithm(arr,8);
        List<ArrayList<int[]>> al1  =  a.getResultMulti(startPoint, al);
        al1.forEach((x)->{
            for (int[] array : x) {
                System.out.print(Arrays.toString(array));
            }

            System.out.println();
            System.out.println("score : " + a.calcDistance(x));
            System.out.println();
        });


//        for (int[] array : resultList) {
//            System.out.println(Arrays.toString(array));
//        }
//
//        List<List<int[]>> aa = a.getScoreMap();
//        System.out.println();
//        int i = 1;
//        for (List<int[]> outerList : aa) {
//            System.out.println("index:" + i);
//            System.out.println();
//            outerList.forEach((arr1) -> {
//
//                System.out.print("       arr1: " + Arrays.toString(arr1));
//                System.out.println();
//            });
//
//            System.out.println();
//            i++;
//        }
    }
}
