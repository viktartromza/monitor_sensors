package by.agsr.monitorsensors.service;

import by.agsr.monitorsensors.model.summaryentity.Report;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<Report> getReportsOnPeriod (LocalDate from, LocalDate to);
}
