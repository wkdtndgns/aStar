package com.example.test2.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
	private Square[][] grid;
	private Square start;
	private Square end;
	private Square currState;
	public int lMax;
	public int cMax;
	public char[] order = {'N', 'E', 'S', 'W'}; //Shift order in the grid during solving in cardinals

	/*
	 * Constructor with no file
	 * lRange: Number of lines in the maze
	 * cRange: Number of columns in the maze
	 * start: The starting square of the maze
	 * end: the ending square of the maze
	 */
	public Maze(int lRange, int cRange, Square start, Square end) {
		//Init grid
		this.grid = new Square[lRange][cRange];
		//Set max values
		this.lMax = lRange;
		this.cMax = cRange;
		for (int i = 0; i < lRange; i++) {
			for (int j = 0; j < cRange; j++) {
				this.grid[i][j] = new Square(i, j, " ");
			}
		}

		this.assignMazeToGridSquares();

		//Create start and end MazeState objects contaning the start and end squares (stated)
		this.end = end;
		this.start = start;

		this.currState = this.getStart();
		//At this point, the grid is and stay the inital Maze unsolved.
	}


	//, int[][] arr, int[] startPoint, int[] endPoint
	public Maze(int[][] arr,int[] startPoint, int[] endPoint) throws IOException {
		try {
			BufferedReader file = new BufferedReader(new FileReader("./lab.txt"));
			String line = file.readLine();
			System.out.println(line + "testData");
			String buffer = line.toUpperCase();

			//파라미터 하드코딩, 월드,스타트,엔드
			int count = 0;


			//Start,End 포인트 마킹
			arr[startPoint[0]][startPoint[1]] = 1;
			arr[endPoint[0]][endPoint[1]] = 9;

			//Get columns range
			//this.cMax = line.length();
			//this.lMax = 1;


			//cMax ,lMax 재 설정
			this.cMax = arr.length;
			this.lMax = arr[1].length;

			System.out.println(cMax + "value check" + lMax);
			// cMax 값 교체
			/*
			 * endPoint 값 바꿔주기 -> E로
			 * startPoint 값 바꿔주기 -> 로
			 *
			 * */
			//lMax 값도 arr[1].length


			//int[][]로 넘어온 값 바꿔주기
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < arr.length; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					sb.append(arr[i][j]);
				}
			}
			String temp = sb.toString();
			System.out.println(temp + "temptest");
			temp = temp.replace("0", "O");
			temp = temp.replace("1", "S");
			temp = temp.replace("9", "E");
			temp = temp.replace("5", "X");


			System.out.println(temp + "temptest");
			String flag="SOOXOOXOOXXOXOOXOXOOEOXOXOOXXOXOXXOO";
			System.out.println(flag.length());
			System.out.println(arr.length + "check arr.length");
			//Get lines range
