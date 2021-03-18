package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.playerShips.port.PlayerShipGateway;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.google.inject.Inject;
import io.vavr.collection.HashMap;
import io.vavr.collection.HashSet;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public final class PlayerShipsInMemoryGateway implements PlayerShipGateway {
    private final ShipPointsGateway shipPointsGateway;
    private Map<String, Set<String>> entities;

    @Inject
    public PlayerShipsInMemoryGateway(final ShipPointsGateway shipPointsGateway) {
        this.entities = HashMap.empty();
        this.shipPointsGateway = shipPointsGateway;

    }

    @Override
    public PlayerShipDto save(PlayerShipDto inputData) {
        Option<Set<String>> old = entities.get(inputData.getPlayer().getId());
        if (old.isDefined()) {
            Set<String> oldSet = old.get();
            Set<String> newSet = oldSet.add(inputData.getShip().getId());
            entities = entities.replaceValue(inputData.getPlayer().getId(), newSet);
        } else {
            entities = entities.put(inputData.getPlayer().getId(), HashSet.of(inputData.getShip().getId()));
        }
        return inputData;
    }

    @Override
    public Option<Set<ShipWithPointsDto>> findAllShips(String playerId) {
        return entities.get(playerId).flatMap(shipPointsGateway::findAllById);
    }

    @Override
    public Option<ShipWithPointsDto> findByPlayerIdAndShipClassShortName(String playerId, String shipClassShortName) {
        Option<Set<String>> playerShips = entities.get(playerId);
        if (playerShips.isEmpty()) {
            return Option.none();
        }
        return playerShips
                .flatMap(shipPointsGateway::findAllById)
                .flatMap(l -> l.find(k -> k.getShip().getShipClass().getShortName().equals(shipClassShortName)));
    }

    @Override
    public Option<ShipWithPointsDto> findByPlayerIdAndPointString(String playerId, String pointString) {
        Option<Set<ShipWithPointsDto>> all = findAllShips(playerId);
        if (all.isDefined()) {
            Set<ShipWithPointsDto> l = all.get();
            for (ShipWithPointsDto ship : l) {
                Option<PointDto> a = ship.getPoints().find(c -> c.getPointString().equals(pointString));
                if (a.isDefined()) {
                    return Option.some(ship);
                }
            }
        }
        return Option.none();
    }

    @Override
    public void remove(String playerId) {
        // TODO Auto-generated method stub

    }


}
