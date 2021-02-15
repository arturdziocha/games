package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;

public class PlayerMapper {

    public PlayerInMemory mapToEntity(PlayerDto inputData) {
        return new PlayerInMemory.Builder()
                .id(inputData.getId())
                .playerType(inputData.getPlayerType().getId())
                .name(inputData.getName())
                .build();
    }

    public PlayerDto mapToDto(PlayerInMemory playerInMemory, PlayerTypeDto playerType) {
        return new PlayerDto.Builder().id(playerInMemory.getId()).name(playerInMemory.getName()).playerType(playerType).build();
    }
}
