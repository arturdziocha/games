package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;

final class Mapper {

    public PlayerDto mapToDto(Player player) {
        return new PlayerDto.Builder()
                .id(player.getId())
                .name(player.getName())
                .playerType(player.getPlayerType())
                .build();
    }

    public Player mapToEntity(String id, PlayerCreateDto inputData) {
        return new Player.Builder().id(id).name(inputData.getName()).playerType(inputData.getPlayerType()).build();
    }

}
