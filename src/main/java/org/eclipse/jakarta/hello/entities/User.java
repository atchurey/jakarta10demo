package org.eclipse.jakarta.hello.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.eclipse.jakarta.hello.enums.SystemClearance;
import org.eclipse.jakarta.hello.utils.AppUtils;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users", indexes = {
        @Index(name = "index_users_on_country_code", columnList = "country_code"),
        @Index(name = "index_users_on_email", columnList = "email"),
        @Index(name = "index_users_on_first_name", columnList = "first_name"),
        @Index(name = "index_users_on_last_name", columnList = "last_name"),
        @Index(name = "index_users_on_account_claimed", columnList = "account_claimed"),
        @Index(name = "index_users_on_system_clearance", columnList = "system_clearance"),

})
public class User implements Serializable {

    @Id
    @GeneratedValue(generator="UUID")
    private Long id;

    @NotNull
    @Column(name = "country_code")
    private String countryCode;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @NotNull
    @Column(name = "birthday")
    private LocalDate birthday;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "pst")
    private String pst;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_names")
    private String otherNames;

    @Transient
    private String fullName;

    @NotNull
    @Column(name = "account_claimed")
    private boolean accountClaimed;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "successive_failed_attempts")
    private int successiveFailedAttempts;

    @Column(name = "system_clearance")
    @Enumerated(EnumType.STRING)
    private SystemClearance systemClearance;

    @ManyToMany(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_code") }
    )
    private List<Role> roles = new ArrayList<>();


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPst() {
        return pst;
    }

    public void setPst(String pst) {
        this.pst = pst;
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

    public int getSuccessiveFailedAttempts() {
        return successiveFailedAttempts;
    }

    public void setSuccessiveFailedAttempts(int successiveFailedAttempts) {
        this.successiveFailedAttempts = successiveFailedAttempts;
    }

    public SystemClearance getSystemClearance() {
        return systemClearance;
    }

    public void setSystemClearance(SystemClearance systemClearance) {
        this.systemClearance = systemClearance;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getFullName() {
        if (AppUtils.isEmpty(fullName)) {
            fullName =  firstName + (AppUtils.isEmpty(otherNames) ? "" : (" " + otherNames)) + " " + lastName;
        }

        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @PrePersist
    public void beforeSave() {
        LocalDateTime now = LocalDateTime.now();
        setCreatedAt(now);
        setUpdatedAt(now);
    }

    @PreUpdate
    public void beforeUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }
}
