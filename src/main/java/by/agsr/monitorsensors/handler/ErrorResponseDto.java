package by.agsr.monitorsensors.handler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseDto {

    private String errorMessage;

    static ErrorResponseDto createErrorDto(Exception e){
        return new ErrorResponseDto(e.getMessage());
    }

    static ErrorResponseDto createErrorDto(String errorMessage){
        return new ErrorResponseDto(errorMessage);
    }
}
