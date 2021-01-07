package com.ara.game.usecases.battleship.shipClass;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import io.vavr.control.Either;
import org.apache.commons.lang3.StringUtils;

final class ShipClassFinder {
    private final ShipClassMapper mapper;

    ShipClassFinder(final ShipClassMapper mapper) {
        this.mapper = mapper;
    }

    Either<Error, ShipClassDto> findByName(final String name) {
        if (StringUtils.isEmpty(name)) {
            return Either.left(ShipClassError.DATA_CANNOT_BE_EMPTY);
        }
        return Stream
                .of(ShipClass.values())
                .find(sC -> sC.getName().equals(name))
                .map(mapper::mapToDTO)
                .toEither(ShipClassError.SHIP_CLASS_NOT_FOUND);
    }

    Either<Error, ShipClassDto> findByShortName(final String shortName) {
        if (StringUtils.isEmpty(shortName)) {
            return Either.left(ShipClassError.DATA_CANNOT_BE_EMPTY);
        }
        return Stream
                .of(ShipClass.values())
                .find(sC -> sC.getShortName().equals(shortName))
                .map(mapper::mapToDTO)
                .toEither(ShipClassError.SHIP_CLASS_NOT_FOUND);
    }

    Seq<ShipClassDto> findAll() {
        return Stream.of(ShipClass.values()).map(mapper::mapToDTO);
    }

}