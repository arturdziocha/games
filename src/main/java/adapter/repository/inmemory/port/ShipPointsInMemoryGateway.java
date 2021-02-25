package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.google.inject.Inject;

import adapter.repository.inmemory.entity.ShipPointsMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.collection.SortedSet;
import io.vavr.collection.TreeSet;
import io.vavr.control.Option;

public class ShipPointsInMemoryGateway implements ShipPointsGateway {
    private final PointGateway pointGateway;
    /**
     * Map<String>, Set<String>> first value is shipId, second point ids
     */
    private Map<String, Set<String>> entities;
    private final ShipPointsMapper mapper;
    private final ShipGateway shipGateway;

    @Inject
    public ShipPointsInMemoryGateway(final PointGateway pointGateway, final ShipGateway shipGateway) {
        this.pointGateway = pointGateway;
        this.entities = HashMap.empty();
        this.mapper = new ShipPointsMapper();
        this.shipGateway = shipGateway;
    }

    @Override
    public ShipPointsDto save(ShipPointsDto shipPoints) {
        entities = entities.put(shipPoints.getShip().getId(), mapper.mapToEntity(shipPoints));
        return shipPoints;
    }

    @Override
    public Option<ShipPointsDto> findById(String shipId) {
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
    public Option<Set<ShipPointsDto>> findAllById(Set<String> shipsIds) {
        Set<ShipPointsDto> ships = HashSet.empty();
        for (String id : shipsIds) {
            Option<ShipPointsDto> ship = findById(id);
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
