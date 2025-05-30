package by.agsr.monitorsensors.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class TypeRsDto {
    private int id;
    private String name;
    private Set<UnitRsDto> units;
}
