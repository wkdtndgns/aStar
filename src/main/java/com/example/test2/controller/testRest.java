package com.example.test2.controller;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        int direction = requestData.getDirection();
        if (pathStart[0] == pathEnd[0] && pathStart[1] == pathEnd[1]) {
            return new int[][]{pathStart};
        }

        System.out.println(" 결과 ------------------------------");

        AStarAlgorithm a = new AStarAlgorithm(world,direction);

        ArrayList<int[]> resultList = a.getResult(pathStart, pathEnd);
        int[][] result = resultList.toArray(new int[0][]);
        System.out.println(resultList);
        System.out.println("------------------------------");

        // 응답 데이터 생성
        //        int[][] result = {{0, 0}, {1, 0}, {1, 1}, {1, 2}, {1, 3}};

        return result;
    }

    @GetMapping("test")
    @CrossOrigin(origins = "null")
    public String test(){
        return "dddd";
    }

    @PostMapping("aStarHistory")
    @CrossOrigin(origins = "null")
    public List<List<int[]>> aStarHistory(@RequestBody RequestData requestData) throws IOException {
        // 받은 요청 데이터 처리
        int[][] world = requestData.getWorld();
        int[] pathStart = requestData.getPathStart();
        int[] pathEnd = requestData.getPathEnd();
        int direction = requestData.getDirection();

        AStarAlgorithm a = new AStarAlgorithm(world, direction);
        ArrayList<int[]> resultList = a.getResult(pathStart, pathEnd);
        List<List<int[]>> result = a.getScoreMap();

        int i = 1;
        for (List<int[]> outerList : result) {
            System.out.println("index:" + i);
            System.out.println();
            outerList.forEach((arr1) -> {

                System.out.print("       arr1: " + Arrays.toString(arr1));
                System.out.println();
            });

            System.out.println();
            i++;
        }
        return result;
    }
}
