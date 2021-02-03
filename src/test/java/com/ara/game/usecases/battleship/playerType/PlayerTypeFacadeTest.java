package com.ara.game.usecases.battleship.playerType;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Either;

class PlayerTypeFacadeTest {
    private PlayerTypeFacade playerTypeFacade;
    @BeforeEach
    void setUp() {
        playerTypeFacade = new PlayerTypeFacade();
    }

    @Test
    @DisplayName("Should return Either left when id is null")
    void test1() {
        // Given when
        Either<Error, PlayerTypeDto> pointStatus = playerTypeFacade.findById(null);
        // Then
        assertThat(pointStatus.getLeft().getCause()).isEqualTo("Data cannot be empty");
    }
    @Test
    @DisplayName("Should return Either left when id is empty")
    void test2() {
        // Given when
        Either<Error, PlayerTypeDto> pointStatus = playerTypeFacade.findById("");
        // Then
        assertThat(pointStatus.getLeft().getCause()).isEqualTo("Data cannot be empty");
    }
    @Test
    @DisplayName("Should return Either left when player id don't exists")
    void test3() {
        // Given when
        Either<Error, PlayerTypeDto> pointStatus = playerTypeFacade.findById("3");
        // Then
        assertThat(pointStatus.getLeft().getCause()).isEqualTo("Cannot find player type");
    }
    @Test
    @DisplayName("Should find player type with id")
    void test4() {
        // Given when
        Either<Error, PlayerTypeDto> pointStatus = playerTypeFacade.findById("1");
        // Then
        assertThat(pointStatus.get().getName()).isEqualTo("Human Player");
    }

}
