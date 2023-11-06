package by.krainet.management.service.mapper;

import by.krainet.management.persistence.model.Direction;
import by.krainet.management.service.dto.DirectionRequestDto;
import by.krainet.management.service.dto.DirectionResponseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DirectionMapper {

    Direction toEntity(DirectionRequestDto requestDto);

    DirectionResponseDto toDto(Direction direction);

}
