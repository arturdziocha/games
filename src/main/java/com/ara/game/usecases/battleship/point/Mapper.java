package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointDto;

class Mapper {

    final PointDto mapToDTO(final Point point) {
        return PointDto.builder()
                .id(point.getId())
                .pointString(point.getPointString())
                .row(point.getRow())
                .column(point.getColumn())
                .build();
    }

}
