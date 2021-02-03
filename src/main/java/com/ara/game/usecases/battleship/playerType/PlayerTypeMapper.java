package com.ara.game.usecases.battleship.playerType;

import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;

final class PlayerTypeMapper {
    final PlayerTypeDto mapToDTO(PlayerType playerType) {
        return new PlayerTypeDto.Builder().id(playerType.getId()).name(playerType.getName()).build();
    }
}