package com.ara.game.usecases.battleship.shipClass;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;
import com.ara.game.usecases.common.Error;
import io.vavr.control.Either;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShipClassFacadeTest {
    private ShipClassFacade shipClassFacade;

    @BeforeEach
    void setUp() {
        shipClassFacade = new ShipClassFacade();
    }

    @Test
    @DisplayName("Find class of ship when name is empty, should return Either.left ")
    void test1() {
        // Given When
        Either<Error, ShipClassDto> shipClass = shipClassFacade.findByName("");
        // Then
        assertThat(shipClass.getLeft().getCause()).isEqualTo("Data cannot be empty");
    }

    @Test
    @DisplayName("Find class of ship when null should return Either.left ")
    void test2() {
        // Given when
        Either<Error, ShipClassDto> shipClass = shipClassFacade.findByName(null);
        // Then
        assertThat(shipClass.getLeft().getCause()).isEqualTo("Data cannot be empty");
    }

    @Test
    @DisplayName("Find class of ship should return Either right ")
    void findShipClassshouldReturnOption() {
        Either<Error, ShipClassDto> shipClass = shipClassFacade.findByName("Barca");
        assertThat(shipClass.get().getName()).isEqualTo("Barca");
        assertThat(shipClass.get().getSize()).isEqualByComparingTo(1);
    }

    @Test
    @DisplayName("Find class of ship should return Either left")
    void findShipClassShouldReturnEitherLeft() {
        Either<Error, ShipClassDto> shipClass = shipClassFacade.findByName("Bridge");
        assertThat(shipClass.getLeft().getCause()).isEqualTo("Ship class cannot be find");
    }
}
