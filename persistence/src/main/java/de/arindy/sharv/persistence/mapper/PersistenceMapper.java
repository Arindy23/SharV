package de.arindy.sharv.persistence.mapper;

import de.arindy.sharv.persistence.SharvJSONPersistenceHandler;
import de.arindy.sharv.persistence.character.Character;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PersistenceMapper {

    PersistenceMapper MAPPER = Mappers.getMapper(PersistenceMapper.class);

    de.arindy.sharv.character.Character map(final Character character, @Context SharvJSONPersistenceHandler sharvPersistenceHandler);

    Character map(final de.arindy.sharv.character.Character character);

    @ObjectFactory
    default <T> T lookup(Loadable entity, @Context SharvJSONPersistenceHandler sharvPersistenceHandler, @TargetType Class<T> target) throws ReflectiveOperationException {
        return sharvPersistenceHandler.readFile(entity.getSource(), entity.getId(), target);
    }

}
