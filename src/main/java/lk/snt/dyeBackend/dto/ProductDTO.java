package lk.snt.dyeBackend.dto;

import jakarta.persistence.OneToMany;
import lk.snt.dyeBackend.entity.RecipeDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long productId;

    private String productName;

    private String productType; // e.g., "CHEMICAL" Or "DYE"



}
