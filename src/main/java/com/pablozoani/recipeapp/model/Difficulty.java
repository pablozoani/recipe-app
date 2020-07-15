package com.pablozoani.recipeapp.model;

public enum Difficulty {
    EASY("Easy"), MEDIUM("Medium"), HARD("Hard");

    private String name;

    Difficulty(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
