package com.demo.project.crawling.controller;

import com.demo.project.crawling.dto.BaseBallPlayerDTO;
import com.demo.project.crawling.model.BaseBallPlayer;
import com.demo.project.crawling.service.BaseBallPlayerService;
import com.demo.project.crawling.util.PlayerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/player")
public class BaseBallPlayerController {

    @Autowired
    private BaseBallPlayerService service;

    @PostMapping
    public ResponseEntity<String> insertPlayer(@RequestBody List<BaseBallPlayerDTO> playerList) {
        List<BaseBallPlayer> players = playerList.stream().map(PlayerConverter::convertToEntity).collect(Collectors.toList());
        service.updateInfo(players);
        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity<List<BaseBallPlayer>> getPlayers() {
        return ResponseEntity.ok(service.getBaseBallPlayerList());
    }

    @GetMapping("/team")
    public ResponseEntity<List<BaseBallPlayer>> getPlayersByTeam(@RequestParam String team) {
        List<BaseBallPlayer> players = service.getBaseBallPlayerListByTeam(team);
        return ResponseEntity.ok(players);
    }
}