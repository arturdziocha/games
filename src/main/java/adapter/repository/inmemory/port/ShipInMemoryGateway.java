package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.ShipInMemory;
import adapter.repository.inmemory.entity.ShipMapper;
import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class ShipInMemoryGateway implements ShipGateway {
    private Map<String, ShipInMemory> entities;
    private final ShipMapper mapper;

    public ShipInMemoryGateway() {
        this.entities = HashMap.empty();
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
        return ShipClass.findByShortName(find.get().getShipClassShortName())
                .map(s -> mapper.mapToDto(find.get(), s))
                .toOption();
    }

    @Override
    public void remove(final String id) {
        entities = entities.remove(id);
    }
}
