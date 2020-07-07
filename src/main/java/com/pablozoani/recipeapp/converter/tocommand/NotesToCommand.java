package com.pablozoani.recipeapp.converter.tocommand;

import com.pablozoani.recipeapp.command.NotesCommand;
import com.pablozoani.recipeapp.model.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToCommand implements Converter<Notes, NotesCommand> {

    @Synchronized
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if (source == null) return null;
        final NotesCommand output = new NotesCommand();
        output.setId(source.getId());
        output.setRecipeNotes(source.getRecipeNotes());
        return output;
    }
}
