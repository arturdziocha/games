package com.ara.game.usecases.battleship.playerShips;

import com.ara.game.usecases.battleship.dataLoader.PlayerLoader;
import com.ara.game.usecases.battleship.dataLoader.ShipsCreator;
import com.ara.game.usecases.battleship.player.dto.PlayerDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipCreateDto;
import com.ara.game.usecases.battleship.playerShips.dto.PlayerShipDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.control.Either;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerShipFacadeTest {
    private final PlayerShipFacade playerShipFacade;
    private final PlayerLoader playerLoader;
    private final ShipsCreator shipsLoader;

    public PlayerShipFacadeTest() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        playerShipFacade = injector.getInstance(PlayerShipFacade.class);
        playerLoader = injector.getInstance(PlayerLoader.class);
        shipsLoader = injector.getInstance(ShipsCreator.class);
    }

    @DisplayName("Should save 9 ships of player")
    @Test
    void test1() {
        // Given
        PlayerDto playerId = playerLoader.loadFirstPlayer();
        Set<ShipWithPointsDto> ships = shipsLoader.createNineShips();
        // When
        for (ShipWithPointsDto ship : ships) {
            playerShipFacade.create(PlayerShipCreateDto.builder().player(playerId).ship(ship).build());
        }
        // Then
        Either<Error, Set<ShipWithPointsDto>> findShips = playerShipFacade.find(playerId.getId());
        assertThat(findShips.get().size()).isEqualTo(9);
    }

    @DisplayName("Should return Either left All ships already placed when try to save more than 9 ships")
    @Test
    void test2() {
        // Given
        PlayerDto playerId = playerLoader.loadFirstPlayer();
        Set<ShipWithPointsDto> ships = shipsLoader.createNineShips();
        ShipWithPointsDto shipToPlace = shipsLoader.createBattleshipE10Up();
        // When
        for (ShipWithPointsDto ship : ships) {
            playerShipFacade.create(PlayerShipCreateDto.builder().player(playerId).ship(ship).build());
        }
        Either<Error, PlayerShipDto> ship = playerShipFacade
                .create(PlayerShipCreateDto.builder().ship(shipToPlace).player(playerId).build());
        // Then
        assertThat(ship.getLeft().getCause()).isEqualTo("All ships already placed");
    }

    @DisplayName("Should return Either left Ship is already placed when try to save already placed ship")
    @Test
    void test3() {
        // Given
        PlayerDto playerId = playerLoader.loadFirstPlayer();
        ShipWithPointsDto firstBattleship = shipsLoader.createBattleshipE10Up();
        ShipWithPointsDto secondBattleship = shipsLoader.createBattleshipE10Up();
        playerShipFacade
                .create(PlayerShipCreateDto.builder().ship(firstBattleship).player(playerId).build());

        // When
        Either<Error, PlayerShipDto> shipWithError = playerShipFacade
                .create(PlayerShipCreateDto.builder().ship(secondBattleship).player(playerId).build());
        // Then
        assertThat(shipWithError.getLeft().getCause()).isEqualTo("Ship is already placed");
    }

    @DisplayName("Should return Either left Ship is to close other ships when trying to save to close ship")
    @Test
    void test4() {
        // Given
        PlayerDto playerId = playerLoader.loadFirstPlayer();
        ShipWithPointsDto firstBattleship = shipsLoader.createCarrierJ10Up();
        ShipWithPointsDto secondBattleship = shipsLoader.createBattleshipToClose();
        playerShipFacade
                .create(PlayerShipCreateDto.builder().ship(firstBattleship).player(playerId).build());

        // When
        Either<Error, PlayerShipDto> shipWithError = playerShipFacade
                .create(PlayerShipCreateDto.builder().ship(secondBattleship).player(playerId).build());
        // Then
        assertThat(shipWithError.getLeft().getCause()).isEqualTo("Ship is to close other ships");
    }

}
