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
                "x=" + x + ", " +
                "y=" + y + ", " +
                "g=" + g + ", " +
                "h=" + h + ", " +
                "f=" + f + " " +
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


    private static List<List<int[]>> scoreMap = new ArrayList<>();

    AStarAlgorithm() {
    }

    AStarAlgorithm(int[][] world) {
        this.GRID = world;
        numCols = GRID[0].length;
        numRows = GRID.length;
    }

    public ArrayList<int[]> getResult(int[] startPoint, int[] goalPoint) {
        scoreMap = new ArrayList<>();
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

    //    public boolean
    void initWorld(int[][] world) {
        this.GRID = world;
        numCols = GRID[0].length;
        numRows = GRID.length;
    }

    public int calcDistance(ArrayList<int[]> paths) {
        int distance = 0;
        for (int i = 0; i < paths.size() - 1; i++) {
            distance += (paths.get(i)[0] + paths.get(i + 1)[0]) + (paths.get(i)[1] + paths.get(i + 1)[1]) == 2 ? DIAGONAL_COST : VERTICAL_HORIZONTAL_COST;
        }
        return distance;
    }

    public ArrayList<int[]> getEndPoint(int[][] world, int[][] startPoint) {
        initWorld(world);
        Integer minDistance = Integer.MAX_VALUE;
        int[] resultEndPoint = new int[2];
        int minDistance2 = Integer.MAX_VALUE;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                // 장애물 1이 있을 경우는 end포인트로 설정 불가능
                if (this.GRID[i][j] == 1) continue;

                int totalDistance = 0;
//                ArrayList<int[]> results = new ArrayList<>();
                boolean continueFlag = false;
                ArrayList<Integer> distanceList = new ArrayList<>();
                for (int[] start : startPoint) {
                    if (i == start[0] && j == start[1]) continueFlag = true;
                    ArrayList<int[]> results2 = getResult(start, new int[]{i, j});
                    if (results2 == null) continueFlag = true;
//                    totalDistance += results2.size();
                    distanceList.add(calcDistance(results2));
                    totalDistance += calcDistance(results2);
//                    System.out.print(results2.size() + "    " + totalDistance + " | ");
                }
                if (continueFlag) continue;
                if (minDistance >= totalDistance) {
                    if (minDistance2 > Collections.max(distanceList) - Collections.min(distanceList)) {
                        minDistance2 = Collections.max(distanceList) - Collections.min(distanceList);

                        System.out.println(totalDistance);
                        System.out.println(i + ", " + j);
                        minDistance = totalDistance;
                        resultEndPoint[0] = i;
                        resultEndPoint[1] = j;
                    }
                }
            }
        }

        return new ArrayList<int[]>() {{
            add(resultEndPoint);
        }};
//        return null;
    }

    public static void main(String[] args) {
        int[] startPoint = {9, 9};
        int[] endPoint = {0, 0};
        int size = 9;
        int[][] arr = new int[size][size];

        int[][] startPoints = new int[][]{
                new int[]{9, 9},
                new int[]{4, 4},
                new int[]{1, 1},
                new int[]{0, 0},
                new int[]{0, 9},
                new int[]{9, 0},
                new int[]{4, 4}
        };

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                arr[i][j] = 0;
            }
        }

//        AStarAlgorithm a = new AStarAlgorithm();
        AStarAlgorithm a = new AStarAlgorithm(arr);
        ArrayList<int[]> result = a.getEndPoint(arr, startPoints);
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).length; j++) {
                System.out.println(result.get(i)[j]);
            }
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

//        Node2 startNode = new Node2(9, 9);
//        Node2 goalNode = new Node2(1, 1);
//
//        List<Node2> path = findPath(startNode, goalNode);
//        if (path != null) {
//            System.out.println("경로를 찾았습니다!");
//            for (Node2 node : path) {
//                System.out.println("(" + node.x + ", " + node.y + ")");
//            }
//        } else {
//            System.out.println("경로를 찾을 수 없습니다.");
//        }
    }

    public static List<Node2> findPath(Node2 startNode, Node2 goalNode) {
        PriorityQueue<Node2> openList = new PriorityQueue<>();
        Set<Node2> closedSet = new HashSet<>();
        Map<Node2, Integer> gScores = new HashMap<>();
        openList.add(startNode);
        gScores.put(startNode, 0);

        while (!openList.isEmpty()) {
            Node2 currentNode = openList.poll();

            System.out.println("---------- closed set");
            for (Node2 node : closedSet) {
                System.out.println(node);
            }
            System.out.println("---------- -------------");

            if (currentNode.x == goalNode.x && currentNode.y == goalNode.y) {
                return reconstructPath(currentNode);
            }

            closedSet.add(currentNode);

            // 4방향, 8방향
            for (int[] direction : DIRECTIONS) {
                int newX = currentNode.x + direction[0];
                int newY = currentNode.y + direction[1];
                int a[] = {newX, newY};

                if (isValid(newX, newY)) {
                    Node2 neighbor = new Node2(newX, newY);
                    int gScore = gScores.get(currentNode) + getCost(currentNode, neighbor);

                    //                    gScores.forEach((x, y) -> {
                    //                        int[] arr1  = { x.x, x.y};
                    //                        System.out.print("arr : " + Arrays.toString(arr1));
                    //                        System.out.print("   score  : " + y);
                    //                        System.out.println();
                    //                    });

                    if (closedSet.contains(neighbor) && gScore >= gScores.get(neighbor)) {
                        continue;
                    }

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

            System.out.println("---------- open list");
            System.out.println("current node " + currentNode.toString());
            openList.forEach(System.out::println);
            System.out.println("------------");

            List<int[]> li = new ArrayList<>();
            for (Node2 node : openList) {
                int[] arr = {node.x, node.y, node.f};
                li.add(arr);
            }

            scoreMap.add(li);
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


    public static List<List<int[]>> getScoreMap() {
        return scoreMap;
    }
}

