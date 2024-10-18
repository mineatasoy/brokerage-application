package org.broker.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
public class OrderRequest {

    String customerId;
    String assetName;
    String orderSide;
    int size;
    double price;
    String status;
    LocalDate createDate;

}
