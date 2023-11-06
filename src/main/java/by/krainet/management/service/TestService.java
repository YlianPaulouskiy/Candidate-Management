package by.krainet.management.service;

import by.krainet.management.service.dto.TestFilter;
import by.krainet.management.service.dto.TestRequestDto;
import by.krainet.management.service.dto.TestResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TestService {
    Page<TestResponseDto> getTests(TestFilter filter, Pageable pageable);

    TestResponseDto save(TestRequestDto requestDto);

    TestResponseDto update(Long id, TestRequestDto updateDto);

}
