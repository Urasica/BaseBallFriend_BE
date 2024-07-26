package com.demo.project.crawling.service;

import com.demo.project.crawling.model.PitcherRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.project.crawling.repository.PitcherRankRepo;

import java.util.List;

@Service
public class PitcherRankService {

    @Autowired
    private PitcherRankRepo pitcherRankRepo;

    public void updateRank(List<PitcherRank> pitcherRankList) {
        pitcherRankRepo.saveAll(pitcherRankList);
    }

    public List<PitcherRank> getPitcherRank() {
        return pitcherRankRepo.findAll();
    }
}
