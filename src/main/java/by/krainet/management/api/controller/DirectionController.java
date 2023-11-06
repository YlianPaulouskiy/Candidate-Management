package by.krainet.management.api.controller;

import by.krainet.management.service.DirectionService;
import by.krainet.management.service.dto.DirectionFilter;
import by.krainet.management.service.dto.DirectionRequestDto;
import by.krainet.management.service.dto.DirectionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Tag(name = "Direction Controller", description = "API for working with Direction")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/directions")
@Slf4j
@Validated
public class DirectionController {

    private final DirectionService directionService;

    @Operation(summary = "Get list of directions by filter and pageable")
    @ApiResponse(responseCode = "200", description = "GET",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = DirectionResponseDto.class))))
    @GetMapping
    public ResponseEntity<Page<DirectionResponseDto>> get(@RequestBody(required = false) DirectionFilter filter,
                                                          Pageable pageable) {
        log.warn("Get directions from database by filter {} and pageable {}", filter, pageable);
        Page<DirectionResponseDto> directionResponseList = directionService.getDirections(filter, pageable);
        return new ResponseEntity<>(directionResponseList, HttpStatus.OK);
    }

    @Operation(summary = "Save direction in database")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DirectionResponseDto.class)))
    @PostMapping
    public ResponseEntity<DirectionResponseDto> save(@RequestBody @Valid DirectionRequestDto requestDto) {
        log.warn("Save direction {} in database", requestDto);
        DirectionResponseDto responseDto = directionService.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update direction in database by id")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = DirectionResponseDto.class)))
    @PutMapping("/{id}")
    public ResponseEntity<DirectionResponseDto> update(@PathVariable @NotNull @Positive Long id,
                                                       @Valid @RequestBody DirectionRequestDto updateDto) {
        log.warn("Update direction by id {} to new model {} in database", id, updateDto);
        DirectionResponseDto responseDto = directionService.update(id, updateDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
