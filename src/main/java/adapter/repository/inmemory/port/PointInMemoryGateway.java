package adapter.repository.inmemory.port;

import java.util.Comparator;

import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;

import adapter.repository.inmemory.entity.PointInMemory;
import adapter.repository.inmemory.entity.PointMapper;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;
import io.vavr.collection.Set;
import io.vavr.collection.SortedSet;
import io.vavr.control.Option;

public class PointInMemoryGateway implements PointGateway {
    private static Comparator<PointInMemory> COMPARATOR = Comparator.comparing(PointInMemory::getRow).thenComparing(PointInMemory::getColumn);
    Map<String, PointInMemory> entities;
    private final PointMapper mapper;

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
                        .toSortedSet(COMPARATOR)
                        .map(mapper::mapToDto));
    }

    @Override
    public void removeAll(Set<String> points) {
        entities.removeAll(points);

    }    
}
