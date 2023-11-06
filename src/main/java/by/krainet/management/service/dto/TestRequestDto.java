package by.krainet.management.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record TestRequestDto (
        @NotNull(message = "Test name must not be null")
        @NotBlank(message = "Test name must not be empty")
        @Length(max = 100, message = "Test name is too long, the max number of symbols is 100")
        @Schema(defaultValue = "Test Name", description = "Name test")
        String name,
        @Length(max = 255, message = "Description of test is too long, the max number of symbols is 255")
        @Schema(description = "Description of test")
        String description,
        @Schema(description = "List ids applicable direction of test")
        List<Long> applicableDirections
) {
}
