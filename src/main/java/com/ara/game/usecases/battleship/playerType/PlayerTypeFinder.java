package com.ara.game.usecases.battleship.playerType;

import org.apache.commons.lang3.StringUtils;

import com.ara.game.usecases.battleship.playerType.dto.PlayerTypeDto;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import io.vavr.control.Either;

final class PlayerTypeFinder {
    private final PlayerTypeMapper mapper;

    public PlayerTypeFinder(final PlayerTypeMapper mapper) {
        this.mapper = mapper;
    }

    final Either<Error, PlayerTypeDto> findById(String id) {
        if (StringUtils.isEmpty(id)) {
            return Either.left(PlayerTypeError.DATA_CANNOT_BE_EMPTY);
        }
        return Stream
                .of(PlayerType.values())
                .find(pT -> pT.getId().equals(id))
                .map(mapper::mapToDTO)
                .toEither(PlayerTypeError.CANNOT_FIND_TYPE_OF_PLAYER);
    }

    final Either<Error, PlayerTypeDto> findByName(String name) {
        if (StringUtils.isEmpty(name)) {
            return Either.left(PlayerTypeError.DATA_CANNOT_BE_EMPTY);
        }
        return Stream
                .of(PlayerType.values())
                .find(pT -> pT.getName().equals(name))
                .map(mapper::mapToDTO)
                .toEither(PlayerTypeError.CANNOT_FIND_TYPE_OF_PLAYER);
    }

    final Set<PlayerTypeDto> findAll() {
        return Stream.of(PlayerType.values()).map(mapper::mapToDTO).toSet();
    }
}
