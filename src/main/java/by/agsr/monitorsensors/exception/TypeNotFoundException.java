package by.agsr.monitorsensors.exception;

public class TypeNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Тип сенсора с id=%s не найден в БД";
    public TypeNotFoundException(int id) {
        super(MESSAGE.formatted(id));
    }
}
