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
        List<baseBallPlayer> newPlayers = baseBallPlayerList.stream()
                .filter(this::isValidPlayer)
                .filter(player -> baseBallPlayerRepo.findByNameAndBirthday(player.getName(), player.getBirthday()).isEmpty())
                .collect(Collectors.toList());
        baseBallPlayerRepo.saveAll(newPlayers);
    }

    public List<baseBallPlayer> getBaseBallPlayerList() {
        return baseBallPlayerRepo.findAll();
    }

    private boolean isValidPlayer(baseBallPlayer player) {
        return player != null &&
                isValidString(player.getName()) &&
                isValidString(player.getBirthday()) &&
                isValidString(player.getPosition()) &&
                isValidString(player.getHeight()) &&
                isValidString(player.getWeight()) &&
                isValidString(player.getHandedInfo()) &&
                player.getNumber() > 0;
    }

    private boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
