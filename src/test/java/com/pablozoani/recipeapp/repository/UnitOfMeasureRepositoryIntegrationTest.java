package com.pablozoani.recipeapp.repository;

import com.pablozoani.recipeapp.bootstrap.DataLoader;
import com.pablozoani.recipeapp.model.UnitOfMeasure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class UnitOfMeasureRepositoryIntegrationTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.deleteAll();
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();
        DataLoader dataLoader = new DataLoader(categoryRepository, unitOfMeasureRepository, recipeRepository);
        dataLoader.onApplicationEvent(null);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByUnitOfMeasure() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUnitOfMeasure("Cups");
        assertEquals("Cups", unitOfMeasureOptional.get().getUnitOfMeasure());
    }

    @Test
    void findByUnitOfMeasureTeaspoon() {
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon");
        assertEquals("Teaspoon", unitOfMeasureOptional.get().getUnitOfMeasure());
    }
}