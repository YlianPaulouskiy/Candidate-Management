package by.krainet.management.service;

import by.krainet.management.service.dto.CandidateFilter;
import by.krainet.management.service.dto.CandidateRequestDto;
import by.krainet.management.service.dto.CandidateResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CandidateService {

    Page<CandidateResponseDto> getCandidates(CandidateFilter filter, Pageable pageable);

    CandidateResponseDto save(CandidateRequestDto requestDto);

    CandidateResponseDto update(Long id, CandidateRequestDto updateDto);

}