//         while((line = file.readLine()) != null)
//         {
//            System.out.println(line + "testData");
//
//            this.lMax++;
//            buffer += line.toUpperCase();
//         }

			System.out.println(temp + "testData");
			System.out.println(temp.length());

			String check = "XOOOOXSOXOXXXOXOXXXOXOOEXOXXOXXOOOOX";
			System.out.println(check.length());

			//Init grid
			this.grid = new Square[this.lMax][this.cMax];

			for (int i = 0; i < this.lMax; i++) {
				for (int j = 0; j < this.cMax; j++) {
					switch (flag.charAt(count++)) {
						case 'E':
							this.setEnd(new Square(i, j, "E"));
							break;

						case 'S':
							this.setStart(new Square(i, j, "S"));
							break;

						case 'X':
							this.grid[i][j] = new Square(i, j, true);
							break;

						default:
							this.grid[i][j] = new Square(i, j, " ");
							break;
					}
				}
			}

			file.close();

			this.assignMazeToGridSquares();

			this.currState = this.getStart();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Maze(Square[][] grid, Square start, Square end, Square currState, int lMax, int cMax) {
		this.grid = grid;
		this.start = start;
		this.end = end;
		this.currState = currState;
		this.lMax = lMax;
		this.cMax = cMax;
	}

	/*
	 * Retuns the starting Square
	 */
	public Square getStart() {
		return start;
	}

	/*
	 * Sets the starting Square for the attribute and the grid
	 * start: The square to set as starting square
	 */
	public void setStart(Square start) {
		this.start = start;
		this.grid[start.getLine()][start.getCol()] = start;
	}

	/*
	 * Returns the ending Square
	 */
	public Square getEnd() {
		return end;
	}

	/*
	 * Sets the ending Square for the attribute and the grid
	 * end: The square to set as ending square
	 */
	public void setEnd(Square end) {
		this.end = end;
		this.grid[end.getLine()][end.getCol()] = end;
	}

	/*
	 * Sets a wall in the maze
	 * l: Line position of the wall
	 * c: Column position of the wall
	 */
	public void setMazeWall(int l, int c) {
		this.grid[l][c].setWall();
	}

	public Square getCurrState() {
		return this.currState;
	}

	public void setCurrState(Square c) {
		this.currState = c;
	}

	public void setNextState(Square c) {
		this.grid[this.currState.getLine()][this.currState.getCol()].setAttribute("*");
		this.currState = c;
	}

	public void assignMazeToGridSquares() {
		for (int i = 0; i < this.lMax; i++) {
			for (int j = 0; j < this.cMax; j++) {
				this.grid[i][j].assignMaze(this);
			}
		}
	}

	/*
	 * Initiates the maze
	 */
	public void initMaze() {
		//Init grid
		this.resetGrid();

		this.currState = this.getStart();
	}

	public void resetGrid() {
		for (int i = 0; i < this.lMax; i++) {
			for (int j = 0; j < this.cMax; j++) {
				if (this.grid[i][j].getAttribute() == "*")
					this.grid[i][j].setAttribute(" ");
			}
		}
	}

	/*
	 * Sets a new shift order
	 * newOrder: 4 entries char array, each char (in caps) means a shift direction, for example : ['N', 'S', 'W', 'E'] will shifts North -> South -> West -> East.
	 */
	public void setOrder(char[] newOrder) {
		if (newOrder.length == 4) {
			this.order = newOrder;
		}
	}

	/*
	 * Resets the default order North -> East -> South -> West (Clockwise)
	 */
	public void resetOrder() {
		char[] o = {'N', 'E', 'S', 'W'};
		this.order = o;
	}

	/*
	 * Returns the maze grid
	 */
	public Square[][] getGrid() {
		return grid;
	}

	public Maze clone() {
		return new Maze(this.grid, this.start, this.end, this.currState, this.lMax, this.cMax);
	}

	/*
	 * Get the maze in a unicode box draw format
	 */
	public String printMaze() {
		String res = "   ";
		String res_under = "";
		Square temp = null;
		Square templineunder = null;
		Square tempnextcol = null;
		Square tempdiag = null;

		//Columns numbers
		for (int i = 0; i < cMax; i++) {
			if (i < 10)
				res += "  " + i + " ";
			else
				res += "  " + i;
		}
		res += "\n   ╔";

		//First row : Maze top edge
		for (int i = 1; i < this.cMax; i++) {
			temp = this.grid[0][i - 1];
			tempnextcol = this.grid[0][i];
			if (temp.isWall())
				res += "═══╤";
			else if (tempnextcol.isWall())
				res += "═══╤";
			else
				res += "════";
		}
		res += "═══╗\n";

		//Browse all squares
		// res = the line containing the square states
		// res_under = the graphics under the squares line with the corner unicode characters
		// contatenation of res + res_under at each line
		//Example :
		//      │   │   │   │ <- res
		//      └───┼───┼───┘ <- res_under
		//          │   │     <- res
		//          └───┘     <- res_under
		//      etc...
		for (int l = 0; l < this.lMax; l++) {
			res_under = "";
			for (int c = 0; c < this.cMax; c++) {
				//Get Squares
				temp = this.grid[l][c]; // = A -> Current square
				tempnextcol = temp.getEast(); // = B -> Square at the right of temp
				templineunder = temp.getSouth(); // = C -> Square below temp
				if (l < this.lMax - 1 && c < this.cMax - 1)
					tempdiag = templineunder.getEast(); // = D -> Square in the temp lower right-hand diagonal

				if (c == 0) //First colomn of current line l
				{
					if (l < 10)
						res += l + "  ║";
					else
						res += l + " ║";

					if (templineunder != null) {
						if (temp.isWall() || templineunder.isWall())
							res_under += "   ╟";
						else
							res_under += "   ║";
					}
				}

				if (temp.isWall()) {
					res += "   ";
					res_under += "───";
				} else {
					if (temp.getLine() == this.currState.getLine() && temp.getCol() == this.currState.getCol())
						res += " o ";
					else if (temp.getLine() == this.start.getLine() && temp.getCol() == this.start.getCol())
						res += " S ";
					else if (temp.getLine() == this.end.getLine() && temp.getCol() == this.end.getCol())
						res += " E ";
					else
						res += " " + temp.getAttribute() + " ";


					if (l < this.lMax - 1) {
						if (templineunder.isWall())
							res_under += "───";
						else
							res_under += "   ";
					}
				}

				//Maze right edge
				if (c == this.cMax - 1) {
					res += "║";
					if (temp != null && templineunder != null) {
						if (temp.isWall() || templineunder.isWall())
							res_under += "╢";
						else
							res_under += "║";
					}
				} else {
					//Squares corners.
					// two cases : wall square or not
					if (temp.isWall()) {
						res += "│";
						if (templineunder != null && tempdiag != null && tempnextcol != null) {
							//"┼" = (B + D).(C + D) -> The most reccurent corner to write
							if ((tempnextcol.isWall() || tempdiag.isWall()) && (templineunder.isWall() || tempdiag.isWall()))
								res_under += "┼";
							else {
								if (!templineunder.isWall() && !tempdiag.isWall() && !tempnextcol.isWall()) //Wall on top left only
									res_under += "┘";
								else if (!templineunder.isWall() && !tempdiag.isWall() && tempnextcol.isWall()) // Walls on top
									res_under += "┴";
								else if (templineunder.isWall() && !tempdiag.isWall() && !tempnextcol.isWall()) // Walls on left
									res_under += "┤";
							}
						}
					} else {
						if (tempnextcol != null) {
							if (tempnextcol.isWall())
								res += "│";
							else
								res += " ";

							if (templineunder != null && tempdiag != null) {
								//"┼" = (C).(D) -> The most reccurent corner to write
								if (templineunder.isWall() && tempnextcol.isWall())
									res_under += "┼";
								else {
									if (!templineunder.isWall() && !tempdiag.isWall() && !tempnextcol.isWall()) //No wall
										res_under += " ";
									else if (!templineunder.isWall() && tempdiag.isWall() && !tempnextcol.isWall()) //Wall on right below
										res_under += "┌";
									else if (templineunder.isWall() && !tempdiag.isWall() && !tempnextcol.isWall()) //Wall on left below
										res_under += "┐";
									else if (!templineunder.isWall() && !tempdiag.isWall() && tempnextcol.isWall()) //Wall on top right
										res_under += "└";
									else if (!templineunder.isWall() && tempdiag.isWall() && tempnextcol.isWall()) //Walls on right
										res_under += "├";
									else if (templineunder.isWall() && tempdiag.isWall() && !tempnextcol.isWall()) //Walls below
										res_under += "┬";
								}
							}
						}
					}
				}
			}//<- for each column
			if (l < this.lMax - 1)
				res += "\n" + res_under + "\n"; //Concatenate res + res_under
			else {
				//Maze bottom edge
				res += "\n   ╚";
				for (int i = 1; i < this.cMax; i++) {
					temp = this.getGrid()[l][i - 1];
					if (temp.getEast().isWall() || temp.isWall())
						res += "═══╧";
					else
						res += "════";
				}
				res += "═══╝\n";
			}
		}

      /*//Affichage simple
      String res = "   ";
      for(int i = 0; i < cMax; i++)
      {
         if(i >= 9)
            res += " " + i;
         else
            res += " " + i + " ";
      }
      res += "\n";
      for(int i = 0; i < this.lMax; i++)
      {
         if(i > 9)
            res += i + " ";
         else
            res += i + "  ";

         for(int j = 0; j < this.cMax; j++)
         {
            if(this.getGrid()[i][j].getAttribute() != "" && !this.getGrid()[i][j].isWall())
               res += "[" + this.getGrid()[i][j].getAttribute() + "]";
            else
               res += "[■]";
         }
         res += "\n";
      }*/
		return res;
	}

	public String toString() {
		return this.currState.toString();
	}
}