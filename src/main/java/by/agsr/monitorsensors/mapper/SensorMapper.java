package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.model.dto.CreateSensorRqDto;
import by.agsr.monitorsensors.model.dto.RangeDto;
import by.agsr.monitorsensors.model.dto.SensorRsDto;
import by.agsr.monitorsensors.model.dto.UpdateSensorRqDto;
import by.agsr.monitorsensors.model.entity.Sensor;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import java.util.Objects;
import java.util.Optional;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SensorMapper {

    @Mapping(target = "range", source = "range", qualifiedByName = "toRange")
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "unit", ignore = true)
    Sensor toEntity(CreateSensorRqDto source);

    @Mapping(target = "typeName", source = "type.name")
    @Mapping(target = "unitName", source = "unit.name")
    @Mapping(target = "range", source = "range", qualifiedByName = "toRangeDto")
    SensorRsDto toRsDto(Sensor source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "range", source = "range", qualifiedByName = "toRange")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Sensor updateEntity(@MappingTarget Sensor target, UpdateSensorRqDto source);

    @Named("toRange")
    default Range<Long> toRange(RangeDto source) {
        if (Objects.isNull(source)) {
            return null;
        }
        var from = Optional.ofNullable(source.getFrom()).orElse(0L);
        return Optional.ofNullable(source.getTo())
                .map(to -> Range.open(from, to))
                .orElseGet(() -> Range.closedInfinite(from));
    }

    @Named("toRangeDto")
    default RangeDto toRangeDto(Range<Long> source) {
        if (Objects.isNull(source)) {
            return null;
        }
        var from = source.lower();
        var to = source.upper();
        return new RangeDto(from, to);
    }
}
