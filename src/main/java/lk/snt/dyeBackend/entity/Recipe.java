package lk.snt.dyeBackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
//import org.hibernate.annotations.processing.Pattern;
import javax.validation.constraints.Pattern;

//import org.hibernate.mapping.List;

import java.time.LocalDateTime;
import java.util.List;



@Entity
@Table(name = "Recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;
    private String color;
    @Column(unique = true)
    @Pattern(regexp = "^[A-Z]{4} [0-9]{4}$", message = "Invalid labDip format")
    private String labDip;
    private int roleCount;
    private double weight;
    private String liquorRatio;
    private double volume;
    private String createdUser;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDateTime;

   /*@JsonManagedReference
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RecipeDetail> recipeDetails;*/
    @JsonIgnore
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    private List<RecipeDetail> recipeDetails;
}

