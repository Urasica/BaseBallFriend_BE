package com.demo.project.crawling.service;

import com.demo.project.crawling.model.pitcherRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.project.crawling.repository.pitcherRankRepo;

import java.util.List;

@Service
public class pitcherRankService {

    @Autowired
    private pitcherRankRepo pitcherRankRepo;

    public void updateRank(List<pitcherRank> pitcherRankList) {
        pitcherRankRepo.deleteAll();
        pitcherRankRepo.saveAll(pitcherRankList);
    }

    public List<pitcherRank> getPitcherRank() {
        return pitcherRankRepo.findAll();
    }
}
