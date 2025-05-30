package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.model.dto.UnitRsDto;
import by.agsr.monitorsensors.model.entity.Unit;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UnitMapper {
    UnitRsDto toUnitDto(Unit source);
}
