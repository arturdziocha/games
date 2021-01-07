package adapter.repository.port;

import adapter.repository.entity.ShipInMemory;
import adapter.repository.entity.ShipMapper;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipClass.ShipClassFacade;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class ShipInMemoryGateway implements ShipGateway {
    private Map<String, ShipInMemory> entities;
    private final ShipMapper mapper;
    private final ShipClassFacade shipClassFacade;

    public ShipInMemoryGateway() {
        this.entities = HashMap.empty();
        this.mapper = new ShipMapper();
        this.shipClassFacade = new ShipClassFacade();

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
        return shipClassFacade
                .findByShortName(find.get().getShipClassShortName())
                .map(s -> mapper.mapToDto(find.get(), s))
                .toOption();
    }

    @Override
    public void remove(final String id) {
        entities = entities.remove(id);
    }
}
