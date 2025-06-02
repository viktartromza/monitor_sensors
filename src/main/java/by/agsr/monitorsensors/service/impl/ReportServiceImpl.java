package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.model.summaryentity.Report;
import by.agsr.monitorsensors.service.ReportService;
import by.agsr.monitorsensors.summaryrepository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {
    private final ReportRepository repository;

    @Override
    public List<Report> getReportsOnPeriod(LocalDate from, LocalDate to) {
        return repository.findReportsByDateIsBetween(from, to);
    }
}
