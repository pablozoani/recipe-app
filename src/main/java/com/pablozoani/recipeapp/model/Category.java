package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
@Table(name = "category")
public class Category {

    // == fields ==

    @Id
    @GeneratedValue(generator = "native")
    protected Long id;

    @Column(name = "category_name", nullable = false, unique = true)
    protected String categoryName;

    // == relationships ==

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    protected Set<Recipe> recipes = new HashSet<>();
}