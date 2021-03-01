package com.ara.game.usecases.battleship.playerShips;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.resources.PlayerLoader;
import com.ara.game.usecases.battleship.resources.ShipsLoader;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.control.Either;

class PlayerShipFacadeTest {
    private final PlayerShipFacade playerShipFacade;
    private final PlayerLoader playerLoader;
    private final ShipsLoader shipsLoader;

    public PlayerShipFacadeTest() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        playerShipFacade = injector.getInstance(PlayerShipFacade.class);
        playerLoader = injector.getInstance(PlayerLoader.class);
        shipsLoader = injector.getInstance(ShipsLoader.class);
    }

    @DisplayName("Should save 9 ships of player")
    @Test
    void test() {
        // Given
        PlayerDto playerId = playerLoader.loadFirstPlayer();        
        Set<ShipPointsDto> ships = shipsLoader.loadSix();
        //When
        for(ShipPointsDto ship : ships) {
            Either<Error, PlayerShipDto> added= playerShipFacade.create(new PlayerShipCreateDto.Builder().player(playerId).ship(ship.getShip()).build());
        }        
        // Then
        Either<Error, Set<ShipPointsDto>> findShips = playerShipFacade.find(playerId.getId());
        assertThat(findShips.get().size()).isEqualTo(9);
    }

}
