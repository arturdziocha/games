package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.enums.Direction;
import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.dto.PointsCreateDto;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import io.vavr.control.Either;

final class PointsCreator {
    private final Finder finder;
    private final Creator creator;

    PointsCreator(final Finder finder, final Creator creator) {
        this.finder = finder;
        this.creator = creator;
    }

    final Either<Error, Set<PointDto>> create(final PointsCreateDto inputData) {
        return fill(inputData);
    }

    final Either<Error, Set<PointDto>> createRandom(final PointsCreateDto inputData) {
        // TODO It's not generating Random points. Have to change
        Either<Error, Set<PointDto>> chooser = fill(inputData);
        while (chooser.isLeft()) {
            chooser = fill(inputData);
        }
        return chooser;
    }

    private Either<Error, Set<PointDto>> fill(final PointsCreateDto pointsCreateInputData) {
        Set<PointDto> points = HashSet.empty();
        for (int i = 0; i < pointsCreateInputData.getSize(); i++) {
            Either<Error, DirectionStrategy> direction = getDirection(pointsCreateInputData.getDirection());
            if (direction.isRight()) {
                RowColumn rowColumn = calculate(direction.get(), new RowColumn(
                                pointsCreateInputData.getPoint().getRow(), pointsCreateInputData.getPoint().getColumn()),
                        i);
                Either<Error, PointDto> created = finder
                        .findByRowAndColumn(rowColumn.getRow(), rowColumn.getColumn())
                        .orElse(create(rowColumn.getRow(), rowColumn.getColumn()));
                if (created.isLeft()) {
                    return Either.left(created.getLeft());
                }
                points = points.add(created.get());
            } else {
                return Either.left(direction.getLeft());
            }
        }
        return Either.right(points);
    }

    private Either<Error, PointDto> create(final Integer row, final Integer column) {
        return creator.create(PointCreateRowColDto.builder().withRow(row).withColumn(column).build());
    }

    private RowColumn calculate(DirectionStrategy directionStrategy, RowColumn rowColumn, Integer move) {
        return directionStrategy.calculate(rowColumn, move);

    }

    private Either<Error, DirectionStrategy> getDirection(Direction direction) {
        switch (direction.getShortName()) {
            case "u":
                return Either.right(new UpStrategy());
            case "r":
                return Either.right(new RightStrategy());
            case "d":
                return Either.right(new DownStrategy());
            case "l":
                return Either.right(new LeftStrategy());
            default:
                return Either.left(PointsCreatorError.CANNOT_CREATE_POINTS);
        }
    }

    private interface DirectionStrategy {
        RowColumn calculate(RowColumn rowColumn, Integer move);
    }

    private static class RowColumn {
        private final Integer row;
        private final Integer column;

        RowColumn(Integer row, Integer column) {
            this.row = row;
            this.column = column;
        }

        public Integer getRow() {
            return row;
        }

        public Integer getColumn() {
            return column;
        }
    }

    private static class UpStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow() - move, rowColumn.getColumn());
        }
    }

    private static class RightStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow(), rowColumn.getColumn() + move);
        }
    }

    private static class DownStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow() + move, rowColumn.getColumn());
        }
    }

    private static class LeftStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow(), rowColumn.getColumn() - move);
        }

    }

}
