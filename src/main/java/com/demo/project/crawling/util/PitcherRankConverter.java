package com.demo.project.crawling.util;

import com.demo.project.crawling.model.PitcherRank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PitcherRankConverter {
    static public PitcherRank convertToEntity(List<String> data) {
        PitcherRank pitcherRank = new PitcherRank();
        pitcherRank.setRank(Integer.parseInt(data.get(0)));
        pitcherRank.setName(data.get(1));
        pitcherRank.setTeam(data.get(2));
        pitcherRank.setGames(Integer.parseInt(data.get(3)));
        pitcherRank.setWins(Integer.parseInt(data.get(4)));
        pitcherRank.setLosses(Integer.parseInt(data.get(5)));
        pitcherRank.setSave(Integer.parseInt(data.get(6)));
        pitcherRank.setHold(Integer.parseInt(data.get(7)));
        pitcherRank.setInnings(data.get(8));
        pitcherRank.setPitchCount(Integer.parseInt(data.get(9)));
        pitcherRank.setHits(Integer.parseInt(data.get(10)));
        pitcherRank.setHomeRuns(Integer.parseInt(data.get(11)));
        pitcherRank.setStrikeout(Integer.parseInt(data.get(12)));
        pitcherRank.setBaseOnBall(Integer.parseInt(data.get(13)));
        pitcherRank.setRuns(Integer.parseInt(data.get(14)));
        pitcherRank.setEarnedRuns(Integer.parseInt(data.get(15)));
        pitcherRank.setEarnedRunsAVG(Double.parseDouble(data.get(16)));
        pitcherRank.setWHIP(Double.parseDouble(data.get(17)));
        pitcherRank.setQS(Integer.parseInt(data.get(18)));
        return pitcherRank;
    }
}