package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.PointInMemory;
import adapter.repository.inmemory.entity.PointMapper;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.collection.SortedSet;
import io.vavr.control.Option;

public class PointInMemoryGateway implements PointGateway {
    private final PointMapper mapper;
    Map<String, PointInMemory> entities;

    public PointInMemoryGateway() {
        entities = HashMap.empty();
        mapper = new PointMapper();
    }

    @Override
    public PointDto save(PointDto point) {
        entities = entities.put(point.getId(), mapper.mapToEntity(point));
        return point;
    }

    @Override
    public Option<PointDto> findById(String id) {
        return entities.get(id).map(mapper::mapToDto);
    }

    @Override
    public Option<PointDto> findByRowAndColumn(Integer row, Integer column) {
        return entities
                .values()
                .find(entity -> entity.getRow().equals(row) && entity.getColumn().equals(column))
                .map(mapper::mapToDto);
    }

    @Override
    public Option<PointDto> findByPointString(String pointString) {
        return entities.values().find(entity -> entity.getPointString().equals(pointString)).map(mapper::mapToDto);
    }

    @Override
    public Option<SortedSet<PointDto>> findAllById(Set<String> points) {
        return Option
                .of(points
                        .flatMap(s -> entities.get(s))
                        .toSortedSet()
                        .map(mapper::mapToDto));
    }

    @Override
    public Set<String> removeAll(Set<String> points) {
        entities = entities.removeAll(points);
        return points;
    }
}
