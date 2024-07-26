package com.demo.project.crawling.service;

import com.demo.project.crawling.model.BaseBallPlayer;
import com.demo.project.crawling.repository.BaseBallPlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseBallPlayerService {
    @Autowired
    private BaseBallPlayerRepo baseBallPlayerRepo;

    public void updateInfo(List<BaseBallPlayer> baseBallPlayerList) {
        baseBallPlayerRepo.saveAll(baseBallPlayerList);
    }

    public List<BaseBallPlayer> getBaseBallPlayerList() {
        return baseBallPlayerRepo.findAll();
    }
}