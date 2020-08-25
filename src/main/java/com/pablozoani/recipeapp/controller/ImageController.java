package com.pablozoani.recipeapp.controller;

import com.pablozoani.recipeapp.Service.ImageService;
import com.pablozoani.recipeapp.Service.RecipeService;
import com.pablozoani.recipeapp.command.RecipeCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Controller
public class ImageController {

    private final ImageService imageService;

    private final RecipeService recipeService;

    @Autowired
    public ImageController(ImageService imageService, RecipeService recipeService) {
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model) {
        log.debug(getClass().getSimpleName() + " - showUploadForm - 1");
        model.addAttribute("recipe", recipeService.findRecipeCommandById(id));
        return "recipe/imguploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id,
                                  @RequestParam("imagefile") MultipartFile file) {
        imageService.saveImageFile(id, file);
        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFormDB(@PathVariable String id,
                                  HttpServletResponse response) throws IOException {
        RecipeCommand recipeCommand = recipeService.findRecipeCommandById(id);
        byte[] bytes = new byte[recipeCommand.getImage().length];
        int i = 0;
        for (byte b : recipeCommand.getImage()) {
            bytes[i++] = b;
        }
        response.setContentType("image/jpeg");
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }
}
