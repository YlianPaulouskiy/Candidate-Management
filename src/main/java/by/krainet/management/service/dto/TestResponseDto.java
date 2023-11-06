package by.krainet.management.service.dto;

import java.util.List;

public record TestResponseDto(
        Long id,
        String name,
        String description,
        List<DirectionResponseDto> applicableDirections
) {
}
