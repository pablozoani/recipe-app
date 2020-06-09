package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "notes")
public class Notes {

    // == fields ==

    @Id @Getter
    @GeneratedValue(generator = "native")
    protected Long id;

    @Getter @Setter
    @Column(name = "notes", nullable = false)
    protected String notes;

    // == relationships ==

    protected Recipe recipe;
}
