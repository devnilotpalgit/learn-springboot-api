package com.codewithmosh.store.mappers;

import ch.qos.logback.core.model.ComponentModel;
import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface userMapper {
    UserDto toDto(User user);
}
