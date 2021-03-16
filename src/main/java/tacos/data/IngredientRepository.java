package tacos.data;

import tacos.domain.Ingredient;

public interface IngredientRepository {
    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    int save(Ingredient ingredient);
}
