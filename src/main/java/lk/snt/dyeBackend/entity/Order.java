package lk.snt.dyeBackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "OrderDetails")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId; // Unique identifier for the order

    private int grnNumber; // GRN number for the order

    @Temporal(TemporalType.DATE)
    private Date orderDate; // Date when the order was placed

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "recipe_id", referencedColumnName = "recipeId")
    private Recipe recipe; // Recipe associated with the order
}