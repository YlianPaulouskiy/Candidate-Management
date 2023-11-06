package by.krainet.management.service.dto;

import java.time.LocalDate;

public record ResultDto(
        LocalDate date,
        Double score
) {
}
