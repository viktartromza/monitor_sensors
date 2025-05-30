package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.exception.SensorNotFoundException;
import by.agsr.monitorsensors.exception.TypeNotFoundException;
import by.agsr.monitorsensors.exception.UnitNoMatchingException;
import by.agsr.monitorsensors.mapper.SensorMapper;
import by.agsr.monitorsensors.model.dto.CreateSensorRqDto;
import by.agsr.monitorsensors.model.dto.SensorRsDto;
import by.agsr.monitorsensors.model.dto.UpdateSensorRqDto;
import by.agsr.monitorsensors.model.entity.Unit;
import by.agsr.monitorsensors.repository.SensorRepository;
import by.agsr.monitorsensors.repository.TypeRepository;
import by.agsr.monitorsensors.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {
    private final SensorRepository repository;
    private final TypeRepository typeRepository;
    private final SensorMapper mapper;

    @Override
    public SensorRsDto save(CreateSensorRqDto rq) {
        var sensorType = typeRepository.findById(rq.getType())
                .orElseThrow(() -> new TypeNotFoundException(rq.getType()));
        var unit = sensorType.getUnits().stream()
                .filter(u -> u.getId() == rq.getUnit())
                .findFirst()
                .orElseThrow(
                        () -> new UnitNoMatchingException(sensorType.getUnits().stream().map(Unit::getName).collect(Collectors.joining(", ")))
                );

        var sensor = mapper.toEntity(rq);
        sensor.setType(sensorType);
        sensor.setUnit(unit);
        var savedSensor = repository.save(sensor);
        return new SensorRsDto(savedSensor.getId());
    }

    @Override
    public List<SensorRsDto> getAll() {
        return repository.findAll().stream().map(mapper::toRsDto).toList();
    }

    @Override
    public void updateSensor(UpdateSensorRqDto rq) {
        var sensorId = rq.getId();
        var currentSensor = repository.findById(sensorId)
                .orElseThrow(() -> new SensorNotFoundException(sensorId));
        var updatedEntity = mapper.updateEntity(currentSensor, rq);
        repository.save(updatedEntity);
    }

    @Override
    public void deleteSensor(UUID sensorId) {
        repository.deleteById(sensorId);
    }


}
