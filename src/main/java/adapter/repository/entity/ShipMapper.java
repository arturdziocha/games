package adapter.repository.entity;

import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;

public class ShipMapper {
    public ShipInMemory mapToEntity(ShipDto dto) {
        return new ShipInMemory.Builder().id(dto.getId()).shipClassShortName(dto.getShipClassDto().getShortName()).health(dto.getHealth()).build();
    }

    public ShipDto mapToDto(ShipInMemory ship, ShipClassDto shipClass) {
        return new ShipDto.Builder().id(ship.getId()).health(ship.getHealth()).shipClassDto(shipClass).build();
    }
}
