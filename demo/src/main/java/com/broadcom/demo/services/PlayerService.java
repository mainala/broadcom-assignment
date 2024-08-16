package com.broadcom.demo.services;

import com.broadcom.demo.dto.PlayerDto;
import com.broadcom.demo.entities.Player;
import com.broadcom.demo.repositories.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Page<PlayerDto> getPlayers(String lastName, Integer age, String[] sortBy, String[] sortOrder, int page) {
        Sort sort = createSort(sortBy, sortOrder);
        PageRequest pageRequest = PageRequest.of(page, 20, sort);
        Page<Player> playerPage = playerRepository.findByLastNameAndAge(lastName, age, pageRequest);

        return playerPage.map(this::convertToDto);
    }

    private Sort createSort(String[] sortBy, String[] sortOrder) {
        if (sortBy == null || sortBy.length == 0) {
            return Sort.by(Sort.Order.asc("id")); // Default sort
        }

        Sort.Order[] orders = new Sort.Order[sortBy.length];
        for (int i = 0; i < sortBy.length; i++) {
            String field = sortBy[i];
            String order = (i < sortOrder.length) ? sortOrder[i] : "asc";
            Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
            orders[i] = new Sort.Order(direction, field);
        }
        return Sort.by(orders);
    }

    private PlayerDto convertToDto(Player player) {
        return modelMapper.map(player, PlayerDto.class);
    }
}
