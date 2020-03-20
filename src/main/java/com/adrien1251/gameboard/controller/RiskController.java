package com.adrien1251.gameboard.controller;

import com.adrien1251.gameboard.bo.risk.RiskBoard;
import com.adrien1251.gameboard.bo.risk.RiskCard;
import com.adrien1251.gameboard.exceptions.ExceptionCatcher;
import com.adrien1251.gameboard.service.risk.RiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/risk")
@CrossOrigin(origins = "*")
public class RiskController extends ExceptionCatcher {
    private final RiskService riskService;

    @Autowired
    public RiskController(RiskService riskService) {
        this.riskService = riskService;
    }

    @PostMapping("/{nbPlayer}")
    public ResponseEntity<RiskBoard> createBoard(@PathVariable int nbPlayer) {
        return ResponseEntity.ok(riskService.createBoard(nbPlayer));
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<RiskBoard> getBoard(@PathVariable UUID uuid) {
        return ResponseEntity.ok(riskService.getBoard(uuid));
    }

    @GetMapping("/{uuid}/pick")
    public ResponseEntity<RiskCard> pickCard(@PathVariable UUID uuid) {
        return ResponseEntity.ok(riskService.pickCard(uuid));
    }

}
