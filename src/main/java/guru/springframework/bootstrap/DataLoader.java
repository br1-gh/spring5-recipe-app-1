package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;
    private RecipeRepository recipeRepository;

    public DataLoader(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository,
                      RecipeRepository recipeRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadGuacamoleRecipe();
        loadSpicyGrilledChickenTacosRecipe();
    }

    private void loadGuacamoleRecipe() throws IOException {
        Notes perfectGuacamoleNotes = new Notes();
        perfectGuacamoleNotes.setRecipeNotes("Variations\n" +
                "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!");

        System.out.println("Setup notes for Guac done...");

        Ingredient ripeAvocado = new Ingredient();
        ripeAvocado.setAmount(new BigDecimal(2));
        ripeAvocado.setDescription("Avocado");
        ripeAvocado.setUom(unitOfMeasureRepository.findByDescription("Unit").get());

        Ingredient kosherSalt = new Ingredient();
        kosherSalt.setAmount(new BigDecimal(0.5));
        kosherSalt.setDescription("Kosher Salt");
        kosherSalt.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient freshLimeJuice = new Ingredient();
        freshLimeJuice.setAmount(new BigDecimal(1));
        freshLimeJuice.setDescription("Lime Juice");
        freshLimeJuice.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient mincedRedOnion = new Ingredient();
        mincedRedOnion.setAmount(new BigDecimal(2));
        mincedRedOnion.setDescription("Minced red Onion");
        mincedRedOnion.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient serranoChiles = new Ingredient();
        serranoChiles.setAmount(new BigDecimal(2));
        serranoChiles.setDescription("Serrano Chiles");
        serranoChiles.setUom(unitOfMeasureRepository.findByDescription("Unit").get());

        Ingredient cilantro = new Ingredient();
        cilantro.setAmount(new BigDecimal(2));
        cilantro.setDescription("Cilantro");
        cilantro.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient gratedBlackPepper = new Ingredient();
        gratedBlackPepper.setAmount(new BigDecimal(1));
        gratedBlackPepper.setDescription("Grated black Pepper");
        gratedBlackPepper.setUom(unitOfMeasureRepository.findByDescription("Dash").get());

        Ingredient ripeTomato = new Ingredient();
        ripeTomato.setAmount(new BigDecimal(0.5));
        ripeTomato.setDescription("Ripe Tomato");
        ripeTomato.setUom(unitOfMeasureRepository.findByDescription("Unit").get());

        System.out.println("Setup ingredients for Guac done...");

        Recipe perfectGuacamoleRecipe = new Recipe();
        Optional<Category> perfectGuacamoleCategory = categoryRepository.findByDescription("American");
        if (perfectGuacamoleCategory.isPresent()) {
            perfectGuacamoleRecipe.getCategories().add(perfectGuacamoleCategory.get());
            System.out.println("Setup category for Guac done...");
        }
        else {
            System.out.println("Category for Guac not found...");
        }
        perfectGuacamoleRecipe.getIngredients().addAll(Arrays.asList(
                ripeAvocado, kosherSalt, freshLimeJuice, mincedRedOnion, serranoChiles, cilantro,
                gratedBlackPepper, ripeTomato));
        perfectGuacamoleRecipe.setDescription("Perfect Guacamole");
        perfectGuacamoleRecipe.setCookTime(0);
        perfectGuacamoleRecipe.setDifficulty(Difficulty.EASY);
        perfectGuacamoleRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.");
        perfectGuacamoleRecipe.setNotes(perfectGuacamoleNotes);
        perfectGuacamoleRecipe.setPrepTime(10);
        perfectGuacamoleRecipe.setServings(4);
        perfectGuacamoleRecipe.setSource("Simply Recipes");
        perfectGuacamoleRecipe.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        try {
            File guacImage = ResourceUtils.getFile("classpath:images/guacamole-image.jpg");
            byte[] imageToBytes = Files.readAllBytes(guacImage.toPath());
            perfectGuacamoleRecipe.setImage(byteToObject(imageToBytes));
            System.out.println("Recipe image processed...");
        } catch (FileNotFoundException e) {
            System.out.println("No image available for Chicken...");
        } catch (IOException e) {
            System.out.println("No image available for Chicken...");
        }

        ripeAvocado.setRecipe(perfectGuacamoleRecipe);
        kosherSalt.setRecipe(perfectGuacamoleRecipe);
        freshLimeJuice.setRecipe(perfectGuacamoleRecipe);
        mincedRedOnion.setRecipe(perfectGuacamoleRecipe);
        serranoChiles.setRecipe(perfectGuacamoleRecipe);
        cilantro.setRecipe(perfectGuacamoleRecipe);
        gratedBlackPepper.setRecipe(perfectGuacamoleRecipe);
        ripeTomato.setRecipe(perfectGuacamoleRecipe);

        System.out.println("Recipe attirbuted to ingredients...");

        recipeRepository.save(perfectGuacamoleRecipe);

        System.out.println("Guac Recipe saved!");
    }

    private void loadSpicyGrilledChickenTacosRecipe() {

        System.out.println("Chicken has no notes...");

        Ingredient anchoChiliPowder = new Ingredient();
        anchoChiliPowder.setAmount(new BigDecimal(2));
        anchoChiliPowder.setDescription("Ancho chili powder");
        anchoChiliPowder.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient driedOregano = new Ingredient();
        driedOregano.setAmount(new BigDecimal(1));
        driedOregano.setDescription("Dried Oregano");
        driedOregano.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient driedCumin = new Ingredient();
        driedCumin.setAmount(new BigDecimal(1));
        driedCumin.setDescription("Dried Cumin");
        driedCumin.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient sugar = new Ingredient();
        sugar.setAmount(new BigDecimal(1));
        sugar.setDescription("Sugar");
        sugar.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient salt = new Ingredient();
        salt.setAmount(new BigDecimal(0.5));
        salt.setDescription("Salt");
        salt.setUom(unitOfMeasureRepository.findByDescription("Teaspoon").get());

        Ingredient cloveGarlic = new Ingredient();
        cloveGarlic.setAmount(new BigDecimal(1));
        cloveGarlic.setDescription("Clove Garlic");
        cloveGarlic.setUom(unitOfMeasureRepository.findByDescription("Unit").get());

        Ingredient orangeZest = new Ingredient();
        orangeZest.setAmount(new BigDecimal(1));
        orangeZest.setDescription("Orange Zest");
        orangeZest.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient orangeJuice = new Ingredient();
        orangeJuice.setAmount(new BigDecimal(3));
        orangeJuice.setDescription("Orange Juice");
        orangeJuice.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient oliveOil = new Ingredient();
        oliveOil.setAmount(new BigDecimal(2));
        oliveOil.setDescription("Olive Oil");
        oliveOil.setUom(unitOfMeasureRepository.findByDescription("Tablespoon").get());

        Ingredient chickenThigh = new Ingredient();
        chickenThigh.setAmount(new BigDecimal(6));
        chickenThigh.setDescription("Chicken Thigh");
        chickenThigh.setUom(unitOfMeasureRepository.findByDescription("Unit").get());

        System.out.println("Setup ingredients for Chicken done...");

        Recipe spicyGrilledChickenTacosRecipe = new Recipe();
        Optional<Category> spicyGrilledChickenTacosCategory = categoryRepository.findByDescription("Mexican");
        if (spicyGrilledChickenTacosCategory.isPresent()) {
            spicyGrilledChickenTacosRecipe.getCategories().add(spicyGrilledChickenTacosCategory.get());
            System.out.println("Setup category for Chicken done...");
        }
        else {
            System.out.println("Category for Chicken not found...");
        }
        spicyGrilledChickenTacosRecipe.getIngredients().addAll(Arrays.asList(
                anchoChiliPowder, driedOregano, driedCumin, sugar, salt, cloveGarlic,
                orangeZest, orangeJuice, oliveOil, chickenThigh));
        spicyGrilledChickenTacosRecipe.setDescription("Spicy Grilled Chicken Tacos");
        spicyGrilledChickenTacosRecipe.setCookTime(15);
        spicyGrilledChickenTacosRecipe.setDifficulty(Difficulty.MODERATE);
        spicyGrilledChickenTacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
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
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        spicyGrilledChickenTacosRecipe.setPrepTime(20);
        spicyGrilledChickenTacosRecipe.setServings(6);
        spicyGrilledChickenTacosRecipe.setSource("Simply Recipes");
        spicyGrilledChickenTacosRecipe.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        File chickenTacosImage = null;
        try {
            chickenTacosImage = ResourceUtils.getFile("classpath:images/grilled-chicken-tacos-image.jpg");
            byte[] chickenImageToBytes = Files.readAllBytes(chickenTacosImage.toPath());
            spicyGrilledChickenTacosRecipe.setImage(byteToObject(chickenImageToBytes));
            System.out.println("Recipe image processed...");
        } catch (FileNotFoundException e) {
            System.out.println("No image available for Chicken...");
        } catch (IOException e) {
            System.out.println("No image available for Chicken...");
        }

        anchoChiliPowder.setRecipe(spicyGrilledChickenTacosRecipe);
        driedOregano.setRecipe(spicyGrilledChickenTacosRecipe);
        driedCumin.setRecipe(spicyGrilledChickenTacosRecipe);
        sugar.setRecipe(spicyGrilledChickenTacosRecipe);
        salt.setRecipe(spicyGrilledChickenTacosRecipe);
        cloveGarlic.setRecipe(spicyGrilledChickenTacosRecipe);
        orangeZest.setRecipe(spicyGrilledChickenTacosRecipe);
        orangeJuice.setRecipe(spicyGrilledChickenTacosRecipe);
        oliveOil.setRecipe(spicyGrilledChickenTacosRecipe);
        chickenThigh.setRecipe(spicyGrilledChickenTacosRecipe);

        System.out.println("Recipe attributed to ingredients...");

        recipeRepository.save(spicyGrilledChickenTacosRecipe);

        System.out.println("Chicken Recipe saved!");
    }

    private Byte[] byteToObject(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        Arrays.setAll(bytes, n -> bytesPrim[n]);
        return bytes;
    }
}
