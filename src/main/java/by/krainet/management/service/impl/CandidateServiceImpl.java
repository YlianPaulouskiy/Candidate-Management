package by.krainet.management.service.impl;

import by.krainet.management.persistence.model.Candidate;
import by.krainet.management.persistence.model.QCandidate;
import by.krainet.management.persistence.repository.CandidateRepository;
import by.krainet.management.persistence.repository.QPredicates;
import by.krainet.management.service.CandidateService;
import by.krainet.management.service.FileService;
import by.krainet.management.service.dto.CandidateFilter;
import by.krainet.management.service.dto.CandidateRequestDto;
import by.krainet.management.service.dto.CandidateResponseDto;
import by.krainet.management.service.exception.CandidateExistException;
import by.krainet.management.service.exception.CandidateNotFoundException;
import by.krainet.management.service.mapper.CandidateMapper;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final FileService fileService;

    @Override
    public Page<CandidateResponseDto> getCandidates(CandidateFilter filter, Pageable pageable) {
        if (filter != null) {
            Predicate predicate = QPredicates.builder()
                    .add(filter.lastname(), QCandidate.candidate.lastname::containsIgnoreCase)
                    .add(filter.name(), QCandidate.candidate.name::containsIgnoreCase)
                    .add(filter.middleName(), QCandidate.candidate.middleName::containsIgnoreCase)
                    .add(filter.description(), QCandidate.candidate.description::containsIgnoreCase)
                    .build();
            return candidateRepository.findAll(predicate, pageable)
                    .map(candidateMapper::toDto);
        }
        return candidateRepository.findAll(pageable)
                .map(candidateMapper::toDto);
    }

    @Override
    public CandidateResponseDto save(CandidateRequestDto requestDto) {
        Candidate candidate = candidateMapper.toEntity(requestDto);
        if (candidateRepository.findAll().contains(candidate)) {
            throw new CandidateExistException(String.format("Candidate %s %s %s already exists.",
                    requestDto.lastname(), requestDto.name(), requestDto.middleName()));
        }
        uploadFiles(requestDto.photo(), requestDto.cv());
        return candidateMapper.toDto(
                candidateRepository.save(candidate));
    }

    @Override
    public CandidateResponseDto update(Long id, CandidateRequestDto updateDto) {
        if (candidateRepository.findById(id).isEmpty()) {
            throw new CandidateNotFoundException("Candidate with id: " + id + " not exists.");
        }
        Candidate updateCandidate = candidateMapper.toEntity(updateDto);
        updateCandidate.setId(id);
        removeFiles(id);
        uploadFiles(updateDto.photo(), updateDto.cv());
        return candidateMapper.toDto(
                candidateRepository.save(updateCandidate));
    }

    private void removeFiles(Long candidateId) {
        Candidate candidate = candidateRepository.getReferenceById(candidateId);
        if (candidate.getPhoto() != null && !candidate.getPhoto().isBlank()) {
            fileService.remove(candidate.getPhoto());
        }
        if (candidate.getCv() != null && !candidate.getCv().isBlank()) {
            fileService.remove(candidate.getCv());
        }
    }

    private void uploadFiles(MultipartFile... files) {
        for (MultipartFile file : files) {
            if (file != null && !file.isEmpty()) {
                try {
                    log.warn("Upload new file {} in project ", file);
                    fileService.upload(file.getOriginalFilename(), file.getInputStream());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
