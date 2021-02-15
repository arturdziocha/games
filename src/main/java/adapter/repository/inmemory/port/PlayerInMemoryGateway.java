package adapter.repository.inmemory.port;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import com.ara.game.usecases.battleship.playerType.PlayerTypeFacade;

import adapter.repository.inmemory.entity.PlayerInMemory;
import adapter.repository.inmemory.entity.PlayerMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class PlayerInMemoryGateway implements PlayerGateway {
    Map<String, PlayerInMemory> entities = HashMap.empty();
    private final PlayerMapper mapper;
    private final PlayerTypeFacade playerTypeFacade;

    public PlayerInMemoryGateway() {
        this.mapper = new PlayerMapper();
        this.playerTypeFacade = new PlayerTypeFacade();
    }

    @Override
    public Option<PlayerDto> findById(final String id) {
        Option<PlayerInMemory> find = entities.get(id);
        if (find.isEmpty()) {
            return Option.none();
        }
        return playerTypeFacade
                .findById(find.get().getPlayerType())
                .map(pT -> mapper.mapToDto(find.get(), pT))
                .toOption();
    }

    @Override
    public PlayerDto save(final PlayerDto inputData) {
        entities = entities.put(inputData.getId(), mapper.mapToEntity(inputData));
        return inputData;
    }
}
