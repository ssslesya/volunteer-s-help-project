package ru.lutsenko.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.lutsenko.user.dto.user.CreateUserDto;
import ru.lutsenko.user.dto.user.UserDto;
import ru.lutsenko.user.entity.User;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDto(User user);

    User toUser(CreateUserDto createUserDto);
}
