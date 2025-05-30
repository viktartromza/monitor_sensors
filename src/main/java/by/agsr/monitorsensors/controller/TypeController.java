package by.agsr.monitorsensors.controller;


import by.agsr.monitorsensors.model.dto.TypeRsDto;
import by.agsr.monitorsensors.service.TypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Types", description = "The Sensor Type API")
@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
@Tag(name = "Types")
public class TypeController {
    private final TypeService service;

    @Operation(
            summary = "Типы датчиков",
            description = "Данный метод позволяет пользователю с ролью Administrator вывести все доступные типы датчиков с единицами измерения",
            security = { @SecurityRequirement(name = "bearer-key") }
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные предоставлены"),
            @ApiResponse(responseCode = "400", description = "Некорректные параметры запроса"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")
    })
    @GetMapping("")
    public ResponseEntity<List<TypeRsDto>> getAllTypes() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }
}
