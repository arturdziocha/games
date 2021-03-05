package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.collection.Set;
import io.vavr.collection.SortedSet;
import io.vavr.control.Either;

public class PointFacade {
    private final Creator creator;
    private final Finder finder;
    private final PointsCreator pointsCreator;

    @Inject
    public PointFacade(PointGateway pointGateway, IdGenerator idGenerator) {
        Mapper mapper = new Mapper();
        creator = new Creator(pointGateway, idGenerator, mapper);
        finder = new Finder(pointGateway);
        pointsCreator = new PointsCreator(finder, creator);
    }

    public final Either<Error, CreateDto> create(final PointCreateStringDto inputData) {
        return creator.create(inputData);
    }

    public final Either<Error, CreateDto> create(final PointCreateRowColDto inputData) {
        return creator.create(inputData);
    }

    public Either<Error, Set<CreateDto>> createPoints(final PointsCreateDto inputData) {
        return pointsCreator.create(inputData);
    }

    public Either<Error, Set<CreateDto>> createRandomPoints(final PointsCreateDto inputData) {
        return pointsCreator.createRandom(inputData);
    }

    public final Either<Error, PointDto> findById(final String id) {
        return finder.findById(id);
    }

    public final Either<Error, PointDto> findByRowAndColumn(final Integer row, final Integer column) {
        return finder.findByRowAndColumn(row, column);
    }

    public final Either<Error, PointDto> findByPointString(final String pointString) {
        return finder.findByPointString(pointString);
    }

    public Either<Error, SortedSet<PointDto>> findAllById(Set<String> pointsIds) {
        return finder.findAllById(pointsIds);
    }

}
