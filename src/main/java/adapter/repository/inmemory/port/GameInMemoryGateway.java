package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;

import adapter.repository.inmemory.entity.GameInMemory;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class GameInMemoryGateway implements GameGateway {
    private Map<String, GameInMemory> entities;
    private final PlayerGateway playerGateway;

    public GameInMemoryGateway(final PlayerGateway playerGateway) {
        this.entities = HashMap.empty();
        this.playerGateway = playerGateway;
    }

    @Override
    public GameDto save(GameDto game) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameDto update(GameDto mapToDto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Option<GameDto> find(String gameId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(String gameId) {
        // TODO Auto-generated method stub

    }

}
