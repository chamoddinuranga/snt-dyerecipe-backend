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


   /* @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
    private Recipe recipe; // Recipe associated with the recipe detail

   @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product; // Chemical associated with the recipe detail*/

    @ManyToOne//(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id",nullable = true)
    private Recipe recipe; // Recipe associated with the recipe detail

    @ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id",nullable = true)
    private Product product; // Chemical associated with the recipe detail

    private double quantityInGrams; // Quantity of the chemical used in grams
}

