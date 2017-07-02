package com.social.eshop.service.mapper;

import com.social.eshop.domain.*;
import com.social.eshop.service.dto.RolesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Roles and its DTO RolesDTO.
 */
@Mapper(componentModel = "spring", uses = {ManagerMapper.class, })
public interface RolesMapper extends EntityMapper <RolesDTO, Roles> {

    @Mapping(source = "manager.id", target = "managerId")
    RolesDTO toDto(Roles roles); 

    @Mapping(source = "managerId", target = "manager")
    Roles toEntity(RolesDTO rolesDTO); 
    default Roles fromId(Long id) {
        if (id == null) {
            return null;
        }
        Roles roles = new Roles();
        roles.setId(id);
        return roles;
    }
}
