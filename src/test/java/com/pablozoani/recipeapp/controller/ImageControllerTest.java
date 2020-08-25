package com.pablozoani.recipeapp.controller;

import com.pablozoani.recipeapp.Service.ImageService;
import com.pablozoani.recipeapp.Service.RecipeService;
import com.pablozoani.recipeapp.command.RecipeCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

class ImageControllerTest {

    @Mock
    ImageService imageService;

    @Mock
    RecipeService recipeService;

    ImageController imageController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imageController = new ImageController(imageService, recipeService);
        mockMvc = MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void getImageForm() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("abcd");

        Mockito.when(recipeService.findRecipeCommandById(anyString())).thenReturn(recipeCommand);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/6/image"))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.model().attributeExists("recipe"));

        Mockito.verify(recipeService, Mockito.times(1)).findRecipeCommandById(ArgumentMatchers.anyString());
    }

    @Test
    void handleImagePost() throws Exception {
        MockMultipartFile multipartFile =
            new MockMultipartFile("imagefile", "testing.txt", "text/plain", "pablo zoani".getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/recipe/1/image").file(multipartFile))
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andExpect(MockMvcResultMatchers.header().string("Location", "/recipe/1/show"));

        Mockito.verify(imageService, Mockito.times(1))
               .saveImageFile(ArgumentMatchers.anyString(), ArgumentMatchers.any());
    }

    @Test
    void renderImageFromDB() throws Exception {
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId("abcd");
        String string = "abcde";
        Byte[] bytes = new Byte[string.getBytes().length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = string.getBytes()[i];
        }

        recipeCommand.setImage(bytes);

        Mockito.when(recipeService.findRecipeCommandById(ArgumentMatchers.anyString())).thenReturn(recipeCommand);

        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/recipe/4/recipeimage"))
                                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                                  .andReturn().getResponse();

        byte[] responseContent = response.getContentAsByteArray();
        assertEquals(string.getBytes().length, responseContent.length);
    }
}