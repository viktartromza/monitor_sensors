package by.agsr.monitorsensors.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Создание датчика: запрос")
public class CreateSensorRqDto {

    @NotBlank(message = "Поле name не может быть пустым")
    @Size(
            min = 3,
            max = 30,
            message = "Поле name должно содержать от 3 до 30 символов")
    @Schema(description = "Название датчика")
    private String name;

    @NotBlank(message = "Поле model не может быть пустым")
    @Size(max = 15, message = "Поле model должно содержать не более 15 символов")
    @Schema(description = "Модель датчика")
    private String model;

    @Schema(description = "Радиус действия датчика")
    @Valid
    private RangeDto range;

    @NotNull(message = "Тип датчика должен быть задан")
    @Schema(description = "id типа датчика")
    private Integer type;

    @NotNull(message = "Единица измерения должна быть задана")
    @Schema(description = "id единицы измерения")
    private Integer unit;

    @Size(max = 40, message = "Поле location должно содержать не более 40 символов")
    @Schema(description = "Местоположение датчика")
    private String location;

    @Size(max = 200, message = "Поле description должно содержать не более 200 символов")
    @Schema(description = "Описание датчика")
    private String description;
}
