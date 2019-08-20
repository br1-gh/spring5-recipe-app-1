package guru.springframework.controllers;

import guru.springframework.repositories.RecipeRepository;
import guru.springframework.services.Transformer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jt on 6/1/17.
 */
@Controller
public class IndexController {

    private RecipeRepository recipeRepository;
    private Transformer recipeTransformerService;

    public IndexController(RecipeRepository recipeRepository, Transformer recipeTransformerService) {
        this.recipeRepository = recipeRepository;
        this.recipeTransformerService = recipeTransformerService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("viewReadyRecipes", recipeTransformerService.transform(recipeRepository.findAll()));
        return "index";
    }
}
