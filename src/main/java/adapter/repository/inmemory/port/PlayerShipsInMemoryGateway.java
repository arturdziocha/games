package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;

import adapter.repository.inmemory.entity.PlayerShipsMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public final class PlayerShipsInMemoryGateway implements PlayerShipGateway {
    private Map<String, Set<String>> entities;
    private final PlayerShipsMapper mapper;
    public PlayerShipsInMemoryGateway() {
        this.entities = HashMap.empty();
        this.mapper = new PlayerShipsMapper();

    }
    @Override
    public PlayerShipDto save(PlayerShipDto mapToDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Option<Set<ShipPointsDto>> findAllShips(String playerId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Option<ShipPointsDto> findByPlayerIdAndShipClassShortName(String playerId, String shipClassShortName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(String playerId) {
        // TODO Auto-generated method stub

    }

}
