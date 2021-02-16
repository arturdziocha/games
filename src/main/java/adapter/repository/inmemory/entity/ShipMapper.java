package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;

public class ShipMapper {
    public ShipInMemory mapToEntity(final ShipDto dto) {
        return new ShipInMemory.Builder()
                .id(dto.getId())
                .shipClassShortName(dto.getShipClass().getShortName())
                .health(dto.getHealth())
                .build();
    }

    public ShipDto mapToDto(final ShipInMemory ship, final ShipClass shipClass) {
        return new ShipDto.Builder().id(ship.getId()).health(ship.getHealth()).shipClass(shipClass).build();
    }
}
