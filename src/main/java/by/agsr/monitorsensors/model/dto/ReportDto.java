package by.agsr.monitorsensors.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Отчет")
public class ReportDto {

    private LocalDate date;

    private Long pressure;

    private Long voltage;

    private Long temperature;

    private Long humidity;

    private Long overall;
}
