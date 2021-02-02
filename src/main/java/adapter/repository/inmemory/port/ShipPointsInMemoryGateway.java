package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.ShipPointsInMemory;
import adapter.repository.inmemory.entity.ShipPointsMapper;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.google.inject.Inject;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public class ShipPointsInMemoryGateway implements ShipPointsGateway {
    private final PointGateway pointGateway;
    private Map<String, ShipPointsInMemory> entities;
    private final ShipPointsMapper mapper;

    @Inject
    public ShipPointsInMemoryGateway(PointGateway pointGateway) {
        this.pointGateway = pointGateway;
        this.entities = HashMap.empty();
        this.mapper = new ShipPointsMapper();
    }

    @Override
    public ShipPointsDto save(ShipPointsDto shipPoints) {
        ShipPointsInMemory shipPointsInMemory = mapper.mapToEntity(shipPoints);
        entities = entities.put(shipPointsInMemory.getShipId(), shipPointsInMemory);
        return shipPoints;
    }

    @Override
    public Option<ShipPointsDto> findById(String shipId) {
        Option<ShipPointsInMemory> find = entities.get(shipId);
        if (find.isDefined()) {
            Set<String> pointIds = find.get().getPointIds();
            Option<Set<PointDto>> points = pointGateway.findAllById(pointIds);
            if (points.isDefined()) {
                return Option.of(new ShipPointsDto.Builder().shipId(shipId).points(points.get()).build());
            }
            return Option.none();
        }
        return Option.none();
    }

    @Override
    public void remove(String shipId) {

    }
}
