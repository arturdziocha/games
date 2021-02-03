package external;

import adapter.id.UuidGenerator;
import adapter.repository.inmemory.port.PointInMemoryGateway;
import adapter.repository.inmemory.port.ShipInMemoryGateway;
import adapter.repository.inmemory.port.ShipPointsInMemoryGateway;

import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.AbstractModule;

public class ConsoleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IdGenerator.class).to(UuidGenerator.class);
        bind(PointGateway.class).to(PointInMemoryGateway.class);
        bind(ShipGateway.class).to(ShipInMemoryGateway.class);
        bind(ShipPointsGateway.class).to(ShipPointsInMemoryGateway.class);
    }
}
