package com.demo.project.crawling.service;

import com.demo.project.crawling.model.teamRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.project.crawling.repository.teamRankRepo;

import java.util.List;

@Service
public class teamRankService {
    @Autowired
    private teamRankRepo teamRankRepo;

    public void updateRank(List<teamRank> teams) {
        teamRankRepo.deleteAll();
        teamRankRepo.saveAll(teams);
    }

    public List<teamRank> getAllTeams() {
        return teamRankRepo.findAll();
    }
}
