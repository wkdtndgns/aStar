package com.example.test2.controller;

import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
public class testRest {

    @GetMapping("aStar")
    public int[][] aStar() {
        aStar as = new aStar();
        int[][] ss = new int[3][3];
        ss[0][0] = 1;
        ss[0][1] = 1;
        ss[0][2] = 1;

        return ss;
    }
}


class aStar {


    int[][] world;
    int[] start;
    int[] end;
    aStar(){

    }
}
