package by.krainet.management.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record DirectionRequestDto(
        @NotNull(message = "Direction name must not be null")
        @NotBlank(message = "Direction name must not be empty")
        @Length(max = 100, message = "Direction name is too long, the max number of symbols is 100")
        @Schema(defaultValue = "Direction Name", description = "Name direction of speciality")
        String name,
        @Length(max = 255, message = "Description of direction is too long, the max number of symbols is 255")
        @Schema(description = "Description of direction")
        String description
) {
}
