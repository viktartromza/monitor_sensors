package by.agsr.monitorsensors.controller;


import by.agsr.monitorsensors.model.dto.CreateSensorRqDto;
import by.agsr.monitorsensors.model.dto.SensorRsDto;
import by.agsr.monitorsensors.model.dto.UpdateSensorRqDto;
import by.agsr.monitorsensors.service.SensorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Sensors", description = "The Sensors API")
@RestController
@RequestMapping("/sensors")
@RequiredArgsConstructor
public class SensorController {
    private final SensorService service;

    @Operation(
            summary = "Добавление датчика",
            description = "Данный метод позволяет пользователю с ролью Administrator добавлять датчик в базу данных",
            security = {@SecurityRequirement(name = "bearer-key")}
    )
    @PostMapping("/create")
    public ResponseEntity<SensorRsDto> createSensor(@Valid @RequestBody CreateSensorRqDto rq) {
        return new ResponseEntity<>(service.save(rq), HttpStatus.OK);
    }

    @Operation(
            summary = "Просмотр списка датчиков",
            description = "Данный метод позволяет пользователю с ролью Administrator или Viewer просматривать список датчиков",
            security = {@SecurityRequirement(name = "bearer-key")}
    )
    @GetMapping("")
    public ResponseEntity<List<SensorRsDto>> getAllSensors() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Operation(
            summary = "Поиск датчиков по данным",
            description = "Данный метод позволяет пользователю с ролью Administrator или Viewer искать датчики по названию и(или) модели." +
                    "Поиск происходит по частичному совпадению текста",
            security = {@SecurityRequirement(name = "bearer-key")}
    )
    @GetMapping("/find")
    public ResponseEntity<List<SensorRsDto>> getSensorsByNameAndModel(@Nullable @RequestParam String name,
                                                                      @Nullable @RequestParam String model,
                                                                      @Nullable @RequestParam boolean isStart) {
        var onlyFirstCharacters = BooleanUtils.isTrue(isStart);
        return new ResponseEntity<>(service.variableSearchByNameAndModel(name, model, onlyFirstCharacters), HttpStatus.OK);
    }

    @Operation(
            summary = "Удаление датчика",
            description = "Данный метод позволяет пользователю с ролью Administrator удалять запись о датчике",
            security = {@SecurityRequirement(name = "bearer-key")}
    )
    @DeleteMapping()
    public ResponseEntity<Void> deleteSensor(@RequestParam UUID sensorId) {
        service.deleteSensor(sensorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(
            summary = "Обновление датчика",
            description = "Данный метод позволяет пользователю с ролью Administrator обновлять запись о датчике",
            security = {@SecurityRequirement(name = "bearer-key")}
    )
    @PutMapping("/update")
    public ResponseEntity<Void> updateSensor(@RequestBody UpdateSensorRqDto rq) {
        service.updateSensor(rq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
