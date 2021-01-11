package adapter.repository.inmemory.entity;

import com.ara.game.usecases.battleship.point.dto.PointDto;

public class PointMapper {
    public PointInMemory mapToEntity(final PointDto point) {
        return new PointInMemory.Builder().id(point.getId()).row(point.getRow()).column(point.getColumn()).pointString(point.getPointString()).build();
    }

    public PointDto mapToDto(final PointInMemory point) {
        return new PointDto.Builder().id(point.getId()).row(point.getRow()).column(point.getColumn()).pointString(point.getPointString()).build();
    }
}
