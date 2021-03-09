package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.game.dto.GameDto;

public class GameMapper {
    GameInMemory mapToEntity(GameDto game) {
        return new GameInMemory.Builder()
                .id(game.getId())
                .firstPlayer(game.getFirstPlayer().getId())
                .secondPLayer(game.getSecondPlayer().getId())
                .currentPlayer(game.getCurrentPlayer().getId())
                .build();
    }
}
