package com.capgemini.sports.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.sports.model.entities.Player;
import com.capgemini.sports.model.entities.Team;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Integer>{

	List<Player> findByTeam(Team team);
}
