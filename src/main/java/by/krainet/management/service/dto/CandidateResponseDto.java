package by.krainet.management.service.dto;

import java.util.List;

public record CandidateResponseDto(
        Long id, String lastname,
        String name, String middleName,
        String description, String photo,
         String cv, List<DirectionResponseDto> directions
) {
}
