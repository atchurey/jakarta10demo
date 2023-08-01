package org.eclipse.jakarta.hello.utils;

import org.eclipse.jakarta.hello.dtos.RoleDto;
import org.eclipse.jakarta.hello.dtos.UserDto;
import org.eclipse.jakarta.hello.entities.User;

import java.util.List;

public class AppUtils {

    public static boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }

    public static UserDto mapToDto(User user) {

        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setCountryCode(user.getCountryCode());
        dto.setEmail(user.getEmail());
        dto.setBirthday(user.getBirthday());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setOtherNames(user.getOtherNames());
        dto.setFullName(user.getFullName());
        dto.setAccountClaimed(user.isAccountClaimed());
        dto.setSystemClearance(user.getSystemClearance());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());


        List<RoleDto> rolesDtos = user.getRoles().stream()
                .map(role -> {
                    RoleDto d = new RoleDto();
                    d.setCode(role.getCode());
                    d.setName(role.getName());
                    return d;
                }).toList();

        dto.setRoles(rolesDtos);

        return dto;
    }

}
