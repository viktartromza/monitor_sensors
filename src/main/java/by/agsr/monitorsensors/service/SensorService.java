package by.agsr.monitorsensors.service;

import by.agsr.monitorsensors.model.dto.CreateSensorRqDto;
import by.agsr.monitorsensors.model.dto.SensorRsDto;
import by.agsr.monitorsensors.model.dto.UpdateSensorRqDto;

import java.util.List;
import java.util.UUID;

public interface SensorService {

    SensorRsDto save(CreateSensorRqDto rq);

    List<SensorRsDto> getAll();
    List<SensorRsDto> variableSearchByNameAndModel(String name, String model, boolean onlyFirstCharacters);

    void deleteSensor(UUID sensorId);

    void updateSensor(UpdateSensorRqDto rq);
}
