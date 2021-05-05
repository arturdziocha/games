package com.ara.game.usecases.battleship.ship;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.enums.ShipClass;
import com.ara.game.usecases.battleship.ship.dto.ShipCreateDto;
import com.ara.game.usecases.battleship.ship.dto.ShipDto;
import com.ara.game.usecases.common.Error;
import com.google.inject.Guice;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.control.Either;

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
        Either<Error, ShipDto> ship = shipFacade.create(createInputData);
        // Then
        
        assertThat(ship.get().getHealth()).isEqualByComparingTo(3);
        assertThat(ship.get().getShipClass().getName()).isEqualTo("Submarine");
    }
}
