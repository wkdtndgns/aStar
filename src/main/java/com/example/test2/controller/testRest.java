package com.example.test2.controller;

import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class testRest {

    @PostMapping("aStar")
    @CrossOrigin(origins = "null")
    public int[][] aStar(@RequestBody RequestData requestData) {
        // 받은 요청 데이터 처리
        int[][] world = requestData.getWorld();
        int[] pathStart = requestData.getPathStart();
        int[] pathEnd = requestData.getPathEnd();

        for (int[] a : world) {
            for (int i : a) {
                System.out.print(i);
            }
            System.out.println();
        }
        System.out.println();

        for (int i : pathStart) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println();
        for (int i : pathEnd) {
            System.out.print(i);
        }
        System.out.println();
        System.out.println();

        // 응답 데이터 생성
        int[][] result = {{0, 0}, {1, 0}, {1, 1}, {1, 2}, {1, 3}};

        return result;
    }
}

class aStar {
    int[][] world;
    int[] start;
    int[] end;

    aStar() {

    }
}
