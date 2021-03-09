package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.common.CreateDto;

final class Mapper {

    public PlayerDto mapToDto(Player player) {
        return new PlayerDto.Builder()
                .id(player.getId())
                .name(player.getName())
                .playerType(player.getPlayerType())
                .build();
    }

    final CreateDto mapToCreateDto(PlayerDto dto) {
        return new CreateDto.Builder().id(dto.getId()).build();
    }

    public Player mapToEntity(String id, PlayerCreateDto inputData) {
        return new Player.Builder().id(id).name(inputData.getName()).playerType(inputData.getPlayerType()).build();
    }

}
