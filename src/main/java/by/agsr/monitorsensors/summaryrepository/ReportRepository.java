package by.agsr.monitorsensors.summaryrepository;

import by.agsr.monitorsensors.model.summaryentity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ReportRepository extends JpaRepository<Report, UUID> {

    List<Report> findReportsByDateIsBetween(LocalDate from, LocalDate to);
}
