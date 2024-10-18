package org.broker.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.broker.model.Order;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    Order order;
    String message="";


}
