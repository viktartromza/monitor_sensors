package by.agsr.monitorsensors.repository;

import by.agsr.monitorsensors.model.entity.Type;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TypeRepository extends JpaRepository<Type, Integer> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "units")
    List<Type> findAll();
}
