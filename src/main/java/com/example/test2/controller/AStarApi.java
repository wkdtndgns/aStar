package com.example.test2.controller;

import com.example.test2.vo.RequestData;
import com.example.test2.vo.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

@RestController
public class AStarApi {
    @PostMapping("aStar")
    @CrossOrigin(origins = "null")
    public ResponseData aStar(@RequestBody RequestData requestData) throws IOException {
        // 받은 요청 데이터 처리
        int[][] world = requestData.getWorld();
        int[] pathStart = requestData.getPathStart();
        int[] pathEnd = requestData.getPathEnd();
        int direction = requestData.getDirection();
        int heuristic = requestData.getHeuristic();
        // response
        ResponseData responseData = new ResponseData();
        if (pathStart[0] == pathEnd[0] && pathStart[1] == pathEnd[1]) {
            responseData.setPath(new int[][]{pathStart});
            return responseData;
        }

        // 알고리즘 시작
        AStarAlgorithm a = new AStarAlgorithm(world, direction);
        a.setHeuristic(heuristic);
        ArrayList<int[]> resultList = a.getResult(pathStart, pathEnd);
        int[][] result = resultList.toArray(new int[0][]);

        // 응답 데이터 생성
        responseData.setPath(result);
        responseData.setHistory(a.getScoreMap());
        responseData.setTime(a.getTime());
        return responseData;
    }



//    @PostMapping("aStarMulti")
//    @CrossOrigin(origins = "null")
//    public int[] aStarMulti(@RequestBody int[][] requestData) throws IOException {
//        // 받은 요청 데이터 처리
//        //int[][] startMulti = requestData.getStartMulti();
//        System.out.println(requestData);
//
//        return new int[]{0,1};
//    }
@PostMapping("/aStarMulti")
@CrossOrigin(origins = "null")
public int[] handleAStarMultiRequest(@RequestBody RequestData requestData) {
    int[][] world= requestData.getWorld();
    int[] pathStart = requestData.getPathStart();
    int[] pathStart1 = requestData.getPathStart1();
    int[] pathStart2 = requestData.getPathStart2();


    for (int[] row : world) {
        for (int value : row) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    // 받은 좌표를 활용하여 필요한 작업 수행
    System.out.println("pathStart:");
    for (int value : pathStart) {
        System.out.print(value + ",");
    }
    System.out.println();

    System.out.println("pathStart1:");
    for (int value : pathStart1) {
        System.out.println(value+",");
    }
    System.out.println();
    System.out.println("pathStart2:");
    for (int value : pathStart2) {
        System.out.println(value+",");
    }
    System.out.println();

    //
    System.out.println(pathStart.toString());
    int size = 9;
    int[][] arr = new int[size][size];

    int[][] startPoints = new int[][]{
            pathStart,
            pathStart1,
            pathStart2
//            new int[]{9, 9},
//            new int[]{9, 0},
//            new int[]{0, 0},
    };

    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            arr[i][j] = 0;
        }
    }

//        AStarAlgorithm a = new AStarAlgorithm();
    // 휴리스틱 함수 선택 인덱스 0 : 맨허튼, 1 : 유클리드
    AStarAlgorithm a = new AStarAlgorithm(world, 8);
    int heuristicIndex = 0;
    a.setHeuristic(heuristicIndex);
    long start = System.nanoTime();
    ArrayList<ArrayList<int[]>> result = a.getEndPoint(world, startPoints);
    long end = System.nanoTime();
    System.out.println("휴리스틱 인덱스" + heuristicIndex + " : " + (end - start));
    for (int i = 0; i < result.size(); i++) {
        for (int j = 0; j < result.get(i).size(); j++) {
            for (int k = 0; k < result.get(i).get(j).length; k++) {
                System.out.print(result.get(i).get(j)[k] + ", ");
            }
            System.out.print(" ");
        }
        System.out.println();
    }

    return new int[]{
            result.get(0).get(result.get(0).size()-1)[0],
            result.get(0).get(result.get(0).size()-1)[1]
    };
}
}