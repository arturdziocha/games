package com.ara.game.usecases.battleship.shipPoints;

import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsCreateDto;
import com.ara.game.usecases.battleship.shipPoints.dto.ShipPointsDto;
import com.ara.game.usecases.battleship.shipPoints.port.ShipPointsGateway;

import io.vavr.control.Either;
import io.vavr.control.Option;
import com.ara.game.usecases.common.Error;
class ShipPointsCreator {
    private final ShipPointsGateway shipPointsGateway;
    private final ShipPointsValidator validator;

    public ShipPointsCreator(final ShipPointsGateway shipPointsGateway) {
        this.shipPointsGateway = shipPointsGateway;
        this.validator = new ShipPointsValidator();
    }
    Either<Error, ShipPointsDto> createPoints(ShipPointsCreateDto shipPoints) {
        Option<Error> validated = validator.validateAll(shipPoints);

        if (validated.isEmpty()) {
            ShipPointsDto dto = shipPointsGateway.saveAll(shipPoints);
            return Either.right(dto);
        }
        return Either.left(validated.get());
    }
    private Either<Error, ShipPointsDto> savePoints(ShipPoints shipPoints){
        
    }
}
