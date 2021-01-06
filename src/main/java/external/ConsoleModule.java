package external;

import adapter.id.UuidGenerator;
import adapter.repository.port.ShipInMemoryGateway;
import com.ara.game.usecases.battleship.ship.port.ShipGateway;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.AbstractModule;

public class ConsoleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(IdGenerator.class).to(UuidGenerator.class);
        bind(ShipGateway.class).to(ShipInMemoryGateway.class);
    }
}
