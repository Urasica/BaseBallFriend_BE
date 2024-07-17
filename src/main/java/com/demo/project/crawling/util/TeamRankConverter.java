package com.demo.project.crawling.util;

import com.demo.project.crawling.model.TeamRank;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeamRankConverter {
    static public TeamRank convertToEntity(List<String> data) {
        TeamRank team = new TeamRank();
        team.setRank(Integer.parseInt(data.get(0)));
        team.setTeamName(data.get(1));
        team.setGames(Integer.parseInt(data.get(2)));
        team.setWins(Integer.parseInt(data.get(3)));
        team.setLosses(Integer.parseInt(data.get(4)));
        team.setDraws(Integer.parseInt(data.get(5)));
        team.setWinRate(Double.parseDouble(data.get(6)));
        team.setWinningMargin(Double.parseDouble(data.get(7)));
        team.setContinuity(data.get(8));
        return team;
    }
}
