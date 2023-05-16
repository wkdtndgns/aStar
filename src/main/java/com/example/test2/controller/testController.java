package com.example.test2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class testController {
    @RequestMapping("index")
    public String index(Model model) {
        System.out.println("ddddd");
        // 현재 시간 값 생성
        LocalDateTime currentTime = LocalDateTime.now();

        // 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 형식에 맞춰 시간 값을 문자열로 변환
        String formattedTime = currentTime.format(formatter);

        // 모델에 시간 값 추가
        model.addAttribute("currentTime", "sADSADSADS");

        return "index";
    }
}

class aStar {
    int[][] start;
    int[][] end;

    public aStar(int[][] start, int[][] end) {
        this.start = start;
        this.end = end;
    }

    public int[][] getStart() {
        return start;
    }

    public void setStart(int[][] start) {
        this.start = start;
    }

    public int[][] getEnd() {
        return end;
    }

    public void setEnd(int[][] end) {
        this.end = end;
    }
}
