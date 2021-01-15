package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;
import com.ara.game.usecases.common.port.IdGenerator;
import io.vavr.control.Either;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

final class PointCreator {
    private final PointGateway pointGateway;
    private final IdGenerator idGenerator;
    private final PointMapper mapper;
    private final PointValidator validator;
    private final Logger log;

    private final List<Character> chars = Arrays
            .asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
                'U', 'W', 'X', 'Y', 'Z');

    PointCreator(final PointGateway pointGateway, final IdGenerator idGenerator, final PointMapper mapper) {
        this.pointGateway = pointGateway;
        this.idGenerator = idGenerator;
        this.mapper = mapper;
        this.validator = new PointValidator();
        this.log = LoggerFactory.getLogger(PointCreator.class);
    }

    final Either<Error, CreateDto> create(final PointCreateStringDto inputData) {
        Option<Error> validation = validator.validatePointStrint(inputData);
        if (validation.isDefined()) {
            return Either.left(validation.get());
        }
        String pointString = inputData.getPointString().toUpperCase();
        Option<PointDto> find = pointGateway.findByPointString(pointString);
        if (find.isDefined()) {
            return Either.right(mapper.mapToCreatePointOutput(mapper.mapToEntity(find.get())));
        } else {
            Either<Error, CreateDto> output;
            Either<Error, Integer> row = createRow(pointString);
            Either<Error, Integer> column = createColumn(pointString);
            if (row.isRight() && column.isRight()) {
                Point point = new Point.Builder()
                        .id(idGenerator.generate())
                        .row(row.get())
                        .column(column.get())
                        .pointString(pointString)
                        .build();

                output = savePoint(point);
            } else if (row.isLeft()) {
                output = Either.left(row.getLeft());
            } else {
                output = Either.left(column.getLeft());
            }
            return output;
        }
    }

    final Either<Error, CreateDto> create(final PointCreateRowColDto inputData) {
        Option<Error> validationCheck = validator.validateRowCol(inputData);
        if (validationCheck.isDefined()) {
            return Either.left(validationCheck.get());
        }
        Option<PointDto> find = pointGateway.findByRowAndColumn(inputData.getRow(), inputData.getColumn());
        if (find.isDefined()) {
            return Either.right(mapper.mapToCreatePointOutput(mapper.mapToEntity(find.get())));
        } else {
            Either<Error, CreateDto> output;
            Either<Error, String> pointString = createPointString(inputData.getRow(), inputData.getColumn());
            if (pointString.isRight()) {
                Point point = new Point.Builder()
                        .id(idGenerator.generate())
                        .row(inputData.getRow())
                        .column(inputData.getColumn())
                        .pointString(pointString.get())
                        .build();
                output = savePoint(point);
            } else {
                output = Either.left(pointString.getLeft());
            }
            return output;
        }
    }

    private Either<Error, CreateDto> savePoint(final Point point) {
        return Try
                .of(() -> save(point))
                .map(mapper::mapToCreatePointOutput)
                .onFailure(e -> log.error(e.getMessage()))
                .toEither(PointError.PERSISTENCE_FAILED);
    }

    private Point save(final Point point) {
        pointGateway.save(mapper.mapToDTO(point));
        return point;
    }

    private Either<Error, Integer> createColumn(final String pointString) {
        char posY = pointString.charAt(0);
        int y = chars.indexOf(posY);
        return y == -1 ? Either.left(PointError.WRONG_COLUMN_SPECIFIED) : Either.right(y);
    }

    private Either<Error, Integer> createRow(final String pointString) {
        String posX = pointString.substring(1);
        return Try
                .of(() -> Integer.parseInt(posX))
                .filter(i -> i >= 0)
                .map(val -> val - 1)
                .toEither(PointError.CANNOT_PARSE_STRING);
    }

    private Either<Error, String> createPointString(final int row, final int column) {
        Try<Character> tryCharacter = Try.of(() -> chars.get(column));
        if (tryCharacter.isFailure()) {
            return Either.left(PointError.WRONG_COLUMN_SPECIFIED);
        }
        String builder = String.valueOf(tryCharacter.get()) + (row + 1);
        return Either.right(builder);
    }
}
