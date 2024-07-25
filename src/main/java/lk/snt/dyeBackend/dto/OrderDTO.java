package lk.snt.dyeBackend.dto;

import lk.snt.dyeBackend.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private long orderId;
    private int grnNumber;
    private Date orderDate;
    private Recipe recipe;
}
