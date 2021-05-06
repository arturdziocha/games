package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointDto;

class Mapper {

    final PointDto mapToDTO(final Point point) {
        return new PointDto.Builder()
                .id(point.getId())
                .pointString(point.getPointString())
                .row(point.getRow())
                .column(point.getColumn())
                .build();
    }

}
