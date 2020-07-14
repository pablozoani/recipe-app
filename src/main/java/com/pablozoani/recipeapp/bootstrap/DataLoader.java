package com.pablozoani.recipeapp.bootstrap;

import com.pablozoani.recipeapp.model.Difficulty;
import com.pablozoani.recipeapp.model.Ingredient;
import com.pablozoani.recipeapp.model.Notes;
import com.pablozoani.recipeapp.model.Recipe;
import com.pablozoani.recipeapp.repository.CategoryRepository;
import com.pablozoani.recipeapp.repository.RecipeRepository;
import com.pablozoani.recipeapp.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Slf4j
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final RecipeRepository recipeRepository;

    @Autowired
    public DataLoader(CategoryRepository categoryRepository,
                      UnitOfMeasureRepository unitOfMeasureRepository,
                      RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        saveRecipes();
    }

    @Transactional
    private void saveRecipes() {

        // == Guacamole ==

        Ingredient avocados = new Ingredient();
        avocados.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Units").get());
        avocados.setAmount(new BigDecimal("2"));
        avocados.setDescription("Ripe Avocados");

        Ingredient salt = new Ingredient();
        salt.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Teaspoon").get());
        salt.setAmount(new BigDecimal("0.25"));
        salt.setDescription("Salt");

        Ingredient limeJuice = new Ingredient();
        limeJuice.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Tablespoon").get());
        limeJuice.setAmount(new BigDecimal("1"));
        limeJuice.setDescription("Lime or Lemon Juice");

        Ingredient onion = new Ingredient();
        onion.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Tablespoon").get());
        onion.setAmount(new BigDecimal("2"));
        onion.setDescription("Minced Red Onion");

        Recipe perfectGuacamole = new Recipe();
        perfectGuacamole.addCategories(categoryRepository.findByCategoryName("Mexican").get(),
                                       categoryRepository.findByCategoryName("Fast Food").get());
        perfectGuacamole.addIngredients(avocados, salt, limeJuice, onion);
        perfectGuacamole.setPrepTime("10 minutes");
        perfectGuacamole.setServings(4);
        perfectGuacamole.setCookTime("15 minutes");
        perfectGuacamole.setDescription("Perfect Guacamole");
        perfectGuacamole.setNotes(new Notes().setRecipeNotes(
            "The best guacamole keeps it simple: just ripe avocados, salt, a squeeze of lime, onions, chiles, cilantro, and some chopped tomato. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade. "));
        perfectGuacamole.setDifficulty(Difficulty.EASY);
        perfectGuacamole.setDirections(
            "1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. Place in a bowl.\n" +
            "\n" +
            "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
            "\n" +
            "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
            "\n" +
            "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
            "\n" +
            "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
            "\n" +
            "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
            "\n" +
            "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n");
        perfectGuacamole.setSource("https://www.simplyrecipes.com");
        perfectGuacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");

        recipeRepository.save(perfectGuacamole);

        // == Grilled Chicken ==

        Ingredient chiliPowder = new Ingredient();
        chiliPowder.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Tablespoon").get());
        chiliPowder.setAmount(new BigDecimal("2"));
        chiliPowder.setDescription("Ancho Chili Powder");

        Ingredient cornTortilla = new Ingredient();
        cornTortilla.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Units").get());
        cornTortilla.setAmount(new BigDecimal("8"));
        cornTortilla.setDescription("Small Corn Tortillas");

        Ingredient babyArugula = new Ingredient();
        babyArugula.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Cups").get());
        babyArugula.setAmount(new BigDecimal("3"));
        babyArugula.setDescription("Baby Arugula");

        Ingredient chickenTights = new Ingredient();
        chickenTights.setUnitOfMeasure(unitOfMeasureRepository.findByUnitOfMeasure("Units").get());
        chickenTights.setAmount(new BigDecimal("4"));
        chickenTights.setDescription("Boneless Chicken Thighs");

        Recipe grilledChicken = new Recipe();
        grilledChicken.setDescription("Grilled Chicken");
        grilledChicken.addCategories(categoryRepository.findByCategoryName("Fast Food").get(),
                                     categoryRepository.findByCategoryName("Italian").get());
        grilledChicken.setCookTime("15 Minutes");
        grilledChicken.setPrepTime("20 Minutes");
        grilledChicken.setNotes(new Notes().setRecipeNotes(
            "Spicy grilled chicken tacos! Quick marinade, then grill. Ready in about 30 minutes. Great for a quick weeknight dinner, backyard cookouts, and tailgate parties."));
        grilledChicken.addIngredients(cornTortilla, babyArugula, chickenTights);
        grilledChicken.setDifficulty(Difficulty.MEDIUM);
        grilledChicken.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                                     "\n" +
                                     "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                                     "\n" +
                                     "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                                     "\n" +
                                     "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                                     "\n" +
                                     "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                                     "\n" +
                                     "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                                     "\n" +
                                     "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n");
        grilledChicken.setServings(5);
        grilledChicken.setSource("https://www.simplyrecipes.com");
        grilledChicken.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");

        recipeRepository.save(grilledChicken);
    }
}
