package com.pablozoani.recipeapp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "recipe")
public class Recipe {

    // == fields ==

    @Id @Getter
    @GeneratedValue(generator = "native")
    protected Long id;

    @Getter @Setter
    @Column(nullable = false)
    protected String description;

    @Getter @Setter
    @Column(name = "preparation_time", nullable = false)
    protected String prepTime;

    @Getter @Setter
    @Column(name = "cook_time")
    protected String cookTime;

    @Getter @Setter
    protected Integer servings;

    @Getter @Setter
    protected String source;

    @Getter @Setter
    protected String url;

    @Getter @Setter
    @Column(nullable = false)
    protected String directions;

    @Getter @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    protected Difficulty difficulty;

    @Getter @Setter @Lob
    protected Byte[] image;

    // == relationships ==

    @Getter @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    protected Notes notes;

    @Getter @Setter
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "recipe")
    protected Set<Ingredient> ingredients = new HashSet<>();

    @Getter @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "recipe_category",
               joinColumns = @JoinColumn(name = "recipe_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    protected Set<Category> categories = new HashSet<>();
}
