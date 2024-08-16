package com.broadcom.demo.repositories;

import com.broadcom.demo.entities.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT p FROM Player p WHERE (:lastName IS NULL OR p.lastName = :lastName) AND (:age IS NULL OR p.age = :age)")
    Page<Player> findByLastNameAndAge(@Param("lastName") String lastName, @Param("age") Integer age, Pageable pageable);
}
