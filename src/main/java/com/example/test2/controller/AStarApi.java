package com.example.test2.controller;

import com.example.test2.vo.RequestData;
import com.example.test2.vo.ResponseData;
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

        // response
        ResponseData responseData = new ResponseData();
        if (pathStart[0] == pathEnd[0] && pathStart[1] == pathEnd[1]) {
            responseData.setPath(new int[][]{pathStart});
            return responseData;
        }

        // 알고리즘 시작
        AStarAlgorithm a = new AStarAlgorithm(world, direction);
        ArrayList<int[]> resultList = a.getResult(pathStart, pathEnd);
        int[][] result = resultList.toArray(new int[0][]);

        // 응답 데이터 생성
        responseData.setPath(result);
        responseData.setHistory(a.getScoreMap());
        responseData.setTime(a.getTime());
        return responseData;
    }
}
