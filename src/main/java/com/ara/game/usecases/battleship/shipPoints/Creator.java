package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipWithPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;
import com.ara.game.usecases.common.Error;
import com.google.inject.Inject;
import io.vavr.control.Either;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Creator {
    private final ShipPointsGateway shipPointsGateway;
    private final Validator validator;
    private final Mapper mapper;
    private final Logger log;

    @Inject
    public Creator(final ShipPointsGateway shipPointsGateway) {
        this.shipPointsGateway = shipPointsGateway;
        this.validator = new Validator();
        this.mapper = new Mapper();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    Either<Error, ShipWithPointsDto> createPoints(ShipPointsCreateDto inputData) {
        return validator.validate(inputData).flatMap(s -> savePoints(mapper.mapToEntity(s)));
    }

    private Either<Error, ShipWithPointsDto> savePoints(ShipPoints shipPoints) {
        return Try
                .of(() -> save(shipPoints))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(ShipPointsError.PERSISTENCE_FAILED);
    }

    private ShipWithPointsDto save(ShipPoints shipPoints) {
        return shipPointsGateway.save(mapper.mapToDto(shipPoints));
    }
}
