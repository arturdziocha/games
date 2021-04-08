package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointCreateRowColDto;
import com.ara.game.usecases.battleship.point.dto.PointCreateStringDto;
import com.ara.game.usecases.common.Error;
import io.vavr.collection.List;
import io.vavr.control.Option;
import io.vavr.control.Try;
import org.apache.commons.lang3.StringUtils;

class Validator {
    static final List<Character> chars = List
            .of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                    'W', 'X', 'Y', 'Z');

    final Option<Error> validatePointString(final PointCreateStringDto inputData) {

        if (inputData == null) {
            return Option.some(PointError.DATA_CANNOT_BE_NULL);
        }
        if (StringUtils.isBlank(inputData.getPointString())) {
            return Option.some(PointError.POINT_STRING_CANNOT_BE_EMPTY);
        }
        if (chars.indexOf(inputData.getPointString().toUpperCase().charAt(0)) == -1) {
            return Option.some(PointError.CANNOT_PARSE_COLUMN);
        }
        return Try.of(() -> Integer.parseInt(inputData.getPointString().substring(1))).isSuccess() ? Option.none()
                : Option.some(PointError.CANNOT_PARSE_ROW);
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
        if (inputData.getColumn() >= chars.size()) {
            return Option.some(PointError.WRONG_COLUMN_SPECIFIED);
        }
        return Option.none();
    }

}
