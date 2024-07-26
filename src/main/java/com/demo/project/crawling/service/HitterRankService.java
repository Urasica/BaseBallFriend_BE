package com.demo.project.crawling.service;

import com.demo.project.crawling.model.HitterRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.project.crawling.repository.HitterRankRepo;

import java.util.List;

@Service
public class HitterRankService {
    @Autowired
    private HitterRankRepo hitterRankRepo;

    public void updateRank(List<HitterRank> hitterRankList) {
        hitterRankRepo.saveAll(hitterRankList);
    }

    public List<HitterRank> getHitterRank() {
        return hitterRankRepo.findAllByOrderByRankAsc();
    }
}
