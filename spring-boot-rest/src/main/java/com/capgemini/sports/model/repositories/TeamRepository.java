package com.capgemini.sports.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.sports.model.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer>{

	Team findByName(String name);
	
	Team findByStadium(String stadium);
}
