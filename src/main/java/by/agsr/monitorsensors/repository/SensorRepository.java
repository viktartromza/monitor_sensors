package by.agsr.monitorsensors.repository;

import by.agsr.monitorsensors.model.entity.Sensor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, UUID> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "SensorFullGraph")
    List<Sensor> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "SensorFullGraph")
    List<Sensor> findSensorByNameContainingIgnoreCase(String str);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "SensorFullGraph")
    @Query("""
            SELECT sensor FROM Sensor AS sensor
            WHERE UPPER(sensor.name) LIKE CONCAT (UPPER(:str),'%')
            """)
    List<Sensor> findSensorsByNameStartingWithIgnoreCase(@Param("str") String str);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "SensorFullGraph")
    List<Sensor> findSensorByModelContainingIgnoreCase(String str);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "SensorFullGraph")
    @Query("""
            SELECT sensor FROM Sensor AS sensor
            WHERE UPPER(sensor.model) LIKE CONCAT (UPPER(:str),'%')
            """)
    List<Sensor> findSensorsByModelStartingWithIgnoreCase(@Param("str") String str);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "SensorFullGraph")
    @Query("""
            SELECT sensor FROM Sensor AS sensor
            WHERE UPPER(sensor.name) LIKE CONCAT (UPPER(:name),'%')
            AND UPPER(sensor.model) LIKE CONCAT (UPPER(:model),'%')
            """)
    List<Sensor> findSensorsByNameStartingWithIgnoreCaseAndModelStartingWithIgnoreCase(@Param("name") String name,
                                                                                       @Param("model") String model);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "SensorFullGraph")
    List<Sensor> findSensorsByNameContainingIgnoreCaseAndModelContainingIgnoreCase(String name, String model);
}
