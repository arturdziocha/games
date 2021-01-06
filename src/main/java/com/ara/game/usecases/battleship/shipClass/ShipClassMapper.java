package com.ara.game.usecases.battleship.shipClass;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;

final class ShipClassMapper {
    ShipClassDto mapToDTO(final ShipClass shipClass) {
        return new ShipClassDto.Builder()
                .name(shipClass.getName())
                .shortName(shipClass.getShortName())
                .size(shipClass.getSize())
                .build();
    }
}