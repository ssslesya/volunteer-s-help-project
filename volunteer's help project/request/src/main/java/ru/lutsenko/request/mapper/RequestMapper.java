package ru.lutsenko.request.mapper;

import org.mapstruct.*;
import ru.lutsenko.request.dto.CreateRequestDto;
import ru.lutsenko.request.dto.RequestDto;
import ru.lutsenko.request.entity.Request;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RequestMapper {

    Request toEntity(CreateRequestDto createRequestDto);

    RequestDto toRequestDto(Request request);
}