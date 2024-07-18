package com.demo.project.crawling.util;

import com.demo.project.crawling.dto.PitcherRankDTO;
import com.demo.project.crawling.model.PitcherRank;
import org.springframework.stereotype.Component;

@Component
public class PitcherRankConverter {
    static public PitcherRank convertToEntity(PitcherRankDTO data) {
        PitcherRank pitcherRank = new PitcherRank();
        pitcherRank.setRank(data.getRank());
        pitcherRank.setName(data.getName());
        pitcherRank.setTeam(data.getTeam());
        pitcherRank.setGames(data.getGames());
        pitcherRank.setWins(data.getWins());
        pitcherRank.setLosses(data.getLosses());
        pitcherRank.setSave(data.getSave());
        pitcherRank.setHold(data.getHold());
        pitcherRank.setInnings(data.getInnings());
        pitcherRank.setPitchCount(data.getPitchCount());
        pitcherRank.setHits(data.getHits());
        pitcherRank.setHomeRuns(data.getHomeRuns());
        pitcherRank.setStrikeout(data.getStrikeout());
        pitcherRank.setBaseOnBall(data.getBaseOnBall());
        pitcherRank.setRuns(data.getRuns());
        pitcherRank.setEarnedRuns(data.getEarnedRuns());
        pitcherRank.setEarnedRunsAVG(data.getEarnedRunsAVG());
        pitcherRank.setWHIP(data.getWhip());
        pitcherRank.setQS(data.getQs());
        return pitcherRank;
    }
}