package com.pablozoani.recipeapp.converter.fromcommand;

import com.pablozoani.recipeapp.command.NotesCommand;
import com.pablozoani.recipeapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CommandToNotes implements Converter<NotesCommand, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if (source == null) return null;
        final Notes output = new Notes();
        output.setId(source.getId());
        output.setRecipeNotes(source.getRecipeNotes());
        return output;
    }
}
