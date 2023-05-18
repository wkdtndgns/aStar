package com.example.test2.controller;

import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.Map;

@RestController
public class testRest {

    @GetMapping("aStar")
    public int[][] aStar(int[][] world, int[] startPoint) {
        aStar as = new aStar();
        int[][] ss = new int[3][3];
        ss[0][0] = 1;
        ss[0][1] = 1;
        ss[0][2] = 1;

        return ss;
    }
}


class aStar {


    int[][] world;
    int[] start;
    int[] end;
    aStar(){

    }
    public static void main(String[] args) throws IOException
    {
        Square start1 = new Square(1, 0, "S");
        Square end1 = new Square(3, 4, "E");

        Maze lab1 = new Maze(6, 5, start1, end1);

        lab1.setMazeWall(2, 0);
        lab1.setMazeWall(3, 0);
        lab1.setMazeWall(1, 2);
        lab1.setMazeWall(2, 2);
        lab1.setMazeWall(3, 2);
        lab1.setMazeWall(4, 2);
        lab1.setMazeWall(4, 3);
        lab1.setMazeWall(1, 4);
        lab1.setMazeWall(2, 4);

        //====================
        //====================
        int[][] arr = new int[6][6];


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = 0;
            }
        }
        int[] startPoint = new int[2];
        for (int j = 0; j < 2; j++) {
            startPoint[j] = 0;
        }

        int[] endPoint = new int[2];
        for (int j = 0; j < 2; j++) {
            endPoint[j] = 3;
        }
        Maze lab2 = new Maze(arr,startPoint,endPoint);

        //char[] order = {'W', 'E', 'N', 'S'};
        //lab2.setOrder(order);

        //System.out.println(lab2.toString());

//        BFS_Solver b2 = new BFS_Solver(lab2);
//        DFS_Solver d2 = new DFS_Solver(lab2);
        AStarSolver a2 = new AStarSolver(lab2, true);

//        System.out.println(b2.solve());
//        System.out.println(d2.solve());
        System.out.println(a2.solve());

//        a2 = new AStarSolver(lab2, false);
//
//        System.out.println(a2.solve());
    }
}
