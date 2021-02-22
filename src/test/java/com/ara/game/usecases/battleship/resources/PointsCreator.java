package com.ara.game.usecases.battleship.resources;

import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import external.ConsoleModule;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

final class PointsCreator {
    private final PointFacade pointFacade;

    @Inject
    public PointsCreator(final PointGateway pointGateway, final IdGenerator idGenerator) {
        this.pointFacade = new PointFacade(pointGateway, idGenerator);
    }

    Set<String> createSixPoints() {
        PointCreateStringDto[] points = { new PointCreateStringDto.Builder().pointString("a1").build(),
                new PointCreateStringDto.Builder().pointString("c1").build(),
                new PointCreateStringDto.Builder().pointString("e1").build(),
                new PointCreateStringDto.Builder().pointString("g1").build(),
                new PointCreateStringDto.Builder().pointString("i1").build(),
                new PointCreateStringDto.Builder().pointString("b9").build() };
        return Stream.of(points).map(p -> pointFacade.create(p).get()).map(CreateDto::getId).toSet();
    }

    Set<String> createFivePoints() {
        PointCreateStringDto[] points = { new PointCreateStringDto.Builder().pointString("a1").build(),
                new PointCreateStringDto.Builder().pointString("c1").build(),
                new PointCreateStringDto.Builder().pointString("e1").build(),
                new PointCreateStringDto.Builder().pointString("g1").build(),
                new PointCreateStringDto.Builder().pointString("i1").build() };
        return Stream.of(points).map(p -> pointFacade.create(p).get()).map(CreateDto::getId).toSet();
    }

}
