package com.demo.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/live")
    public ModelAndView live(@RequestParam("teamName1") String teamName1, @RequestParam("teamName2") String teamName2, @RequestParam("matchDate") String matchDate) {
        // 쿼리 파라미터를 사용하여 데이터 처리
        ModelAndView modelAndView = new ModelAndView("live");
        modelAndView.addObject("teamName", teamName1);
        modelAndView.addObject("teamName", teamName2);
        modelAndView.addObject("matchDate", matchDate);
        return modelAndView;
    }

    @GetMapping("/rank")
    public String rank() {
        return "rank";
    }

    @GetMapping("/team")
    public String team() {
        return "team";
    }

    @GetMapping("/board")
    public String board() {
        return "board";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/control/admin/board")
    public String controlBoard() {
        return "board-admin";
    }
}
