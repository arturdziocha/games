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
        return entities.get(id).flatMap(this::mapperHelper);
    }

    @Override
    public Option<PlayerDto> findByName(String name) {
        return entities.values().find(find -> find.getName().equals(name)).flatMap(this::mapperHelper);
    }

    @Override
    public PlayerDto save(final PlayerDto inputData) {
        entities = entities.put(inputData.getId(), mapper.mapToEntity(inputData));
        return inputData;
    }

    private Option<PlayerDto> mapperHelper(PlayerInMemory player) {
        return PlayerType.findById(player.getPlayerTypeId()).map(pT -> mapper.mapToDto(player, pT));
    }


}
