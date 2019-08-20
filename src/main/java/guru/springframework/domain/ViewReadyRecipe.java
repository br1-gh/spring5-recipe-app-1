package guru.springframework.domain;

import org.springframework.stereotype.Component;

@Component
public class ViewReadyRecipe extends Recipe implements Cloneable {

    String base64ImageString;

    public ViewReadyRecipe() {
    }

    public ViewReadyRecipe(Recipe recipe) {
        super.setCookTime(recipe.getCookTime());
        super.setDescription(recipe.getDescription());
        super.setDifficulty(recipe.getDifficulty());
        super.setDirections(recipe.getDirections());
        super.setImage(recipe.getImage());
        super.setPrepTime(recipe.getPrepTime());
        super.setServings(recipe.getServings());
        super.setSource(recipe.getSource());
        super.setUrl(recipe.getUrl());
        super.setNotes(recipe.getNotes());
        super.setCategories(recipe.getCategories());
        super.setIngredients(recipe.getIngredients());
        super.setId(recipe.getId());
    }

    public String getBase64ImageString() {
        return base64ImageString;
    }

    public void setBase64ImageString(String base64ImageString) {
        this.base64ImageString = base64ImageString;
    }


}
