package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;

import adapter.repository.inmemory.entity.ShipInMemory;
import adapter.repository.inmemory.entity.ShipMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public class ShipInMemoryGateway implements ShipGateway {
    private Map<String, ShipInMemory> entities;
    private final ShipPointsGateway shipPointsGateway;
    private final ShipMapper mapper;

    public ShipInMemoryGateway(final ShipPointsGateway shipPointsGateway) {
        this.entities = HashMap.empty();
        this.shipPointsGateway = shipPointsGateway;
        this.mapper = new ShipMapper();

    }

    @Override
    public ShipDto save(final ShipDto shipDto) {
        entities = entities.put(shipDto.getId(), mapper.mapToEntity(shipDto));
        return shipDto;
    }

    @Override
    public Option<ShipDto> findById(final String id) {
        Option<ShipInMemory> find = entities.get(id);
        if (find.isEmpty()) {
            return Option.none();
        }
        return ShipClass
                .findByShortName(find.get().getShipClassShortName())
                .map(s -> mapper.mapToDto(find.get(), s))
                .toOption();
    }

    @Override
    public Option<Set<ShipDto>> findAllById(Set<String> shipsId) {
        Set<ShipDto> ships = HashSet.empty();
        for (String shipId : shipsId) {
            Option<ShipDto> ship = findById(shipId);
            if (ship.isEmpty()) {
                return Option.none();
            }
            if (ship.isDefined()) {
                ships.add(ship.get());
            }
        }
        return Option.of(ships);
    }

    @Override
    public void remove(String id) {
        entities = entities.remove(id);
    }

}
