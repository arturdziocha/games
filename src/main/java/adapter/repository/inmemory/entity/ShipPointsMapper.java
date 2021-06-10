package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import io.vavr.collection.Set;

public class ShipPointsMapper {
    public Set<String> mapToEntity(ShipWithPointsDto shipPoints) {
        return shipPoints.getPoints().map(PointDto::getId);
    }

    public ShipWithPointsDto mapToOutputData(ShipDto ship, Set<PointDto> points) {
        return ShipWithPointsDto.builder().withShip(ship).withPoints(points).build();
    }
}
