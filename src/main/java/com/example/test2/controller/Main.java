package com.example.test2.controller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int size = 6;
        int[][] arr = new int[size][size];


        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
            }
        }
        int[] startPoint = new int[2];
        for (int j = 0; j < 2; j++) {
            startPoint[j] = 0;
        }

        int[] endPoint = new int[2];
        for (int j = 0; j < 2; j++) {
            endPoint[j] = 1;
        }
        System.out.println("파라미터 ------------------------------");
        System.out.println("world");
        for (int[] a : arr) {
            for (int i : a) {
                System.out.print(i);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("start");
        for (int i : startPoint) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println();
        System.out.println("end");
        for (int i : endPoint) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println();
        System.out.println("------------------------------");

        Maze lab2 = new Maze(arr, startPoint, endPoint);
        BFS_Solver b2 = new BFS_Solver(lab2);
        DFS_Solver d2 = new DFS_Solver(lab2);
        AStarSolver a2 = new AStarSolver(lab2, true);

        System.out.println(b2.solve());
        System.out.println(d2.solve());
        System.out.println("ss" + a2.solve());
		a2 = new AStarSolver(lab2, false);

		System.out.println(a2.solve());
//		Square start1 = new Square(1, 0, "S");
//		Square end1 = new Square(3, 4, "E");
//
//		Maze lab1 = new Maze(6, 5, start1, end1);
//
//		lab1.setMazeWall(2, 0);
//		lab1.setMazeWall(3, 0);
//		lab1.setMazeWall(1, 2);
//		lab1.setMazeWall(2, 2);
//		lab1.setMazeWall(3, 2);
//		lab1.setMazeWall(4, 2);
//		lab1.setMazeWall(4, 3);
//		lab1.setMazeWall(1, 4);
//		lab1.setMazeWall(2, 4);
//
//		//====================
//		//====================
//
//		Maze lab2 = new Maze("./data/lab.txt");
//
//		//char[] order = {'W', 'E', 'N', 'S'};
//		//lab2.setOrder(order);
//
//		//System.out.println(lab2.toString());
//
//		BFS_Solver b2 = new BFS_Solver(lab2);
//		DFS_Solver d2 = new DFS_Solver(lab2);
//		AStarSolver a2 = new AStarSolver(lab2, true);
//
//		System.out.println(b2.solve());
//		System.out.println(d2.solve());
//		System.out.println(a2.solve());
//
//		a2 = new AStarSolver(lab2, false);
//
//		System.out.println(a2.solve());
    }
}
