package com.ara.game.usecases.battleship.shipClass;

import com.ara.game.usecases.battleship.shipClass.dto.ShipClassDto;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.Seq;
import io.vavr.control.Either;

public final class ShipClassFacade {
    private final ShipClassFinder finder;

    public ShipClassFacade() {
        ShipClassMapper mapper = new ShipClassMapper();
        this.finder = new ShipClassFinder(mapper);

    }

    public Either<Error, ShipClassDto> findByName(String name) {
        return finder.findByName(name);
    }

    public Either<Error, ShipClassDto> findByShortName(String shortName) {
        return finder.findByShortName(shortName);
    }

    public Seq<ShipClassDto> findAll() {
        return finder.findAll();
    }
}