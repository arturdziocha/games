package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.game.dto.GameDto;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import io.vavr.collection.Set;

public class GameMapper {
    public GameInMemory mapToEntity(GameDto game) {
        return new GameInMemory.Builder()
                .id(game.getId())
                .players(game.getPlayers().map(PlayerDto::getId))
                .currentPlayer(game.getCurrentPlayer().getId())
                .isStarted(game.isStarted())
                .size(game.getSize())
                .isFinished(game.isFinished())
                .createdTime(game.getCreatedTime())
                .build();
    }

    public GameDto mapToDto(GameInMemory game, Set<PlayerDto> players, PlayerDto currentPlayer) {
        return new GameDto.Builder()
                .id(game.getId())
                .players(players)
                .currentPlayer(currentPlayer)
                .isStarted(game.isStarted())
                .size(game.getSize())
                .isFinished(game.isFinished())
                .createdTime(game.getCreatedTime())
                .build();
    }
}
