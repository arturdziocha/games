package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;

import io.vavr.control.Option;

public class GameMapper {
    public GameInMemory mapToEntity(GameDto game) {
        return new GameInMemory.Builder()
                .id(game.getId())
                .firstPlayer(game.getFirstPlayer().getId())
                .secondPLayer(game.getSecondPlayer().isDefined()?game.getSecondPlayer().get().getId():"")
                .currentPlayer(game.getCurrentPlayer().getId())
                .build();
    }

    public GameDto mapToDto(String gameId, PlayerDto firstPlayer, Option<PlayerDto> secondPlayer, PlayerDto currentPlayer) {
        return new GameDto.Builder()
                .id(gameId)
                .player(firstPlayer)
                .secondPlayer(secondPlayer)
                .currentPlayer(currentPlayer)
                .build();
    }
}
