package com.demo.project.crawling.util;

import com.demo.project.crawling.model.baseBallPlayer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class playerConverter {
    static public baseBallPlayer convertToEntity(List<String> data) {
        baseBallPlayer player = new baseBallPlayer();
        player.setTeam(data.get(0));
        player.setNumber(Integer.parseInt(data.get(1)));
        player.setName(data.get(2));
        player.setPosition(data.get(3));
        player.setHeight(data.get(4));
        player.setWeight(data.get(5));
        player.setBirthday(data.get(6));
        player.setHandedInfo(data.get(7));
        return player;
    }
}
