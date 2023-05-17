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
}

public class AStarAlgorithm {
    private static final int DIAGONAL_COST = 14;
    private static final int VERTICAL_HORIZONTAL_COST = 10;

    private static int[][] GRID = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 1, 0, 1, 1, 0, 1, 0},
            {0, 1, 0, 1, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
    };

    private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };

    AStarAlgorithm(int[][] world){
        this.GRID = world;
    }
    private static final int numRows = GRID.length;
    private static final int numCols = GRID[0].length;

    public ArrayList<Integer> getResult(Integer[] startPoint, Integer[] goalPoint){
        Node2 startNode = new Node2(startPoint[0], startPoint[1]);
        Node2 goalNode = new Node2(goalPoint[0], goalPoint[1]);
        List<Node2> path = findPath(startNode, goalNode);
        ArrayList<Integer> resultList = new ArrayList<>();
        if (path!=null){
            for (Node2 node : path){
                resultList.add(node.x);
                resultList.add(node.y);
            }
            return resultList;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Node2 startNode = new Node2(0, 0);
        Node2 goalNode = new Node2(6, 5);

        List<Node2> path = findPath(startNode, goalNode);
        if (path != null) {
            System.out.println("경로를 찾았습니다!");
            for (Node2 node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("경로를 찾을 수 없습니다.");
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

            for (int[] direction : DIRECTIONS) {
                int newX = currentNode.x + direction[0];
                int newY = currentNode.y + direction[1];

                if (isValid(newX, newY)) {
                    Node2 neighbor = new Node2(newX, newY);
                    int gScore = gScores.get(currentNode) + getCost(currentNode, neighbor);

                    if (closedSet.contains(neighbor) && gScore >= gScores.get(neighbor)) {
                        continue;
                    }

                    if (!openList.contains(neighbor) || gScore < gScores.get(neighbor)) {
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

    private static int caculateEuclid(Node2 node, Node2 goalNode){
        int dx = Math.abs(node.x - goalNode.x);
        int dy = Math.abs(node.y - goalNode.y);
        return (int) (Math.sqrt(dx * dx + dy * dy) * VERTICAL_HORIZONTAL_COST); // 유클리드 거리 계산
    }


    private static boolean isValid(int x, int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols && GRID[x][y] == 0;
    }
}

