package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.model.dto.TypeRsDto;
import by.agsr.monitorsensors.model.dto.UnitRsDto;
import by.agsr.monitorsensors.model.entity.Type;
import by.agsr.monitorsensors.model.entity.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UnitMapper.class})
public interface TypeMapper {
    TypeRsDto toDto(Type source);
}
