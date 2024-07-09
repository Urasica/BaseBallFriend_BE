package com.demo.project.crawling.controller;

import com.demo.project.crawling.model.baseBallPlayer;
import com.demo.project.crawling.service.baseBallPlayerService;
import com.demo.project.crawling.util.playerConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/player")
public class baseBallPlayerController {

    @Autowired
    private baseBallPlayerService service;

    @PostMapping
    public ResponseEntity<String> insertPlayer(@RequestBody List<List<String>> playerList) {
        List<baseBallPlayer> players = playerList.stream().map(playerConverter::convertToEntity).collect(Collectors.toList());
        service.updateInfo(players);
        return ResponseEntity.ok("Success");
    }

    @GetMapping
    public ResponseEntity<List<baseBallPlayer>> getPlayers() {
        return ResponseEntity.ok(service.getBaseBallPlayerList());
    }
}