package by.agsr.monitorsensors.exception;

import java.util.UUID;

public class SensorNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Cенсор с id=%s не найден в БД";
    public SensorNotFoundException(UUID id) {
        super(MESSAGE.formatted(id));
    }
}
