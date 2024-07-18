package com.demo.project.crawling.controller;

import com.demo.project.crawling.dto.TeamRankDTO;
import com.demo.project.crawling.model.TeamRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.demo.project.crawling.service.TeamRankService;
import com.demo.project.crawling.util.TeamRankConverter;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/team")
public class TeamRankController {

    @Autowired
    TeamRankService teamRankService;

    @PostMapping
    public ResponseEntity<String> updateTeams(@RequestBody List<TeamRankDTO> teamData){
        List<TeamRank> teams = teamData.stream().map(TeamRankConverter::convertToEntity).collect(Collectors.toList());
        teamRankService.updateRank(teams);
        return ResponseEntity.ok("Teams updated successfully");
    }

    @GetMapping
    public ResponseEntity<List<TeamRank>> getAllTeams() {
        List<TeamRank> teams = teamRankService.getTeamRank();
        return ResponseEntity.ok(teams);
    }
}
