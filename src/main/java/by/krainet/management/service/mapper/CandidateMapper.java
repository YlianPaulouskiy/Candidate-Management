package by.krainet.management.service.mapper;

import by.krainet.management.persistence.model.Candidate;
import by.krainet.management.persistence.repository.DirectionRepository;
import by.krainet.management.service.dto.CandidateRequestDto;
import by.krainet.management.service.dto.CandidateResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = DirectionMapper.class)
public abstract class CandidateMapper {

    @Autowired
    protected DirectionRepository directionRepository;

    @Mapping(target = "photo", expression = "java(requestDto.photo() != null ? requestDto.photo().getOriginalFilename() : null)")
    @Mapping(target = "cv", expression = "java(requestDto.cv() != null ? requestDto.cv().getOriginalFilename() : null)")
    @Mapping(target = "directions", expression = "java(directionRepository.findAllById(requestDto.directions()))")
    public abstract Candidate toEntity(CandidateRequestDto requestDto);

    public abstract CandidateResponseDto toDto(Candidate candidate);



}
