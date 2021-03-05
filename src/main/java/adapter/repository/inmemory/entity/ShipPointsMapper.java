package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import io.vavr.collection.Set;

public class ShipPointsMapper {
    public Set<String> mapToEntity(ShipPointsDto shipPoints) {
        return shipPoints.getPoints().map(PointDto::getId);
    }

    public ShipPointsDto mapToOutputData(ShipDto ship, Set<PointDto> points) {
        return new ShipPointsDto.Builder().ship(ship).points(points).build();
    }
}
