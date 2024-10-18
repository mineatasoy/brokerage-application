package org.broker.service;

import org.broker.model.Asset;
import org.broker.model.Order;
import org.broker.model.response.AssetResponse;
import org.broker.model.response.OrderResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface BrokerageService {

    public OrderResponse createOrder(Order order);

    public OrderResponse cancelOrder(String orderId);

    public ArrayList<Asset> getAssetsByCustomer(String customerId);

    public AssetResponse depositMoney(String customerId, double size);

    public AssetResponse withdrawMoney(String customerId, double size);

    public List<Order> getOrdersByCustomerWithDate(String customerId, LocalDate dateStart, LocalDate dateEnd);
}
