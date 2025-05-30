package by.agsr.monitorsensors.model.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Информация о датчике")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SensorRsDto {

    @Schema(description = "id датчика")
    private UUID id;

    @Schema(description = "Название датчика")
    private String name;

    @Schema(description = "Модель датчика")
    private String model;

    @Schema(description = "Радиус действия датчика")
    private RangeDto range;

    @Schema(description = "Название типа датчика")
    private String typeName;

    @Schema(description = "Название единицы измерения")
    private String unitName;

    @Schema(description = "Местоположение датчика")
    private String location;

    @Schema(description = "Описание датчика")
    private String description;

    public SensorRsDto(UUID id) {
        this.id = id;
    }
}
