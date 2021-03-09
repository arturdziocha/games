package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.GameInMemory;
import adapter.repository.inmemory.entity.GameMapper;
import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.game.port.GameGateway;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class GameInMemoryGateway implements GameGateway {
    private final GameMapper mapper;
    private final PlayerGateway playerGateway;
    private Map<String, GameInMemory> entities;

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
        PlayerDto firstPlayer = playerGateway.findById(gameInMemory.getFirstPlayer()).getOrElse(fancyPlayer());
        PlayerDto secondPlayer = playerGateway.findById(gameInMemory.getSecondPlayer()).getOrElse(fancyPlayer());
        PlayerDto currentPlayer = playerGateway.findById(gameInMemory.getCurrentPlayer()).getOrElse(fancyPlayer());
        return Option.of(mapper.mapToDto(gameId, firstPlayer, secondPlayer, currentPlayer));
    }

    @Override
    public void remove(String gameId) {
        // TODO Auto-generated method stub

    }

    private PlayerDto fancyPlayer() {
        return new PlayerDto.Builder().id("").playerType(PlayerType.FANCY_PLAYER).name("").build();
    }

}
