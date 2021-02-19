package com.ara.game.usecases.battleship.point;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.common.CreateDto;

class Mapper {

    final CreateDto mapToCreatePointOutput(final Point point) {
        return new CreateDto.Builder().id(point.getId()).build();
    }

    final PointDto mapToDTO(final Point point) {
        return new PointDto.Builder()
                .id(point.getId())
                .pointString(point.getPointString())
                .row(point.getRow())
                .column(point.getColumn())
                .build();
    }

    final Point mapToEntity(final PointDto pointDto) {
        return new Point.Builder()
                .id(pointDto.getId())
                .pointString(pointDto.getPointString())
                .row(pointDto.getRow())
                .column(pointDto.getColumn())
                .build();

    }

}