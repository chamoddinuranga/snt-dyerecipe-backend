package lk.snt.dyeBackend.dto;

import lk.snt.dyeBackend.entity.Recipe;

import java.sql.Date;

public class OrderDTO {
    private long orderId;
    private int grnNumber;
    private Date orderDate;
    private Recipe recipe;
}
