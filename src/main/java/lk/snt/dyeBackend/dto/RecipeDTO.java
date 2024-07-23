package lk.snt.dyeBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RecipeDTO {


    private Long recipeId; // Unique identifier for the recipe

    private int grnNumber; // Goods Receipt Number (4 digits)

    private String color; // Color associated with the recipe

    private String machineNo; // Machine number (one of 9 machines)

    private String labDip; // Lab Dip identifier (e.g., AS/PC 0675)

    private int roleCount; // Number of roles to be dyed

    private Double weight; // Weight in kilograms

    private String liquorRatio; // Liquor ratio (e.g., 1:7, 1:5)

    private Double volume; // Volume calculated based on weight and liquor ratio

    private LocalDate createdDate; // Date the recipe was created

    private LocalTime createdTime; // Time the recipe was created

    private String createdUser; // User who created the recipe
}

