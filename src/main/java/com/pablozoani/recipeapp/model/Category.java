package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    // == fields ==

    @Getter @Setter
    @Column(name = "category_name", nullable = false, unique = true)
    protected String categoryName;

    // == relationships ==

    protected Set<Recipe> recipes = new HashSet<>();
}
