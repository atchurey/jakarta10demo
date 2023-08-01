package org.eclipse.jakarta.hello.dtos;

import org.eclipse.jakarta.hello.enums.SystemClearance;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserDto implements Serializable {

    private Long id;
    private String countryCode;
    private String email;
    private LocalDate birthday;
    private String firstName;
    private String otherNames;
    private String lastName;
    private String fullName;
    private boolean accountClaimed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private SystemClearance systemClearance;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAccountClaimed() {
        return accountClaimed;
    }

    public void setAccountClaimed(boolean accountClaimed) {
        this.accountClaimed = accountClaimed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SystemClearance getSystemClearance() {
        return systemClearance;
    }

    public void setSystemClearance(SystemClearance systemClearance) {
        this.systemClearance = systemClearance;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
