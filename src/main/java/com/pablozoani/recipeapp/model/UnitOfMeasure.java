package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "unit_of_measure")
public class UnitOfMeasure {

    // == fields ==

    @Id @Getter
    @GeneratedValue(generator = "native")
    protected Long id;

    @Getter @Setter
    @Column(name = "unit_of_measure", nullable = false, unique = true)
    protected String unitOfMeasure;
}
