package com.capgemini.sports.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.sports.hateoast.PlayerHal;
import com.capgemini.sports.hateoast.TeamHal;
import com.capgemini.sports.model.dto.PlayerDto;
import com.capgemini.sports.model.entities.Player;
import com.capgemini.sports.model.entities.Team;
import com.capgemini.sports.model.repositories.PlayerRepository;
import com.capgemini.sports.model.repositories.TeamRepository;
import com.capgemini.sports.utilities.ConvertEntityDto;

@RestController
@RequestMapping("/players")
public class PlayerRest {

	@Autowired
	private PlayerRepository playerRepo;

	@Autowired
	private TeamRepository teamRepo;

	@GetMapping(path = "", produces = "application/json")
	public List<PlayerDto> findAll(@RequestParam(name = "teamID", required = false) Optional<Integer> teamId) {
		List<PlayerDto> listDto = new ArrayList<PlayerDto>();
		if(teamId.isPresent()) {
			Optional<Team> teamOpt = teamRepo.findById(teamId.get());
			if(teamOpt.isPresent()) {
				playerRepo.findByTeam(teamOpt.get()).forEach((player) -> {
					PlayerDto dto = ConvertEntityDto.fromPEntityToPDto(player);
					TeamHal.linkSelf(dto.getTeamDto()); 
					PlayerHal.linkSelf(dto);
					listDto.add(dto);
				});
			}
		}else {
			playerRepo.findAll().forEach((player) -> {
				PlayerDto dto = ConvertEntityDto.fromPEntityToPDto(player);
				PlayerHal.linkSelf(dto);
				TeamHal.linkSelf(dto.getTeamDto()); 
				listDto.add(dto);
			});
		}
		
		return listDto;
	}

	@GetMapping(path = "/{id}", produces = "application/json")
	public List<PlayerDto> findById(@PathVariable("id") int playerId) {
		List<PlayerDto> listDto = new ArrayList<PlayerDto>();
		playerRepo.findAll().forEach((player) -> {
			PlayerDto dto = ConvertEntityDto.fromPEntityToPDto(player);
			PlayerHal.linkSelf(dto);
			TeamHal.linkSelf(dto.getTeamDto()); 
			listDto.add(dto);
		});
		return listDto;
	}
	
	/*
	@PostMapping(path="", consumes = "application/json")
	public ResponseEntity<PlayerDto> create(@RequestBody PlayerDto dto){
		Player player = ConvertEntityDto.fromPDtoToPEntity(dto);
		playerRepo.save(player);
		return ResponseEntity.ok(dto);
	}
	
	@PutMapping(path="/{id}", consumes = "application/json")
	public ResponseEntity<PlayerDto> update(@PathVariable(name = "id") int playerId, @RequestBody PlayerDto dto){
		Optional<Player> playerOpt = playerRepo.findById(playerId);
		if(playerOpt.isPresent()) {
			Player player = playerOpt.get();
			player.setFirstName(dto.getFirstName());
			player.setLastName(dto.getLastName());
			player.setTeam(ConvertEntityDto.fromTDtoToTEntity(dto.getTeamDto()));
			playerRepo.save(player);
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<PlayerDto>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<PlayerDto> delete(@PathVariable(name = "id") int playerId){
		Optional<Player> playerOpt = playerRepo.findById(playerId);
		if(playerOpt.isPresent()) {
			Player player = playerOpt.get();
			playerRepo.delete(player);
			return ResponseEntity.ok(ConvertEntityDto.fromPEntityToPDto(player));
		}
		return new ResponseEntity<PlayerDto>(HttpStatus.NOT_FOUND);
	}*/
}
