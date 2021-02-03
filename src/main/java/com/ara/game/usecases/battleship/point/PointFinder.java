package com.ara.game.usecases.battleship.point;

import org.apache.commons.lang3.StringUtils;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import com.ara.game.usecases.common.Error;

import io.vavr.collection.Set;
import io.vavr.control.Either;

class PointFinder {
    private final PointGateway pointGateway;

    public PointFinder(final PointGateway pointGateway) {
        this.pointGateway = pointGateway;
    }

    final Either<Error, PointDto> findById(final String id) {
        if (StringUtils.isEmpty(id)) {
            return Either.left(PointError.POINT_ID_CANNOT_BE_EMPTY);
        } else {
            return pointGateway.findById(id).toEither(PointError.CANNOT_FIND_POINT);
        }
    }

    final Either<Error, PointDto> findByRowAndColumn(final Integer row, final Integer column) {
        if (row == null || column == null) {
            return Either.left(PointError.DATA_CANNOT_BE_NULL);
        }
        return pointGateway.findByRowAndColumn(row, column).toEither(PointError.CANNOT_FIND_POINT);
    }

    final Either<Error, PointDto> findByPointString(final String pointString) {
        if (StringUtils.isEmpty(pointString)) {
            return Either.left(PointError.DATA_CANNOT_BE_NULL);
        }
        return pointGateway.findByPointString(pointString.toUpperCase()).toEither(PointError.CANNOT_FIND_POINT);
    }

    public Either<Error, Set<PointDto>> findAllById(Set<String> pointsIds) {
        if (pointsIds.isEmpty()) {
            return Either.left(PointError.DATA_CANNOT_BE_NULL);
        }
        return pointGateway.findAllById(pointsIds).toEither(PointError.CANNOT_FIND_POINT);
    }
}
