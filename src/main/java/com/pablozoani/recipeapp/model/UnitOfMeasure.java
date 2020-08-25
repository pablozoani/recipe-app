package com.pablozoani.recipeapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document
public class UnitOfMeasure {

    // == fields ==

    @Id
    protected String id;

    protected String unitOfMeasure;

    public UnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }
}
