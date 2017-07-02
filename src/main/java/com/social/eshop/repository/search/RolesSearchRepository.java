package com.social.eshop.repository.search;

import com.social.eshop.domain.Roles;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Roles entity.
 */
public interface RolesSearchRepository extends ElasticsearchRepository<Roles, Long> {
}
