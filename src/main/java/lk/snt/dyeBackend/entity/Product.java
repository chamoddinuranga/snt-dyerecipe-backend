package lk.snt.dyeBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productType; // e.g., "CHEMICAL" or "DYE"

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<RecipeDetail> recipeDetails; // One Product can be used in many RecipeDetails
}


