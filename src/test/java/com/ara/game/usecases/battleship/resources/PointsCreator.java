package com.ara.game.usecases.battleship.resources;

import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;
import io.vavr.control.Either;

final class PointsCreator {
    private final PointFacade pointFacade;

    @Inject
    public PointsCreator(final PointGateway pointGateway, final IdGenerator idGenerator) {
        this.pointFacade = new PointFacade(pointGateway, idGenerator);
    }

    public PointDto createA1() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("a1").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createC1() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("c1").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createe1() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("e1").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createG1() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("g1").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createI1() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("i1").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createA10() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("a10").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createC10() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("c10").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createE10() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("e10").build());
        return pointFacade.findByPointString("a1").get();
    }

    public PointDto createJ10() {
        Either<Error, CreateDto> point =
                pointFacade.create(new PointCreateStringDto.Builder().pointString("j10").build());
        return pointFacade.findByPointString("a1").get();
    }

    Set<PointDto> createNinePoints() {
        PointCreateStringDto[] points = {new PointCreateStringDto.Builder().pointString("a1").build(),
                new PointCreateStringDto.Builder().pointString("c1").build(),
                new PointCreateStringDto.Builder().pointString("e1").build(),
                new PointCreateStringDto.Builder().pointString("g1").build(),
                new PointCreateStringDto.Builder().pointString("i1").build(),
                new PointCreateStringDto.Builder().pointString("a10").build(),
                new PointCreateStringDto.Builder().pointString("c10").build(),
                new PointCreateStringDto.Builder().pointString("e10").build(),
                new PointCreateStringDto.Builder().pointString("j10").build()};

        return Stream
                .of(points)
                .map(p -> pointFacade.create(p).get())
                .map(l -> pointFacade.findById(l.getId()).get())
                .toSortedSet();
    }

    Set<String> createEightPoints() {
        PointCreateStringDto[] points = {new PointCreateStringDto.Builder().pointString("a1").build(),
                new PointCreateStringDto.Builder().pointString("c1").build(),
                new PointCreateStringDto.Builder().pointString("e1").build(),
                new PointCreateStringDto.Builder().pointString("g1").build(),
                new PointCreateStringDto.Builder().pointString("i1").build(),
                new PointCreateStringDto.Builder().pointString("a10").build(),
                new PointCreateStringDto.Builder().pointString("c10").build(),
                new PointCreateStringDto.Builder().pointString("e10").build()};
        return Stream.of(points).map(p -> pointFacade.create(p).get()).map(CreateDto::getId).toSet();
    }

}
