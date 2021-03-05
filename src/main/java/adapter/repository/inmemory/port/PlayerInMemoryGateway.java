package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.PlayerInMemory;
import adapter.repository.inmemory.entity.PlayerMapper;
import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class PlayerInMemoryGateway implements PlayerGateway {
    private final PlayerMapper mapper;
    Map<String, PlayerInMemory> entities = HashMap.empty();

    public PlayerInMemoryGateway() {
        this.mapper = new PlayerMapper();
    }

    @Override
    public Option<PlayerDto> findById(final String id) {
        Option<PlayerInMemory> find = entities.get(id);
        if (find.isEmpty()) {
            return Option.none();
        }
        return PlayerType
                .findById(find.get().getPlayerTypeId())
                .map(pT -> mapper.mapToDto(find.get(), pT))
                .toOption();
    }

    @Override
    public PlayerDto save(final PlayerDto inputData) {
        entities = entities.put(inputData.getId(), mapper.mapToEntity(inputData));
        return inputData;
    }
}
