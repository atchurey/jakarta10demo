package org.eclipse.jakarta.hello.services.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.jakarta.hello.authmidleware.AuthManager;
import org.eclipse.jakarta.hello.dtos.*;
import org.eclipse.jakarta.hello.dtos.payload.CreateUserPayload;
import org.eclipse.jakarta.hello.dtos.payload.LoginPayload;
import org.eclipse.jakarta.hello.dtos.payload.UpdateUserPayload;
import org.eclipse.jakarta.hello.dtos.payload.UpdateUserRolesPayload;
import org.eclipse.jakarta.hello.entities.User;
import org.eclipse.jakarta.hello.exceptions.ServiceException;
import org.eclipse.jakarta.hello.repositories.RoleRepository;
import org.eclipse.jakarta.hello.repositories.UserRepository;
import org.eclipse.jakarta.hello.services.
        UserService;
import org.eclipse.jakarta.hello.utils.AppUtils;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserServiceImpl implements UserService {

    @Inject
    private UserRepository userRepository;
    @Inject
    private RoleRepository roleRepository;
    @Inject
    private AuthManager authManager;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ServiceException(1, "User not found"));
        return AppUtils.mapToDto(user);
    }

    @Override
    public List<UserDto> listUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(AppUtils::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto createUser(CreateUserPayload payload) {
        return authManager.signUp(payload);
    }

    @Override
    public UserDto updateUser(UpdateUserPayload payload) {

        User user = userRepository.findById(payload.getId()).orElseThrow(() -> new ServiceException(1, "User not found"));

        user.setCountryCode(payload.getCountryCode());
        user.setFirstName(payload.getFirstName());
        user.setOtherNames(payload.getOtherNames());
        user.setLastName(payload.getLastName());
        user.setBirthday(payload.getBirthday());

        userRepository.update(user);

        return AppUtils.mapToDto(user);
    }

    @Override
    public UserDto login(LoginPayload payload) {
        UserDto user = authManager.logIn(payload.getEmail(), payload.getPassword());

        if (user == null) {
            throw new ServiceException(1, "Login failed");
        }

        return user;
    }

    @Override
    public UserDto updateUserRoles(UpdateUserRolesPayload payload) {

        return null;
    }

    @Override
    public UserDto blockUser(Long userId, boolean block) {
        return null;
    }
}
