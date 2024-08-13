package com.broadcom.demo.controllers;

import com.broadcom.demo.dto.PlayerDto;
import com.broadcom.demo.services.PlayerService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public Page<PlayerDto> getPlayers(
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) Integer age,
            @RequestParam(required = false) String sortBy,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return playerService.getPlayers(lastName, age, sortBy, page, size);
    }
}
