package com.demo.project.crawling.service;

import com.demo.project.crawling.model.baseBallPlayer;
import com.demo.project.crawling.repository.baseBallPlayerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class baseBallPlayerService {
    @Autowired
    private baseBallPlayerRepo baseBallPlayerRepo;

    public void updateInfo(List<baseBallPlayer> baseBallPlayerList) {
        baseBallPlayerRepo.saveAll(baseBallPlayerList);
    }

    public List<baseBallPlayer> getBaseBallPlayerList() {
        return baseBallPlayerRepo.findAll();
    }
}
