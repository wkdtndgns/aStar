package com.example.test2.test;

import com.example.test2.controller.AStarAlgorithm;

import java.util.ArrayList;

public class test1 {
    public static void main(String[] args) {
        int size = 9;
        int[][] arr = new int[size][size];

        int[][] startPoints = new int[][]{
                new int[]{9, 9},
                new int[]{9, 0},
                new int[]{0, 0},
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
            }
        }

//        AStarAlgorithm a = new AStarAlgorithm();
        AStarAlgorithm a = new AStarAlgorithm(arr, 8);
        ArrayList<ArrayList<int[]>> result = a.getEndPoint(arr, startPoints);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                for (int k = 0; k < result.get(i).get(j).length; k++) {
                    System.out.print(result.get(i).get(j)[k] + ", ");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
//        ArrayList<int[]> b = a.getResult(startPoint, endPoint);
//        for (int[] nums :
//                b) {
//            for (int qwer :
//                    nums) {
//                System.out.print(qwer);
//            }
//            System.out.println();
//        }

//        Node startNode = new Node(9, 9);
//        Node goalNode = new Node(1, 1);
//
//        List<Node> path = findPath(startNode, goalNode);
//        if (path != null) {
//            System.out.println("경로를 찾았습니다!");
//            for (Node node : path) {
//                System.out.println("(" + node.x + ", " + node.y + ")");
//            }
//        } else {
//            System.out.println("경로를 찾을 수 없습니다.");
//        }
    }
}
