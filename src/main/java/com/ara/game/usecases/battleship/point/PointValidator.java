package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import org.apache.commons.lang3.StringUtils;

import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Option;

class PointValidator {

    final Option<Error> validatePointStrint(final PointCreateStringDto inputData) {
        if (inputData == null) {
            return Option.some(PointError.DATA_CANNOT_BE_NULL);
        }
        if (StringUtils.isEmpty(inputData.getPointString())) {
            return Option.some(PointError.POINT_STRING_CANNOT_BE_EMPTY);
        }
        return Option.none();
    }

    final Option<Error> validateRowCol(final PointCreateRowColDto inputData) {
        if (inputData == null) {
            return Option.some(PointError.DATA_CANNOT_BE_NULL);
        }
        if (inputData.getRow() == null) {
            return Option.some(PointError.ROW_CANNOT_BE_NULL);
        }
        if (Integer.signum(inputData.getRow()) < 0) {
            return Option.some(PointError.ROW_CANNOT_BE_NEGATIVE);
        }
        if (inputData.getColumn() == null) {
            return Option.some(PointError.COLUMN_CANNOT_BE_NULL);
        }
        if (Integer.signum(inputData.getColumn()) < 0) {
            return Option.some(PointError.COLUMN_CANNOT_BE_NEGATIVE);
        }
        return Option.none();
    }

}
