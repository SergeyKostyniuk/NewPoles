package com.social.eshop.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Roles entity.
 */
public class RolesDTO implements Serializable {

    private Long id;

    private String role;

    private Long managerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RolesDTO rolesDTO = (RolesDTO) o;
        if(rolesDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rolesDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RolesDTO{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            "}";
    }
}
