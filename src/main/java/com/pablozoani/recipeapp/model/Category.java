package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

    // == fields ==

    @Id @Getter
    @GeneratedValue(generator = "native")
    protected Long id;

    @Getter @Setter
    @Column(name = "category_name", nullable = false, unique = true)
    protected String categoryName;

    // == relationships ==

    @Getter @Setter
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
    protected Set<Recipe> recipes = new HashSet<>();
}