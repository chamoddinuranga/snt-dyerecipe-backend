package lk.snt.dyeBackend.dto;

import lk.snt.dyeBackend.entity.Product;
import lk.snt.dyeBackend.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetailDTO {
    private Long recipeDetailId;
    private Long recipeId; // ID reference to Recipe
    private Long productId;
    private double quantityInGrams;

}
