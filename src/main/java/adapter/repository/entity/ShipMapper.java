package adapter.repository.entity;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;

public class ShipMapper {
    public ShipInMemory mapToEntity(final ShipDto dto) {
        return new ShipInMemory.Builder()
                .id(dto.getId())
                .shipClassShortName(dto.getShipClassDto().getShortName())
                .health(dto.getHealth())
                .build();
    }

    public ShipDto mapToDto(final ShipInMemory ship, final ShipClassDto shipClass) {
        return new ShipDto.Builder().id(ship.getId()).health(ship.getHealth()).shipClassDto(shipClass).build();
    }
}
