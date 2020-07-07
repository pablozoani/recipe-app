package com.pablozoani.recipeapp.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryCommand {

    private Long id;

    private String categoryName;
}
