package com.demo.project.crawling.util;

import com.demo.project.crawling.dto.HitterRankDTO;
import com.demo.project.crawling.model.HitterRank;
import org.springframework.stereotype.Component;

@Component
public class HitterRankConverter {
    static public HitterRank convertToEntity(HitterRankDTO data) {
        HitterRank hitterRank = new HitterRank();
        hitterRank.setRank(data.getRank());
        hitterRank.setName(data.getName());
        hitterRank.setTeam(data.getTeam());
        hitterRank.setGames(data.getGames());
        hitterRank.setPlateAppearance(data.getPlateAppearance());
        hitterRank.setAtBat(data.getAtBat());
        hitterRank.setHits(data.getHits());
        hitterRank.setDoubles(data.getDoubles());
        hitterRank.setTriples(data.getTriples());
        hitterRank.setHomeRuns(data.getHomeRuns());
        hitterRank.setRunBattedIn(data.getRunBattedIn());
        hitterRank.setRunsScored(data.getRunsScored());
        hitterRank.setStolenBases(data.getStolenBases());
        hitterRank.setBaseOnBall(data.getBaseOnBall());
        hitterRank.setStrikeOuts(data.getStrikeOuts());
        hitterRank.setBattingAVG(data.getBattingAVG());
        hitterRank.setOnBaseAVG(data.getOnBaseAVG());
        hitterRank.setSluggingAVG(data.getSluggingAVG());
        hitterRank.setOps(data.getOps());
        return hitterRank;
    }
}
