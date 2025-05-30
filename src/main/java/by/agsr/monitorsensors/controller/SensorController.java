package by.agsr.monitorsensors.controller;


import by.agsr.monitorsensors.model.dto.CreateSensorRqDto;
import by.agsr.monitorsensors.model.dto.SensorRsDto;
import by.agsr.monitorsensors.model.dto.UpdateSensorRqDto;
import by.agsr.monitorsensors.service.SensorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/create")
   // @ApiOperation(value = "Add sensor", tags = "Sensors", authorizations = @Authorization(value = "jwtToken"))
    public ResponseEntity<SensorRsDto> createSensor(@Valid @RequestBody CreateSensorRqDto rq) {
        return new ResponseEntity<>(service.save(rq), HttpStatus.OK);
    }

    @GetMapping("")
   // @ApiOperation(value = "Get all sensor types", tags = "Types", authorizations = @Authorization(value = "jwtToken"))
    public ResponseEntity<List<SensorRsDto>> getAllSensors() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    //@ApiOperation(value = "Deleting sensor", tags = "Sensors", authorizations = @Authorization(value = "jwtToken"))
    @DeleteMapping()
    //@Secured("ROLE_GAME_DEV")
    public ResponseEntity<Void> deleteSensor(@RequestParam UUID sensorId) {
        service.deleteSensor(sensorId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();//TODO only for gameDev and Admins
    }

    @PutMapping("/update")
   // @ApiOperation(value = "Updating sensor", tags = "Sensors", authorizations = @Authorization(value = "jwtToken"))
    //@Secured("ROLE_USER")
    public ResponseEntity<Void> updateSensor(@RequestBody UpdateSensorRqDto rq) {
        service.updateSensor(rq);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
