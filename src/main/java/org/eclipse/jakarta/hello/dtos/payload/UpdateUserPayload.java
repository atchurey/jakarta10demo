package org.eclipse.jakarta.hello.dtos.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.eclipse.jakarta.hello.dtos.RoleDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UpdateUserPayload implements Serializable {

    @NotNull
    private Long id;
    @NotNull
    private String countryCode;
    @NotNull
    @Past
    private LocalDate birthday;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String otherNames;
    @NotNull
    private List<RoleDto> roles = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
