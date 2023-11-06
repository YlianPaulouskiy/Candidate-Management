package by.krainet.management.api.controller;

import by.krainet.management.service.CandidateService;
import by.krainet.management.service.dto.CandidateFilter;
import by.krainet.management.service.dto.CandidateRequestDto;
import by.krainet.management.service.dto.CandidateResponseDto;
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

@Tag(name = "Candidate Controller", description = "API for working with Candidate")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/candidates")
@Slf4j
@Validated
public class CandidateController {

    private final CandidateService candidateService;

    @Operation(summary = "Get list of candidates by filter and pageable")
    @ApiResponse(responseCode = "200", description = "GET",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = CandidateResponseDto.class))))
    @GetMapping
    public ResponseEntity<Page<CandidateResponseDto>> get(@RequestBody(required = false) CandidateFilter filter,
                                                          Pageable pageable) {
        log.warn("Get candidates from database by filter {} and pageable {}", filter, pageable);
        Page<CandidateResponseDto> candidatesResponseList = candidateService.getCandidates(filter, pageable);
        return new ResponseEntity<>(candidatesResponseList, HttpStatus.OK);
    }

    @Operation(summary = "Save candidate in database")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CandidateResponseDto.class)))
    @PostMapping
    public ResponseEntity<CandidateResponseDto> save(@RequestBody @Valid CandidateRequestDto requestDto) {
        log.warn("Save candidate {} in database", requestDto);
        CandidateResponseDto responseDto = candidateService.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update candidate in database by id")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = CandidateResponseDto.class)))
    @PutMapping("/{id}")
    public ResponseEntity<CandidateResponseDto> update(@PathVariable @NotNull @Positive Long id,
                                                  @Valid @RequestBody CandidateRequestDto updateDto) {
        log.warn("Update candidate by id {} to new model {} in database", id, updateDto);
        CandidateResponseDto responseDto = candidateService.update(id, updateDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
