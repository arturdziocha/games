package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.google.inject.Inject;

import adapter.repository.inmemory.entity.ShipPointsInMemory;
import adapter.repository.inmemory.entity.ShipPointsMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class ShipPointsInMemoryGateway implements ShipPointsGateway {
    private final PointGateway pointGateway;
    private Map<String, ShipPointsInMemory> entities;
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
        Option<ShipPointsInMemory> shipPoints = entities.get(shipId);
        if (shipPoints.isEmpty()) {
            return Option.none();
        }
        Option<ShipDto> ship = entities.get(shipId).flatMap(w -> shipGateway.findById(w.getShipId()));
        if (ship.isEmpty()) {
            return Option.none();
        }
        return entities
                .get(shipId)
                .map(ShipPointsInMemory::getPointIds)
                .flatMap(s -> pointGateway.findAllById(s).map(w -> mapper.mapToOutputData(ship.get(), w)));
    }

    @Override
    public void remove(String shipId) {
        entities = entities.remove(shipId);
    }
}
