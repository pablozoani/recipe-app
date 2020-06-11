package com.pablozoani.recipeapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {

    // == fields ==

    @Id
    @GeneratedValue(generator = "native")
    protected Long id;

    @Column(name = "unit_of_measure", nullable = false, unique = true)
    protected String unitOfMeasure;
}
