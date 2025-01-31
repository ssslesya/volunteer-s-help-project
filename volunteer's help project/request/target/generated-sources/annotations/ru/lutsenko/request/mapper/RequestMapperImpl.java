package ru.lutsenko.request.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.lutsenko.request.dto.CreateRequestDto;
import ru.lutsenko.request.dto.RequestDto;
import ru.lutsenko.request.entity.Request;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-01-26T18:10:52+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class RequestMapperImpl implements RequestMapper {

    @Override
    public Request toEntity(CreateRequestDto createRequestDto) {
        if ( createRequestDto == null ) {
            return null;
        }

        Request.RequestBuilder request = Request.builder();

        request.type( createRequestDto.getType() );
        request.description( createRequestDto.getDescription() );
        request.address( createRequestDto.getAddress() );
        request.executionDateTime( createRequestDto.getExecutionDateTime() );
        request.needingId( createRequestDto.getNeedingId() );

        return request.build();
    }

    @Override
    public RequestDto toRequestDto(Request request) {
        if ( request == null ) {
            return null;
        }

        RequestDto.RequestDtoBuilder requestDto = RequestDto.builder();

        requestDto.id( request.getId() );
        requestDto.type( request.getType() );
        requestDto.description( request.getDescription() );
        requestDto.address( request.getAddress() );
        requestDto.createdAt( request.getCreatedAt() );
        requestDto.executionDateTime( request.getExecutionDateTime() );
        requestDto.executedDateTime( request.getExecutedDateTime() );
        requestDto.needingId( request.getNeedingId() );
        requestDto.volunteerId( request.getVolunteerId() );
        requestDto.requestStatus( request.getRequestStatus() );

        return requestDto.build();
    }
}
