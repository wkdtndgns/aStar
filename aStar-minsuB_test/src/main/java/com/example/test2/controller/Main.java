package com.example.test2.controller;

import java.io.IOException;

public class Main 
{
	public static void main(String[] args) throws IOException 
	{

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

		BFS_Solver b2 = new BFS_Solver(lab2);
		DFS_Solver d2 = new DFS_Solver(lab2);
		AStarSolver a2 = new AStarSolver(lab2, true);

		System.out.println(b2.solve());
		System.out.println(d2.solve());
		System.out.println(a2.solve());

		a2 = new AStarSolver(lab2, true);

		System.out.println(a2.solve());
	}
}
