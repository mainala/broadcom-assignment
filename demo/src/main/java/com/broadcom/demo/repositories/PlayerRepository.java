package com.broadcom.demo.repositories;

import com.broadcom.demo.entities.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepository extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {

    Page<Player> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Player> findByAge(Integer age, Pageable pageable);

    Page<Player> findByNameContainingIgnoreCaseAndAge(String name, Integer age, Pageable pageable);
}
