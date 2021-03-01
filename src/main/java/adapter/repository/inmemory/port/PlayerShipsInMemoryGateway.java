package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.google.inject.Inject;

import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public final class PlayerShipsInMemoryGateway implements PlayerShipGateway {
    private Map<String, Set<String>> entities;
    private final ShipPointsGateway shipPointsGateway;

    @Inject
    public PlayerShipsInMemoryGateway(final ShipPointsGateway shipPointsGateway) {
        this.entities = HashMap.empty();
        this.shipPointsGateway = shipPointsGateway;

    }

    @Override
    public PlayerShipDto save(PlayerShipDto inputData) {       
        Option<Set<String>> old = entities.get(inputData.getPlayer().getId());
        if (old.isDefined()) {
            Set<String> toReplace = old.get().add(inputData.getShip().getId());
            entities = entities.replaceValue(inputData.getPlayer().getId(), toReplace);
        } else {
            entities.put(inputData.getPlayer().getId(), HashSet.of(inputData.getShip().getId()));
        }
        return inputData;
    }

    @Override
    public Option<Set<ShipPointsDto>> findAllShips(String playerId) {
        return entities.get(playerId).flatMap(s -> shipPointsGateway.findAllById(s));
    }

    @Override
    public Option<ShipPointsDto> findByPlayerIdAndShipClassShortName(String playerId, String shipClassShortName) {
        Option<Set<String>> playerShips = entities.get(playerId);
        if (playerShips.isEmpty()) {
            return Option.none();
        }
        return playerShips
                .flatMap(shipPointsGateway::findAllById)
                .flatMap(l -> l.find(k -> k.getShip().getShipClass().getShortName().equals(shipClassShortName)));
    }

    @Override
    public void remove(String playerId) {
        // TODO Auto-generated method stub

    }

}
