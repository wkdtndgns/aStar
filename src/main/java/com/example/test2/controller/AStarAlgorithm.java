package com.example.test2.controller;

import java.util.*;

class Node2 implements Comparable<Node2> {
    int x, y; // 노드의 좌표
    int g; // 시작 노드로부터의 비용
    int h; // 목표 노드까지의 예상 비용
    int f; // 총 비용 (g + h)
    Node2 parent; // 이전 노드

    public Node2(int x, int y) {
        this.x = x;
        this.y = y;
        g = 0;
        h = 0;
        f = 0;
        parent = null;
    }

    @Override
    public int compareTo(Node2 other) {
        return Integer.compare(this.f, other.f);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Node2 other = (Node2) obj;
        return x == other.x && y == other.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Node2{ " +
                "x=" + x +", "+
                "y=" + y +", "+
                "g=" + g +", "+
                "h=" + h +", "+
                "f=" + f +" "+
                '}';
    }
}

public class AStarAlgorithm {
    private static final int DIAGONAL_COST = 14;
    private static final int VERTICAL_HORIZONTAL_COST = 10;

    private static int[][] GRID = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}
    };

    private static int numRows = GRID.length;
    private static int numCols = GRID[0].length;


    private static  Map<Integer, int[]> scoreMap = new HashMap<>();

    AStarAlgorithm() {
    }

    AStarAlgorithm(int[][] world) {
        this.GRID = world;
        numCols = GRID[0].length;
        numRows = GRID.length;
    }

    public ArrayList<int[]> getResult(int[] startPoint, int[] goalPoint) {
        scoreMap = new HashMap<>();
        Node2 startNode = new Node2(startPoint[0], startPoint[1]);
        Node2 goalNode = new Node2(goalPoint[0], goalPoint[1]);
        List<Node2> path = findPath(startNode, goalNode);
        ArrayList<int[]> resultList = new ArrayList<>();

        if (path != null) {
            for (Node2 node : path) {
                resultList.add(new int[]{node.x, node.y});
            }
            return resultList;
        } else {
            return null;
        }
    }

    public static List<Node2> findPath(Node2 startNode, Node2 goalNode) {
        PriorityQueue<Node2> openList = new PriorityQueue<>();
        Set<Node2> closedSet = new HashSet<>();
        Map<Node2, Integer> gScores = new HashMap<>();
        openList.add(startNode);
        gScores.put(startNode, 0);

        while (!openList.isEmpty()) {
            Node2 currentNode = openList.poll();

            if (currentNode.x == goalNode.x && currentNode.y == goalNode.y) {
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode);

            // 4방향, 8방향
            for (int[] direction : DIRECTIONS) {
                int newX = currentNode.x + direction[0];
                int newY = currentNode.y + direction[1];
                int a[] ={newX,newY};

                if (isValid(newX, newY)) {
                    Node2 neighbor = new Node2(newX, newY);
                    int gScore = gScores.get(currentNode) + getCost(currentNode, neighbor);
                    scoreMap.put(gScore, a);

                    gScores.forEach((x, y) -> {
                        int[] arr1  = { x.x, x.y};
                        System.out.print("arr : " + Arrays.toString(arr1));
                        System.out.print("   score  : " + y);
                        System.out.println();
                    });

                    // openList 출력
                    for (Node2 node : openList) {
                        System.out.println(node);
                    }
                    System.out.println("-----------------------------");
                    if (closedSet.contains(neighbor) && gScore >= gScores.get(neighbor)) {
                        continue;
                    }

                    System.out.println();
                    if (!openList.contains(neighbor) || gScore < gScores.get(neighbor)) {
//                        System.out.println("이웃에 값을 넣어줌");
                        neighbor.g = gScore;
                        neighbor.h = caculateEuclid(neighbor, goalNode);
                        neighbor.f = neighbor.g + neighbor.h;
                        neighbor.parent = currentNode;

                        openList.remove(neighbor);
                        openList.add(neighbor);
                        gScores.put(neighbor, gScore);
                    }
                }
            }
        }

        return null;
    }

    private static List<Node2> reconstructPath(Node2 currentNode) {
        List<Node2> path = new ArrayList<>();
        while (currentNode != null) {
            path.add(0, currentNode);
            currentNode = currentNode.parent;
        }
        return path;
    }

    private static int getCost(Node2 currentNode, Node2 neighbor) {
        int dx = Math.abs(currentNode.x - neighbor.x);
        int dy = Math.abs(currentNode.y - neighbor.y);
        if (dx + dy == 2) {
            return DIAGONAL_COST;
        } else {
            return VERTICAL_HORIZONTAL_COST;
        }
    }

    private static int calculateHeuristic(Node2 node, Node2 goalNode) {
        int dx = Math.abs(node.x - goalNode.x);
        int dy = Math.abs(node.y - goalNode.y);
        return (dx + dy) * VERTICAL_HORIZONTAL_COST;
    }

    private static int caculateEuclid(Node2 node, Node2 goalNode) {
        int dx = Math.abs(node.x - goalNode.x);
        int dy = Math.abs(node.y - goalNode.y);
        return (int) (Math.sqrt(dx * dx + dy * dy) * VERTICAL_HORIZONTAL_COST); // 유클리드 거리 계산
    }


    private static boolean isValid(int x, int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols && GRID[x][y] == 0;
    }


    public static Map<Integer, int[]> getScoreMap() {
        return scoreMap;
    }
}

