package by.krainet.management.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public record CandidateTestRequestDto(
        @NotNull(message = "Candidate Id must not be null")
        @Positive(message = "Candidate Id must be positive")
        @Schema(defaultValue = "0", description = "Candidate Id for reference to candidate")
        Long candidateId,
        @NotNull(message = "Test Id must not be null")
        @Positive(message = "Test Id must be positive")
        @Schema(defaultValue = "0", description = "Test Id for reference to testInfo")
        Long testId,
        @NotNull(message = "Results must not be null")
        @Size(min = 1, message = "Results must be Results model")
        @Schema(description = "Results of testing")
        List<ResultDto> results
) {
}
