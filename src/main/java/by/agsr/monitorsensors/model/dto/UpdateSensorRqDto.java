package by.agsr.monitorsensors.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Обновление датчика: запрос")
public class UpdateSensorRqDto {

    @NotNull(message = "id датчика должно быть указано")
    @Schema(description = "id датчика")
    private UUID id;

    @Size(
            min = 3,
            max = 30,
            message = "Поле name должно содержать от 3 до 30 символов")
    @Schema(description = "Название датчика")
    private String name;

    @Size(max = 15, message = "Поле model должно содержать не более 15 символов")
    @Schema(description = "Модель датчика")
    private String model;

    @Schema(description = "Радиус действия датчика")
    @Valid
    private RangeDto range;

    @Size(max = 40, message = "Поле location должно содержать не более 40 символов")
    @Schema(description = "Местоположение датчика")
    private String location;

    @Size(max = 200, message = "Поле description должно содержать не более 200 символов")
    @Schema(description = "Описание датчика")
    private String description;
}
