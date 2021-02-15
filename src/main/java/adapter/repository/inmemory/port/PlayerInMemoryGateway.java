package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.PlayerInMemory;
import adapter.repository.inmemory.entity.PlayerMapper;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.player.port.PlayerGateway;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.control.Option;

public class PlayerInMemoryGateway implements PlayerGateway {
    Map<String, PlayerInMemory> entities = HashMap.empty();
    private PlayerMapper mapper;

    @Override
    public Option<PlayerDto> findById(String id) {
        return null;
    }

    @Override
    public PlayerDto save(PlayerDto inputData) {
        return null;
    }
}
