package com.demo.project.crawling.controller;

import com.demo.project.crawling.util.hitterRankConverter;
import com.demo.project.crawling.model.hitterRank;
import com.demo.project.crawling.service.hitterRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hitter")
public class hitterRankController {
    @Autowired
    private hitterRankService hitterRankService;

    @PostMapping
    public ResponseEntity<String> updateHitterRank(@RequestBody List<List<String>> hitterRank) {
        List<hitterRank> hitters = hitterRank.stream().map(hitterRankConverter::convertToEntity).collect(Collectors.toList());
        hitterRankService.updateRank(hitters);
        return ResponseEntity.ok("Hitter rank updated");
    }

    @GetMapping
    public ResponseEntity<List<hitterRank>> getHitterRank() {
        return ResponseEntity.ok(hitterRankService.getHitterRank());
    }
}
