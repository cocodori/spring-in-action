package tacos;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacos.data.IngredientRepository;
import tacos.domain.Ingredient;
import tacos.domain.Taco;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();

        ingredientRepository.findAll()
                .forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();

        for (Ingredient.Type type : types) {
            log.info("model name : {}", type.toString().toLowerCase());
            log.info("model body : {}", filterByType(ingredients, type));
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }

        model.addAttribute("taco", new Taco());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Taco design, Errors errors) {
        if (errors.hasErrors())
            return "design";

        log.info("Processing design : {} ", design);

        return "redirect:/order/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
