package com.demo.project.crawling.util;

import com.demo.project.crawling.dto.BaseBallPlayerDTO;
import com.demo.project.crawling.model.BaseBallPlayer;
import org.springframework.stereotype.Component;

@Component
public class PlayerConverter {
    static public BaseBallPlayer convertToEntity(BaseBallPlayerDTO data) {
        BaseBallPlayer player = new BaseBallPlayer();
        player.setTeam(data.getTeam());
        player.setNumber(data.getNumber());
        player.setName(data.getName());
        player.setPosition(data.getPosition());
        player.setHeight(data.getHeight());
        player.setWeight(data.getWeight());
        player.setBirthday(data.getBirthday());
        player.setHandedInfo(data.getHandedInfo());
        return player;
    }
}
