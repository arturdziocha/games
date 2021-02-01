package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

public class ShipPointsMapper {
    public ShipPointsInMemory mapToEntity(ShipPointsDto shipPoints) {
        return new ShipPointsInMemory.Builder().shipId(shipPoints.getShipId()).pointIds(shipPoints.getPoints().map(PointDto::getId)).build();
    }
}
