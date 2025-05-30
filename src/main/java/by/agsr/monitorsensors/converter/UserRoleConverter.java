package by.agsr.monitorsensors.converter;

import by.agsr.monitorsensors.model.enums.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class UserRoleConverter implements AttributeConverter<UserRole, String> {
    @Override
    public String convertToDatabaseColumn(UserRole userRole) {
        return userRole != null ? userRole.getValue() : null;
    }

    @Override
    public UserRole convertToEntityAttribute(String name) {
       return Stream.of(UserRole.values())
                .filter(e -> e.getValue().equals(name))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("Неизвестная роль %s", name))
                );
    }
}
