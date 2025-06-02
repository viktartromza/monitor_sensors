package by.agsr.monitorsensors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MonitorSensorsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorSensorsApplication.class, args);
	}

}
