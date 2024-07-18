package com.demo.project.crawling.util;

import com.demo.project.crawling.dto.TeamRankDTO;
import com.demo.project.crawling.model.TeamRank;
import org.springframework.stereotype.Component;

@Component
public class TeamRankConverter {
    static public TeamRank convertToEntity(TeamRankDTO data) {
        TeamRank team = new TeamRank();
        team.setRank(data.getRank());
        team.setTeamName(data.getTeamName());
        team.setGames(data.getGames());
        team.setWins(data.getWins());
        team.setLosses(data.getLosses());
        team.setDraws(data.getDraws());
        team.setWinRate(data.getWinRate());
        team.setWinningMargin(data.getWinningMargin());
        team.setContinuity(data.getContinuity());
        return team;
    }
}
