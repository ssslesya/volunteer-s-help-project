package ru.lutsenko.request.mapper;

import org.mapstruct.*;
import ru.lutsenko.request.dto.CreateRequestDto;
import ru.lutsenko.request.dto.RequestDto;
import ru.lutsenko.request.dto.SmallRequestDto;
import ru.lutsenko.request.entity.Request;
import ru.lutsenko.request.entity.RequestStatus;
import ru.lutsenko.request.entity.RequestType;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, imports = {
        RequestType.class,
        RequestStatus.class
})
public interface RequestMapper {

    Request toEntity(CreateRequestDto createRequestDto);

    @Mapping(target = "type", expression = "java(request.getType().getDescription())")
    @Mapping(target = "requestStatus", expression = "java(request.getRequestStatus().getDescription())")
    RequestDto toRequestDto(Request request);

    @Mapping(target = "requestStatus", expression = "java(request.getRequestStatus().getDescription())")
    SmallRequestDto toSmallRequestDto(Request request);
}