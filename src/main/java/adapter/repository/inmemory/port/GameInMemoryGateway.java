package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.google.inject.Inject;

import adapter.repository.inmemory.entity.GameInMemory;
import adapter.repository.inmemory.entity.GameMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.control.Option;

public class GameInMemoryGateway implements GameGateway {
    private final GameMapper mapper;
    private final PlayerGateway playerGateway;
    private Map<String, GameInMemory> entities;

    @Inject
    public GameInMemoryGateway(final PlayerGateway playerGateway) {
        this.playerGateway = playerGateway;
        this.mapper = new GameMapper();
        this.entities = HashMap.empty();

    }

    @Override
    public GameDto save(GameDto game) {
        entities = entities.put(game.getId(), mapper.mapToEntity(game));
        return game;
    }

    @Override
    public GameDto update(GameDto game) {
        entities = entities.replaceValue(game.getId(), mapper.mapToEntity(game));
        return game;
    }

    @Override
    public Option<GameDto> find(String gameId) {
        Option<GameInMemory> find = entities.get(gameId);
        if (find.isEmpty()) {
            return Option.none();
        }
        GameInMemory gameInMemory = find.get();
        Set<PlayerDto> players = gameInMemory.getPlayers().flatMap(playerGateway::findById);

        Option<PlayerDto> currentPlayer = playerGateway.findById(gameInMemory.getCurrentPlayer());
        if (currentPlayer.isDefined()) {
            return Option.of(mapper.mapToDto(gameInMemory, players, currentPlayer.get()));
        }
        return Option.none();
    }

    @Override
    public void remove(String gameId) {
        // TODO Auto-generated method stub

    }
}
