package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Notes {

    // == fields ==

    @Getter @Setter
    @Column(name = "notes", nullable = false)
    protected String notes;

    // == relationships ==

    protected Recipe recipe;
}
