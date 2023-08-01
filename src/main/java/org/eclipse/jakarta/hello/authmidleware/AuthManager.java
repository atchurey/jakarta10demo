package org.eclipse.jakarta.hello.authmidleware;

import org.eclipse.jakarta.hello.dtos.payload.CreateUserPayload;
import org.eclipse.jakarta.hello.dtos.RoleDto;
import org.eclipse.jakarta.hello.dtos.UserDto;
import org.eclipse.jakarta.hello.entities.Role;
import org.eclipse.jakarta.hello.entities.User;
import org.eclipse.jakarta.hello.enums.SystemClearance;
import org.eclipse.jakarta.hello.exceptions.ServiceException;
import org.eclipse.jakarta.hello.repositories.RoleRepository;
import org.eclipse.jakarta.hello.repositories.UserRepository;
import org.eclipse.jakarta.hello.utils.AppUtils;
import org.eclipse.jakarta.hello.utils.PasswordUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AuthManager {

    private AuthMiddleware middleware;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AuthManager(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void setMiddleware(AuthMiddleware middleware) {
        this.middleware = middleware;
    }

    public UserDto logIn(String email, String password) {
        if (middleware.check(email, password)) {

            Optional<User> userOptional = userRepository.findByEmail(email);
            userOptional.get().setSuccessiveFailedAttempts(0);
            userRepository.update(userOptional.get());
            return userOptional.map(AppUtils::mapToDto).get();
        } else {
            return null;
        }
    }

    public UserDto signUp(CreateUserPayload payload) {
        User user = new User();
        user.setCountryCode(payload.getCountryCode());
        user.setFirstName(payload.getFirstName());
        user.setOtherNames(payload.getOtherNames());
        user.setLastName(payload.getLastName());
        user.setBirthday(payload.getBirthday());
        user.setEmail(payload.getEmail());

        List<Role> roles = new ArrayList<>();
        for (RoleDto roleDto : payload.getRoles()) {
            Optional<Role> roleOptional = roleRepository.findByCode(roleDto.getCode());
            if (roleOptional.isPresent()) {
                roles.add(roleOptional.get());
            } else {
                throw new ServiceException(0, "Invalid Role provided.");
            }
        }
        user.setRoles(roles);

        String salt = PasswordUtils.getSalt(30);
        String mySecurePassword = PasswordUtils.generateSecurePassword(payload.getPassword(), salt);
        user.setPst(salt);
        user.setPassword(mySecurePassword);
        user.setSystemClearance(SystemClearance.CLEARED);
        user.setAccountClaimed(true);

        userRepository.save(user);

        return AppUtils.mapToDto(user);
    }

    public boolean hasEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public boolean isCleared(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        return userOptional.filter(user -> SystemClearance.CLEARED == user.getSystemClearance()).isPresent();
    }

    public boolean isValidPassword(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        boolean isValid =  userOptional.filter(user -> PasswordUtils.verifyUserPassword(password, user.getPassword(), user.getPst())).isPresent();

        if (!isValid) {
            User user = userOptional.get();
            user.setSuccessiveFailedAttempts(user.getSuccessiveFailedAttempts() + 1);

            if (user.getSuccessiveFailedAttempts() >= 3) {
                user.setSystemClearance(SystemClearance.BLOCKED);
            }

            userRepository.update(user);
        }

        return isValid;
    }
}