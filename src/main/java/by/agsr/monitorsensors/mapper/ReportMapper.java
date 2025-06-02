package by.agsr.monitorsensors.mapper;

import by.agsr.monitorsensors.model.summaryentity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;
import java.util.Map;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = LocalDate.class)
public interface ReportMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", expression = "java(LocalDate.now())")
    Report toEntity(Map<String, Long> source);
}
