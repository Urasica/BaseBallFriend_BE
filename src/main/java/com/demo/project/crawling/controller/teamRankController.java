package com.demo.project.crawling.controller;

import com.demo.project.crawling.model.teamRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.project.crawling.service.teamRankService;
import com.demo.project.crawling.util.teamRankConverter;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/team")
public class teamRankController {

    @Autowired
    teamRankService teamRankService;

    @PostMapping
    public ResponseEntity<String> updateTeams(@RequestBody List<List<String>> teamData){
        List<teamRank> teams = teamData.stream().map(teamRankConverter::convertToEntity).collect(Collectors.toList());
        teamRankService.updateRank(teams);
        return ResponseEntity.ok("Teams updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<teamRank>> getAllTeams() {
        List<teamRank> teams = teamRankService.getAllTeams();
        return ResponseEntity.ok(teams);
    }
}
