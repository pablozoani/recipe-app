package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.converter.tocommand.UnitOfMeasureToCommand;
import com.pablozoani.recipeapp.model.UnitOfMeasure;
import com.pablozoani.recipeapp.repository.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToCommand unitOfMeasureToCommand;

    UnitOfMeasureService unitOfMeasureService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        unitOfMeasureToCommand = new UnitOfMeasureToCommand();
        unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository,
                                                            unitOfMeasureToCommand);
    }

    @Test
    void findAll() {
        Set<UnitOfMeasure> input = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom1.setId("1L");
        uom2.setId("2L");
        input.add(uom1);
        input.add(uom2);

        Mockito.when(unitOfMeasureRepository.findAll()).thenReturn(input);
        Set<UnitOfMeasureCommand> output = unitOfMeasureService.findAll();

        assertEquals(input.size(), output.size());
        Mockito.verify(unitOfMeasureRepository, Mockito.times(1)).findAll();
    }
}