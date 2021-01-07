package com.ara.game.usecases.battleship.point;

import org.apache.commons.lang3.StringUtils;

import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.common.Error;

import io.vavr.control.Option;

class Validator {

    Option<Error> validatePointStrint(PointCreateStringDto inputData) {
        if (inputData == null) {
            return Option.some(PointError.DATA_CANNOT_BE_NULL);
        }
        if (StringUtils.isEmpty(inputData.getPointString())) {
            return Option.some(PointError.POINT_STRING_CANNOT_BE_EMPTY);
        }
        return Option.none();
    }

}
