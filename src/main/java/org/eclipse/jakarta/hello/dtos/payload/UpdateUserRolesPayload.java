package org.eclipse.jakarta.hello.dtos.payload;

import jakarta.validation.constraints.NotNull;
import org.eclipse.jakarta.hello.dtos.RoleDto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UpdateUserRolesPayload implements Serializable {

    @NotNull
    private Long id;
    @NotNull
    private List<RoleDto> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
