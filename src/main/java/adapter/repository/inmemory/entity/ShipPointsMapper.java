package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

import io.vavr.collection.Set;

public class ShipPointsMapper {
    public ShipPointsInMemory mapToEntity(ShipPointsDto shipPoints) {
        return new ShipPointsInMemory.Builder().shipId(shipPoints.getShipId()).pointIds(shipPoints.getPoints().map(PointDto::getId)).build();
    }

    public ShipPointsDto mapToOutputData(String shipId, Set<PointDto> points) {
        return new ShipPointsDto.Builder().shipId(shipId).points(points).build();
    }
}
