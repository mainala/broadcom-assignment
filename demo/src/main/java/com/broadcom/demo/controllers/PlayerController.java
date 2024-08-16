package com.broadcom.demo.controllers;

import com.broadcom.demo.dto.PlayerDto;
import com.broadcom.demo.services.MatchService;
import com.broadcom.demo.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping
    @CrossOrigin(origins = "*")
    public Page<PlayerDto> getPlayers(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String[] sortBy,
            @RequestParam(required = false) String[] sortOrder,
            @RequestParam(defaultValue = "0") int page) {

        return playerService.getPlayers(lastName, age, sortBy, sortOrder, page);
    }
}
