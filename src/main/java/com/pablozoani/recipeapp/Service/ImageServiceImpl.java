package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImageFile(String recipeId, MultipartFile file) {

        Recipe recipe = recipeRepository
                .findById(recipeId)
                .orElseThrow(() -> new RuntimeException(getClass().getSimpleName() + " - saveImageFile() - 1"));

        try {

            log.debug(getClass().getSimpleName() + " - saveImageFile() - 1");

            log.debug("Parameter name=[" + file.getName() + "], Content type=[" + file.getContentType() + "]");

            Byte[] bytes = new Byte[file.getBytes().length];

            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = file.getBytes()[i];
            }

            recipe.setImage(bytes);

            recipeRepository.save(recipe);

        } catch (IOException exc) {

            exc.printStackTrace();
        }
    }
}
