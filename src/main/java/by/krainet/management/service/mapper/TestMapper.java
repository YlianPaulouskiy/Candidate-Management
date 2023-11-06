package by.krainet.management.service.mapper;

import by.krainet.management.persistence.model.Test;
import by.krainet.management.persistence.repository.DirectionRepository;
import by.krainet.management.service.dto.TestRequestDto;
import by.krainet.management.service.dto.TestResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = DirectionMapper.class)
public abstract class TestMapper {

    @Autowired
    protected DirectionRepository directionRepository;

    @Mapping(target = "applicableDirections", expression = "java(directionRepository.findAllById(requestDto.applicableDirections()))")
    public abstract Test toEntity(TestRequestDto requestDto);

    public abstract TestResponseDto toDto(Test test);
}
