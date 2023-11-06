package by.krainet.management.service;

import by.krainet.management.service.dto.CandidateTestFilter;
import by.krainet.management.service.dto.CandidateTestRequestDto;
import by.krainet.management.service.dto.CandidateTestResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateTestService {

    Page<CandidateTestResponseDto> getCandidateTests(CandidateTestFilter filter, Pageable pageable);

    CandidateTestResponseDto save(CandidateTestRequestDto requestDto);

    CandidateTestResponseDto update(Long id, CandidateTestRequestDto updateDto);

}
