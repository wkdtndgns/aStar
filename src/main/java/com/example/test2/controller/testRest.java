package com.example.test2.controller;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;

@RestController
public class testRest {

    @PostMapping("aStar")
    @CrossOrigin(origins = "null")
    public int[][] aStar(@RequestBody RequestData requestData) throws IOException {
        // 받은 요청 데이터 처리
        int[][] world = requestData.getWorld();
        int[] pathStart = requestData.getPathStart();
        int[] pathEnd = requestData.getPathEnd();

        // param toString
        System.out.println("파라미터 ------------------------------");
        System.out.println("world");
        for (int[] a : world) {
            for (int i : a) {
                System.out.print(i);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("start");
        for (int i : pathStart) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println();
        System.out.println("end");
        for (int i : pathEnd) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println();
        System.out.println("------------------------------");


        Maze lab2 = new Maze(world, pathStart, pathEnd);
//        System.out.println(lab2.toString());
        System.out.println(" 결과 ------------------------------");
        BFS_Solver b2 = new BFS_Solver(lab2);
        DFS_Solver d2 = new DFS_Solver(lab2);
        AStarSolver a2 = new AStarSolver(lab2, true);
        a2 = new AStarSolver(lab2, false);

        System.out.println(b2.solve());
        System.out.println(d2.solve());
        System.out.println("1111" + a2.solve());
        System.out.println("------------------------------");
        // 응답 데이터 생성
        int[][] result = {{0, 0}, {1, 0}, {1, 1}, {1, 2}, {1, 3}};

        return result;
    }
}

//class aStar {
//
//
//    int[][] world;
//    int[] start;
//    int[] end;
//
//    aStar() {
//
//    }
//
//    public static void main(String[] args) throws IOException {
//        Square start1 = new Square(1, 0, "S");
//        Square end1 = new Square(3, 4, "E");
//
//        Maze lab1 = new Maze(6, 5, start1, end1);
//
//        lab1.setMazeWall(2, 0);
//        lab1.setMazeWall(3, 0);
//        lab1.setMazeWall(1, 2);
//        lab1.setMazeWall(2, 2);
//        lab1.setMazeWall(3, 2);
//        lab1.setMazeWall(4, 2);
//        lab1.setMazeWall(4, 3);
//        lab1.setMazeWall(1, 4);
//        lab1.setMazeWall(2, 4);
//
//        //====================
//        //====================
//
////        Maze lab2 = new Maze("./lab.txt");
//
//        //char[] order = {'W', 'E', 'N', 'S'};
//        //lab2.setOrder(order);
//
//        //System.out.println(lab2.toString());
//
////        BFS_Solver b2 = new BFS_Solver(lab2);
////        DFS_Solver d2 = new DFS_Solver(lab2);
////        AStarSolver a2 = new AStarSolver(lab2, true);
//
////        System.out.println(b2.solve());
////        System.out.println(d2.solve());
////        String result = a2.solve();
//        result = result.replaceAll("[\\[\\]\\s]", "");
//        String[] strArray = result.split(",");
////        System.out.println(strArray);
////        System.out.println(a2.solve());
////        for (String s: strArray) {
////            System.out.print(Integer.valueOf(s));
////        }
//        int[][] resultIntList = new int[strArray.length / 2][2];
//        int col = 0;
//        System.out.println(strArray.length);
//        System.out.println(strArray[0]);
//        for (int i = 1; i < strArray.length + 1; i++) {
//            System.out.print(col + " " + i % 2 + ",  ");
//            resultIntList[col][i % 2] = Integer.valueOf(strArray[strArray.length - i]);
//            if (i % 2 == 0) {
//                col++;
//            }
//        }
//
//        for (int[] a : resultIntList
//        ) {
//            for (int i : a
//            ) {
//                System.out.print("[" + i + "]");
//            }
//            System.out.println();
//        }
////        a2 = new AStarSolver(lab2, false);
////
////        System.out.println(a2.solve());
//    }
//}
