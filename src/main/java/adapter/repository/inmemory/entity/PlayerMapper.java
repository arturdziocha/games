package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.enums.PlayerType;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;

public class PlayerMapper {

    public PlayerInMemory mapToEntity(PlayerDto inputData) {
        return new PlayerInMemory.Builder()
                .id(inputData.getId())
                .playerTypeId(inputData.getPlayerType().getId())
                .name(inputData.getName())
                .build();
    }

    public PlayerDto mapToDto(PlayerInMemory playerInMemory, PlayerType playerType) {
        return new PlayerDto.Builder().withId(playerInMemory.getId()).withName(playerInMemory.getName()).withPlayerType(playerType).build();
    }
}
