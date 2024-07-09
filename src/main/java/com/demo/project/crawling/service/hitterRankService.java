package com.demo.project.crawling.service;

import com.demo.project.crawling.model.hitterRank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.project.crawling.repository.hitterRankRepo;

import java.util.List;

@Service
public class hitterRankService {
    @Autowired
    private hitterRankRepo hitterRankRepo;

    public void updateRank(List<hitterRank> hitterRankList) {
        hitterRankRepo.deleteAll();
        hitterRankRepo.saveAll(hitterRankList);
    }

    public List<hitterRank> getHitterRank() {
        return hitterRankRepo.findAll();
    }
}
