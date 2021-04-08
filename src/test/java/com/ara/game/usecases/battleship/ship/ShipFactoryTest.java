package com.ara.game.usecases.battleship.ship;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;
import external.ConsoleModule;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShipFactoryTest {
    private ShipFacade shipFacade;

    @BeforeEach
    void before() {
        Injector injector = Guice.createInjector(new ConsoleModule());
        shipFacade = injector.getInstance(ShipFacade.class);
    }

    @Test
    @DisplayName("Should create Ship Submarine")
    void test1() {
        // Given
        ShipClass shipClass = ShipClass.SUBMARINE;
        ShipCreateDto createInputData = new ShipCreateDto.Builder().shipClass(shipClass).build();
        // When
        Either<Error, CreateDto> ship = shipFacade.create(createInputData);
        // Then
        Either<Error, ShipDto> findShip = shipFacade.find(ship.get().getId());
        assertThat(findShip.get().getHealth()).isEqualByComparingTo(3);
        assertThat(findShip.get().getShipClass().getName()).isEqualTo("Submarine");
    }
}
