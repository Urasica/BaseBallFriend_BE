package com.demo.project.crawling.controller;

import com.demo.project.crawling.dto.InningDto;
import com.demo.project.crawling.dto.TeamMatchDto;
import com.demo.project.crawling.model.Inning;
import com.demo.project.crawling.model.TeamMatch;
import com.demo.project.crawling.service.TeamMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/match")
public class TeamMatchController {

    @Autowired
    private TeamMatchService teamMatchService;

    @PostMapping("/save")
    public ResponseEntity<String> saveMatch(@RequestBody TeamMatchDto teamMatchDto) {
        // DTO -> 엔티티로 변환
        TeamMatch teamMatch = new TeamMatch();
        teamMatch.setTeamName(teamMatchDto.getTeamName());
        teamMatch.setMatchDate(teamMatchDto.getMatchDate());

        // 각 이닝 데이터를 추가
        for (InningDto inningDto : teamMatchDto.getInnings()) {
            Inning inning = new Inning();
            inning.setInningNumber(inningDto.getInningNumber());
            inning.setDetails(inningDto.getDetails());
            teamMatch.addInning(inning);  // 연관관계 편의 메서드 호출
        }

        // 중복 확인 후 저장
        teamMatchService.saveTeamMatch(teamMatch);

        return ResponseEntity.ok("Match data saved successfully!");
    }

    @GetMapping("/get")
    public ResponseEntity<TeamMatchDto> getMatch(@RequestParam String teamName, @RequestParam String matchDate) {
        // 경기 정보를 조회
        TeamMatch teamMatch = teamMatchService.findByTeamNameAndMatchDate(teamName, matchDate);

        if (teamMatch == null) {
            return ResponseEntity.notFound().build();
        }

        // 엔티티 -> DTO로 변환
        TeamMatchDto teamMatchDto = new TeamMatchDto();
        teamMatchDto.setTeamName(teamMatch.getTeamName());
        teamMatchDto.setMatchDate(teamMatch.getMatchDate());

        List<InningDto> inningDtos = teamMatch.getInnings().stream()
                .map(inning -> {
                    InningDto inningDto = new InningDto();
                    inningDto.setInningNumber(inning.getInningNumber());
                    inningDto.setDetails(inning.getDetails());
                    return inningDto;
                })
                .collect(Collectors.toList());

        teamMatchDto.setInnings(inningDtos);

        return ResponseEntity.ok(teamMatchDto);
    }
}
