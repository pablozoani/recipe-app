package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.command.UnitOfMeasureCommand;
import com.pablozoani.recipeapp.converter.tocommand.UnitOfMeasureToCommand;
import com.pablozoani.recipeapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final UnitOfMeasureToCommand unitOfMeasureToCommand;

    @Autowired
    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureToCommand unitOfMeasureToCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureToCommand = unitOfMeasureToCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> findAll() {
        log.debug(getClass().getSimpleName() + " - findAll() - 1");
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                            .map(unitOfMeasureToCommand::convert)
                            .collect(Collectors.toSet());
    }
}
