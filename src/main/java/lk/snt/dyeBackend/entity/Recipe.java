package lk.snt.dyeBackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Recipe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId; // Unique identifier for the recipe

    private String recipeName; // Name of the recipe

    private String color; // Color associated with the recipe

    @Column(unique = true)
    private String labDip; // Unique identifier for the lab dip

    private int roleCount; // Number of roles for the recipe

    private double weight; // Weight of the recipe

    private String liquorRatio; // Liquor ratio for the recipe

    private double volume; // Volume of the recipe

    @Temporal(TemporalType.DATE)
    private Date createdDate; // Date when the recipe was created

    @Temporal(TemporalType.TIME)
    private Date createdTime; // Time when the recipe was created

    @ManyToOne
    @JoinColumn(name = "created_user_id", referencedColumnName = "userId")
    private User createdUser; // User who created the recipe

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "orderId")
    private Order order; // Order associated with the recipe

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL)
    private Set<RecipeDetail> recipeDetails; // Set of recipe details associated with the recipe
}
