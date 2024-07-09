package com.demo.project.crawling.util;

import com.demo.project.crawling.model.hitterRank;
import java.util.List;

public class hitterRankConverter {
    static public hitterRank convertToEntity(List<String> data) {
        hitterRank hitterRank = new hitterRank();
        hitterRank.setRank(Integer.parseInt(data.get(0)));
        hitterRank.setName(data.get(1));
        hitterRank.setTeam(data.get(2));
        hitterRank.setGames(Integer.parseInt(data.get(3)));
        hitterRank.setPlateAppearance(Integer.parseInt(data.get(4)));
        hitterRank.setAtBat(Integer.parseInt(data.get(5)));
        hitterRank.setHits(Integer.parseInt(data.get(6)));
        hitterRank.setDoubles(Integer.parseInt(data.get(7)));
        hitterRank.setTriples(Integer.parseInt(data.get(8)));
        hitterRank.setHomeRuns(Integer.parseInt(data.get(9)));
        hitterRank.setRunBattedIn(Integer.parseInt(data.get(10)));
        hitterRank.setRunsScored(Integer.parseInt(data.get(11)));
        hitterRank.setStolenBases(Integer.parseInt(data.get(12)));
        hitterRank.setBaseOnBall(Integer.parseInt(data.get(13)));
        hitterRank.setStrikeOuts(Integer.parseInt(data.get(14)));
        hitterRank.setBattingAVG(Double.parseDouble(data.get(15)));
        hitterRank.setOnBaseAVG(Double.parseDouble(data.get(16)));
        hitterRank.setSluggingAVG(Double.parseDouble(data.get(17)));
        hitterRank.setOPS(Double.parseDouble(data.get(18)));
        return hitterRank;
    }
}
