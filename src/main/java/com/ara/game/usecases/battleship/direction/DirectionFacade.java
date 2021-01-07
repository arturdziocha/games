package com.ara.game.usecases.battleship.direction;

import com.ara.game.usecases.battleship.direction.dto.DirectionDto;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Seq;
import io.vavr.control.Either;

public final class DirectionFacade {
    private final DirectionFinder finder;

    public DirectionFacade() {
        DirectionMapper mapper = new DirectionMapper();
        this.finder = new DirectionFinder(mapper);
    }

    public final Either<Error, DirectionDto> findByName(final String name) {
        return finder.findByName(name);
    }

    public final Either<Error, DirectionDto> findByShortName(final String shortName) {
        return finder.findByShortName(shortName);
    }

    public final Seq<DirectionDto> findAll() {
        return finder.findAll();
    }

    public final DirectionDto findRandom() {
        return finder.findRandom();
    }

}