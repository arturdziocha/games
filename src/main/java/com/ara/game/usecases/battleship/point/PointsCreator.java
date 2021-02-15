package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.direction.dto.DirectionDto;
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
            /*int row = 0;
            int column = 0;
            switch (pointsCreateInputData.getDirection().getShortName()) {
                case "u":
                    row = pointsCreateInputData.getPoint().getRow() - i;
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
            }*/
            Either<Error, DirectionStrategy> direction = getDirection(pointsCreateInputData.getDirection());
            if (direction.isRight()) {
                RowColumn rowColumn = calculate(direction.get(),
                        new RowColumn(pointsCreateInputData.getPoint().getRow(),
                                pointsCreateInputData.getPoint().getColumn()), i);
                Either<Error, CreateDto> created = finder
                        .findByRowAndColumn(rowColumn.getRow(), rowColumn.getColumn())
                        .map(p -> new CreateDto.Builder().id(p.getId()).build())
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

    private Either<Error, CreateDto> create(final Integer row, final Integer column) {
        return creator.create(new PointCreateRowColDto.Builder().row(row).column(column).build());
    }

    private class RowColumn {
        private Integer row, column;

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

    private RowColumn calculate(DirectionStrategy directionStrategy, RowColumn rowColumn, Integer move) {
        return directionStrategy.calculate(rowColumn, move);

    }

    private Either<Error, DirectionStrategy> getDirection(DirectionDto direction) {        
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

    private class UpStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow() - move, rowColumn.getColumn());
        }
    }

    private class RightStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow(), rowColumn.getColumn() + move);
        }
    }

    private class DownStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow() + move, rowColumn.getColumn());
        }
    }

    private class LeftStrategy implements DirectionStrategy {
        @Override
        public RowColumn calculate(RowColumn rowColumn, Integer move) {
            return new RowColumn(rowColumn.getRow(), rowColumn.getColumn() - move);
        }

    }

}
