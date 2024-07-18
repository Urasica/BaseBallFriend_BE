package com.demo.project.crawling.controller;

import com.demo.project.crawling.dto.PitcherRankDTO;
import com.demo.project.crawling.util.PitcherRankConverter;
import com.demo.project.crawling.model.PitcherRank;
import com.demo.project.crawling.service.PitcherRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pitcher")
public class PitcherRankController {
    @Autowired
    private PitcherRankService pitcherRankService;

    @PostMapping
    public ResponseEntity<String> updatePitcherRank(@RequestBody @Valid List<PitcherRankDTO> pitcherRank) {
        List<PitcherRank> pitchers = pitcherRank.stream().map(PitcherRankConverter::convertToEntity).collect(Collectors.toList());
        pitcherRankService.updateRank(pitchers);
        return ResponseEntity.ok("Pitcher rank updated");
    }

    @GetMapping
    public ResponseEntity<List<PitcherRank>> getPitcherRank() {
        return ResponseEntity.ok(pitcherRankService.getPitcherRank());
    }
}
