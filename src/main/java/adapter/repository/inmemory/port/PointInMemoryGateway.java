package adapter.repository.inmemory.port;

import adapter.repository.inmemory.entity.PointInMemory;
import adapter.repository.inmemory.entity.PointMapper;
import com.ara.game.usecases.battleship.point.dto.PointDto;
import com.ara.game.usecases.battleship.point.port.PointGateway;
import io.vavr.collection.*;
import io.vavr.control.Option;


public class PointInMemoryGateway implements PointGateway {
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
        return entities.values().find(entity -> entity.getRow().equals(row) && entity.getColumn().equals(column)).map(mapper::mapToDto);
    }

    @Override
    public Option<PointDto> findByPointString(String pointString) {
        return entities.values().find(entity -> entity.getPointString().equals(pointString)).map(mapper::mapToDto);
    }

    @Override
    public Option<Set<PointDto>> findAllById(Set<String> points) {
        Set<PointDto> p = HashSet.empty();
        for (String id : points) {
            entities.get(id).map(mapper::mapToDto).peek(p::add);
        }
        return p.isEmpty() ? Option.none() : Option.some(p);

    }
}
