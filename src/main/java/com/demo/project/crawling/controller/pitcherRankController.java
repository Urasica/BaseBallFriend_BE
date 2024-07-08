package com.demo.project.crawling.controller;

import com.demo.project.crawling.util.pitcherRankConverter;
import com.demo.project.crawling.model.pitcherRank;
import com.demo.project.crawling.service.pitcherRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pitcher")
public class pitcherRankController {
    @Autowired
    private pitcherRankService pitcherRankService;

    @PostMapping
    public ResponseEntity<String> updatePitcherRank(@RequestBody List<List<String>> pitcherRank) {
        List<pitcherRank> pitchers = pitcherRank.stream().map(pitcherRankConverter::convertToEntity).collect(Collectors.toList());
        pitcherRankService.updateRank(pitchers);
        return ResponseEntity.ok("Pitcher rank updated");
    }

    @GetMapping
    public ResponseEntity<List<pitcherRank>> getPitcherRank() {
        return ResponseEntity.ok(pitcherRankService.getPitcherRank());
    }
}
