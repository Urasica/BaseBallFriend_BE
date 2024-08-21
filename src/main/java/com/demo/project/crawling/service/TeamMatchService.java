package com.demo.project.crawling.service;

import com.demo.project.crawling.model.TeamMatch;
import com.demo.project.crawling.repository.TeamMatchRepository;
import org.springframework.stereotype.Service;

@Service
public class TeamMatchService {

    private final TeamMatchRepository teamMatchRepository;

    public TeamMatchService(TeamMatchRepository teamMatchRepository) {
        this.teamMatchRepository = teamMatchRepository;
    }

    public void saveTeamMatch(TeamMatch newMatch) {
        // 중복 확인
        TeamMatch existingMatch = teamMatchRepository.findByTeamNameAndMatchDate(newMatch.getTeamName(), newMatch.getMatchDate());

        if (existingMatch != null) {
            // 중복된 경우, 해당 경기를 업데이트할 수 있습니다.
            return;
        }

        // 중복이 아니라면 새로 저장
        teamMatchRepository.save(newMatch);
    }

    // 경기 정보를 조회하는 메서드
    public TeamMatch findByTeamNameAndMatchDate(String teamName, String matchDate) {
        return teamMatchRepository.findByTeamNameAndMatchDate(teamName, matchDate);
    }
}
