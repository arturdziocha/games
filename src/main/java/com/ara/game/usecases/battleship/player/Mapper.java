package com.ara.game.usecases.battleship.player;

import com.ara.game.usecases.battleship.player.dto.PlayerCreateDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;

final class Mapper {

    public PlayerDto mapToDto(Player player) {
        return new PlayerDto.Builder()
                .withId(player.getId())
                .withName(player.getName())
                .withPlayerType(player.getPlayerType())
                .build();
    }

    public Player mapToEntity(String id, PlayerCreateDto inputData) {
        return Player.builder().id(id).withName(inputData.getName()).withPlayerType(inputData.getPlayerType()).build();
    }

}
