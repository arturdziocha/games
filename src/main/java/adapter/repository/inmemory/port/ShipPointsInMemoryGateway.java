package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.ShipPointsMapper;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.google.inject.Inject;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public class ShipPointsInMemoryGateway implements ShipPointsGateway {
    private final PointGateway pointGateway;
    private final ShipPointsMapper mapper;
    private final ShipGateway shipGateway;
    /**
     * Map<String>, Set<String>> first value is shipId, second point ids
     */
    private Map<String, Set<String>> entities;

    @Inject
    public ShipPointsInMemoryGateway(final PointGateway pointGateway, final ShipGateway shipGateway) {
        this.pointGateway = pointGateway;
        this.entities = HashMap.empty();
        this.mapper = new ShipPointsMapper();
        this.shipGateway = shipGateway;
    }

    @Override
    public ShipWithPointsDto save(ShipWithPointsDto shipPoints) {
        entities = entities.put(shipPoints.getShip().getId(), mapper.mapToEntity(shipPoints));
        return shipPoints;
    }

    @Override
    public Option<ShipWithPointsDto> findById(String shipId) {
        Option<Set<String>> shipPoints = entities.get(shipId);
        if (shipPoints.isEmpty()) {
            return Option.none();
        }
        Option<ShipDto> ship = shipGateway.findById(shipId);
        if (ship.isEmpty()) {
            return Option.none();
        }
        return entities
                .get(shipId)
                .flatMap(s -> pointGateway.findAllById(s).map(w -> mapper.mapToOutputData(ship.get(), w)));
    }

    @Override
    public Option<Set<ShipWithPointsDto>> findAllById(Set<String> shipsIds) {
        Set<ShipWithPointsDto> ships = HashSet.empty();
        for (String id : shipsIds) {
            Option<ShipWithPointsDto> ship = findById(id);
            if (ship.isEmpty()) {
                return Option.none();
            }
            ships = ships.add(ship.get());
        }
        return Option.of(ships);
    }

    @Override
    public void remove(String shipId) {
        entities = entities.remove(shipId);
    }

}
