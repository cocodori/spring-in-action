package tacos.web;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import tacos.domain.Ingredient;
import tacos.domain.Taco;

import java.util.Date;
import java.util.List;

@Getter
public class TacoResource extends RepresentationModel<CollectionModel<Taco>> {
    private final String name;

    private final Date createdAt;

    private final List<Ingredient> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = taco.getIngredients();
    }
}
