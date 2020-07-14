package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> findAll();
}
