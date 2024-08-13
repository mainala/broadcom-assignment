package com.broadcom.demo.services;

import com.broadcom.demo.dto.PlayerDto;
import com.broadcom.demo.entities.Player;
import com.broadcom.demo.repositories.PlayerRepository;
import com.broadcom.demo.specifications.PlayerSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;

    public PlayerService(PlayerRepository playerRepository, ModelMapper modelMapper) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
    }

    public Page<PlayerDto> getPlayers(String lastName, Integer age, String sortBy, int page, int size) {
        Pageable pageable;

        // Apply sorting if sortBy is provided
        if (sortBy != null && !sortBy.isEmpty()) {
            pageable = PageRequest.of(page, size, Sort.by(sortBy));
        } else {
            pageable = PageRequest.of(page, size); // No sorting if sortBy is not provided
        }

        // Apply filtering
        Specification<Player> spec = PlayerSpecification.getPlayersByFilter(lastName, age);

        // Fetch the data based on the filter and sorting specifications
        Page<Player> playerPage = playerRepository.findAll(spec, pageable);

        // Convert Player entities to PlayerDto using ModelMapper
        return playerPage.map(player -> {
            PlayerDto playerDto = modelMapper.map(player, PlayerDto.class);
            String[] names = player.getName().split(" ", 2); // Assuming 'Name' is 'FirstName LastName'
            playerDto.setFirstName(names.length > 0 ? names[0] : "");
            playerDto.setLastName(names.length > 1 ? names[1] : "");
            return playerDto;
        });
    }
}
