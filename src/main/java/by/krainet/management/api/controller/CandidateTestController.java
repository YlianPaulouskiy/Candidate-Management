package by.krainet.management.api.controller;

import by.krainet.management.service.CandidateTestService;
import by.krainet.management.service.dto.*;
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

@Tag(name = "Candidate-Test Controller", description = "API for working with CandidateTest")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidate-test")
@Slf4j
@Validated
public class CandidateTestController {

    private final CandidateTestService candidateTestService;

    @Operation(summary = "Get list of candidate-test by filter and pageable")
    @ApiResponse(responseCode = "200", description = "GET",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = CandidateTestResponseDto.class))))
    @GetMapping
    public ResponseEntity<Page<CandidateTestResponseDto>> get(@RequestBody(required = false) CandidateTestFilter filter,
                                                              Pageable pageable) {
        log.warn("Get all candidate-tests from database by filter {} and pageable {}", filter, pageable);
        Page<CandidateTestResponseDto> candidateTestResponseList = candidateTestService.getCandidateTests(filter, pageable);
        return new ResponseEntity<>(candidateTestResponseList, HttpStatus.OK);
    }

    @Operation(summary = "Save candidate-test in database")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CandidateTestResponseDto.class)))
    @PostMapping
    public ResponseEntity<CandidateTestResponseDto> save(@RequestBody @Valid CandidateTestRequestDto requestDto) {
        log.warn("Save candidate-test {} in database", requestDto);
        CandidateTestResponseDto responseDto = candidateTestService.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update candidate in database by id")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CandidateTestResponseDto.class)))
    @PutMapping("/{id}")
    public ResponseEntity<CandidateTestResponseDto> update(@PathVariable @NotNull @Positive Long id,
                                                       @Valid @RequestBody CandidateTestRequestDto updateDto) {
        log.warn("Update candidate-test by id {} to new model {} in database", id, updateDto);
        CandidateTestResponseDto responseDto = candidateTestService.update(id, updateDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
