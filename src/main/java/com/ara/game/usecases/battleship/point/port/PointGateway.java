package com.ara.game.usecases.battleship.point.port;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import io.vavr.collection.Set;
import io.vavr.collection.SortedSet;
import io.vavr.control.Option;

public interface PointGateway {
    PointDto save(PointDto point);

    Option<PointDto> findById(String id);

    Option<PointDto> findByRowAndColumn(Integer row, Integer column);

    Option<PointDto> findByPointString(String pointString);

    Option<SortedSet<PointDto>> findAllById(Set<String> points);

    Set<String> removeAll(Set<String> points);
}
