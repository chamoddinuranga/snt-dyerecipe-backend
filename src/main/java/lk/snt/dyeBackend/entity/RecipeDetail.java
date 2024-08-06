package lk.snt.dyeBackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe; // Recipe associated with the recipe detail

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Product associated with the recipe detail

    private String addFunction;
    private double dose;
    private int temp;
    private int time;
    private double quantityInGrams; // Quantity of the product used in grams
}
