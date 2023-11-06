package by.krainet.management.service.impl;

import by.krainet.management.persistence.model.Direction;
import by.krainet.management.persistence.model.QDirection;
import by.krainet.management.persistence.repository.DirectionRepository;
import by.krainet.management.persistence.repository.QPredicates;
import by.krainet.management.service.DirectionService;
import by.krainet.management.service.dto.DirectionFilter;
import by.krainet.management.service.dto.DirectionRequestDto;
import by.krainet.management.service.dto.DirectionResponseDto;
import by.krainet.management.service.exception.DirectionExistException;
import by.krainet.management.service.exception.DirectionNotFoundException;
import by.krainet.management.service.mapper.DirectionMapper;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;
    private final DirectionMapper directionMapper;

    @Override
    public Page<DirectionResponseDto> getDirections(DirectionFilter filter, Pageable pageable) {
        if (filter != null) {
            Predicate predicate = QPredicates.builder()
                    .add(filter.name(), QDirection.direction.name::containsIgnoreCase)
                    .add(filter.description(), QDirection.direction.description::containsIgnoreCase)
                    .build();
            return directionRepository.findAll(predicate, pageable)
                    .map(directionMapper::toDto);
        }
        return directionRepository.findAll(pageable)
                .map(directionMapper::toDto);
    }

    @Override
    public DirectionResponseDto save(DirectionRequestDto requestDto) {
        if (directionRepository.existsByName(requestDto.name())) {
            throw new DirectionExistException("Direction with name: " + requestDto.name() + " already exists.");
        }
        return directionMapper.toDto(
                directionRepository.save(
                        directionMapper.toEntity(requestDto)));
    }

    @Override
    public DirectionResponseDto update(Long id, DirectionRequestDto updateDto) {
        if (directionRepository.findById(id).isEmpty()) {
            throw new DirectionNotFoundException("Direction with id: " + id + " not exists.");
        }
        Direction updateDirection = directionMapper.toEntity(updateDto);
        updateDirection.setId(id);
        return directionMapper.toDto(
                directionRepository.save(updateDirection));
    }

}
