package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {

    // == fields ==
    @Getter @Setter
    @Column(name = "unit_of_measure", nullable = false, unique = true)
    protected String unitOfMeasure;
}
