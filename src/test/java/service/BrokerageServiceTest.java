package service;


import org.broker.model.Asset;
import org.broker.model.Order;
import org.broker.model.response.OrderResponse;
import org.broker.repository.AssetRepository;
import org.broker.repository.OrderRepository;
import org.broker.service.impl.BrokerageServiceImpl;
import org.broker.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BrokerageServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private BrokerageServiceImpl brokerageService;

    @Test
    void createOrderSELLTest() {

        Order order = Order.builder().orderId(Long.valueOf("111111")).assetName("ASSET1").orderSide("SELL").size(1).createDate(LocalDate.now()).price(100).customerId("34232322").build();
        OrderResponse response = OrderResponse.builder().order(order).message(Constants.ORDER_CREATED_SUCCESSFULLY).build();
        OrderResponse result = brokerageService.createOrder(order);

        Assertions.assertEquals(response, result);
    }

    @Test
    void createOrderBUYFailTest() {

    }

    @Test
    void createOrderBUYSuccessTest() {

    }

    @Test
    void cancelOrderTest(){

    }

    @Test
    void getAssetsByCustomer(){

    }

    @Test
    void depositMoney(){

    }

    @Test
    void withdrawMoney(){

    }


}
