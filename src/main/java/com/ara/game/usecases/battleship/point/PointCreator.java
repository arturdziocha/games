package com.ara.game.usecases.battleship.point;

import java.util.Arrays;
import java.util.List;

import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.port.IdGenerator;

final class PointCreator {
    private final PointGateway pointGateway;
    private final IdGenerator idGenerator;
    private final PointMapper mapper;
    private final Validator validator;

    private final List<Character> chars = Arrays
            .asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'W', 'X', 'Y', 'Z');

    PointCreator(final PointGateway pointGateway, final IdGenerator idGenerator, final PointMapper mapper) {
        this.pointGateway = pointGateway;
        this.idGenerator = idGenerator;
        this.mapper = mapper;
        this.validator = new Validator();
    }
    //TODO CREATE

}
