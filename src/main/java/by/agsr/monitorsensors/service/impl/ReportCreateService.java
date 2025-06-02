package by.agsr.monitorsensors.service.impl;

import by.agsr.monitorsensors.mapper.ReportMapper;
import by.agsr.monitorsensors.model.dto.SensorTypeCount;
import by.agsr.monitorsensors.repository.SensorRepository;
import by.agsr.monitorsensors.summaryrepository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
@EnableAsync
public class ReportCreateService {
    private static final String OVERALL = "overall";
    private final SensorRepository sensorRepository;
    private final ReportRepository reportRepository;
    private final ReportMapper mapper;

    @Scheduled(cron = "0 0 2 * * *")
    void makeReport() {
        log.info("start make report");
        var report = sensorRepository.getReport();
        var reportMap = new HashMap<String, Long>();
        for (SensorTypeCount count : report
        ) {
            reportMap.put(count.type().toLowerCase(), count.count());
            reportMap.put(OVERALL, reportMap.getOrDefault(OVERALL, 0L) + count.count());
        }
        reportRepository.save(mapper.toEntity(reportMap));
        log.info("report saved successfully");
    }
}
