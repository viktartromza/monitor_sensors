package by.agsr.monitorsensors.model.dto;

import by.agsr.monitorsensors.util.validation.annotation.ValidRange;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Радиус работы")
@JsonInclude(JsonInclude.Include.NON_NULL)
@ValidRange
public class RangeDto {

    @Schema(description = "Нижняя граница радиуса")
    @Positive(message = "Нижняя граница радиуса действия должна быть больше 0")
    private Long from;

    @Schema(description = "Верхняя граница радиуса")
    @Positive(message = "Верхняя граница радиуса действия должна быть больше 0")
    private Long to;
}
