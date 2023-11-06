package by.krainet.management.service.impl;

import by.krainet.management.persistence.model.CandidateTest;
import by.krainet.management.persistence.model.QCandidateTest;
import by.krainet.management.persistence.model.Result;
import by.krainet.management.persistence.repository.CandidateRepository;
import by.krainet.management.persistence.repository.CandidateTestRepository;
import by.krainet.management.persistence.repository.QPredicates;
import by.krainet.management.persistence.repository.TestRepository;
import by.krainet.management.service.CandidateTestService;
import by.krainet.management.service.dto.CandidateTestFilter;
import by.krainet.management.service.dto.CandidateTestRequestDto;
import by.krainet.management.service.dto.CandidateTestResponseDto;
import by.krainet.management.service.exception.CandidateNotFoundException;
import by.krainet.management.service.exception.CandidateTestNotFoundException;
import by.krainet.management.service.exception.TestNotFoundException;
import by.krainet.management.service.mapper.CandidateTestMapper;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateTestServiceImpl implements CandidateTestService {

    private final CandidateTestRepository candidateTestRepository;
    private final CandidateTestMapper candidateTestMapper;
    private final TestRepository testRepository;
    private final CandidateRepository candidateRepository;

    @Override
    public Page<CandidateTestResponseDto> getCandidateTests(CandidateTestFilter filter, Pageable pageable) {
        if (filter != null) {
            Predicate predicate = QPredicates.builder()
                    .add(filter.candidateLastname(), QCandidateTest.candidateTest.candidate.lastname::containsIgnoreCase)
                    .add(filter.candidateName(), QCandidateTest.candidateTest.candidate.name::containsIgnoreCase)
                    .add(filter.testName(), QCandidateTest.candidateTest.testInfo.name::containsIgnoreCase)
                    .build();
            return candidateTestRepository.findAll(predicate, pageable)
                    .map(candidateTestMapper::toDto);
        }
        return candidateTestRepository.findAll(pageable)
                .map(candidateTestMapper::toDto);
    }

    @Override
    public CandidateTestResponseDto save(CandidateTestRequestDto requestDto) {
        checkCandidateAndTestExists(requestDto);
        return saveWithResultInDatabase(
                candidateTestMapper.toEntity(requestDto));
    }

    @Override
    public CandidateTestResponseDto update(Long id, CandidateTestRequestDto updateDto) {
        if (candidateTestRepository.findById(id).isEmpty()) {
            throw new CandidateTestNotFoundException("CandidateTest with id: " + id + " not found.");
        }
        checkCandidateAndTestExists(updateDto);
        CandidateTest updateCandidateTest = candidateTestMapper.toEntity(updateDto);
        updateCandidateTest.setId(id);
        return saveWithResultInDatabase(updateCandidateTest);
    }

    private CandidateTestResponseDto saveWithResultInDatabase(CandidateTest updateCandidateTest) {
        List<Result> results = updateCandidateTest.getResults();
        updateCandidateTest.setResults(null);
        candidateTestRepository.saveAndFlush(updateCandidateTest);
        results.forEach(result -> result.setCandidateTest(updateCandidateTest));
        updateCandidateTest.setResults(results);
        return candidateTestMapper.toDto(
                candidateTestRepository.save(updateCandidateTest));
    }

    private void checkCandidateAndTestExists(CandidateTestRequestDto dto) {
        if (!candidateRepository.existsById(dto.candidateId())) {
            throw new CandidateNotFoundException("Candidate with id: " + dto.candidateId() + " not exists.");
        }
        if (!testRepository.existsById(dto.testId())) {
            throw new TestNotFoundException("Test with id: " + dto.testId() + " not exists.");
        }
    }

}
