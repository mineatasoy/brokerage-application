package controller;

import org.broker.controller.BrokerageController;
import org.broker.model.Order;
import org.broker.model.request.OrderRequest;
import org.broker.model.response.OrderResponse;
import org.broker.service.BrokerageService;
import org.broker.util.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class BrokerageControllerTest {

    @Mock
    private BrokerageService brokerageService;

    @InjectMocks
    private BrokerageController brokerageController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createOrderTest() {

        Order order = Order.builder()
                .orderId(Long.valueOf("111111"))
                .assetName("ASSET1")
                .orderSide("SELL")
                .size(1)
                .createDate(LocalDate.now())
                .price(100)
                .customerId("34232322")
                .build();

        OrderResponse response = OrderResponse.builder()
                .order(order)
                .message(Constants.ORDER_CREATED_SUCCESSFULLY)
                .build();

        when(brokerageService.createOrder((any(Order.class)))).thenReturn(response);

        Assertions.assertNotNull(brokerageController.createOrder(OrderRequest.builder().build()));

    }
}

