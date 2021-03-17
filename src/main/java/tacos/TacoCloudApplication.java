package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TacoCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(TacoCloudApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner dataLoader(IngredientRepository ingredientRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<Ingredient> ingredients = Arrays.asList(
                        new Ingredient("FLTO", "Flouir Tortilla", Ingredient.Type.WRAP),
                        new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                        new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                        new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                        new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                        new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                        new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                        new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

                ingredientRepository.saveAll(ingredients);
            }
        };
    }

}
