package by.krainet.management.service.impl;

import by.krainet.management.persistence.model.QDirection;
import by.krainet.management.persistence.model.QTest;
import by.krainet.management.persistence.model.Test;
import by.krainet.management.persistence.repository.QPredicates;
import by.krainet.management.persistence.repository.TestRepository;
import by.krainet.management.service.TestService;
import by.krainet.management.service.dto.TestFilter;
import by.krainet.management.service.dto.TestRequestDto;
import by.krainet.management.service.dto.TestResponseDto;
import by.krainet.management.service.exception.TestExistException;
import by.krainet.management.service.exception.TestNotFoundException;
import by.krainet.management.service.mapper.TestMapper;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final TestMapper testMapper;

    @Override
    public Page<TestResponseDto> getTests(TestFilter filter, Pageable pageable) {
        if (filter != null) {
            Predicate predicate = QPredicates.builder()
                    .add(filter.name(), QTest.test.name::containsIgnoreCase)
                    .add(filter.description(), QTest.test.description::containsIgnoreCase)
                    .build();
            return testRepository.findAll(predicate, pageable)
                    .map(testMapper::toDto);
        }
        return testRepository.findAll(pageable)
                .map(testMapper::toDto);
    }

    @Override
    public TestResponseDto save(TestRequestDto requestDto) {
        if (testRepository.existsByName(requestDto.name())) {
            throw new TestExistException("Test with name " + requestDto.name() + " already exists.");
        }
        return testMapper.toDto(
                testRepository.save(
                        testMapper.toEntity(requestDto)));
    }

    @Override
    public TestResponseDto update(Long id, TestRequestDto updateDto) {
        if (testRepository.findById(id).isEmpty()) {
            throw new TestNotFoundException("Test with id: " + id + " not exists.");
        }
        Test updateTest = testMapper.toEntity(updateDto);
        updateTest.setId(id);
        return testMapper.toDto(
                testRepository.save(updateTest));
    }
}
