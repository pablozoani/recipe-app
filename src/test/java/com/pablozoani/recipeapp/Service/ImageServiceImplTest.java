package com.pablozoani.recipeapp.Service;

import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImageServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    ImageService imageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imageService = new ImageServiceImpl(recipeRepository);
    }

    @Test
    void saveImageFile() throws IOException {
        MultipartFile multipartFile =
            new MockMultipartFile("imagefile", "testing.txt", "text/plain", "pablo zoani".getBytes());
        Recipe recipe = new Recipe();
        recipe.setId("7L");
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        Mockito.when(recipeRepository.findById(ArgumentMatchers.anyString())).thenReturn(recipeOptional);
        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);

        imageService.saveImageFile("7l", multipartFile);

        Mockito.verify(recipeRepository, Mockito.times(1)).save(argumentCaptor.capture());

        Recipe recipe2 = argumentCaptor.getValue();
        assertEquals(multipartFile.getBytes().length, recipe2.getImage().length);
    }
}