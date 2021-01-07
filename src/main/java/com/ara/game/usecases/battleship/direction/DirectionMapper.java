package com.ara.game.usecases.battleship.direction;

import com.ara.game.usecases.battleship.direction.dto.DirectionDto;

final class DirectionMapper {
    final DirectionDto mapToDTO(final Direction direction) {
        return new DirectionDto.Builder().name(direction.getName()).shortName(direction.getShortName()).build();
    }
}