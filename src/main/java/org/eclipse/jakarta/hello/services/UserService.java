package org.eclipse.jakarta.hello.services;

import org.eclipse.jakarta.hello.dtos.*;
import org.eclipse.jakarta.hello.dtos.payload.CreateUserPayload;
import org.eclipse.jakarta.hello.dtos.payload.LoginPayload;
import org.eclipse.jakarta.hello.dtos.payload.UpdateUserPayload;
import org.eclipse.jakarta.hello.dtos.payload.UpdateUserRolesPayload;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id);
    List<UserDto> listUsers();
    UserDto createUser(CreateUserPayload payload);
    UserDto updateUser(UpdateUserPayload payload);
    UserDto updateUserRoles(UpdateUserRolesPayload payload);
    UserDto blockUser( Long userId, boolean block);
    UserDto login(LoginPayload payload);
}
