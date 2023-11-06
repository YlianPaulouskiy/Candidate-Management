package by.krainet.management.api.controller;

import by.krainet.management.service.TestService;
import by.krainet.management.service.dto.TestFilter;
import by.krainet.management.service.dto.TestRequestDto;
import by.krainet.management.service.dto.TestResponseDto;
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

@Tag(name = "Test Controller", description = "API for working with Test")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tests")
@Slf4j
@Validated
public class TestController {

    private final TestService testService;

    @Operation(summary = "Get list of tests by filter and pageable")
    @ApiResponse(responseCode = "200", description = "GET",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = TestResponseDto.class))))
    @GetMapping
    public ResponseEntity<Page<TestResponseDto>> get(@RequestBody(required = false) TestFilter filter,
                                                     Pageable pageable) {
        log.warn("Get tests from database by filter {} and pageable {}", filter, pageable);
        Page<TestResponseDto> testsResponseList = testService.getTests(filter, pageable);
        return new ResponseEntity<>(testsResponseList, HttpStatus.OK);
    }

    @Operation(summary = "Save test in database")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TestResponseDto.class)))
    @PostMapping
    public ResponseEntity<TestResponseDto> save(@RequestBody @Valid TestRequestDto requestDto) {
        log.warn("Save test {} in database", requestDto);
        TestResponseDto responseDto = testService.save(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Update test in database by id")
    @ApiResponse(responseCode = "201", description = "CREATE",
            content = @Content(mediaType = APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = TestResponseDto.class)))
    @PutMapping("/{id}")
    public ResponseEntity<TestResponseDto> update(@PathVariable @NotNull @Positive Long id,
                                                       @Valid @RequestBody TestRequestDto updateDto) {
        log.warn("Update test by id {} to new model {} in database", id, updateDto);
        TestResponseDto responseDto = testService.update(id, updateDto);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
