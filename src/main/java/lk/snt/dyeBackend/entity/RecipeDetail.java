package lk.snt.dyeBackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;

@Entity
@Table(name = "RecipeDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeDetailId; // Unique identifier for the recipe detail

    @ManyToOne
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
    private Recipe recipe; // Recipe associated with the recipe detail

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product; // Chemical associated with the recipe detail

    private double quantityInGrams; // Quantity of the chemical used in grams
}

