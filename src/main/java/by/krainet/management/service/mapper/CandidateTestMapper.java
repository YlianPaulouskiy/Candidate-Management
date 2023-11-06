package by.krainet.management.service.mapper;

import by.krainet.management.persistence.model.CandidateTest;
import by.krainet.management.persistence.repository.CandidateRepository;
import by.krainet.management.persistence.repository.TestRepository;
import by.krainet.management.service.dto.CandidateTestRequestDto;
import by.krainet.management.service.dto.CandidateTestResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring",
        uses = {CandidateMapper.class, TestMapper.class})
public abstract class CandidateTestMapper {

    @Autowired
    protected CandidateRepository candidateRepository;
    @Autowired
    protected TestRepository testRepository;

    // TODO: 06.11.2023 проверку при сохранении и обновлении на наличие таких сущностей в бд
    @Mapping(target = "candidate", expression = "java(candidateRepository.getReferenceById(requestDto.candidateId()))")
    @Mapping(target = "testInfo", expression = "java(testRepository.getReferenceById(requestDto.testId()))")
    public abstract CandidateTest toEntity(CandidateTestRequestDto requestDto);

    public abstract CandidateTestResponseDto toDto(CandidateTest candidateTest);

}
