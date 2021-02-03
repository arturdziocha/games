package com.ara.game.usecases.battleship.playerType;

import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Set;
import io.vavr.control.Either;

public class PlayerTypeFacade {
    private final PlayerTypeFinder finder;

    public PlayerTypeFacade() {
        PlayerTypeMapper mapper = new PlayerTypeMapper();
        this.finder = new PlayerTypeFinder(mapper);
    }

    public final Either<Error, PlayerTypeDto> findById(String id) {
        return finder.findById(id);
    }

    public final Either<Error, PlayerTypeDto> findByName(String name) {
        return finder.findByName(name);
    }

    public final Set<PlayerTypeDto> findAll() {
        return finder.findAll();
    }
}
