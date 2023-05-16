package com.example.test2.controller;

import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
public class testRest {

    @GetMapping("aStar")
    public int[][] aStar() {
        int[][] ss = new int[3][3];

        return ss;
    }
}


class aStar {
    int[][] world;
    int[] start;
    int[] end;
}
