package by.agsr.monitorsensors.exception;

public class UnitNoMatchingException extends RuntimeException {
    public UnitNoMatchingException(String unitName) {
        super(String.format("Для данного типа датчика единица измерения %s не применима",unitName));
    }
}
