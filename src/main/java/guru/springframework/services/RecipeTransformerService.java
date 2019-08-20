package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.domain.ViewReadyRecipe;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Primary
@Service
public class RecipeTransformerService implements Transformer<Recipe, ViewReadyRecipe> {
    @Override
    public Iterable<ViewReadyRecipe> transform(Iterable<Recipe> entities) {
        Set<ViewReadyRecipe> tranformedRecipes = new HashSet<>();
        entities.forEach(recipe -> {
            ViewReadyRecipe viewReadyRecipe = new ViewReadyRecipe(recipe);

            //Refactor recipe image
            byte[] byteArrayImage = objectToByte(recipe.getImage());
            String imageToBase64 = Base64.getEncoder().encodeToString(byteArrayImage);
            viewReadyRecipe.setBase64ImageString(imageToBase64);

            tranformedRecipes.add(viewReadyRecipe);
        });
        return tranformedRecipes;
    }

    private Byte[] byteToObject(byte[] bytesPrim) {
        Byte[] bytes = new Byte[bytesPrim.length];
        Arrays.setAll(bytes, n -> bytesPrim[n]);
        return bytes;
    }

    private byte[] objectToByte(Byte[] byteArrayObject) {
        byte[] bytes = new byte[byteArrayObject.length];
        for (int i=0; i < byteArrayObject.length; i++) {
            bytes[i] = byteArrayObject[i].byteValue();
        }
        return bytes;
    }

}
