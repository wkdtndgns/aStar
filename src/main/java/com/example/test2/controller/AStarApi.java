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
public String handleAStarMultiRequest(@RequestBody RequestData requestData) {
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

    int size = 10;
    int[][] arr = new int[size][size];
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            arr[i][j] = 0;
        }
    }

    int[] startPoint = {0, 0};
    int[][] startPoints = new int[][]{
            new int[]{9, 9},
            new int[]{9, 0},
            new int[]{0, 0},
    };
//    List<int[]> al = new ArrayList<>();
//    al.add( new int[]{pathStart[0], pathStart[1]});
//    al.add( new int[]{pathStart1[0], pathStart1[1]});
//    al.add( new int[]{pathStart2[0], pathStart2[1]});
//
    AStarAlgorithm a = new AStarAlgorithm(world,8);
//    List<ArrayList<int[]>> al1  =  a.getResultMulti(startPoint, al);
//    al1.forEach((x)->{
//        for (int[] array : x) {
//            System.out.print(Arrays.toString(array));
//        }
//
//        System.out.println();
//        System.out.println("score : " + a.calcDistance(x));
//        System.out.println();
//    });
    ArrayList<ArrayList<int[]>> al2 = a.getEndPoint(world, startPoints);
    for (int i = 0; i < al2.size(); i++) {
        for (int j = 0; j < al2.get(i).size(); j++) {
            for (int k = 0; k < al2.get(i).get(k).length; k++) {
                System.out.print(al2.get(i).get(k)[k] + ", ");
            }
            System.out.print(" | ");
        }
        System.out.println();
    }


//        for (int[] array : resultList) {
//            System.out.println(Arrays.toString(array));
//        }
//
//        List<List<int[]>> aa = a.getScoreMap();
//        System.out.println();
//        int i = 1;
//        for (List<int[]> outerList : aa) {
//            System.out.println("index:" + i);
//            System.out.println();
//            outerList.forEach((arr1) -> {
//
//                System.out.print("       arr1: " + Arrays.toString(arr1));
//                System.out.println();
//            });
//
//            System.out.println();
//            i++;
//        }


    return "223";
}
}