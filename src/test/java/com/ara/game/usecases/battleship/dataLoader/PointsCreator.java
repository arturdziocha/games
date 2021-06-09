package com.ara.game.usecases.battleship.dataLoader;

import com.ara.game.usecases.battleship.point.PointFacade;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.port.IdGenerator;
import com.google.inject.Inject;
import io.vavr.collection.Set;
import io.vavr.collection.Stream;

final class PointsCreator {
    private final PointFacade pointFacade;

    @Inject
    public PointsCreator(final PointGateway pointGateway, final IdGenerator idGenerator) {
        this.pointFacade = new PointFacade(pointGateway, idGenerator);
    }

    public PointDto createA1() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("a1").build()).get();
    }

    public PointDto createC1() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("c1").build()).get();
    }

    public PointDto createE1() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("e1").build()).get();
    }

    public PointDto createG1() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("g1").build()).get();
    }

    public PointDto createI1() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("i1").build()).get();
    }

    public PointDto createA10() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("a10").build()).get();
    }

    public PointDto createC10() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("c10").build()).get();
    }

    public PointDto createE10() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("e10").build()).get();
    }

    public PointDto createJ10() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("j10").build()).get();
    }

    public PointDto createI10() {
        return pointFacade.create(PointCreateStringDto.builder().pointString("i10").build()).get();
    }

    Set<PointDto> createNinePoints() {
        PointCreateStringDto[] points = {PointCreateStringDto.builder().pointString("a1").build(),
                PointCreateStringDto.builder().pointString("c1").build(),
                PointCreateStringDto.builder().pointString("e1").build(),
                PointCreateStringDto.builder().pointString("g1").build(),
                PointCreateStringDto.builder().pointString("i1").build(),
                PointCreateStringDto.builder().pointString("a10").build(),
                PointCreateStringDto.builder().pointString("c10").build(),
                PointCreateStringDto.builder().pointString("e10").build(),
                PointCreateStringDto.builder().pointString("j10").build()};

        return Stream.of(points).map(p -> pointFacade.create(p).get()).toSortedSet();
    }

    Set<PointDto> createEightPoints() {
        PointCreateStringDto[] points = {PointCreateStringDto.builder().pointString("a1").build(),
                PointCreateStringDto.builder().pointString("c1").build(),
                PointCreateStringDto.builder().pointString("e1").build(),
                PointCreateStringDto.builder().pointString("g1").build(),
                PointCreateStringDto.builder().pointString("i1").build(),
                PointCreateStringDto.builder().pointString("a10").build(),
                PointCreateStringDto.builder().pointString("c10").build(),
                PointCreateStringDto.builder().pointString("e10").build()};
        return Stream.of(points).map(p -> pointFacade.create(p).get()).toSortedSet();
    }

}
