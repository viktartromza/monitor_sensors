package by.agsr.monitorsensors.repository;

import by.agsr.monitorsensors.model.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SensorRepository extends JpaRepository<Sensor, UUID> {
}
