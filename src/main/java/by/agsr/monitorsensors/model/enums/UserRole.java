package by.agsr.monitorsensors.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {

    ROLE_ADMIN("Administrator"),
    ROLE_VIEWER("Viewer");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }
}
