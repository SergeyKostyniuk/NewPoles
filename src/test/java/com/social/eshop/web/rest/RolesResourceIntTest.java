package com.social.eshop.web.rest;

import com.social.eshop.DtoApp;

import com.social.eshop.domain.Roles;
import com.social.eshop.repository.RolesRepository;
import com.social.eshop.service.RolesService;
import com.social.eshop.repository.search.RolesSearchRepository;
import com.social.eshop.service.dto.RolesDTO;
import com.social.eshop.service.mapper.RolesMapper;
import com.social.eshop.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the RolesResource REST controller.
 *
 * @see RolesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DtoApp.class)
public class RolesResourceIntTest {

    private static final String DEFAULT_ROLE = "AAAAAAAAAA";
    private static final String UPDATED_ROLE = "BBBBBBBBBB";

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private RolesMapper rolesMapper;

    @Autowired
    private RolesService rolesService;

    @Autowired
    private RolesSearchRepository rolesSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restRolesMockMvc;

    private Roles roles;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        RolesResource rolesResource = new RolesResource(rolesService);
        this.restRolesMockMvc = MockMvcBuilders.standaloneSetup(rolesResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Roles createEntity(EntityManager em) {
        Roles roles = new Roles()
            .role(DEFAULT_ROLE);
        return roles;
    }

    @Before
    public void initTest() {
        rolesSearchRepository.deleteAll();
        roles = createEntity(em);
    }

    @Test
    @Transactional
    public void createRoles() throws Exception {
        int databaseSizeBeforeCreate = rolesRepository.findAll().size();

        // Create the Roles
        RolesDTO rolesDTO = rolesMapper.toDto(roles);
        restRolesMockMvc.perform(post("/api/roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isCreated());

        // Validate the Roles in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeCreate + 1);
        Roles testRoles = rolesList.get(rolesList.size() - 1);
        assertThat(testRoles.getRole()).isEqualTo(DEFAULT_ROLE);

        // Validate the Roles in Elasticsearch
        Roles rolesEs = rolesSearchRepository.findOne(testRoles.getId());
        assertThat(rolesEs).isEqualToComparingFieldByField(testRoles);
    }

    @Test
    @Transactional
    public void createRolesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rolesRepository.findAll().size();

        // Create the Roles with an existing ID
        roles.setId(1L);
        RolesDTO rolesDTO = rolesMapper.toDto(roles);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRolesMockMvc.perform(post("/api/roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);

        // Get all the rolesList
        restRolesMockMvc.perform(get("/api/roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roles.getId().intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }

    @Test
    @Transactional
    public void getRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);

        // Get the roles
        restRolesMockMvc.perform(get("/api/roles/{id}", roles.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(roles.getId().intValue()))
            .andExpect(jsonPath("$.role").value(DEFAULT_ROLE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingRoles() throws Exception {
        // Get the roles
        restRolesMockMvc.perform(get("/api/roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);
        rolesSearchRepository.save(roles);
        int databaseSizeBeforeUpdate = rolesRepository.findAll().size();

        // Update the roles
        Roles updatedRoles = rolesRepository.findOne(roles.getId());
        updatedRoles
            .role(UPDATED_ROLE);
        RolesDTO rolesDTO = rolesMapper.toDto(updatedRoles);

        restRolesMockMvc.perform(put("/api/roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isOk());

        // Validate the Roles in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeUpdate);
        Roles testRoles = rolesList.get(rolesList.size() - 1);
        assertThat(testRoles.getRole()).isEqualTo(UPDATED_ROLE);

        // Validate the Roles in Elasticsearch
        Roles rolesEs = rolesSearchRepository.findOne(testRoles.getId());
        assertThat(rolesEs).isEqualToComparingFieldByField(testRoles);
    }

    @Test
    @Transactional
    public void updateNonExistingRoles() throws Exception {
        int databaseSizeBeforeUpdate = rolesRepository.findAll().size();

        // Create the Roles
        RolesDTO rolesDTO = rolesMapper.toDto(roles);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restRolesMockMvc.perform(put("/api/roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rolesDTO)))
            .andExpect(status().isCreated());

        // Validate the Roles in the database
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);
        rolesSearchRepository.save(roles);
        int databaseSizeBeforeDelete = rolesRepository.findAll().size();

        // Get the roles
        restRolesMockMvc.perform(delete("/api/roles/{id}", roles.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean rolesExistsInEs = rolesSearchRepository.exists(roles.getId());
        assertThat(rolesExistsInEs).isFalse();

        // Validate the database is empty
        List<Roles> rolesList = rolesRepository.findAll();
        assertThat(rolesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchRoles() throws Exception {
        // Initialize the database
        rolesRepository.saveAndFlush(roles);
        rolesSearchRepository.save(roles);

        // Search the roles
        restRolesMockMvc.perform(get("/api/_search/roles?query=id:" + roles.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(roles.getId().intValue())))
            .andExpect(jsonPath("$.[*].role").value(hasItem(DEFAULT_ROLE.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Roles.class);
        Roles roles1 = new Roles();
        roles1.setId(1L);
        Roles roles2 = new Roles();
        roles2.setId(roles1.getId());
        assertThat(roles1).isEqualTo(roles2);
        roles2.setId(2L);
        assertThat(roles1).isNotEqualTo(roles2);
        roles1.setId(null);
        assertThat(roles1).isNotEqualTo(roles2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RolesDTO.class);
        RolesDTO rolesDTO1 = new RolesDTO();
        rolesDTO1.setId(1L);
        RolesDTO rolesDTO2 = new RolesDTO();
        assertThat(rolesDTO1).isNotEqualTo(rolesDTO2);
        rolesDTO2.setId(rolesDTO1.getId());
        assertThat(rolesDTO1).isEqualTo(rolesDTO2);
        rolesDTO2.setId(2L);
        assertThat(rolesDTO1).isNotEqualTo(rolesDTO2);
        rolesDTO1.setId(null);
        assertThat(rolesDTO1).isNotEqualTo(rolesDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rolesMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rolesMapper.fromId(null)).isNull();
    }
}
