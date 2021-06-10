package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointDto;

class Mapper {

    final PointDto mapToDTO(final Point point) {
        return PointDto.builder()
                .withId(point.getId())
                .withPointString(point.getPointString())
                .withRow(point.getRow())
                .withColumn(point.getColumn())
                .build();
    }

}
