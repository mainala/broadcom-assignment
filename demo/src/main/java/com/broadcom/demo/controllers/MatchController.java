package com.broadcom.demo.controllers;

import com.broadcom.demo.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/wins")
    public List<List<Long>> getAllPlayersWinCounts() {
        return matchService.getAllPlayersWinCounts();
    }
}
