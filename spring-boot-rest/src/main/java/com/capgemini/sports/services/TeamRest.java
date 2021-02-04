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

import com.capgemini.sports.hateoast.TeamHal;
import com.capgemini.sports.model.dto.TeamDto;
import com.capgemini.sports.model.entities.Team;
import com.capgemini.sports.model.repositories.TeamRepository;
import com.capgemini.sports.utilities.ConvertEntityDto;

@RestController
@RequestMapping("/teams")
public class TeamRest {

	@Autowired
	private TeamRepository teamRepo;

	/*
	 * GET -> /teams
	 * 
	 * returns lista con todos los equipos
	 * 
	 * GET -> /teams?name=xxxx
	 * 
	 * returns Un solo equipo con name=xxxx
	 * 
	 * GET -> /teams?stadium=xxxx
	 * 
	 * returns Un solo equipo con stadium=xxxx
	 */
	@GetMapping(path = "", produces = "application/json")
	public List<TeamDto> findAll(@RequestParam(name = "name", required = false) Optional<String> teamName,
			@RequestParam(name = "stadium", required = false) Optional<String> teamStadium) {
		if(teamName.isPresent()) {
			// Nos llega un parametro name -> buscar por nombre
			Team team = teamRepo.findByName(teamName.get());

			TeamDto dto = ConvertEntityDto.fromTEntityToTDto(team);
			TeamHal.linkSelf(dto);
			TeamHal.link2Teams(dto);
			TeamHal.link2PlayersByTeam(dto, Optional.of(team.getId()));
			TeamHal.link2Players(dto);
			List<TeamDto> list = new ArrayList<TeamDto>();
			list.add(dto);
			return list;
		}else if (teamStadium.isPresent()) {
			// Nos llega un parametro stadium -> buscar por estadio
			Team team = teamRepo.findByStadium(teamStadium.get());

			TeamDto dto = ConvertEntityDto.fromTEntityToTDto(team);
			TeamHal.linkSelf(dto);
			TeamHal.link2Teams(dto);
			TeamHal.link2PlayersByTeam(dto, Optional.of(team.getId()));
			TeamHal.link2Players(dto);
			List<TeamDto> list = new ArrayList<TeamDto>();
			list.add(dto);
			return list;
		}else {
			//Devolvemos todos los equipos
			List<TeamDto> listDto = new ArrayList<TeamDto>();
			teamRepo.findAll().forEach((team) -> {
				TeamDto dto = ConvertEntityDto.fromTEntityToTDto(team);
				TeamHal.linkSelf(dto);
				TeamHal.link2PlayersByTeam(dto, Optional.of(team.getId()));
				TeamHal.link2Players(dto);
				listDto.add(dto);
			});
			return listDto;
		}
		
	}

	/*
	 * GET -> /teams/x
	 * 
	 * returns Un solo equipo con id=x
	 */
	@GetMapping(path = "/{id}", produces = "application/json")
	public TeamDto findOne(@PathVariable("id") int id) {
		Optional<Team> teamOpt = teamRepo.findById(id);
		if (teamOpt.isPresent()) {
			Team team = teamOpt.get();
			TeamDto dto = ConvertEntityDto.fromTEntityToTDto(team);
			TeamHal.linkSelf(dto);
			TeamHal.link2Teams(dto);
			TeamHal.link2PlayersByTeam(dto, Optional.of(id));
			TeamHal.link2Players(dto);
			return dto;
		}

		return null;
	}

	/*
	 * POST -> /teams
	 * 
	 * body(application/json) -> {"name":xxxx, "stadium":aaaa}
	 * 
	 * returns equipo que se ha creado
	 */
	@PostMapping(path = "", consumes = "application/json")
	public ResponseEntity<TeamDto> create(@RequestBody TeamDto dto) {
		Team team = ConvertEntityDto.fromTDtoToTEntity(dto);
		team = teamRepo.save(team);
		return ResponseEntity.ok(dto);
	}

	/*
	 * PUT -> /teams/{id}
	 * 
	 * body(application/json) -> {"name":xxxx, "stadium":aaaa}
	 * 
	 * returns Equipo que se ha modificado ya modificado
	 */
	@PutMapping(path = "/{id}", consumes = "application/json")
	public ResponseEntity<TeamDto> update(@PathVariable(name = "id") int teamId, @RequestBody TeamDto dto) {
		Optional<Team> teamOpt = teamRepo.findById(teamId);
		if (teamOpt.isPresent()) {
			Team team = teamOpt.get();
			team.setName(dto.getName());
			team.setStadium(dto.getStadium());
			teamRepo.save(team);
			return ResponseEntity.ok(dto);
		}
		return new ResponseEntity<TeamDto>(HttpStatus.NOT_FOUND);
	}

	/*
	 * DELETE -> /teams/{id}
	 * 
	 * returns equipo que se ha eliminado
	 */
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<TeamDto> delete(@PathVariable(name = "id") int teamId) {
		Optional<Team> teamOpt = teamRepo.findById(teamId);
		if (teamOpt.isPresent()) {
			Team team = teamOpt.get();
			teamRepo.delete(team);
			return ResponseEntity.ok(ConvertEntityDto.fromTEntityToTDto(team));
		}
		return new ResponseEntity<TeamDto>(HttpStatus.NOT_FOUND);
	}
}
