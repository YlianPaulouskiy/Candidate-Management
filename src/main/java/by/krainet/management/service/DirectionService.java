package by.krainet.management.service;

import by.krainet.management.persistence.model.Direction;
import by.krainet.management.service.dto.DirectionFilter;
import by.krainet.management.service.dto.DirectionRequestDto;
import by.krainet.management.service.dto.DirectionResponseDto;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DirectionService {

    Page<DirectionResponseDto> getDirections(DirectionFilter filter, Pageable pageable);

    DirectionResponseDto save(DirectionRequestDto requestDto);

    DirectionResponseDto update(Long id, DirectionRequestDto updateDto);

}
