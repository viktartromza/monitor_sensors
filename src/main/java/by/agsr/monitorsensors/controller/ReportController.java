package by.agsr.monitorsensors.controller;


import by.agsr.monitorsensors.model.summaryentity.Report;
import by.agsr.monitorsensors.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Reports", description = "The Reports API")
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService service;

    @Operation(
            summary = "Отчеты за период",
            description = "Данный метод позволяет пользователю с ролью Administrator вывести отчеты за период времени",
            security = {@SecurityRequirement(name = "bearer-key")}
    )
    @GetMapping("")
    public ResponseEntity<List<Report>> getAllTypes(@NotNull @RequestParam("from") LocalDate from,
                                                    @NotNull @RequestParam("to") LocalDate to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException(); //TODO message
        }
        return new ResponseEntity<>(service.getReportsOnPeriod(from, to), HttpStatus.OK);
    }
}
