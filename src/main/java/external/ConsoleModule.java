package external;

import adapter.id.UuidGenerator;
import adapter.repository.inmemory.port.PlayerInMemoryGateway;
import adapter.repository.inmemory.port.PointInMemoryGateway;
import adapter.repository.inmemory.port.ShipInMemoryGateway;
import adapter.repository.inmemory.port.ShipPointsInMemoryGateway;

import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class ConsoleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IdGenerator.class).to(UuidGenerator.class);
        bind(PointGateway.class).to(PointInMemoryGateway.class).in(Singleton.class);
        bind(ShipGateway.class).to(ShipInMemoryGateway.class);
        bind(ShipPointsGateway.class).to(ShipPointsInMemoryGateway.class);
        bind(PlayerGateway.class).to(PlayerInMemoryGateway.class);
    }
}
