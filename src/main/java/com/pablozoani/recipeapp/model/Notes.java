package com.pablozoani.recipeapp.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "notes")
public class Notes {

    // == fields ==
    private String notes;

    // == relationships ==
    private Recipe recipe;
}
