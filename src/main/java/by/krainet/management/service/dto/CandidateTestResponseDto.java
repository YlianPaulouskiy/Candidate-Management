package by.krainet.management.service.dto;

import java.util.List;

public record CandidateTestResponseDto(
        Long id,
        CandidateResponseDto candidate,
        TestResponseDto testInfo,
        List<ResultDto> results
) {
}
