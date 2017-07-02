package com.social.eshop.service.impl;

import com.social.eshop.service.RolesService;
import com.social.eshop.domain.Roles;
import com.social.eshop.repository.RolesRepository;
import com.social.eshop.repository.search.RolesSearchRepository;
import com.social.eshop.service.dto.RolesDTO;
import com.social.eshop.service.mapper.RolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Roles.
 */
@Service
@Transactional
public class RolesServiceImpl implements RolesService{

    private final Logger log = LoggerFactory.getLogger(RolesServiceImpl.class);

    private final RolesRepository rolesRepository;

    private final RolesMapper rolesMapper;

    private final RolesSearchRepository rolesSearchRepository;

    public RolesServiceImpl(RolesRepository rolesRepository, RolesMapper rolesMapper, RolesSearchRepository rolesSearchRepository) {
        this.rolesRepository = rolesRepository;
        this.rolesMapper = rolesMapper;
        this.rolesSearchRepository = rolesSearchRepository;
    }

    /**
     * Save a roles.
     *
     * @param rolesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RolesDTO save(RolesDTO rolesDTO) {
        log.debug("Request to save Roles : {}", rolesDTO);
        Roles roles = rolesMapper.toEntity(rolesDTO);
        roles = rolesRepository.save(roles);
        RolesDTO result = rolesMapper.toDto(roles);
        rolesSearchRepository.save(roles);
        return result;
    }

    /**
     *  Get all the roles.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RolesDTO> findAll() {
        log.debug("Request to get all Roles");
        return rolesRepository.findAll().stream()
            .map(rolesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one roles by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public RolesDTO findOne(Long id) {
        log.debug("Request to get Roles : {}", id);
        Roles roles = rolesRepository.findOne(id);
        return rolesMapper.toDto(roles);
    }

    /**
     *  Delete the  roles by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Roles : {}", id);
        rolesRepository.delete(id);
        rolesSearchRepository.delete(id);
    }

    /**
     * Search for the roles corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<RolesDTO> search(String query) {
        log.debug("Request to search Roles for query {}", query);
        return StreamSupport
            .stream(rolesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(rolesMapper::toDto)
            .collect(Collectors.toList());
    }
}
