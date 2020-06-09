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

    @Getter @Setter @Lob
    @Column(name = "recipe_notes", nullable = false)
    protected String recipeNotes;

    // == relationships ==

    @Getter @Setter
    @OneToOne(mappedBy = "notes")
    protected Recipe recipe;
}
