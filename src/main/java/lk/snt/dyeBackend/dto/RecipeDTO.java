package lk.snt.dyeBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RecipeDTO {

    private Long recipeId; // Unique identifier for the recipe

    private String color; // Color associated with the recipe

    private String labDip; // Unique identifier for the lab dip

    private int roleCount; // Number of roles for the recipe

    private double weight; // Weight of the recipe

    private String liquorRatio; // Liquor ratio for the recipe

    private double volume; // Volume of the recipe

    private Date createdDate; // Date when the recipe was created

    private Date createdTime; // Time when the recipe was created

    private Long createdUserId; // ID of the user who created the recipe

    private Set<RecipeDetailDTO> recipeDetails; // Set of recipe details associated with the recipe
}
