package com.demo.project.crawling.controller;

import com.demo.project.crawling.dto.HitterRankDTO;
import com.demo.project.crawling.util.HitterRankConverter;
import com.demo.project.crawling.model.HitterRank;
import com.demo.project.crawling.service.HitterRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/hitter")
public class HitterRankController {
    @Autowired
    private HitterRankService hitterRankService;

    @PostMapping
    public ResponseEntity<String> updateHitterRank(@RequestBody @Valid List<HitterRankDTO> hitterRank) {
        List<HitterRank> hitters = hitterRank.stream().map(HitterRankConverter::convertToEntity).collect(Collectors.toList());
        hitterRankService.updateRank(hitters);
        return ResponseEntity.ok("Hitter rank updated");
    }

    @GetMapping
    public ResponseEntity<List<HitterRank>> getHitterRank() {
        return ResponseEntity.ok(hitterRankService.getHitterRank());
    }
}
