package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.control.Either;

public class PointFacade {
    private final PointCreator creator;
    private final PointFinder finder;

    @Inject
    public PointFacade(PointGateway pointGateway, IdGenerator idGenerator) {
        PointMapper mapper = new PointMapper();
        creator = new PointCreator(pointGateway, idGenerator, mapper);
        finder = new PointFinder(pointGateway);
        //TODO pointsCreator = new PointsCreator(finder, pointCreator);
    }

    public final Either<Error, CreateDto> create(PointCreateStringDto inputData) {
        return creator.create(inputData);
    }

    public final Either<Error, CreateDto> create(PointCreateRowColDto inputData) {
        return creator.create(inputData);
    }
}
