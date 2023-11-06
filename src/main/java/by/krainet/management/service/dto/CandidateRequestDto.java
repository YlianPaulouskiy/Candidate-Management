package by.krainet.management.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CandidateRequestDto(
        @NotNull(message = "Candidate lastname must not be null")
        @NotBlank(message = "Candidate lastname must not be empty")
        @Length(max = 40, message = "Candidate lastname is too long, the max number of symbols is 40")
        @Schema(defaultValue = "Lastname", description = "Candidate lastname")
        String lastname,
        @NotNull(message = "Candidate name must not be null")
        @NotBlank(message = "Candidate name must not be empty")
        @Length(max = 40, message = "Candidate name is too long, the max number of symbols is 40")
        @Schema(defaultValue = "Name", description = "Candidate Name")
        String name,
        @NotNull(message = "Candidate mid name must not be null")
        @NotBlank(message = "Candidate mid name must not be empty")
        @Length(max = 40, message = "Candidate mid name is too long, the max number of symbols is 40")
        @Schema(defaultValue = "Middle name", description = "Candidate mid name")
        String middleName,
        @Schema(description = "Photo of candidate")
        MultipartFile photo,
        @Length(max = 255, message = "Description of candidate is too long, the max number of symbols is 255")
        @Schema(description = "Description of candidate")
        String description,
        @Schema(description = "CV of candidate")
        MultipartFile cv,
        @Schema(description = "List ids directions of candidate")
        List<Long> directions
) {
}
