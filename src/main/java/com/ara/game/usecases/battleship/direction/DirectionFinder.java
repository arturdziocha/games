package com.ara.game.usecases.battleship.direction;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.ara.game.usecases.battleship.direction.dto.DirectionDto;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Either;

final class DirectionFinder {
    private final DirectionMapper mapper;

    DirectionFinder(DirectionMapper mapper) {
        this.mapper = mapper;
    }

    final Either<Error, DirectionDto> findByName(final String name) {
        if (StringUtils.isEmpty(name)) {
            return Either.left(DirectionError.VALUE_CANNOT_BE_EMPTY);
        }
        return Stream
                .of(Direction.values())
                .find(direction -> direction.getName().equals(name))
                .map(mapper::mapToDTO)
                .toEither(DirectionError.CANNOT_FIND_DIRECTION);
    }

    final Either<Error, DirectionDto> findByShortName(final String shortName) {
        if (StringUtils.isEmpty(shortName)) {
            return Either.left(DirectionError.VALUE_CANNOT_BE_EMPTY);
        }
        return Stream
                .of(Direction.values())
                .find(direction -> direction.getShortName().equals(shortName))
                .map(mapper::mapToDTO)
                .toEither(DirectionError.CANNOT_FIND_DIRECTION);
    }

    final Seq<DirectionDto> findAll() {
        return Stream.of(Direction.values()).map(mapper::mapToDTO);
    }

    final DirectionDto findRandom() {
        Seq<DirectionDto> all = findAll();
        return all.get(new Random().nextInt(all.size()));
    }

}
