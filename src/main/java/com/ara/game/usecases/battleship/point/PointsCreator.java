package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.common.CreateDto;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Either;

final class PointsCreator {
    private final PointFinder finder;
    private final PointCreator creator;

    PointsCreator(final PointFinder finder, final PointCreator creator) {
        this.finder = finder;
        this.creator = creator;
    }

    final Either<Error, Set<CreateDto>> create(final PointsCreateDto inputData) {
        return fill(inputData);
    }

    final Either<Error, Set<CreateDto>> createRandom(final PointsCreateDto inputData) {

        Either<Error, Set<CreateDto>> chooser = fill(inputData);
        while (chooser.isLeft()) {
            chooser = fill(inputData);
        }
        return chooser;
    }

    private Either<Error, Set<CreateDto>> fill(final PointsCreateDto pointsCreateInputData) {
        Set<CreateDto> points = HashSet.empty();
        for (int i = 0; i < pointsCreateInputData.getSize(); i++) {
            int row = 0;
            int column = 0;
            switch (pointsCreateInputData.getDirection().getShortName()) {
                case "u":
                    row = pointsCreateInputData.getPoint().getRow() - 1;
                    column = pointsCreateInputData.getPoint().getColumn();
                    break;
                case "r":
                    row = pointsCreateInputData.getPoint().getRow();
                    column = pointsCreateInputData.getPoint().getColumn() + i;
                    break;
                case "d":
                    row = pointsCreateInputData.getPoint().getRow() + i;
                    column = pointsCreateInputData.getPoint().getColumn();
                    break;
                case "l":
                    row = pointsCreateInputData.getPoint().getRow();
                    column = pointsCreateInputData.getPoint().getColumn() - i;
                default:
                    return Either.left(PointsCreatorError.CANNOT_CREATE_POINTS);
            }
            Either<Error, CreateDto> created = finder
                    .findByRowAndColumn(row, column)
                    .map(p -> new CreateDto.Builder().id(p.getId()).build())
                    .orElse(create(row, column));
            if (created.isLeft()) {
                return Either.left(created.getLeft());
            }
            points = points.add(created.get());
        }
        return Either.right(points);
    }

    private Either<Error, CreateDto> create(final Integer row, final Integer column) {
        return creator.create(new PointCreateRowColDto.Builder().row(row).column(column).build());
    }

}
