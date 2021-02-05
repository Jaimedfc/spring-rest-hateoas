package com.capgemini.sports.utilities;

import com.capgemini.sports.model.dto.PlayerDto;
import com.capgemini.sports.model.dto.TeamDto;
import com.capgemini.sports.model.entities.Player;
import com.capgemini.sports.model.entities.Team;

public class ConvertEntityDto {

	public static TeamDto fromTEntityToTDto(Team team) {
		TeamDto dto = new TeamDto();
		dto.setId(team.getId());
		dto.setName(team.getName());
		dto.setStadium(team.getStadium());
		return dto;
	}

	public static PlayerDto fromPEntityToPDto(Player player) {
		
		PlayerDto dto = new PlayerDto();
		dto.setFirstName(player.getFirstName());
		dto.setLastName(player.getLastName());
		dto.setId(player.getId());
		TeamDto teamDto = new TeamDto(player.getTeam().getId(),player.getTeam().getName(),player.getTeam().getStadium());
		dto.setTeamDto(teamDto);
		return dto;
	}
	
	public static Team fromTDtoToTEntity(TeamDto teamDto) {
		Team team = new Team();
		team.setName(teamDto.getName());
		team.setStadium(teamDto.getStadium());
		return team;
	}

	public static Player fromPDtoToPEntity(PlayerDto playerDto) {
		Player player = new Player();
		player.setFirstName(playerDto.getFirstName());
		player.setLastName(playerDto.getLastName());
		Team team = new Team();
		team.setName(playerDto.getTeamDto().getName());
		team.setStadium(playerDto.getTeamDto().getStadium());
		player.setTeam(team);
		return player;
	}
}
