package com.social.eshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Manager.
 */
@Entity
@Table(name = "manager")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "manager")
public class Manager implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private LoginOptions loginOptions;

    @OneToOne
    @JoinColumn(unique = true)
    private PersonalInformation personalInfo;

    @OneToOne
    @JoinColumn(unique = true)
    private Avatar avatar;

    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Bucket> manegers = new HashSet<>();

    @OneToMany(mappedBy = "manager")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Roles> roles = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoginOptions getLoginOptions() {
        return loginOptions;
    }

    public Manager loginOptions(LoginOptions loginOptions) {
        this.loginOptions = loginOptions;
        return this;
    }

    public void setLoginOptions(LoginOptions loginOptions) {
        this.loginOptions = loginOptions;
    }

    public PersonalInformation getPersonalInfo() {
        return personalInfo;
    }

    public Manager personalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
        return this;
    }

    public void setPersonalInfo(PersonalInformation personalInformation) {
        this.personalInfo = personalInformation;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public Manager avatar(Avatar avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Set<Bucket> getManegers() {
        return manegers;
    }

    public Manager manegers(Set<Bucket> buckets) {
        this.manegers = buckets;
        return this;
    }

    public Manager addManeger(Bucket bucket) {
        this.manegers.add(bucket);
        bucket.setManager(this);
        return this;
    }

    public Manager removeManeger(Bucket bucket) {
        this.manegers.remove(bucket);
        bucket.setManager(null);
        return this;
    }

    public void setManegers(Set<Bucket> buckets) {
        this.manegers = buckets;
    }

    public Set<Roles> getRoles() {
        return roles;
    }

    public Manager roles(Set<Roles> roles) {
        this.roles = roles;
        return this;
    }

    public Manager addRoles(Roles roles) {
        this.roles.add(roles);
        roles.setManager(this);
        return this;
    }

    public Manager removeRoles(Roles roles) {
        this.roles.remove(roles);
        roles.setManager(null);
        return this;
    }

    public void setRoles(Set<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Manager manager = (Manager) o;
        if (manager.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), manager.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Manager{" +
            "id=" + getId() +
            "}";
    }
}
