package com.demo.project.crawling.service;

import com.demo.project.crawling.model.TeamRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.project.crawling.repository.TeamRankRepo;

import java.util.List;

@Service
public class TeamRankService {
    @Autowired
    private TeamRankRepo teamRankRepo;

    public void updateRank(List<TeamRank> teams) {
        teamRankRepo.deleteAll();
        teamRankRepo.saveAll(teams);
    }

    public List<TeamRank> getTeamRank() {
        return teamRankRepo.findAll();
    }
}
