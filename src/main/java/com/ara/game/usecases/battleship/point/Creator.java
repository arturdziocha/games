package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

final class Creator {
    private final PointGateway pointGateway;
    private final IdGenerator idGenerator;
    private final Mapper mapper;
    private final Validator validator;
    private final Logger log;

    Creator(final PointGateway pointGateway, final IdGenerator idGenerator) {
        this.pointGateway = pointGateway;
        this.idGenerator = idGenerator;
        this.mapper = new Mapper();
        this.validator = new Validator();
        this.log = LoggerFactory.getLogger(Creator.class);
    }

    final Either<Error, PointDto> create(final PointCreateStringDto inputData) {
        Option<Error> validation = validator.validatePointString(inputData);
        if (validation.isDefined()) {
            return Either.left(validation.get());
        }
        String pointString = inputData.getPointString().toUpperCase();
        Option<PointDto> find = pointGateway.findByPointString(pointString);

        if (find.isDefined()) {
            return Either.right(find.get());
        } else {
            Integer row = createRowFromString(pointString);
            Integer column = createColumnFromString(pointString);

            Point point = Point.builder()
                    .id(idGenerator.generate())
                    .withRow(row)
                    .withColumn(column)
                    .withPointString(pointString)
                    .build();

            return savePoint(point);
        }
    }

    final Either<Error, PointDto> create(final PointCreateRowColDto inputData) {
        Option<Error> validation = validator.validateRowCol(inputData);
        if (validation.isDefined()) {
            return Either.left(validation.get());
        }
        Option<PointDto> find = pointGateway.findByRowAndColumn(inputData.getRow(), inputData.getColumn());
        if (find.isDefined()) {
            return Either.right(find.get());
        } else {
            String pointString = createStringFromRowAndColumn(inputData.getRow(), inputData.getColumn());
            Point point = Point.builder()
                    .id(idGenerator.generate())
                    .withRow(inputData.getRow())
                    .withColumn(inputData.getColumn())
                    .withPointString(pointString)
                    .build();
            return savePoint(point);
        }
    }

    private Either<Error, PointDto> savePoint(final Point point) {
        return Try
                .of(() -> save(point))
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(PointError.PERSISTENCE_FAILED);
    }

    private PointDto save(final Point point) {
        return pointGateway.save(mapper.mapToDTO(point));
    }

    private Integer createColumnFromString(final String pointString) {
        return Validator.chars.indexOf(pointString.toUpperCase().charAt(0));

    }

    private Integer createRowFromString(final String pointString) {
        return Integer.parseInt(pointString.substring(1)) - 1;

    }

    private String createStringFromRowAndColumn(final int row, final int column) {
        return String.valueOf(Validator.chars.get(column)) + (row + 1);
    }
}
