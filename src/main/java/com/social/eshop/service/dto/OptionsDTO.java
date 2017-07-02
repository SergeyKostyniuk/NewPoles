package com.social.eshop.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Options entity.
 */
public class OptionsDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer color;

    @NotNull
    private Double weight;

    @NotNull
    private String metal;

    private Double size;

    private Integer length;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getMetal() {
        return metal;
    }

    public void setMetal(String metal) {
        this.metal = metal;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OptionsDTO optionsDTO = (OptionsDTO) o;
        if(optionsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), optionsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OptionsDTO{" +
            "id=" + getId() +
            ", color='" + getColor() + "'" +
            ", weight='" + getWeight() + "'" +
            ", metal='" + getMetal() + "'" +
            ", size='" + getSize() + "'" +
            ", length='" + getLength() + "'" +
            "}";
    }
}
